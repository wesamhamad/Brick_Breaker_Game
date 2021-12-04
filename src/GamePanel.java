package Brick_Breaker;


import javax.sound.sampled.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GamePanel extends JPanel { // means is a type of JPanel

    // Fields
    private boolean running;
    private BufferedImage image;// main image class in java (an object tht enable to store graphical info in  memory )
    private Graphics2D g;// graphical tool (to draw the game over and over)
    private MyMouseMotionListener theMotionListener;
    private int mousex;
    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;
    public static final int LEVEL3 = 3;
    public static final int SETUP_LEVEL = 4;

    // Entities
    private Ball theBall;
    private Paddle thePaddle;
    private Map theMap;
    private HUD theHud;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<BrickSplosion> brickSplosions;
    public static int currentLevel;

    // Timer
    private final long FPS = 60;
    private final long TARGETTIME = 1000 / FPS;
    private long startTime, elapsedTime, waitTime;

    // Modifier stuff
    private boolean screenShakeActive;
    private long screenShakeTimer;

    // constructor
    public GamePanel(int level) {

        init(level);// to initialized the filed

        this.setFocusable(true);
        this.requestFocus();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                thePaddle.keyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                thePaddle.keyReleased(e.getKeyCode());
            }
        });

    }

    public void init(int level) {

        currentLevel = level;
        mousex = 0;
        screenShakeTimer = System.nanoTime();
        screenShakeActive = false;

        //fast of the ball base on level
        if(currentLevel == LEVEL3){
            theBall = new Ball(6,9);
        }else
            if(currentLevel == LEVEL2 || currentLevel == SETUP_LEVEL){
                theBall = new Ball(5,7);
            } else{
                theBall = new Ball(4,6);
            }

        thePaddle = new Paddle(100, 30);// size of Paddle

        // number of rectangles base on level
        if(currentLevel == LEVEL1){
            theMap = new Map(4, 6);
        }else
            if(currentLevel == LEVEL2){
                theMap = new Map(5, 8);
        }else
            if(currentLevel == LEVEL3){
                theMap = new Map(6, 9);
        }else
            if(currentLevel == SETUP_LEVEL){
                theMap = new Map(LaunchPage.getUserRows(), LaunchPage.getUserColumns());
            }

        theHud = new HUD();
        theMotionListener = new MyMouseMotionListener();
        powerUps = new ArrayList<PowerUp>();
        brickSplosions = new ArrayList<BrickSplosion>();


        addMouseMotionListener(theMotionListener);// it will be listening to the mouse
        running = true;// to run the game
        image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);// (spot to draw)

        g = (Graphics2D) image.getGraphics();// graphical context draw over image
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public void playGame() {

        drawStart();
        playSound("file:./Resources/Pokemon-Song-.aiff", 2);

        // Game Loop
        while (running) {

            startTime = System.nanoTime();

            // update
            update();

            // render or draw
            draw();

            // display
            repaint();

            elapsedTime = System.nanoTime() - startTime;

            waitTime = TARGETTIME - elapsedTime / 1000000;

            if (waitTime < 0) {
                waitTime = 5;
            }

            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }

    public void checkCollisions() {

        Rectangle ballRect = theBall.getRect();
        Rectangle paddleRect = thePaddle.getRect();

        for (int i = 0; i < powerUps.size(); i++) {

            Rectangle puRect = powerUps.get(i).getRect();

            if (paddleRect.intersects(puRect)) {

                if (powerUps.get(i).getType() == PowerUp.WIDEPADDLE && !powerUps.get(i).getWasUsed()) {
                    playSound("file:./Resources/rocket.wav", 0);
                    thePaddle.setWidth(thePaddle.getWidth() * 2);// set width after hit the falling rectangle
                    powerUps.get(i).setWasUsed(true);
                    theHud.addScore(10);
                }
                if (powerUps.get(i).getType() == PowerUp.REDUCEPADDLE && !powerUps.get(i).getWasUsed()) {
                    playSound("file:./Resources/shrink.aif", 0);
                    thePaddle.setWidth(thePaddle.getWidth() / 2);// set width after hit the falling rectangle
                    powerUps.get(i).setWasUsed(true);
                    theHud.reduceScore(10);


                }

            }

        }

        if (ballRect.intersects(paddleRect)) {// when the ball hit the paddel

            theBall.setDY(-theBall.getDY());// change the direction of ball up to down and via vise
            playSound("file:./Resources/hit.aif", 0);

            if (theBall.geX() < mousex + thePaddle.getWidth() / 4) {
                theBall.setDx(theBall.getDX() - .30);
            }

            if (theBall.geX() < mousex + thePaddle.getWidth() && theBall.geX() > mousex + thePaddle.getWidth() / 4) {
                theBall.setDx(theBall.getDX() + .30);
            }
        }

        A: for (int row = 0; row < theMap.getMapArray().length; row++) {
            for (int col = 0; col < theMap.getMapArray()[0].length; col++) {
                if (theMap.getMapArray()[row][col] > 0) {

                    int brickx = col * theMap.getBrickWidth() + theMap.HOR_PAD + col * theMap.BETWEEN_PAD;
                    int bricky = row * theMap.getBrickHeight() + theMap.VERT_PAD + row * theMap.BETWEEN_PAD;
                    int brickWidth = theMap.getBrickWidth();
                    int brickHeight = theMap.getBrickHeight();

                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {

                        if (theMap.getMapArray()[row][col] == 1) {
                            brickSplosions.add(new BrickSplosion(brickx, bricky, theMap));
                            screenShakeActive = true;
                            playSound("file:./Resources/breaking-sound-effect.aiff", 0);
                            screenShakeTimer = System.nanoTime();
                        }

                        if (theMap.getMapArray()[row][col] > 3) {// when hit the brick it will fall down
                            powerUps.add(new PowerUp(brickx, bricky, theMap.getMapArray()[row][col], brickWidth,
                                    brickHeight));// to make it fall when the ball hit it
                            theMap.setBrick(row, col, 3);// 3 because 3 and less are normal breakable rectangles
                        } else {
                             theMap.hitBrick(row, col); // if you want orange rectangles became yellow then it's bricks delete else
                        }

                        theMap.hitBrick(row, col);

                        theBall.setDY(-theBall.getDY());

                        theHud.addScore(10);// add score every time hit a rectangle
                        break A;// once find the collision stop checking everything else

                    }
                }
            }
        }
    }

    public void update() {

        checkCollisions();

        theBall.update();// to update the ball on space and change it's directions

        thePaddle.update();

        for (PowerUp pu : powerUps) {
            pu.update();
        }

        for (int i = 0; i < brickSplosions.size(); i++) {
            brickSplosions.get(i).update();
            if (!brickSplosions.get(i).getIsActive()) {
                brickSplosions.remove(i);
            }
        }

        if ((System.nanoTime() - screenShakeTimer) / 1000000 > 300 && screenShakeActive) { // you can chang numbers which is devied by inorder to change time of shaking
            screenShakeActive = false;
            System.out.println((System.nanoTime() - screenShakeTimer) / 1000000 );
        }
    }

    public void draw() {
        // draw background
        g.setColor(new Color(248, 248, 248));

        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        theMap.draw(g);// draw map

        theBall.draw(g);

        thePaddle.draw(g);

        theHud.draw(g);

        drawPowerUps();

        if (theMap.isThereWin() == true) { // to show massage of winning on game panel when wins
            drawWin();
            running = false;
            GameWindow.theFrame.dispose();
        }

        if (theBall.youLose()) {
            drawLoser();
            running = false;
            GameWindow.theFrame.dispose();
        }

        for (BrickSplosion bs : brickSplosions) {
            bs.draw(g);
        }
    }

    public void drawWin() {

        for (int i = 150; i >= 1; i--) {
            g.setColor(new Color(248, 248, 248));
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
            g.setColor(new Color(207, 120, 120));
            g.setFont(new Font("Courier New", Font.BOLD, i));
            g.drawString("Congrats You Win !!!" , (int) (Main.WIDTH / 2 - (i*4)), Main.HEIGHT / 2);
            if (i == 150)
                playSound("file:./Resources/Win1.aiff", 1);
            repaint();
            try {
                Thread.sleep(28);// timeing to show Congrats
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void drawStart() {
        for (int i = 150; i >= 1; i--) {
            g.setColor(new Color(248, 248, 248));
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
            g.setColor(new Color(37, 49, 82));
            g.setFont(new Font("Courier New", Font.BOLD, i));
            g.drawString("    " + ((i / 30 + (i / 30) + 1) - i / 30), (int) (Main.WIDTH / 2 - (i * 2.7)), Main.HEIGHT / 2);
            if (i == 150)
                playSound("file:./Resources/counting.aiff", 0);
            repaint();
            try {
                Thread.sleep(28);// timeing to show string
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void drawPowerUps() {
        for (PowerUp pu : powerUps) {
            pu.draw(g);
        }
    }

    public void drawLoser() {

        for (int i = 150; i >= 1; i--) {

            g.setColor(new Color(248, 248, 248));
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
            g.setColor(new Color(207, 120, 120));
            g.setFont(new Font("Courier New", Font.BOLD, 60));
            g.drawString("Sorry You Lose ! " ,250, 370);
                if(i == 150)
                playSound("file:./Resources/Wrong.aiff", 1);
            repaint();
            try {
                Thread.sleep(28);// timeing to show
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;// to cast it to Graphics2D

        int x = 0;
        int y = 0;

        if (screenShakeActive == true) {
            x = (int) (Math.random() * 10 - 5);
            y = (int) (Math.random() * 10 - 5);
        }

        g2.drawImage(image, x, y, Main.WIDTH, Main.HEIGHT, null);

        g2.dispose();// to prevent called thousands of time to create new 2D graphic object
    }


    public static void playSound(String soundFile, int times) {
        try {
            URL soundLocation = new URL(soundFile);// location of file on my desktop
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundLocation);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(times);
            clip.start();

        } catch (UnsupportedAudioFileException uae) {
            System.out.println(uae);
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (LineUnavailableException lua) {
            System.out.println(lua);
        }

    }

    private class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {//

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousex = e.getX();
            thePaddle.mouseMoved(e.getX());
        }
    }

}

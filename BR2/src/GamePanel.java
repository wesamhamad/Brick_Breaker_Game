package Brick_Breaker;

import org.w3c.dom.css.Rect;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public  class GamePanel extends JPanel {// means is a type of jpanel
    //Fields
    private boolean running;
    private BufferedImage image;// main image class in java
    private Graphics2D g;// graphical tool (to draw the game over and over)
    private MyMouseMotionListener theMotionListener;

    private int mousex;

    //entities
    private Ball theBall;
    private Paddle thePaddle;
    private Map theMap;
    private HUD theHud;
    private ArrayList<PowerUp> powerUps;

    //constructor
    public GamePanel(){
     init();
    }
    public void init(){
        mousex=0;

        theBall=new Ball();//object
        thePaddle= new Paddle(130,20);//عدليها
        theMap= new Map(4,8);//size of rectangles
        theHud = new HUD();
        theMotionListener = new MyMouseMotionListener();
        powerUps = new ArrayList<PowerUp>();

        addMouseMotionListener(theMotionListener);// it will  listening to the mouse
        running=true;
        image= new BufferedImage(main.WIDTH,main.HEIGHT, BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();//graphical context draw over image
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    }

        public void playGame() {
            //Game loop
             while(running) {
                 // update
                 update();
                 //render or draw
                 draw();
                 // display
                 repaint();// method that jpanel has

                 try{
                     Thread.sleep(10);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
        }
        public void checkCollisions(){

        Rectangle ballRect = theBall.getRect();
        Rectangle paddleRect = thePaddle.getRect();

        for (int i = 0; i<powerUps.size(); i++){

            Rectangle puRect = powerUps.get(i).getRect();

            if (paddleRect.intersects(puRect)){

                if (powerUps.get(i).getType() == PowerUp.WIDEPADDLE && !powerUps.get(i).getWasUsed() ){
                    thePaddle.setWidth(thePaddle.getWidth() * 2);
                    powerUps.get(i).setWasUsed(true);
                }

            }

        }

        if (ballRect.intersects(paddleRect)){

            theBall.setDY(-theBall.getDY());

         if (theBall.geX() < mousex + thePaddle.getWidth() / 4){
             theBall.setDx(theBall.getDX() - .5);

         }

            if (theBall.geX() < mousex + thePaddle.getWidth() && theBall.geX() > mousex + thePaddle.getWidth() / 4 ){
                theBall.setDx(theBall.getDX() + .5);

            }
        }

       A: for(int row =0;row<theMap.getMapArray().length;row++ ){
            for (int col=0;col<theMap.getMapArray()[0].length;col++){
                if(theMap.getMapArray()[row][col]>0){
                    int brickx=col * theMap.getBrickWidth()+theMap.HOR_PAD;
                    int bricky=row * theMap.getBrickHeight()+theMap.VART_PAD;
                    int brickWidth=theMap.getBrickWidth();
                    int brickHeight=theMap.getBrickHeight();

                    Rectangle brickRect = new Rectangle(brickx,bricky,brickWidth,brickHeight);

                    if(ballRect.intersects(brickRect)){

                        if(theMap.getMapArray()[row][col] > 3){//when hit the brick it will fall down
                            powerUps.add(new PowerUp(brickx, bricky, theMap.getMapArray()[row][col], brickWidth, brickHeight));
                            theMap.setBrick(row, col ,3);
                        }else{
                            theMap.hitBrick(row,col);
                        }

                        theMap.hitBrick(row,col);

                        theBall.setDY(-theBall.getDY());

                        theHud.addScore(50);// add score every time hit a rectangle
                        break A;//once find the collision stop checking everything else

                    }
                }
            }
        }
        }
        public void update(){
            checkCollisions();

            theBall.update();

            thePaddle.update();

            for (PowerUp pu : powerUps){
                pu.update();
            }
        }

        public void draw(){
            //draw background
            g.setColor(Color.WHITE);
            g.fillRect(0,0,main.WIDTH,main.HEIGHT);
            theMap.draw(g);// draw map
            theBall.draw(g);// draw ball
            thePaddle.draw(g);
            theHud.draw(g);//draw score
            drawPowerUps();

            if (theMap.isThereWin() == true){ //to  show massage of winning on game panel when wins
                drawWin();
                running = false;
            }

            if (theBall.youLose()){//
                drawLoser();
                running = false;
            }
        }

        public void drawWin(){
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New",Font.BOLD,35));
        g.drawString("Congrats you win !!! ",100,200);
        }

        public void drawPowerUps(){
            for (PowerUp pu : powerUps){
                pu.draw(g);
            }
        }

        public void drawLoser(){
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New",Font.BOLD,35));
            g.drawString("Sorry you lose ! ",150,200);
        }

        public void paintComponent(Graphics g){
        Graphics2D g2 =(Graphics2D) g;// to cast it to Graphics2D
            g2.drawImage(image,0,0,main.WIDTH,main.HEIGHT,null);

            g2.dispose();
        }
    private class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {//

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousex=e.getX();
            thePaddle.mouseMoved(e.getX());
        }
    }
    }



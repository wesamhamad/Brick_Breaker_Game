package Brick_Breaker;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Paddle {
    // Fields
    private double x;
    private int width, height, startWidth;
    private long widthTimer;
    public Image img;
    private boolean left, right;
    private int move;

    public final int YPOS = Main.HEIGHT - 100;// position(place) on y-axis (to move it up and down)

    // Constructor
    public Paddle(int theWidth, int theHeight) {

        move = 13;

        // to sit size of Paddle
        width = theWidth;
        startWidth = theWidth;
        height = theHeight;

        x = Main.WIDTH / 2 - width / 2;// at beginning where should place

        right = left = false;


        String path = "Resources/pika.png";
        File file = new File(path);
        try {
            img = ImageIO.read(file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    // update
    public void update() {

        if ((System.nanoTime() - widthTimer) / 1000 > 4000000) {
            width = startWidth;
        }
        if(left){
            x -= move;
        }
        if(right){
            x += move;
        }

    }

    // draw
    public void draw(Graphics2D g) { // to draw Rectangle that can move
         g.drawImage(img, (int) x, YPOS-25, width, startWidth, null);
    }

    public void mouseMoved(int mouseXPos) {
        x = mouseXPos;
        if (x > Main.WIDTH - width) {
            x = Main.WIDTH - width; // to prevent paddle from going off the right-hand side
        }
    }

    public Rectangle getRect() {
        return new Rectangle((int) x, YPOS, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
        setWidthTimer();
    }

    public void setWidthTimer() {
        widthTimer = System.nanoTime();
    }// set it to billionths of second

    public void keyPressed(int k ) {
        if(k == KeyEvent.VK_LEFT){
            left = true;
        }
        if(k == KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT){
            left = false;
            if (x < Main.WIDTH -  width * 10.2) {
                x = Main.WIDTH - width * 10.2; // to prevent paddle from going off the left-hand side
            }
        }
        if(k == KeyEvent.VK_RIGHT){
            right = false;
            if (x > Main.WIDTH - width) {
                x = Main.WIDTH - width *1.2; // to prevent paddle from going off the right-hand side
            }
        }
    }
}

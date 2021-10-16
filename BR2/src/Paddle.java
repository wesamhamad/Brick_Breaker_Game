package Brick_Breaker;

import java.awt.*;

public class Paddle {
    //Fields
    private double x;
    private int width,height, startWidth;
    private long widthTimer;
    private boolean altWidth;

    public final int YPOS=main.HEIGHT-100;
    //Constructor
    public Paddle( int theWidth, int theHeight){

        altWidth = false;
        width = theWidth;
        startWidth=theWidth;
        height = theHeight;
        x = main.WIDTH / 2 - width / 2;

    }
    //update
    public void update(){

        if ((System.nanoTime() - widthTimer ) / 1000 > 4000000){
            width = startWidth;
            altWidth = false;
        }

    }
    //draw
    public void draw(Graphics2D g){// to draw Rectangle that can move
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x,YPOS,width,height);

        if (altWidth == true){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Curier New", Font.BOLD, 18));
            g.drawString("Shrinking in " + (4 - (System.nanoTime() - widthTimer) / 1000000000),(int)x,YPOS + 18);
        }
    }
    public void mouseMoved(int mouseXPos){
        x = mouseXPos;
        if(x > main.WIDTH - width){
            x = main.WIDTH - width; // to prevent paddle from going off the right-hand side
        }
    }
    public Rectangle getRect(){
        return new Rectangle((int)x,YPOS,width,height);
    }

    public int getWidth(){return width;}
    public void setWidth(int newWidth){
        altWidth = true;
        width=newWidth;
        setWidthTimer();
    }

    public  void setWidthTimer(){widthTimer=System.nanoTime();}// set it to billionths of second
}

package Brick_Breaker;

import java.awt.*;

public class Ball {
    //Fields
    private double x,y,dx,dy;
    private int ballSize=30;
    public  Ball(){
      x=200;
      y=200;
      dx=1;// how fast the x position by how much is updated through the loop game
      dy=3;
    }
    public void update(){
        setPosition();//for setting the ball's position in space
    }
    public void setPosition(){// to check if the ball hit the wall
        //is  called an update every time through the game loop
        x+=dx;
        y+=dy;
        if(x<0){// 0 is left edge
            dx=-dx;// change the direction
        }
        if(y<0){
            dy=-dy;
        }
        if(x>main.WIDTH-ballSize){
            dx=-dx;
        }
        if(y>main.HEIGHT-ballSize){
            dy=-dy;
        }
    }
    public void draw(Graphics2D g){// to draw the ball
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(4));//thick of the line
        g.drawOval((int)x,(int)y,ballSize,ballSize);
    }
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,ballSize,ballSize);
    }

    public void setDY(double theDY){ dy=theDY;}

    public double getDY(){ return dy;}

    public void setDx(double theDX){dx=theDX;}

    public double getDX(){return dx;}

    public double geX(){ return x;}

    public boolean youLose(){
        boolean loser=false;

        if (y > main.HEIGHT - ballSize * 2){
            loser=true;
        }
    return loser;
    }
}


package Brick_Breaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ball {
    //Fields
    private double x,y,dx,dy;
    private final int ballSize = 37;
    private Image img;

    // Constructor
    public  Ball(int xfast, int yfast){

      x = 400;
      y = 400;
      dx = xfast;// how fast the ball side to side, x position by how much is updated every cycle  through the loop game
      dy = yfast;// how fast the ball up and down

        String path = "Resources/ball.png";
        File file = new File(path);
        try {
            img = ImageIO.read(file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void update(){
        setPosition();//for setting the ball's position in space
    }

    public void setPosition(){

        //is  called an update every time through the game loop
        x+=dx;
        y+=dy;

        if(x < 0){// 0 is defined the left edge
            dx = -dx;// change the direction when hit left wall
        }
        if(y < 0){
            dy = -dy;//flip direction when hit up wall
        }
        if(x > Main.WIDTH-ballSize){
            dx = -dx; //flip direction when right up wall
        }
        if(y > Main.HEIGHT-ballSize){// hit bottom wall
            dy = -dy;
        }
    }
    public void draw(Graphics2D g){
        g.drawImage(img, (int) x, (int) y, ballSize, ballSize, null);
    }
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,ballSize,ballSize);//it will return new rectangle when ever the ball in space
    }

    public void setDY(double theDY){ dy = theDY;}

    public double getDY(){ return dy;}

    public void setDx(double theDX){dx = theDX;}

    public double getDX(){return dx;}

    public double geX(){ return x;}

    public boolean youLose(){
        boolean loser = false;

        if (y > Main.HEIGHT - ballSize * 2){
            loser = true;
        }

    return loser;
    }
}


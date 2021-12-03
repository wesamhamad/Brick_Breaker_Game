package Brick_Breaker;

import java.awt.*;

public class BrickPiece {

    //Fields
    private int x, y;
    private double dx, dy, gravity;
    private Map theMap;
    private int size;


    //constructor
    public BrickPiece(int brickx, int bricky, Map theGameMap){

        theMap = theGameMap;

        // to set the place upper corner
        x = brickx + theMap.getBrickWidth() / 2;
        y = bricky + theMap.getBrickHeight() / 2;

        // to determine the velocity
        dx = (Math.random() * 30 - 15);
        dy = (Math.random() * 30 - 15);
        size = (int)(Math.random() * 15 +2);

        gravity = .8;

    }

    public void update(){
        x += dx;
        y += dy;
        dy += gravity;
    }

    public void draw(Graphics2D g){
        g.drawImage(theMap.img[0],(int) x, (int) y, size, size, null);
    }
}

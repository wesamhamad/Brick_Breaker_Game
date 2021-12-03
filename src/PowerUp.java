package Brick_Breaker;

import java.awt.*;

public class PowerUp {

    //Fields
    private int x, y, dy, type, width, height;
    private Map theMap = new Map(4, 8);
    private boolean isOnScreen;
    private  boolean wasUsed;
    private Image image;

    public final static int WIDEPADDLE = 4 ;// 1 to 3 have different levels
    public final static int REDUCEPADDLE = 5;



    //constructor
    public PowerUp(int xStart, int yStart, int theType, int thWidth, int theHeight ){

        x = xStart;
        y = yStart;
        type = theType;
        width = thWidth;
        height = theHeight;

        if (type < 4){ type = 4; }
        if (type > 5){ type = 5; }
        if (type == WIDEPADDLE){ image = theMap.img[1]; }
        if (type == REDUCEPADDLE){ image = theMap.img[2]; }

        dy = (int) (Math.random() * 6 + 1 ); // range of 1-6 to specify fast of falling

        wasUsed = false;

    }

    public void draw(Graphics2D g){
        g.drawImage(image,x,y,width + 10,height + 20, null);
    }

    public void update(){

        y += dy;

        if (y > Main.HEIGHT){
            isOnScreen = false;
        }
    }

    public int getType(){return type;}

    public boolean getWasUsed(){return wasUsed;}
    public void setWasUsed(boolean used){ wasUsed = used;}

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }




}

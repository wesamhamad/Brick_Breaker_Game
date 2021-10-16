package Brick_Breaker;

import java.awt.*;

public class PowerUp {
    //Fields
    private int x, y, dy, type, width, height;

    private boolean isOnScreen;
    private  boolean wasUsed;
    private Color color;

    public final static int WIDEPADDLE =4 ;
    public final static int FASTBALL = 5;
    public final static Color WIDECOLOR = Color.GREEN;
    public final static Color FASTCOLOR = Color.RED;

    //constructor
    public PowerUp(int xStart, int yStart, int theType, int thWidth, int theHeight ){

        x = xStart;
        y = yStart;
        type = theType;
        width = thWidth;
        height = theHeight;

        if (type < 4){ type = 4; }
        if (type > 5){ type = 5; }
        if (type == WIDEPADDLE){ color=WIDECOLOR; }
        if (type == FASTBALL){ color=FASTCOLOR; }
        dy = (int) (Math.random() * 6 +1 ); // range of 1-6

        wasUsed=false;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(x,y,width,height);

    }

    public void update(){

        y +=dy;

        if (y > main.HEIGHT){
            isOnScreen=false;
        }
    }

    public int getX(){return x;}
    public void setX(int newX){x = newX;}

    public int getY(){return y;}
    public void setY(int newY){y = newY;}

    public int getDY(){return dy;}
    public void setDY(int newDY){dy = newDY;}

    public int getType(){return type;}
    public void setType(int newType){type = newType; }

    public boolean getIsOnScreen(){return isOnScreen;}
    public void setIsOnScreen(boolean onScreen){ isOnScreen = onScreen;}

    public boolean getWasUsed(){return wasUsed;}
    public void setWasUsed(boolean used){ wasUsed = used;}

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }


}

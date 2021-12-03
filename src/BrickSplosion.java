package Brick_Breaker;

import java.awt.*;
import java.util.ArrayList;

public class BrickSplosion {

    //Fields
    private ArrayList<BrickPiece> pieces;
    private int x, y;
    private Map theMap;
    private boolean isActive;
    private long startTime;

    //constructor
    public BrickSplosion(int theX, int theY, Map theMap){

        x = theX;
        y = theY;
        this.theMap = theMap;
        isActive = true;
        startTime = System.nanoTime();
        pieces = new ArrayList<BrickPiece>();
        init();

    }

    public void init(){

        int randNum = (int)(Math.random() * 20 + 5);
        for (int i = 0 ; i < randNum ; i++){
            pieces.add(new BrickPiece(x, y, theMap));
        }

    }

    public void update(){

        for (BrickPiece  bp: pieces){
            bp.update();
        }

        if ((System.nanoTime() - startTime) / 1000000 > 2000 && isActive){
            isActive = false;
        }
    }

    public void draw(Graphics2D g){
        for (BrickPiece bp : pieces)
        bp.draw(g);
    }

    public boolean getIsActive(){ return isActive; }

}

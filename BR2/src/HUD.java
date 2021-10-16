package Brick_Breaker;

import java.awt.*;

// to show the score
public class HUD {

    //Fields
    private int score;

    //constructor
    public HUD(){

        init();
    }
    public void init(){
        score=0;
    }

    public void draw(Graphics2D g){
        g.setFont(new Font("Courier New",Font.PLAIN,14));
        g.setColor(Color.RED);
        g.drawString("YouR Score: "+score ,40,40);// print score on the game panel
    }

    public int getScore(){ return  score;}

    public void addScore(int scoreToAdd){
        score+=scoreToAdd;
    }
}

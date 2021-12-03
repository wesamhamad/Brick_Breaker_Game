package Brick_Breaker;

import java.awt.*;


public class HUD { // to show the score

    //Field
    private int score;

    //constructor
    public HUD(){
        init();
    }
    public void init(){
        score=0;
    }

    public void draw(Graphics2D g){
        g.setFont(new Font("Courier New",Font.PLAIN,18));
        g.setColor(new Color(168, 39, 39));
        g.drawString("Your Score: " + score ,40,420);// print score on the game panel
    }

    public void addScore(int scoreToAdd){
        score += scoreToAdd;
    }
    public void reduceScore(int scoreToAdd){
        score -= scoreToAdd;
    }
}

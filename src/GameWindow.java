package Brick_Breaker;

import javax.swing.*;

public class GameWindow {

    //Fields
    public static JFrame theFrame;

    //constructor
    public GameWindow(int level){

        theFrame = new JFrame("Brick Breaker");// to create the window

        GamePanel thePanel = new GamePanel(level);
        theFrame.setSize(Main.WIDTH, Main.HEIGHT);

        theFrame.setLocationRelativeTo(null); // to set position exactly in center
        theFrame.setResizable(false); // so no one can change the dimensions by resizing the window

        theFrame.add(thePanel);

        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when click close it will stop the program running
        theFrame.setVisible(true);// to make frame pop up on the screen

        thePanel.playGame();//to start the game


    }


}

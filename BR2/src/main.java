package Brick_Breaker;

import javax.swing.*;

public class main {
    public static final int WIDTH = 640, HEIGHT = 480;//size of our class

    public static void main(String[] args) {
        JFrame theFrame = new JFrame("Brick Breaker");// to crate the window & provide the title

        GamePanel thePanel = new GamePanel();

        theFrame.setLocationRelativeTo(null);
        theFrame.setResizable(false);// so no one can change the dimensions
        theFrame.setSize(WIDTH, HEIGHT);
        theFrame.add(thePanel);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);// to make frame pop up on the screen
        thePanel.playGame();

    }

}

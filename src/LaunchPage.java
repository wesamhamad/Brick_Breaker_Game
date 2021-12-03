package Brick_Breaker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LaunchPage implements ActionListener{

    //Fields
    private JFrame frame ;
    private JButton level1, level2, level3,choose, exit, info ;
    private static int userRows, userColumns;
    private static String []buttons;
    private static ImageIcon icon ;

   //constructor
    public LaunchPage(){

        frame = new JFrame("Brick Breaker SetUp");
        level1 = new JButton("LEVEL1");
        level2 = new JButton("LEVEL2");
        level3 = new JButton("LEVEL3");
        choose = new JButton("SetUp Yours !");
        exit = new JButton("EXIT");
        info = new JButton("HELP");
        buttons = new String[]{"Close","Ok"};

        //label
        JLabel label = new JLabel();
        label.setText("Please Choose Level : ");
        label.setBounds(400,135,600,40);
        label.setForeground(new Color(253, 253, 255));
        label.setFont(new Font("Helvetica", Font.PLAIN, 20));

        //label which is the background
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("Resources/bg.jpeg"));
        background.setBounds(0,0,Main.WIDTH, Main.HEIGHT);

        //level1 setup
        level1.setBounds(400,195,200,40);
        level1.setFocusable(false);
        level1.addActionListener(this);

        //level2 setup
        level2.setBounds(400,260,200,40);
        level2.setFocusable(false);
        level2.addActionListener(this);

        //level3 setup
        level3.setBounds(400,325,200,40);
        level3.setFocusable(false);
        level3.addActionListener(this);

        //choose setup
        choose.setBounds(400,390,200,40);
        choose.setFocusable(false);
        choose.addActionListener(this);

        //Info setup
        info.setBounds(350,455,100,40);
        info.setFocusable(false);
        info.addActionListener(this);

        //Exit setup
        exit.setBounds(550,455,100,40);
        exit.setFocusable(false);
        exit.addActionListener(this);

        //frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Main.WIDTH, Main.HEIGHT);
        frame.setLayout(null);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //add component to the frame
        frame.add(level1);
        frame.add(level2);
        frame.add(level3);
        frame.add(choose);
        frame.add(label);
        frame.add(info);
        frame.add(exit);
        frame.add(background);


    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == level1 ) {// if level1 is clicked generate the action

            frame.dispose();
            new Thread(){
                public void run(){
                    GameWindow myGame = new GameWindow(1);
                }
            }.start();

        }else
            if(e.getSource()== level2 ) {

            frame.dispose();
            new Thread(){
                public void run(){
                    GameWindow myGame = new GameWindow(2);
                }
            }.start();

        } else
            if(e.getSource()== level3 ) {

            frame.dispose();
            new Thread(){
                public void run(){
                    GameWindow myGame = new GameWindow(3);
                }
            }.start();

        }else
            if(e.getSource()== choose ) {

             //taking the converting values from user
            String rows = JOptionPane.showInputDialog("Enter Number Of Rows :");
            setUserRows(Integer.parseInt(rows));

            String columns = JOptionPane.showInputDialog("Enter Number Of columns :");
            setUserColumns(Integer.parseInt(columns));

            frame.dispose();
            new Thread(){
                public void run(){
                    GameWindow myGame = new GameWindow(4);
                }
            }.start();

        }else
            if(e.getSource()== info ) {

             icon = new ImageIcon("Resources/icon.png");
            JOptionPane.showOptionDialog(null,"\n\nBrick Breaker is a game in which you must smash a wall of bricks by deflecting a bouncing ball with a paddle.\n" +
                            " When the you destroys all the bricks, you will win the game.\n" +
                            "In the game the player gets for each smash the score increase by 10 points.!\n" +
                            "But when hit the fire will reduce by 10.\n" +
                            "the game comes with 3 levels (Level 1 = Easy, Level2 = Medium, Level3 = Hard)"
                    ,"Game Instructions",
                    JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE, icon,buttons,0);

        }else if(e.getSource()== exit ) {

            frame.dispose();
            GameWindow.theFrame.dispose();

        }
    }

    public static void setUserColumns(int col) {
        if (col > 3 && col < 13)
            userColumns = col;
        else {
             icon = new ImageIcon("Resources/errorIcon.png");
            JOptionPane.showOptionDialog(null," Columns number must be greater than 3 and less than 13 !!!! \n it's will set to the default value ","Error Massage",
                    JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,
                    icon,buttons,0);
            userColumns = 6;
        }
    }

    public static int getUserColumns(){return userColumns; }

    public static void setUserRows(int row){

        if(row > 3 && row < 13)
            userRows = row;
        else{
            icon = new ImageIcon("Resources/errorIcon.png");
            JOptionPane.showOptionDialog(null," Rows number must be greater than 3 and less than 13 !!!! \n it's will set to the default value ","Error Massage",
                    JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE,
                    icon,buttons,0);//null determines the Frame in which the dialog is displayed; if null, or if the parentComponent has no Frame, a default Frame is used
            userRows = 4;
        }
    }

    public static int getUserRows(){return userRows;}

}

package Brick_Breaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Map {
    //Fileds
    private int[][] theMap;
    private int brickHeight, brickWidth;
    public final int HOR_PAD = 100, VERT_PAD = 60, BETWEEN_PAD = 20;
    public static Random rand = new Random();
    public Image []img;


    public Map(int row, int col) {

        initMap(row, col);
        brickWidth = (Main.WIDTH - 2 * HOR_PAD) / col - BETWEEN_PAD;
        brickHeight = (Main.HEIGHT / 2 - VERT_PAD * 2) / row - BETWEEN_PAD;
        img = new Image[3];
        uploadImages();

    }

    public void initMap(int row , int col) {

        int []random = new int [4];
        for (int i = 0; i < random.length; i++) {
                random[i] = rand.nextInt(4);
        }

        theMap = new int[row][col];

        for (int i = 0; i < theMap.length; i++) {

            //powerUp value base on level
            if((GamePanel.currentLevel == GamePanel.LEVEL2 || GamePanel.currentLevel == GamePanel.SETUP_LEVEL) && (i == theMap.length-1)){
                theMap[random[0]][random[1]] = PowerUp.WIDEPADDLE;
                theMap[random[2]][random[3]] = PowerUp.REDUCEPADDLE;
            }else if((GamePanel.currentLevel == GamePanel.LEVEL3) && (i == theMap.length-1) ){
                theMap[random[0]][random[1]] = PowerUp.WIDEPADDLE;
                theMap[random[2]][random[0]] = PowerUp.REDUCEPADDLE;
                theMap[random[1]][random[0]] = PowerUp.WIDEPADDLE;
                theMap[random[2]][random[3]] = PowerUp.REDUCEPADDLE;
            }

            for (int j = 0; j < theMap[0].length; j++) {
                theMap[i][j] = (int)(Math.random() * 3 +1);
            }
        }
    }

    public void draw(Graphics2D g) {


        for (int row = 0; row < theMap.length; row++) {
            for (int col = 0; col < theMap[0].length; col++) {

                if(theMap[row][col]>0){

                    // to make bricks have different color base on random generated numbers
                    if (theMap[row][col] == 1){
                            g.setColor(new Color(238, 238, 123));
                    }
                    if (theMap[row][col] == 2){
                        g.setColor(new Color(204, 118, 23));
                    }
                    if (theMap[row][col] == 3){
                        g.setColor(new Color(182, 23, 23));
                    }
                    if (theMap[row][col] == PowerUp.WIDEPADDLE){
                        g.setColor(new Color(47, 73, 94));

                    }if (theMap[row][col] == PowerUp.REDUCEPADDLE){
                        g.setColor(new Color(47, 73, 94));

                    }

                    g.fillRoundRect(col * brickWidth + HOR_PAD + BETWEEN_PAD * col, row * brickHeight + VERT_PAD + BETWEEN_PAD * row, brickWidth, brickHeight, 10, 10);
                    g.setStroke(new BasicStroke(2));

                }
            }
        }
    }

    public boolean isThereWin(){

        boolean thereIsAWin = false;

        int bricksRemaining = 0;

        for (int row = 0; row < theMap.length; row++){
            for (int col =0 ; col < theMap[0].length; col++){
                bricksRemaining += theMap[row][col];
            }
        }

        if(bricksRemaining == 0){
            thereIsAWin = true;
        }

        return  thereIsAWin;

    }

    public int[][] getMapArray(){return theMap;}

    public void setBrick(int row,int col,int value){
        theMap[row][col] = value;
    }

    public int getBrickWidth(){return brickWidth;}
    public int getBrickHeight(){return brickHeight;}

    public void hitBrick(int row, int col){
        theMap[row][col] -= 1;
        if(theMap[row][col] < 0){
            theMap[row][col] = 0;
        }
    }

    public void uploadImages(){

        try {
            img[0] = ImageIO.read(new File("Resources/glassRect.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            img[1] = ImageIO.read(new File("Resources/glassRect1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            img[2] = ImageIO.read( new File("Resources/lava.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}

# Brick Breaker Game
A project for IT214 course, presented to Meshaiel Alsheail, the software development process of Brick Breaker Game .


## Overview
The goal of the project was to create an implementation of the Brick Breaker game with all functionalities. Brick Breaker is a game in which the player must smash a wall of bricks by deflecting a bouncing ball with a paddle.                                        
It has the following game mechanics: 
* **Graphics :** plain colors and simple images.
* **Sound effects:** different sound effects for each possible game event (e.g. hitting a brick, hitting a powerUp, hitting the paddle...).
* **Score:** each time the ball breaks a brick or the paddle hit the blue ball , score will increases by 10. But, when paddle hit the fire score will reduce by 10.
*  **Animation:** each time the ball hit the yellow brick screen shaked and destroyed the brick into smaller pices. 

* **Multiple brick types:** there is four types of brick:
  * Yellow & Orange bricks: this is the basic brick, it just waits to be broken;
  * Red bricks: this is almost the same as the Yellow brick, but needs two hits instead of one to break.
  * Blue bricks: this bricks moves horizontally as either a blue ball (which extends size of paddle) or a fire (which shrink size of paddle).

**Levels with different settings:** there are three different settings (Easy, Normal and Hard) also, user can choose his own !

**Easy (Level1):** this level have :
 *  4x6 bricks
 *  slowly movement of ball
 *  have no powerUp (blue bricks)

**Normal (Level2):** this level have :
 * 5x8 bricks 
 * normal movement of ball
 * have two powerUp (blue bricks)
 
**Hard (Level3):** this level have :
 * 6x9 bricks 
 * fast movement of ball
 * have four powerUp (blue bricks )

**SetUp Yours :** this allows user to choose number of rows and columns and it's have same sittings of Level2

How Levels done ? by click button the constructor of GameWindow with the level as an argument will call in [LaunchPage](https://github.com/wesamhamad/Brick_Breaker_Game/blob/main/src/LaunchPage.java) class,then [GameWindow](https://github.com/wesamhamad/Brick_Breaker_Game/blob/main/src/GameWindow.java) class will called the constructor of [GamePanel](https://github.com/wesamhamad/Brick_Breaker_Game/blob/main/src/GamePanel.java) in order to send the level and to start the game by calling the playGame method,then inside init function I specify the number of regtangles and fast of ball base on level as shown here :
```java

       //fast of the ball base on level
        if(currentLevel == LEVEL3){ 
            theBall = new Ball(6,9);
        }else
            if(currentLevel == LEVEL2 || currentLevel == SETUP_LEVEL){
                theBall = new Ball(5,7);
            } else{
                theBall = new Ball(4,6);
            }

        thePaddle = new Paddle(100, 30);// size of Paddle

        // number of rectangles base on level
        if(currentLevel == LEVEL1){
            theMap = new Map(4, 6);
        }else
            if(currentLevel == LEVEL2){
                theMap = new Map(5, 8);
        }else
            if(currentLevel == LEVEL3){
                theMap = new Map(6, 9);
        }else
            if(currentLevel == SETUP_LEVEL){
                theMap = new Map(LaunchPage.getUserRows(), LaunchPage.getUserColumns());
            }

```
**Also** in [Map](https://github.com/wesamhamad/Brick_Breaker_Game/blob/main/src/Map.java) class inside initMap method which is assigned randomly number 1-3 for each brick, and at end of the loop the PowerUps values will assigned base on currentLevel value. 4 for blue ball and 5 for fire .

```java
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
```

**These all done by help of**  JFrame and JPanels and other useful classes: Image,File,Color, Font, and Graphics. These classes are imported and then  used in GUI . I use the Graphics, to render visuals (e.g., figures, pictures, and even text) in a JPanel that contains the graphics part of a JFrame.    


## Additional Features Implemented
Beyond the scope of the project, I implemented the following additional features:

* SetUp GUI : to choose levels , help button which shows game instructions to the players and exit button to end it .

* Player can setup his own number of bricks

                                              
<p > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img  width="900" alt="GUI" src="https://user-images.githubusercontent.com/74800962/144532375-d53aab53-7d62-474f-a04e-48d6b577e994.png" />
</p>

``
NOTE: The output may be a little different on your computer that’s happening because of different platforms .
``

## Controls

| Keys              | Action                                     |
| ----------------- | -------------------------------------------|
| Arrow Keys        |     Moves the paddel left and right        |
| Mouse over paddel |     Moves the paddel left and right        |

## How To Play
* Clone the repository to your computer 
 ```
 git clone https://github.com/wesamhamad/Brick_Breaker_Game.git
 ```
* Import the project into your IDE (IntelliJIDEA is recommended)
* Simply click run within the IDE.
* The game should open up in a JAR applet.

## Problems 

I faced two problems while developing this game :

**1-** I Create separate JFrame in order to implement gui for levels, so in main I call constructor of [LaunchPage](https://github.com/wesamhamad/Brick_Breaker_Game/blob/main/src/LaunchPage.java) class which create the SetUp frame then call the thePanel.playGame() method to start the game, but the frame of game doesn’t show the graphics, these two frames have effects each other, so I solve it by useing Thread in order to preform both at same time without effecting of each other.

```java
 if(e.getSource() == level1 ) {// if level1 button is clicked start the thread and generate the action 

            frame.dispose();
            new Thread(){
                public void run(){
                    GameWindow myGame = new GameWindow(1);
                }
            }.start();
```
**2-** When I use KeyListener interface, in order to move the paddle, it's could going off the left&right-hand side of screen, I solve it by updating the position (in keyReleased method) each time it’s going off .

```java
   public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT){
            left = false;
            if (x < Main.WIDTH -  width * 10.2) {
                x = Main.WIDTH - width * 10.2; // to prevent paddle from going off the left-hand side
            }
        }
        if(k == KeyEvent.VK_RIGHT){
            right = false;
            if (x > Main.WIDTH - width) {
                x = Main.WIDTH - width *1.2; // to prevent paddle from going off the right-hand side
            }
        }
    }
```

## Output Sample
### Case #1 (Level2)



https://user-images.githubusercontent.com/74800962/144668766-1bd21ee7-c1ed-4ad5-a2b8-b905cec42aad.mov

``
NOTE: To have the whole experience, make sure video is not silent!
``
### Case #2 (SetUp Yours!)


https://user-images.githubusercontent.com/74800962/144670308-311a3e03-6029-4e20-82f0-312789ffd1e9.mov

 ``
NOTE: There is a limitation for player input, but I don’t show a message that's says, instead it’s show a dialogue box (if he enter a value greater than 13 or less than 3) to informed the player as shown above .
``
 
To see [other cases](https://github.com/wesamhamad/Brick_Breaker_Game/tree/main/Output) of the output.
## Learning References :
* [Joel Rogness](https://www.youtube.com/watch?v=Qc_OlE1Xn38&list=PLn6h3KPOiM-ErYSmMH1ULtyKTE765d0V3&index=1) tutorials
* [Bro Code](https://www.youtube.com/channel/UC4SVo0Ue36XCfOyb5Lh1viQ) tutorials
* [Ali Qusay](https://www.youtube.com/watch?v=NDh4B3gb8V4) Java GUI Tutorial | controls using KeyListener
## CONTRIBUTING
**How Can I Contribute?**
When you are ready to start work on an issue:

* Let me know by leaving a comment on the issue. (Also let me know later if you are no longer working on it.)
* Once you have been assigned the issue (or once you have claimed the issue) only then proceed to make the Pull Request. This will help avoid multiple PRs pertaining to the same issue.

If you don't see your idea/issue listed, do one of the following:

* ***If your contribution is minor***, such as a typo fix, you can directly open a pull request.
* ***If your contribution is major***, such as a new feature/enhancement, start by opening an issue first. This way, other people can be also involved in the discussion before you do any work.



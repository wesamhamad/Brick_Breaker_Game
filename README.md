# Brick Breaker Game
A project for IT214 course, presented to Meshaiel Alsheail, the software development process of Brick Breaker Game .


## Overview
The goal of the project was to create an implementation of the Brick Breaker game with all functionalities. Brick Breaker is a game in which the player must smash a wall of bricks by deflecting a bouncing ball with a paddle.                                        
It has the following game mechanics: 
* **Graphics :** plain colors and simple images.
* **Sound effects:** different sound effects for each possible game event (e.g. hitting a brick, hitting a powerUp, hitting the paddle...).
* **Score:** each time the ball breaks a brick or the paddle hit the blue ball , score will increases by 10. But, when the ball hits the paddle hit the fire score will reduce by 10.

* **Multiple brick types:** there is four types of brick:
  * Yellow  bricks: this is the basic brick, it just waits to be broken;
  * Orange bricks: this is almost the same as the White brick, but needs two hits instead of one to break.
  * Red bricks:  needs three hits to break.
  * Blue bricks: this bricks moves horizontally as either a blue ball or a fire.

**Levels with different settings:** there are three different settings (Easy, Normal and Hard) also, user can choose his own !

**Easy (Level1):** this level have :
 1- 4x6 bricks 
 2- slowly movement of ball
 3- have no powerUp (blue bricks)

**Normal (Level2):** this level have :
 1- 5x8 bricks 
 2- normal movement of ball
 3- have two powerUp (blue bricks)
 
**Hard (Level3):** this level have :
 1- 6x9 bricks 
 2- fast movement of ball
 3- have four powerUp (blue bricks)

**SetUp Yours :** this allows user to choose number of rows and columns and it's have same sittings of Level2



**These all done by help of**  JFrame and JPanels and other useful classes: Image, Dimension, Point, Color, Font, and Graphics. These classes are imported and then  used in GUI . We use the last, Graphics, to render visuals (e.g., figures, pictures, and even text) in a JPanel that contains the graphics part of a JFrame.    


## Additional Features Implemented
Beyond the scope of the project, I implemented the following additional features:

* SetUp GUI : to choose levels , help button which shows game instructions to the players and exit button to end it .

* Player can setup his own number of bricks

                                              
<p > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img  width="900" alt="GUI" src="https://user-images.githubusercontent.com/74800962/144532375-d53aab53-7d62-474f-a04e-48d6b577e994.png" />
</p>

## Controls

| Keys              | Action                                     |
| ----------------- | -------------------------------------------|
| Arrow Keys        | Moves the paddel left and right            |
| Mouse over paddel |     --------------                         |

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

**1-** I Create separate JFrame in order to implement gui for levels, so in main I call constructor of [LaunchPage.java]() class which create the SetUp frame then call the thePanel.playGame() method to start the game, but the frame of game doesn’t show the graphics, these two frames have effects each other, so I use Thread for each level (calling of playGame to start the game )

```java
 if(e.getSource() == level1 ) {// if level1 is clicked generate the action

            frame.dispose();
            new Thread(){
                public void run(){
                    GameWindow myGame = new GameWindow(1);
                }
            }.start();
```
**2-** When I use KeyListener the whole panel moves when I press key arrows, I solve it by using 
## CONTRIBUTING
**How Can I Contribute?**
When you are ready to start work on an issue:

* Let me know by leaving a comment on the issue. (Also let me know later if you are no longer working on it.)
* Once you have been assigned the issue (or once you have claimed the issue) only then proceed to make the Pull Request. This will help avoid multiple PRs pertaining to the same issue.

If you don't see your idea/issue listed, do one of the following:

* ***If your contribution is minor***, such as a typo fix, you can directly open a pull request.
* ***If your contribution is major***, such as a new feature/enhancement, start by opening an issue first. This way, other people can be also involved in the discussion before you do any work.


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;




public class GameHandler {
    
public Paddle player1;
public Paddle player2;

public Ball ball;

public ScoreBoard scoreBoard;
    
public static int sceneWidth, sceneHeight;
    
private int gameState = 0;
//game states
private final int START = 0;
private final int PLAYING = 1;
private final int GAME_OVER = 2;
private final int PAUSED = 3;

private KeyListener listener;

public GameHandler(int sceneWidth, int sceneHeight){
    listener = new KeyListener();
    
    GameHandler.sceneWidth = sceneWidth;
    GameHandler.sceneHeight = sceneHeight;
    
    player1 = new Paddle(50, sceneHeight/2);
    player2 = new Paddle(sceneWidth - 50, sceneHeight/2);
    
    scoreBoard = new ScoreBoard();
    
    ball = new Ball(sceneWidth/2, sceneHeight/2, this, scoreBoard);
}
    //updates every frame
    public void update(){
        
        if(gameState == START){
            player1.setPosition(sceneHeight/2);
            player2.setPosition(sceneHeight/2);
            scoreBoard.reset();
            if(pressedSpace()){
                gameState = PLAYING;
            }
        }else if(gameState == PLAYING){
            player1.update(KeyListener.w, KeyListener.s);
            player2.update(KeyListener.up, KeyListener.down);
            //Update ball
            ball.update(pressedSpace());
            //update scoreboard
            //scoreBoard.addScore(<winner (true = p1, false = p2)>);
            if(scoreBoard.getGameOver()){
                gameState = GAME_OVER;
            }
        }else if(gameState == GAME_OVER){
            if(pressedSpace()){
                gameState = START;
            }
        }
    }
    //paints every frame
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sceneWidth, sceneHeight);
        
        if(gameState == START){
            g.setColor(Color.WHITE);
            String startString = "press space to start";
            g.drawString(startString, sceneWidth/2 - g.getFontMetrics().stringWidth(startString)/2, 250);
        }else if (gameState == GAME_OVER){
            g.setColor(Color.WHITE);
            String gameOverString = "GAME OVER";
            g.drawString(gameOverString, sceneWidth/2 - g.getFontMetrics().stringWidth(gameOverString)/2, 250);
        }
        
        player1.paint(g);
        player2.paint(g);
        //paint ball
        ball.paint(g);
        scoreBoard.paint(g);
    }
    
    private boolean wasSpaceDown = false;
    
    private boolean pressedSpace(){
        if(wasSpaceDown){
            if(!KeyListener.space){
                wasSpaceDown = false;
                return true;
            }
        }else if(!wasSpaceDown && KeyListener.space){
            wasSpaceDown = true;
        }
        return false;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author student
 */
public class Ball {
   
    private GameHandler gameHandler;
    private ScoreBoard scoreBoard;
    
    private int posX,posY;
    private int startPosX, startPosY;
    private int ySpeed = 1;
    private int speedInverter = 1;
    
    private final int SPEED = 10;
    private final int MIN_Y_SPEED = 3, MAX_Y_SPEED = 10;
    private final int SIZE = 20;
    
    private int ballState = 0;
    private final int INACTIVE = 0;
    private final int ACTIVE = 1;
    
    private Random r = new Random();
    
    public Ball(int startPosX, int startPosY, GameHandler gameHandler, ScoreBoard scoreBoard)
    {
        posX = this.startPosX = startPosX;
        posY = this.startPosY = startPosY;
        
        this.gameHandler = gameHandler;
        this.scoreBoard = scoreBoard;
    }
    
    public void update (boolean isSpacePressed)
    {
       if(ballState == INACTIVE){
           if(isSpacePressed){
               ballState = ACTIVE;
               ySpeed = 0;
           }
       }
       
       if(ballState == ACTIVE){
           posX += SPEED * speedInverter;
           posY += ySpeed;
           
           if(collides(gameHandler.player1) || collides(gameHandler.player2)){
               speedInverter *= -1;
               ySpeed = (r.nextInt(MAX_Y_SPEED - MIN_Y_SPEED) + MIN_Y_SPEED) * (ySpeed > 0 ? 1 : -1);
           }
           
           wallBounce();
           
           if(outOfBounds()){
                posX = startPosX;
                posY = startPosY;
                ballState = INACTIVE;
           }
       }
    }
    
    private void wallBounce(){
        if(posY - SIZE/2 <= 0 || posY + SIZE/2 >= gameHandler.sceneHeight){
            ySpeed *= -1;
        }
    }
    
    private boolean collides(Paddle player){
        Rectangle paddleCollisionBox = player.getBounds();
        Rectangle collisionBox = new Rectangle(posX - SIZE/2, posY - SIZE/2, SIZE, SIZE);
        if(collisionBox.intersects(paddleCollisionBox)){
            return true;
        }
        return false;
    }
    
    private boolean outOfBounds(){
        if(posX < 0 - SIZE){
            scoreBoard.addScore(false);
            return true;
        }
        else if(posX > GameHandler.sceneWidth + SIZE){
            scoreBoard.addScore(true);
            return true;
        }
        return false;
    }
    
    public void paint (Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(posX - SIZE/2, posY - SIZE/2, SIZE, SIZE);
    }
    
    
}

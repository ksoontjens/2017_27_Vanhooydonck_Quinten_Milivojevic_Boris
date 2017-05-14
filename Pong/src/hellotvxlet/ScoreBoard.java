/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author student
 */
public class ScoreBoard {

    private final int MAX_SCORE = 90;
    
    private Point position;
    
    private int scoreP1 = 0, scoreP2 = 0;
    
    public ScoreBoard(){
        position = new Point(GameHandler.sceneWidth/2, 25);
    }
    
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        String scoreString = scoreP1 + " | " + scoreP2;
        g.drawString(scoreString, position.x - g.getFontMetrics().stringWidth(scoreString)/2, position.y);
    }
    
    public void reset(){
        scoreP1 = 0;
        scoreP2 = 0;
    }
    
    public void addScore(boolean p1Won){
        if(p1Won){
            scoreP1++;
        }
        else{
            scoreP2++;
        }
    }
    
    public boolean getGameOver(){
        if(scoreP1 == MAX_SCORE || scoreP2 == MAX_SCORE){
            return true;
        }
        return false;
    }
    
}

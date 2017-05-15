package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;



/**
 *
 * @author student
 */
public class Paddle {

    private Point position;
    private final int WIDTH = 20, HEIGHT = 100;
            
    private final int SPEED = 15;   
    
    public Paddle(int xPos, int yPos){
        position = new Point(xPos, yPos);
    }
    
    public void update(boolean up, boolean down){
        //move paddle
        if(up){
            position.y -= SPEED;
        }else if(down){
            position.y += SPEED;
        }
        //keep between bounds
        if(position.y < HEIGHT/2){
            position.y = HEIGHT/2;
        }else if(position.y > GameHandler.sceneHeight - HEIGHT/2){
            position.y = GameHandler.sceneHeight - HEIGHT/2;
        }
    }
    
    public void setPosition(int yPos){
        position.y = yPos;
    }
    
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);
    }
    
    //returns min x, max x and min y max y
    public Rectangle getBounds(){
        return new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);
    }
    
}

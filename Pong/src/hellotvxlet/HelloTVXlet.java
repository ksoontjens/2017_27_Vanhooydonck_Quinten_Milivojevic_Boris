package hellotvxlet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

public class HelloTVXlet implements Xlet {
  
    private int sceneWidth, sceneHeight;
    
    private GameHandler gameHandler;
    
    private final int FRAMES_PER_SECOND = 30;
    
    private HScene scene;
    private Container gui;
    
    private boolean  isRunning = false;
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
        scene = HSceneFactory.getInstance().getDefaultHScene();
        gui = new Container(){
            public void paint(Graphics g){
                gameHandler.paint(g);
            }
        };
        sceneWidth = scene.getWidth();
        sceneHeight = scene.getHeight();
        gui.setSize(sceneWidth, sceneHeight);
        scene.add(gui, BorderLayout.CENTER);
        scene.validate();
        
        gameHandler = new GameHandler(sceneWidth, sceneHeight);
    }

    public void startXlet() {
        gui.setVisible(true);
        scene.setVisible(true);
        isRunning = true;
        
        while(isRunning){
            long startTime = System.currentTimeMillis();
            //updates game
            gameHandler.update();
            //draws frame after update
            scene.paint(scene.getGraphics());
            scene.validate();
            
            try {
                Thread.sleep(1000 / FRAMES_PER_SECOND - (System.currentTimeMillis() - startTime)); // sleeps for 1/30 of a second - time it took to execute
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pauseXlet() {
        
    }

    public void destroyXlet(boolean unconditional) {
        
    }
}

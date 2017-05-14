/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;

/**
 *
 * @author student
 */
public class KeyListener {
    
    public static boolean up, down, w, s;
    public static boolean space;
    
    private UserEventListener eventListener;
    private UserEventRepository eventRepository;
    
    public KeyListener(){
        
        eventRepository = new UserEventRepository("pong controls");
        eventRepository.addAllArrowKeys();
        eventRepository.addKey(HRcEvent.VK_W);
        eventRepository.addKey(HRcEvent.VK_Z);
        eventRepository.addKey(HRcEvent.VK_S);
        eventRepository.addKey(HRcEvent.VK_SPACE);
        
        eventListener = new UserEventListener() {

            public void userEventReceived(UserEvent e) {
                if(e.getType() == HRcEvent.KEY_PRESSED){
                    setKey(e, true);
                }else if (e.getType() == HRcEvent.KEY_RELEASED){
                    setKey(e, false);
                }
            }
        };
        
        EventManager.getInstance().addUserEventListener(eventListener, eventRepository);
        
    }
    
    private void setKey(UserEvent e, boolean isKeyDown){
        
        switch(e.getCode()){
            case HRcEvent.VK_UP:
                up = isKeyDown;
                break;
            case HRcEvent.VK_DOWN:
                down = isKeyDown;
                break;
            case HRcEvent.VK_Z:
            case HRcEvent.VK_W:
                w = isKeyDown;
                break;
            case HRcEvent.VK_S:
                s = isKeyDown;
                break;
            case HRcEvent.VK_SPACE:
                space = isKeyDown;
                break;
        }
        
    }
    
}

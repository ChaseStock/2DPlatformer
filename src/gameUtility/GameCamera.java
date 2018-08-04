/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameUtility;

import entity.Entity;
import graphics.Screen;
import level.Level;

/**
 *
 * @author ChaseStock
 */
public class GameCamera {
    public int x;
    public int y;
    private final int WIDTH;
    private final int HEIGHT;
        
    public boolean isTracking = false;
    Entity focus;
    
    private boolean xLocked = false;
    private boolean yLocked = false;
    
    private Level level;

    
    public GameCamera(Screen screen, Level level, int x, int y){
        this.WIDTH = screen.width;
        this.HEIGHT = screen.height;
        focus(x,y);
        
        this.level = level;
    }
    
    public GameCamera(Screen screen, Level level, Entity focusedEntity, boolean isTracking){
        this.WIDTH = screen.width;
        this.HEIGHT = screen.height;
        
        this.level = level;
        
        focus(focusedEntity, isTracking);
    }
    
    public void setX(int x){
        this.x = x;
        
        checkCameraBounds();
    }
    
    public void setY(int y){
        this.y = y;
        
        checkCameraBounds();
    }
    
    public void isLockedX(boolean locked){
        xLocked = locked;
    }
    
    public void isLockedY(boolean locked){
        yLocked = locked;
    }
    
    public void focus(int focusX, int focusY){
        isTracking = false;
        x = focusX;
        y = focusY;
        
        checkCameraBounds();
    }
    
    public void focus(Entity focusedEntity, boolean isTracking){
        focus = focusedEntity;
        x = focusedEntity.getX() - WIDTH/2;
        y = focusedEntity.getY() - HEIGHT/2;
        this.isTracking = isTracking;
        
        checkCameraBounds();
    }
    
    public void setLevel(Level level){
        this.level = level;
        
        checkCameraBounds();
    }
    
    private void checkCameraBounds(){
        if(x < 0){
            x = 0;
        }
        if(level.getWidth() < x + WIDTH){
            x = level.getWidth() - WIDTH;
        }
        if(y < 0){
            y = 0;
        }
        if(level.getHeight() < y + HEIGHT){
            y = level.getHeight() - HEIGHT;
        }
    }
    
    public void stopTracking(){
        isTracking = false;
    }
    
    public void update(){
        
        if(isTracking && focus != null){
            int xScroll = focus.getX() - WIDTH / 2;
            int yScroll = focus.getY() - HEIGHT / 2;
            
            if(!xLocked && 0 <= xScroll && xScroll + WIDTH <= level.getWidth()){
                x = xScroll;
            }
            if(!yLocked && 0 <= yScroll && yScroll + HEIGHT <= level.getHeight()){
                y = yScroll;
            }
        }
    }   
}

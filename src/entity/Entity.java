/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import graphics.Screen;
import java.util.Random;
import level.Level;

/**
 *
 * @author ChaseStock
 */
public class Entity {
    public double x;
    public double y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    
    public void update() {
    }
    
    public void render(Screen screen){
        
    }
    
    public int getX(){
        return (int)x;
    }
    
    public int getY(){
        return (int)y;
    }
    
    public void remove(){
        removed = true;
    }
    
    public boolean isRemoved(){
        return removed;
    }
    
    public void init(Level level){
        this.level = level;
    }
}
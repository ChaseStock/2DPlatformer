/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mob;

import entity.Entity;
import graphics.Sprite;
import level.tile.SlantTile;
import level.tile.Tile;

/**
 *
 * @author ChaseStock
 */
public class Mob extends Entity {
    protected Sprite sprite;
    protected int dir = 1;
    protected boolean moving = false;
    protected boolean onGround = false;
    protected int climbHeight = 2;
    
    protected double dx = 0;
    protected double dy = 0;
    protected double maxdy = 15;
    
    public void move(double dx, double dy){
                
        if(dx != 0 && dy != 0){
            move(dx, 0);
            move(0, dy);
            return;
        }
        //if (dy < 0) dir = 0; 
        //if (dy > 0) dir = 2;
        if (dx > 0) dir = 1;
        if (dx < 0) dir = 3;
        
        //X 
        if(!collision(dx, 0)){
            x += dx;
        }
        else if(!collision(dx, -Math.abs(dx*2))){ 
            x += dx;
            dy -= Math.abs(dx)*2; 
        }
        else{
            moving = false;
        }

        //Y Change
        if(!collision(0, dy)){
            y += dy;
            onGround = false;
        }
        else{
            if(dy > 0){
                onGround = true;
            }
            else {
                onGround = false;
            }
            
            if(dy < 0){
                dy = 0;
                System.out.println("Stopped at " + dy);
            }
            moving = false;
        }
        
    }
    
    public void update(){
        
    }
        
    private boolean collision(double xa, double ya){
        boolean solid = false;
        
        for(int c = 0; c < 4; c++){
            double xt = (x + xa) + c % 2 * 8 + 3;
            double yt = (y + ya) + c / 2 * 14 + 1;
            
            Tile tileInQuestion = level.getTile((int)xt /16, (int)yt/16);
            
            if(tileInQuestion instanceof SlantTile){
                if(tileInQuestion.collision((int)xt, (int) yt)){
                    solid = true;
                }
            }
            else{
                if(tileInQuestion.isSolid) solid = true;
            }
        }
        
        return solid;
    }
    public void render(){
    }
}

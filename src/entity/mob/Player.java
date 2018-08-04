/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mob;

import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

/**
 *
 * @author ChaseStock
 */
public class Player extends Mob {
    
    private final Keyboard input;
    private Sprite sprite; 
    private int anim = 0;
    private boolean walking = false;
    
    private boolean canJump = true;
    private boolean isJumping = false;
    private double jumpVelocity = 0;
    private double jumpStrength = -4;
    
    //public AnimatedSprite playerForward = new AnimatedSprite(Sprite.player_forward_anim);
    //public AnimatedSprite playerSide = new AnimatedSprite(Sprite.player_side_anim);
    
    public AnimatedSprite playerRight = new AnimatedSprite(Sprite.player_right_anim);
    public AnimatedSprite playerLeft = new AnimatedSprite(Sprite.player_left_anim);
    //public AnimatedSprite playerBack = new AnimatedSprite(Sprite.player_back_anim);
    
    public Player(Keyboard input){
        this.input = input;
    }
    
    public Player(int x, int y, Keyboard input){
        this.x = x;
        this.y = y;
        this.input = input;
        this.sprite = Sprite.player_right;
    }
    
    private void checkJump(){         
        // Player is starting new jump
        if(onGround){
            jump();
        }
        
        // Player is mid-jump
        else{
            canJump = false;
        }       
    }
    
    public void jump(){
        dy = jumpStrength;
        isJumping = true;
    }
    
    public void respawn(){
        x = level.spawnX;
        y = level.spawnY;
    }
    
    public void die(){
        dx = 0;
        dy = 0;
        respawn();
    }
        
    public void update(){
        
        if(anim < 7500) anim++;
        else anim = 0;
        
        if(input.left) dx = -1.5;
        else if(input.right) dx = 1.5;
        else dx = 0;
        
        if(input.space && canJump){
            checkJump();
        }
        else{
            isJumping = false;
        }
        
        if(onGround && !isJumping){
            if(dy > .1){
                dy = dy/2;
            }
            canJump = true;
        }
        else if(onGround){
            canJump = true;
        }
        else{
            //Add gravity
            dy += level.getGravity();
        }

        if(dy > maxdy) dy = maxdy;
        
        if(dx != 0 || dy != 0){
            move(dx, dy);
            walking = true;
            
            if(dx == 0){
                walking = false;
            }
            
            if(level.getTile(getX()/16,getY()/16).index == -1){
                die();
            }
        }
        else{
            walking = false;
        }   
    }
    public void render(Screen screen){
        
        int flip = 0;
        
        /*
        if(dir == 0) {
            sprite = Sprite.player_forward;

            if(walking){
                //playerForward.update();
                //sprite = playerForward.getSprite();
            }
        }
        */
        if(dir == 1) {
            sprite = Sprite.player_right;
            if(walking){
                playerRight.update();
                sprite = playerRight.getSprite();
            }
        }
        /*
        if(dir == 2) {
            sprite = Sprite.player_back;

            if(walking){
                //playerBack.update();
                //sprite = playerBack.getSprite();
            }
        }
        */
        if(dir == 3){
            sprite = Sprite.player_left;
            if(walking){
                playerLeft.update();
                sprite = playerLeft.getSprite();
            }
        };
                
        screen.renderPlayer(getX(), getY(), sprite, flip);
    }
}

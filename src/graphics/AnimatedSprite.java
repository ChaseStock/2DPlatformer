/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

/**
 *
 * @author ChaseStock
 */
public class AnimatedSprite{
    
    private int frame = 0;
    private Sprite currentSprite;
    private Sprite[] sprites;
    private int time = 0;
    private int rate = 60;
        
    public AnimatedSprite(Sprite[] sprites){
        this.sprites = sprites;
        currentSprite = sprites[0];
    }
    
    public void update(){
        time++;
        if(time % rate == 0){ 
            if(frame >= sprites.length-1) frame = 0;
            else frame++; 
            currentSprite = sprites[frame];
        }
    }
    
    public Sprite getSprite(){
        return currentSprite;
    }
    
    public void setFrameRate(int frames){
        rate = frames;
    }
}

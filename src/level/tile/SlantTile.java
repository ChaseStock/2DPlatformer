/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.tile;

import graphics.Sprite;

/**
 *
 * @author ChaseStock
 */
public class SlantTile extends Tile{
    
    int slantDir;
    
    public SlantTile(Sprite sprite, int slantDir){
        super(sprite);
        this.slantDir = slantDir;
    }
    
    public SlantTile(Sprite sprite, int slantDir, boolean isSolid, boolean isBreakable){
        super(sprite, isSolid, isBreakable);
        this.slantDir = slantDir;
    }
    
    public SlantTile(Sprite sprite, int slantDir, int index, boolean isSolid, boolean isBreakable){
        super(sprite, index, isSolid, isBreakable);
        this.slantDir = slantDir;
    }
    
    public boolean collision(int xc, int yc){
        
        xc = xc % 16;
        yc = yc % 16; 
                
        if(slantDir == 0){
            if(xc + yc > 16) return true;
            else return false;
        }
        else if(slantDir == 1){
            if(xc - yc < 0) return true;
            else return false;
        }
        else if(slantDir == 2){
            if(xc + yc < 16) return true;
            else return false;
        }
        else if(slantDir == 3){
            if(xc - yc > 0) return true;
            else return false;
        }
        
        return false;
    }
}

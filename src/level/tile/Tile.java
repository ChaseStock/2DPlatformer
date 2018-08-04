/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.tile;

import graphics.Screen;
import graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author ChaseStock
 */
public class Tile {
    
    public static ArrayList<Tile> tileTypes = new ArrayList<>();
    
    public int x, y;
    public Sprite sprite;
    
    public int index;
    
    public boolean isSolid = false;
    public boolean isBreakable = false;
    
    /*
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    
    public static Tile voidTile = new VoidTile(Sprite.voidSprite, -1);
    */

    public static Tile wood = new Tile(Sprite.wood, true, false);
    public static Tile cement = new Tile(Sprite.cement, true, false);
    public static Tile wood2 = new Tile(Sprite.wood2, true, false);
    public static Tile woodBackground = new Tile(Sprite.woodBackground);
    public static Tile woodSlant0 = new SlantTile(Sprite.woodSlant0, 0, true, false);
    public static Tile woodSlant1 = new SlantTile(Sprite.woodSlant1, 1, true, false);
    public static Tile woodSlant2 = new SlantTile(Sprite.woodSlant2, 2, true, false);
    public static Tile woodSlant3 = new SlantTile(Sprite.woodSlant3, 3, true, false);
    
    public static Tile voidTile = new Tile(Sprite.voidSprite, -1, true, false);
    
    
    public Tile(Sprite sprite){
        this.sprite = sprite;
        index = tileTypes.size();
        
        tileTypes.add(this);
    }
    
    public Tile(Sprite sprite, boolean isSolid, boolean isBreakable){
        this.sprite = sprite;
        index = tileTypes.size();
        
        this.isSolid = isSolid;
        this.isBreakable = isBreakable;
        
        tileTypes.add(this);
    }
    
    public Tile(Sprite sprite, int index, boolean isSolid, boolean isBreakable){
        this.sprite = sprite;
        this.index = index;
        
        tileTypes.add(this);
    }
    
    public boolean collision(int xc, int yc){
        if(x < xc && xc < x + 16){
            if(y < yc && yc < y + 16){
                return true;
            }
        }
        return false;
    }
    
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this);
    }
    
    public boolean solid(){
        return isSolid;
    }
    
}

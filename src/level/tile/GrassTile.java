/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.tile;

import graphics.Screen;
import graphics.Sprite;

/**
 *
 * @author ChaseStock
 */
public class GrassTile extends Tile{
    
    public GrassTile(Sprite sprite){
        super(sprite);
    }
    
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this);
    }
    
}

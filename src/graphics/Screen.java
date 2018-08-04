package graphics;

import entity.mob.Player;
import java.util.Random;
import level.tile.Tile;

/**
 *
 * @author ChaseStock
 */
public class Screen 
{
    public int width, height;
    public int[] pixels;
    
    public final int MAP_SIZE = 8*8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    
    public final int TILE_SIZE = 16;
    public int[]tiles = new int[MAP_SIZE * MAP_SIZE];
    
    public int xOffset, yOffset;
    
    private Random random = new Random();
    
    
    public Screen(int width,int height)
    {
        
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        
        for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++)
        {
            tiles[i] = random.nextInt(0xffffff);
            tiles[0] = 0;
        }
    }
    
    public void clear()
    {
        for(int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0;
        }
    }
    
    public void render(int xOffset, int yOffset)
    {
        for(int y = 0; y < height; y++)
        {   
            int yp = y + yOffset;
            if( yp < 0 || yp >= height) continue;
            for(int x = 0; x < width; x++)
            {       
                int xp = x + xOffset;

                if( xp < 0 || xp >= width) continue;
                
                //pixels[xp + yp * width] = Sprite.grass.pixels[(x&15) + (y&15) * Sprite.grass.SIZE];
            }            
        }
                
    }
    
    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){   
        if(fixed){
            xp -= xOffset;
            yp -= yOffset;
        }
        
        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                if(xa < 0 || xa >= width || ya < 0 || ya > height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }
    
    public void renderTile(int xp, int yp, Tile tile){
        
        xp -= xOffset;
        yp -= yOffset;
        
        for(int y=0; y < tile.sprite.SIZE; y++){
            int ya = y + yp;
            for(int x = 0; x < tile.sprite.SIZE; x++){
                int xa = x + xp;
                if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
    
    public void renderPlayer(int xp, int yp, Sprite sprite, int flip){
        xp -= xOffset;
        yp -= yOffset;
        
        for(int y=0; y < 16; y++){
            int ya = y + yp;
            int ys = y;
            if(flip == 2 || flip == 3) ys = 15 - y;
            for(int x = 0; x < 16; x++){
                int xa = x + xp;
                int xs = x;
                if(flip == 1 || flip == 3) xs = 15 - x;
                if(xa < -16 || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0) xa = 0;
                
                int col = sprite.pixels[xs + ys * 16];
                
                if(col != 0xFFFF00FF){
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }
    
    public void setOffset (int xOffset, int yOffset) {
        //if(xOffset > 0 && xOffset + width < MAP_SIZE * TILE_SIZE) this.xOffset = xOffset;
        //if(yOffset > 0 && xOffset + width < MAP_SIZE * TILE_SIZE) this.yOffset = yOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
}

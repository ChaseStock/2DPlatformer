
package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import level.tile.Tile;

public class SpawnLevel extends Level {
    
    private int[] tiles;
    
    public SpawnLevel(String path, int spawnX, int spawnY){
        super(path, spawnX, spawnY);
    }
    
    protected void loadLevel(String path){
        try{
            BufferedImage image= ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e){
            System.out.println("Could not load level file.");
        }
    }
    
    protected void generateLevel(){
        
    }
}

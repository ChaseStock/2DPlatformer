
package level;

import graphics.Screen;
import level.tile.Tile;

/**
 *
 * @author ChaseStock
 */
public class Level {
    protected int width, height;
    protected int[] tilesInt;
    
    public int spawnX;
    public int spawnY;
    private double gravity = .2;
    
    public Level (int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int [width * height];
        generateLevel();
    }
    
    public Level (String path, int spawnX, int spawnY){
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        loadLevel(path);
    }
    
    
    protected void generateLevel(){
    }
    
    protected void loadLevel(String path) {
        
    }
    
    public int getWidth(){
        return width * 16; //PLEASE make tile size variable
    }
    
    public int getHeight(){
        return height * 16; //PLEASE make tile size variable
    }
    
    public double getGravity(){
        return gravity;
    }
        
    public void update(){
        
    }
    
    private void time(){
        
    }
    
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;
        
        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x,y).render(x,y,screen);
            }
        }
    }
    
    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >=height) return Tile.voidTile;
        
        //if(tilesInt[x + y * width] == 0) return Tile.grass;
        //if(tilesInt[x + y * width] == 1) return Tile.flower;
        //if(tilesInt[x + y * width] == 2) return Tile.rock;
        
        
        return Tile.voidTile; //BAD IDEA
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ChaseStock
 */
public class SpriteSheet {
    
    private String path;
    public final int SIZE;
    public final int WIDTH, HEIGHT;
    public int[] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet("res/textures/spritesheet2.png", 256);
    public static SpriteSheet chase = new SpriteSheet("res/textures/chasespritesheet.png", 256);
    
    //public static SpriteSheet player = new SpriteSheet("res/textures/chasespritesheet.png", );
    
    public SpriteSheet(String path, int size)
    {          
        this.path = path;
        SIZE = size;
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }
    
    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize){
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        WIDTH = w;
        HEIGHT = h;
        
        if(width == height) SIZE = width;
        else SIZE = -1;
        
        pixels = new int[w * h];
        
        for(int y0 = 0; y0 < h; y0++){
            int yp = yy + y0;
            for(int x0 = 0; x0 < w; x0++){
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }
    }
    
    private void load()
    {
        try
        {
            BufferedImage image = ImageIO.read(new File(path));
            //BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
            System.out.println("Sprite Sheet Loaded: " + path);
        } 
        catch(IOException e)
        {
            System.out.println("Sprite Sheet Not Found " + path);
            e.printStackTrace();
        }
        
    }
    
}

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
public class Sprite 
{
    public final int SIZE;
    private int x, y;
    private int width, height;
    public int[] pixels;
    private SpriteSheet sheet;
    private SpriteSheet playerSheet;
    
    //Tiles
    public static Sprite wood = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite cement = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite wood2 = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite woodBackground = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite woodSlant0 = new Sprite(16, 4, 0, SpriteSheet.tiles);
    public static Sprite woodSlant1 = new Sprite(16, 5, 0, SpriteSheet.tiles);
    public static Sprite woodSlant2 = new Sprite(16, 6, 0, SpriteSheet.tiles);
    public static Sprite woodSlant3 = new Sprite(16, 7, 0, SpriteSheet.tiles);
    
    public static Sprite voidSprite = new Sprite(16, 10000000);    
    
    //Player
    public static Sprite player_forward = new Sprite(16, 5, 0, SpriteSheet.chase);
    public static Sprite player_forward_1 = new Sprite(16, 6, 0, SpriteSheet.chase);
    public static Sprite player_forward_2 = new Sprite(16, 7, 0, SpriteSheet.chase);
    public static Sprite[] player_forward_anim = {player_forward, player_forward_1, player_forward, player_forward_2};
       
    public static Sprite player_side = new Sprite(16, 3, 0, SpriteSheet.chase);
    public static Sprite player_side_1 = new Sprite(16, 4, 0, SpriteSheet.chase);
    public static Sprite[] player_side_anim = {player_side, player_side_1};
    
    //Right
    public static Sprite player_right = new Sprite(16, 0, 1, SpriteSheet.chase);
    public static Sprite player_right_1 = new Sprite(16, 1, 1, SpriteSheet.chase);
    public static Sprite player_right_2 = new Sprite(16, 2, 1, SpriteSheet.chase);
    public static Sprite[] player_right_anim = {player_right, player_right_1, player_right, player_right_2};
    
    //Left
    public static Sprite player_left = new Sprite(16, 3, 1, SpriteSheet.chase);
    public static Sprite player_left_1 = new Sprite(16, 4, 1, SpriteSheet.chase);
    public static Sprite player_left_2 = new Sprite(16, 5, 1, SpriteSheet.chase);
    public static Sprite[] player_left_anim = {player_left, player_left_1, player_left, player_left_2};
    
    public static Sprite player_back = new Sprite(16, 0, 0, SpriteSheet.chase);
    public static Sprite player_back_1 = new Sprite(16, 1, 0, SpriteSheet.chase);
    public static Sprite player_back_2 = new Sprite(16, 2, 0, SpriteSheet.chase);
    public static Sprite[] player_back_anim = {player_back, player_back_1, player_back, player_back_2};

    
    protected Sprite(SpriteSheet sheet, int width, int height){
        SIZE = (width == height) ? width : -1;         
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }
    
    public Sprite(int size, int x, int y, SpriteSheet sheet)
    {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    public Sprite(int width, int height, int color){
        SIZE = -1;
        this.width = width;
        this.height = height;
        setColor(color);
    }
    
    public Sprite(int size, int color){
        SIZE = size;
        this.width = width;
        this.height = height;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }
    
    private void setColor(int color){
        for(int i = 0; i < width * height; i++){
            pixels[i] = color;
        }
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    private void load()
    {
        for(int y = 0; y < SIZE; y++)
        {
            for(int x = 0; x < SIZE; x++)
            {
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}

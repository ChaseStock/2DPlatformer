/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import graphics.Screen;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import level.tile.Tile;
import mapReader.Map;

/**
 *
 * @author ChaseStock
 */
public class MappedLevel extends Level{
    
    public Tile[] tileArray; //Don't do this...
    
    private String mapPath;
    private File mapFile;
    
    
    public MappedLevel(String path, int spawnX, int spawnY){
        super(path, spawnX, spawnY);
        mapPath = path;
        mapFile = new File(path);
        loadLevel();
    }
    
    private void loadLevel(){
        int[] tileInt = readLevel();

        tileArray = new Tile[width * height];
        
        for(int i = 0; i < tileInt.length; i++){
            for(Tile tile : Tile.tileTypes){
                if(tile.index == tileInt[i]){
                    tileArray[i] = tile;
                    tileArray[i].x = i % width;
                    tileArray[i].y = i / width;
                    //System.out.println("X: " + tileArray[i].x + " Y: " + tileArray[i].y);
                }
            }
        }       
    }
    
    private int[] readLevel(){
        int[] tileInt = new int[0];
        Scanner mapReader;
        
        try{
            mapReader = new Scanner(mapFile);
            
            int index = 0;                
            
            if(mapReader.hasNextLine()){
                String toRead = mapReader.nextLine();
                if(toRead.equals("Width:")){
                    width = Integer.parseInt(mapReader.nextLine());
                }
                else{
                    System.out.println("No Width Found.");
                }
            }
            if(mapReader.hasNextLine()){
                String toRead = mapReader.nextLine();
                if(toRead.equals("Height:")){
                    height = Integer.parseInt(mapReader.nextLine());
                }
                else{
                    System.out.println("No Height Found.");
                }
            }
            
            tileInt = new int[width * height];
            
            while(mapReader.hasNextLine()){ 
                String toRead = mapReader.nextLine();
                try{
                    tileInt[index] = Integer.parseInt(toRead);
                    index++;
                }
                catch(NumberFormatException e){
                    System.out.println("Incorrect Tile Index Format");
                }               
            }
            
            System.out.println("Map Loaded: " + mapPath);
            mapReader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Map file not found.");
        }
        
        return tileInt;
    }
    
    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >=height) return Tile.voidTile;
        
        return tileArray[x + y * width];
    }
    
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;
        
        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                //getTile(x,y).render(x,y,screen);
                if (x < 0 || y < 0 || x >= width || y >= height) Tile.voidTile.render(x, y, screen);
                else {getTile(x,y).render(x, y, screen);}
            }
        }
    }
}

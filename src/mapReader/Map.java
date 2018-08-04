package mapReader;

import java.util.ArrayList;
import level.tile.Tile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ChaseStock
 */
public class Map {
    
    int pixelWidth;
    int pixelHeight;
    public int mapTileWidth;
    public int mapTileHeight;
    
    int pixels[];
    public Tile tileArray[];
    
    int size;
    int tileSize;
    int tileAmount;
    
    public Map(int width, int height, int tileSize, int[]pixels){
        this.pixelWidth = width;
        this.pixelHeight = height;
        this.tileSize = tileSize;
        this.pixels = pixels;
        
        tileAmount = (width / tileSize) * (height / tileSize);
        mapTileWidth = pixelWidth/tileSize;
        mapTileHeight = pixelHeight/tileSize;
        
        tileArray = readTiles();
    }
    
    public Tile[] readTiles(){     //Needs each tile to have an index

        tileArray = new Tile[mapTileWidth * mapTileHeight];
                
        //Scan each tile of the map
        for(int yTile = 0; yTile < mapTileHeight ; yTile++){
            
            for(int xTile = 0; xTile < mapTileWidth ; xTile++){
            
                int currentTile[] = new int[tileSize * tileSize];
                
                //Scan each pixel of a tile
                for(int y = 0; y < tileSize; y++){
                    
                    for(int x = 0; x < tileSize; x++){
                        currentTile[x+y*tileSize] = pixels[(x + xTile*tileSize) + (yTile * pixelWidth * tileSize) + (y * pixelWidth)];
                        // ^ likely to be wrong
                    }
                }
                
                Tile tileMatch;
                tileMatch = matchToKnownTile(currentTile);
                
                if(tileMatch != null){
                    tileArray[xTile + yTile * mapTileWidth] = tileMatch;
                    System.out.println("Detected sprite with index: " + tileMatch.index + " at tile [" + xTile + ", " + yTile + "].");                    
                }
                else{
                    tileArray[xTile + yTile * mapTileWidth] = null;
                    System.out.println("No sprite match found at tile: [" + xTile + ", " + yTile + "]. Sprite set to Null");
                }
            }
        } 
        
        System.out.println("Map Read Complete.");
        
        if(tileArray.length == 0){
            return null;
        }
        else{
            return tileArray;
        }        
    }
    
    public Tile matchToKnownTile(int[] newTile){
        for(Tile tile : Tile.tileTypes){
                    
            int pixelDifference = 0;

            for(int pixel = 0; pixel < tile.sprite.pixels.length; pixel++){
                if(tile.sprite.pixels[pixel] != newTile[pixel]){
                    pixelDifference++;                    
                }
            }
            
            if(pixelDifference == 0){
                return tile;
            }
        }        
        return null;
    }
}

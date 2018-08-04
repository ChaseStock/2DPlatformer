package mapReader;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import level.tile.Tile;


public class MapReader {
    
    public static ArrayList<Map> maps = new ArrayList<>();

    public static void main(String[] args) {

        maps.add(createMap("res/textures/PinkTieStage1.png", 16));
        writeMapFile(maps.get(0), "level0");
    }
    
    public static Map createMap(String path, int tileSize){
        
        int[]pixels;
        
        try
        {
            BufferedImage image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();
            pixels = new int[w * h];
            image.getRGB(0, 0, w, h, pixels, 0, w);
            System.out.println("Map Loaded: " + path);
            
            return new Map(w, h, tileSize, pixels);
        } 
        catch(IOException e)
        {
            System.out.println("Map Not Found " + path);
            e.printStackTrace();
            return null;
        }
    }
    
    private static void writeMapFile(Map newMap, String mapName){
        File mapFile = new File("res/maps/" + mapName);
        PrintWriter mapWriter;
        
        try{
            mapWriter = new PrintWriter(mapFile);
            
            //Write Width / Height
            mapWriter.println("Width:");
            mapWriter.println(newMap.mapTileWidth);
            mapWriter.println("Height:");
            mapWriter.println(newMap.mapTileHeight);
            
            //Write Tiles
            for(int i = 0; i < newMap.tileArray.length; i++){
                mapWriter.println(newMap.tileArray[i].index);
            }
            System.out.println("Map Load Complete.");
            mapWriter.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("No file found to write to.");
        }           
    }   
}

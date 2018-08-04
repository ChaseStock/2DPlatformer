
import entity.mob.Player;
import gameUtility.GameCamera;
import input.Keyboard;
import graphics.Screen;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import level.Level;
import level.MappedLevel;
import level.RandomLevel;

/**
 *
 * @author ChaseStock
 */
public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 16 * 13;
    public static final int HEIGHT = (WIDTH / 4) * 3;
    public static final int SCALE = 4;
    public static final String NAME = "Project: Pink Tie";
    
    public boolean running = false;
    public int tickCount = 0;
    
    private Thread thread;
    private JFrame frame;
    private Screen screen;
    private Keyboard key;
    private MappedLevel level;
    private Player player;    
    private GameCamera camera;
    
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    Game() 
    {
        screen = new Screen(WIDTH, HEIGHT);
        frame = new JFrame();
        key = new Keyboard();
        level = new MappedLevel("res/maps/level0", 8 * 16, 4 * 16);
        player = new Player(level.spawnX, level.spawnY, key);
        player.init(level);
        
        camera = new GameCamera(screen, level, player, true); 
        camera.isLockedY(true);
        camera.setY(level.getHeight() - screen.height);
        
        Dimension size = new Dimension(WIDTH * SCALE,HEIGHT * SCALE);
        
        frame.setPreferredSize(size);
        frame.setTitle("Game Test");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
                
        frame.addKeyListener(key);
        
        screen = new Screen(WIDTH, HEIGHT);        
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        running = true;
        thread.start();
    }
    
    public synchronized void stop()
    {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nsPerTick = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        
        int ticks = 0;
        int frames = 0;        
                
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            
            while(delta >= 1)
            {
                tick();
                ticks++;
                delta--;
            }
            
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("Ticks: " + ticks + ", Frames: " + frames);
                ticks = 0;
                frames = 0;
            }
            
        }
    }
        
    public void tick(){
        key.update();
        player.update();
        camera.update();
    }
    
    
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        //screen.render(xOffset,yOffset);
        int xScroll =  player.getX() - screen.width / 2;
        int yScroll = player.getY() - screen.height / 2;
        //level.render(xScroll,yScroll,screen);
        level.render(camera.x,camera.y,screen);
        
        player.render(screen);
        
        for(int i = 0; i<pixels.length;i++)
        {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
        g.dispose();
        bs.show();
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        new Game().start();
    }
}
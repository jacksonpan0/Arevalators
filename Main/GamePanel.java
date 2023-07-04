package Main;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Entity.Player;
import Main.tileC.tileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GamePanel extends JPanel implements Runnable { 
    //Values for size of tile within screen and determine scale of character and other objects
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    //World Setting Parameters
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //Start boolean
    public static boolean start;
    //FPS value
    int FPS = 60;
    //Instantiante Key Handler
    KeyReader keyRead = new KeyReader();
    //Instantiate game thread
    Thread gameThread;
    //Create new Player object
    public Player newPlayer = new Player(this, keyRead);
    //Tile Manager
    tileManager tileM = new tileManager(this);
    //Start screen JPanel
    public static JPanel startScreen = new JPanel();
    //Create game panel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyRead);
        this.setFocusable(true);
    }
    //Method to start to game thread, let program know game has started
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    //Method used in .start() to start the game thread in above method.
    //Basically method is used to update game information and draw screen to reflect those updates 
    public void run() {
        //Game loop variables to ensure updates are restricted and visible
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        while(gameThread != null) {
            //Accumulator method used to update game loop. The methods adds current time minus previous draw time (time pasted per interval) 
            // and divides by draw interval. When delta reaches the 1 threshold, delta is reset and update() and repaint() is called.
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;
            if(delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    //Update method used to change player location
    public void update() {
        newPlayer.update();
    }
    //Paint component used to draw objects in game panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        newPlayer.draw(g2);
        g2.dispose();
    }
    public void startGame(JFrame window) {
        window.setLayout(new GridBagLayout());
        startScreen.setPreferredSize(new Dimension(768, 576));
        JLabel title = new JLabel("Arevalators");
        title.setFont(new Font("Gothic", 1, 20));
        startScreen.add(title);
        window.add(startScreen, new GridBagConstraints());
        window.setPreferredSize(new Dimension(768, 576));
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

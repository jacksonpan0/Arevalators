package Main.tileC;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;
public class tileManager {
    GamePanel gp;
    tileClass[] tile;
    int mapTileNum [][];

    public tileManager(GamePanel gp) {
        this.gp = gp;
        tile = new tileClass[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        File f1 = new File("./Main/tiles/grass.png");
        File f2 = new File("./Main/tiles/wall.png");
        File f3 = new File("./Main/tiles/water.png");
        try {
            tile[0] = new tileClass();
            tile[0].image = ImageIO.read(f1);

            tile[1] = new tileClass();
            tile[1].image = ImageIO.read(f2);

            tile[2] = new tileClass();
            tile[2].image = ImageIO.read(f3);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void loadMap() {
        File mapFile = new File("./Main/map/map01.txt");
        try {
            InputStream is = new FileInputStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0; 
        int y = 0;

         while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
         }
    }
}

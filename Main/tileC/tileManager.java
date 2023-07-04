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
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
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

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
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
        int worldCol = 0;
        int worldRow = 0;

         while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.newPlayer.worldX + gp.newPlayer.screenX;
            int screenY = worldY - gp.newPlayer.worldY + gp.newPlayer.screenY;

            if(worldX + gp.tileSize > gp.newPlayer.worldX - gp.newPlayer.screenX && 
               worldX - gp.tileSize < gp.newPlayer.worldX + gp.newPlayer.screenX &&
               worldY + gp.tileSize > gp.newPlayer.worldY - gp.newPlayer.screenY && 
               worldY - gp.tileSize < gp.newPlayer.worldY + gp.newPlayer.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
         }
    }
}

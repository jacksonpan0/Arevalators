package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyReader;

public class Player extends Entity {
    GamePanel gp;
    KeyReader keyRead;

    public Player(GamePanel gp, KeyReader keyRead) {
        this.gp = gp;
        this.keyRead = keyRead;
        setDefValues();
        getPlayerImage();
    }

    public void setDefValues() {
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {
        File f1 = new File("./Main/player/boy_up_1.png");
        File f2 = new File("./Main/player/boy_up_2.png");
        File f3 = new File("./Main/player/boy_down_1.png");
        File f4 = new File("./Main/player/boy_down_2.png");
        File f5 = new File("./Main/player/boy_left_1.png");
        File f6 = new File("./Main/player/boy_left_2.png");
        File f7 = new File("./Main/player/boy_right_1.png");
        File f8 = new File("./Main/player/boy_right_2.png");
        try {
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyRead.upBool == true || keyRead.leftBool == true || keyRead.downBool == true || keyRead.rightBool == true) {
            if(keyRead.upBool == true) {
                direction = "up";
                y -= speed;
            }
            else if(keyRead.leftBool == true) {
                direction = "left";
                x -= speed;
            }
            else if(keyRead.downBool == true) {
                direction = "down";
                y += speed;
            }
            else if(keyRead.rightBool == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > 17) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction) {
            case "up":
            if(spriteNum == 1) {
                image = up1;
            }
            if(spriteNum == 2) {
                image = up2;
            }
                break;
            case "left":
            if(spriteNum == 1) {
                image = left1;
            }
            if(spriteNum == 2) {
                image = left2;
            }
                break;
            case "down":
            if(spriteNum == 1) {
                image = down1;
            }
            if(spriteNum == 2) {
                image = down2;
            }
                break;
            case "right":
            if(spriteNum == 1) {
                image = right1;
            }
            if(spriteNum == 2) {
                image = right2;
            }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

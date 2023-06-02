package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Main.KeyReader;

public class Player extends Entity {
    GamePanel gp;
    KeyReader keyRead;

    public Player(GamePanel gp, KeyReader keyRead) {
        this.gp = gp;
        this.keyRead = keyRead;
        setDefValues();
    }

    public void setDefValues() {
        x = 100;
        y = 100;
        speed = 5;
    }

    public void update() {
        if(keyRead.upBool == true) {
            y -= speed;
        }
        else if(keyRead.leftBool) {
            x -= speed;
        }
        else if(keyRead.downBool == true) {
            y += speed;
        }
        else if(keyRead.rightBool == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}

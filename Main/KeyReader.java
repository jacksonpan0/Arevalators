package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyReader implements KeyListener {
    public boolean upBool;
    public boolean leftBool;
    public boolean downBool;
    public boolean rightBool;
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upBool = true;
        }
        if(code == KeyEvent.VK_A) {
            leftBool = true;
        }
        if(code == KeyEvent.VK_S) {
            downBool = true;
        }
        if(code == KeyEvent.VK_D) {
            rightBool = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upBool = false;
        }
        if(code == KeyEvent.VK_A) {
            leftBool = false;
        }
        if(code == KeyEvent.VK_S) {
            downBool = false;
        }
        if(code == KeyEvent.VK_D) {
            rightBool = false;
        }
    }
}

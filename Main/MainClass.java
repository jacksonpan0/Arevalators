package Main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
public class MainClass {
    //Sound component method
    public void sound() {
        try {
            AudioInputStream gameMusic = AudioSystem.getAudioInputStream(this.getClass().getResource("ProjectMusic.wav"));
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(gameMusic);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Main Method
    public static void main(String[] args) {
        //Create window for game panel
        JFrame window = new JFrame();
        //Window properties
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Arevalators");
        //Initiate game panel and put it into game window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //Start screen
        gamePanel.startGame(window);
        //Start game thread
        gamePanel.startGameThread();
        MainClass newGame = new MainClass();
        //Sound method
        newGame.sound();
    }
}
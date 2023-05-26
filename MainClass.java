import javax.swing.JFrame;
public class MainClass {
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
        //Start game thread
        gamePanel.startGameThread();
    }
}
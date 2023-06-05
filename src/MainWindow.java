import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    private Image icon;
    private MainPanel mainPanel=new MainPanel();
    public MainWindow(){
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(mainPanel);
        setImages();
        this.setIconImage(this.icon);

        this.setVisible(true);
    }
    public void setImages(){
        try {
            this.icon = ImageIO.read(new File("src/Pictures/ayrdIcon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

//src/Pictures/TelegramBackground.jpg

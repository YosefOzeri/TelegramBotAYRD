import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainPanel extends JPanel {
    private BufferedImage background;
    private BufferedImage ayrdIcon;
    private JLabel title;
    private JCheckBox first;
    private JCheckBox second=new JCheckBox();
    private JCheckBox third=new JCheckBox();
    private JCheckBox forth=new JCheckBox();
    private JCheckBox fifth=new JCheckBox();
    private List<JCheckBox> checkBoxes;
    private boolean canChooseMore=true;
    private JLabel moreThen3Error=new JLabel("Error! you chose to much options");
public MainPanel(){
    this.setBounds(Constants.STARTING_X_PANEL_POSITION,Constants.STARTING_Y_PANEL_POSITION, Constants.WIDTH, Constants.HEIGHT);
    addTitle();
    addFirstCheckBox();
    addSecondCheckBox();
    addThirdCheckBox();
    addForthCheckBox();
    addFifthCheckBox();
    mergeJCheckBox(this.first,this.second,this.third,this.forth,this.fifth);
    checkOptions();


    this.setLayout(null);
    this.setVisible(true);
    readImages();

}

    public void addTitle(){
        this.title=new JLabel("Please choose just-3 API you want:");
        Font font=this.title.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        this.title.setFont(font.deriveFont(attributes));
       // this.title.setFont(font);
        //new Font("Serif",Font.BOLD, 12)
        this.title.setForeground(Color.white);
        this.title.setBounds(Constants.STARTING_X_PANEL_POSITION+20,Constants.STARTING_Y_PANEL_POSITION+20, Constants.CHECK_BOX_WIDTH*2,Constants.CHECK_BOX_HEIGHT);
        this.add(this.title);
    }
    public void addFirstCheckBox(){
        this.first=new JCheckBox("First API");
        this.first.setFont(new Font("arial", Font.BOLD, 18));
        this.first.setForeground(Color.white);
        this.first.setBounds(this.title.getX(),this.title.getY()+this.title.getHeight(), Constants.CHECK_BOX_WIDTH,Constants.CHECK_BOX_HEIGHT);
        this.first.setBorderPainted(false);
        this.first.setContentAreaFilled(false);
        this.add(this.first);
    }
    public void addSecondCheckBox(){
        this.second=new JCheckBox("Second API");
        this.second.setFont(new Font("arial", Font.BOLD, 18));
        this.second.setForeground(Color.green);
        this.second.setBounds(this.first.getX(),this.first.getY()+this.first.getHeight(),Constants.CHECK_BOX_WIDTH,Constants.CHECK_BOX_HEIGHT);
        this.second.setBorderPainted(false);
        this.second.setContentAreaFilled(false);
        this.add(this.second);
    }
    public void addThirdCheckBox(){
        this.third=new JCheckBox("Third API");
        this.third.setFont(new Font("arial", Font.BOLD, 18));
        this.third.setForeground(Color.orange);
        this.third.setBounds(this.second.getX(),this.second.getY()+this.second.getHeight(), Constants.CHECK_BOX_WIDTH,Constants.CHECK_BOX_HEIGHT);
        this.third.setBorderPainted(false);
        this.third.setContentAreaFilled(false);
        this.add(this.third);
    }
    public void addForthCheckBox(){
        this.forth=new JCheckBox("Forth API");
        this.forth.setFont(new Font("arial", Font.BOLD, 18));
        this.forth.setForeground(Color.red);
        this.forth.setBounds(this.third.getX(),this.third.getY()+this.third.getHeight(), Constants.CHECK_BOX_WIDTH,Constants.CHECK_BOX_HEIGHT);
        this.forth.setBorderPainted(false);
        this.forth.setContentAreaFilled(false);
        this.add(this.forth);
    }
    public void addFifthCheckBox(){
        this.fifth=new JCheckBox("Fifth API");
        this.fifth.setFont(new Font("arial", Font.BOLD, 18));
        this.fifth.setForeground(Color.CYAN);
        this.fifth.setBounds(this.forth.getX(),this.forth.getY()+this.forth.getHeight(), Constants.CHECK_BOX_WIDTH,Constants.CHECK_BOX_HEIGHT);
        this.fifth.setBorderPainted(false);
        this.fifth.setContentAreaFilled(false);
        this.add(this.fifth);
    }


    public void readImages() {
        try {
            this.background = ImageIO.read(new File("src/Pictures/TelegramBackground.jpg"));
            this.ayrdIcon = ImageIO.read(new File("src/Pictures/ayrdIcon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(this.background,0,0,Constants.WIDTH,Constants.HEIGHT,null);
        graphics.drawImage(this.ayrdIcon,0,Constants.HEIGHT-150,100,100,null);
    }



    private boolean withinTheCapacity(List<JCheckBox> apiOptions){
    boolean isValid=false;
    int countSelections=0;
    if (apiOptions!=null){
        for (int i=0;i<apiOptions.size();i++){
            if (apiOptions.get(i).isSelected()){
                countSelections++;
            }
        }
    }
    return countSelections>=3;
    }

    public void checkOptions(){
    new Thread(()->{
        while (true){
          this.canChooseMore=withinTheCapacity(this.checkBoxes);
          if (!this.canChooseMore){
              JOptionPane jOptionPane=new JOptionPane(this.moreThen3Error,JOptionPane.NO_OPTION);
              this.add(jOptionPane);
          }
        }
    }).start();
    }

    private void mergeJCheckBox(JCheckBox first,JCheckBox second,JCheckBox third,JCheckBox forth,JCheckBox fifth){
    this.checkBoxes= Arrays.asList(first,second,third,forth,fifth);
    }
}

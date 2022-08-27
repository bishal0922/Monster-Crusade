import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(){
        ImageIcon newImage = new ImageIcon("src/iconFortnite.jpg");

        this.setIcon(newImage); //add image
        this.setVerticalAlignment(JLabel.TOP); //position of icon+text
        this.setHorizontalAlignment(JLabel.CENTER); //position of icon+text

        this.setText("This is just a label");//set text
        this.setHorizontalTextPosition(JLabel.CENTER); //the text will be on top of the
        this.setVerticalTextPosition(JLabel.TOP); //making the text be on top of the image
        this.setForeground(new Color(0x000FFF)); //set color of text
        this.setFont(new Font("MV Boli", Font.BOLD,25)); //set the font of the text
        this.setIconTextGap(5); //distance between the text and the icon

        //we can see that the label takes up as much space is possible.
        this.setBackground(new Color(0xFFFDD0)); //background to yellow
        this.setOpaque(true);

        //creating and setting the border
        Border border = BorderFactory.createLineBorder(Color.blue,15);
        this.setBorder(border);
        this.setSize(400,400);
        // this.setBounds(250,0,250,250); //set x,y position within frame as well as dimensions

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class defaultPanel extends JPanel {
    //SECTION 2 -
    JButton newChar;
    JButton editChar;
    JButton saveChar;
    JButton loadChar;
    public defaultPanel(){
        this.setBackground(new Color(0xc9aa88));
        this.setBounds(0,0,800,800);
        this.setLayout(null);

        newChar = new JButton("New Character");
        newChar.setFont(new Font("Sans", Font.BOLD,15));
        newChar.setBounds(200,155+200,200,50);
        newChar.addActionListener(this::actionPerformed);

        editChar = new JButton("Edit Character");
        editChar.setFont(new Font("Sans", Font.BOLD,15));
        editChar.setBounds(200,210+200,200,50);
        editChar.addActionListener(this::actionPerformed);

        saveChar = new JButton("Save Character");
        saveChar.setFont(new Font("Sans", Font.BOLD,15));
        saveChar.setBounds(200,265+200,200,50);
        saveChar.addActionListener(this::actionPerformed);

        loadChar = new JButton("Load Character");
        loadChar.setFont(new Font("Sans", Font.BOLD,15));
        loadChar.setBounds(200,265+55+200,200,50);
        loadChar.addActionListener(this::actionPerformed);

        this.setBackground(Color.green);

        this.setBackground(Color.green);
        //this.add(switcher);
        this.add(newChar);
        this.add(editChar);
        this.add(saveChar);
        this.add(loadChar);
        this.setLayout(null);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==newChar){
           /* this.setVisible(false);
            if (current ==0) {
                this.add(editing);
                this.remove(initial);
                //layered pane??
                current=1;
                System.out.println(current);
            }

            this.setVisible(true);*/
            System.out.println("clicked new char");

        }
        if (e.getSource()==editChar){

            System.out.println("clicked edit char");

        }
        if (e.getSource()==saveChar){
            //need to create a current player and save it as a csv file
            System.out.println("clicked save char");
        }
        if (e.getSource()==loadChar){
            //let them select a character saved file
            System.out.println("clicked load char");
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class IndexPanel extends JPanel {

    ArrayList<ArrayList<String>> Armory = new ArrayList<>();
    ArrayList<String> weapons= new ArrayList<>();
    ArrayList<String> dice= new ArrayList<>();
    ArrayList<String> bonus= new ArrayList<>();
    static JComboBox comboBox;
    JLabel playerNamelabel;

    JLabel imageselection;
    JLabel statsPicker;
    JLabel weaponPicker;
    static JTextField playerName;
    JTextField playerStats;
    static JTextField strfield;
    static JTextField confield;
    static JTextField dexfield;
    JButton confirm;
    JButton cancel;
    JButton selectImage;
    String name;
    File avatarpath;
    int str;
    int dex;
    int con;
    static int j=0;
    Weapon playerweapon;

    static String[] weaponlist;
    static String[] dicelist;
    static String[] bonuslist;
    Player createdPlayer = new Player();
    static String fileLocation = "null";

    public IndexPanel(){
        this.setBackground(new Color(0xc9aa88));
       this.setBounds(0,0,800,800);
       this.setLayout(null);

          playerNamelabel = new JLabel("", JLabel.LEFT);
         playerNamelabel.setText(" Name:");
         playerNamelabel.setFont(new Font("Sans", Font.BOLD, 25));
         playerNamelabel.setBounds(100,100, 375, 70);
         playerNamelabel.setForeground(Color.black);
         playerNamelabel.setBorder(BorderFactory.createLineBorder(Color.black));

        playerName = new JTextField();
        playerName.setFont(new Font("Times", Font.PLAIN, 25));
        playerName.setBounds(100,15,250,40);

        //avatar selection
        imageselection = new JLabel();
        imageselection.setBounds(100,180, 375, 70);
        imageselection.setBorder(BorderFactory.createLineBorder(Color.black));
        selectImage = new JButton("Select Avatar");
        selectImage.setBounds(90,8,200,50);
        selectImage.addActionListener(this::actionPerformed);

        //stats selection
        statsPicker = new JLabel();

        //STR
        JLabel str = new JLabel(" Enter Str:");
        str.setBounds(0,8,120,50);
        strfield = new JTextField();
        strfield.setFont(new Font("Times", Font.PLAIN, 25));
        strfield.setBounds(65,8,50,35);
        str.setBorder(BorderFactory.createLineBorder(Color.black));

        //DEX
        JLabel dex = new JLabel(" Enter Dex:");
        dex.setBounds(130,8,120,50);
        dexfield = new JTextField();
        dexfield.setFont(new Font("Times", Font.PLAIN, 25));
        dexfield.setBounds(65,8,50,35);
        dex.setBorder(BorderFactory.createLineBorder(Color.black));

        //con
        JLabel con = new JLabel(" Enter Con:");
        con.setBounds(135+120,8,120,50);
        confield = new JTextField();
        confield.setFont(new Font("Times", Font.PLAIN, 25));
        confield.setBounds(65,8,50,35);
        con.setBorder(BorderFactory.createLineBorder(Color.black));

        statsPicker.setBounds(100,260, 375, 70);
        statsPicker.setBorder(BorderFactory.createLineBorder(Color.black));

        //Weapon selection
        weaponPicker = new JLabel("Select your Weapon: ");
        weaponPicker.setFont(new Font("Sans", Font.BOLD, 25));
        weaponPicker.setBounds(100,340, 375, 70);
        weaponPicker.setBorder(BorderFactory.createLineBorder(Color.black));


        //**************************************************
        //*************************************************

        String file_line = "";

        Armory.add(weapons);
        Armory.add(dice);
        Armory.add(bonus);

        //READING THE SCREEN
        String path = "C:\\Users\\Owner\\Desktop\\Bishal\\College\\Summer 2022\\CSE 1325\\csvScanner\\src\\weapons.csv";
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));

            while ((file_line = in.readLine()) != null){
                String[] weapon = file_line.split(",");    // use comma as separator
                weapons.add(weapon[0]);
                dice.add(weapon[1]);
                bonus.add(weapon[2]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         weaponlist = new String[weapons.size()];
        for (int i=0; i<weapons.size();i++){
            weaponlist[i]=weapons.get(i);
        }

       // System.out.println(weaponlist[0]);
         comboBox = new JComboBox(weaponlist);
         comboBox.setFont(new Font("Sanskrit", Font.BOLD, 15));
         comboBox.setEditable(true);
         comboBox.setBounds(265,8,105,55);
         comboBox.addActionListener(this::actionPerformed);


        confirm = new JButton("Confirm");
        confirm.setBounds(275,600,150,50);
        confirm.addActionListener(this::actionPerformed);

        cancel = new JButton("Cancel");
        cancel.setBounds(430,600,150,50);
        cancel.addActionListener(this::actionPerformed);


    playerNamelabel.add(playerName);
    imageselection.add(selectImage);

    str.add(strfield);
    statsPicker.add(str);

    dex.add(dexfield);
    statsPicker.add(dex);

    con.add(confield);
    statsPicker.add(con);

        weaponPicker.add(comboBox);


        this.add(playerNamelabel);
        this.add(imageselection);
        this.add(statsPicker);
        this.add(weaponPicker);
      //  this.add(confirm);
       // this.add(cancel);

    }


    public void actionPerformed(ActionEvent e){
        if (e.getSource()==confirm){
            str = Integer.parseInt(strfield.getText());
            dex = Integer.parseInt(dexfield.getText());
            con = Integer.parseInt(confield.getText());


            if (str+con+dex > 15){
                JOptionPane.showMessageDialog(this, "STR, DEX & CON add upto over 15", "Stats Error", JOptionPane.ERROR_MESSAGE);
            } else {
                 j = comboBox.getSelectedIndex();
                name = playerName.getText();
              //  System.out.println(name + fileLocation);

                dicelist = new String[dice.size()];
                for (int i=0; i<dice.size();i++){
                    dicelist[i]=dice.get(i);
                }
                bonuslist = new String[bonus.size()];
                for (int i=0; i<bonus.size();i++){
                    bonuslist[i]=bonus.get(i);
                }

               // createdPlayer = new Player(name,fileLocation,50,15,str, dex,con,new Weapon(weaponlist[j], dicelist[j], bonuslist[j]));
                createdPlayer.setName(name);
                createdPlayer.setHp(50);
                createdPlayer.setAc(15);
                createdPlayer.setStr(str);
                createdPlayer.setDex(dex);
                createdPlayer.setCon(con);
                createdPlayer.setWeapon(new Weapon(weaponlist[j], dicelist[j], bonuslist[j]));
                System.out.println(createdPlayer);
            }

            try {
                PrintWriter writer = new PrintWriter(new FileWriter("current/"+createdPlayer.getName() + ".log", false));
                String csvLine = String.join(",", createdPlayer.getName(),
                        String.valueOf(createdPlayer.getHp()),
                        String.valueOf( createdPlayer.getAc()),
                        String.valueOf(createdPlayer.getStr()),
                        String.valueOf(createdPlayer.getDex()),
                        String.valueOf(createdPlayer.getCon()),
                        createdPlayer.getWeapon().getWeapon_name(),
                        createdPlayer.getWeapon().getDice_type(),
                        String.valueOf(createdPlayer.getWeapon().getBonus_num()),
                        createdPlayer.getAvatar() +"\n");
                writer.write(csvLine);
                System.out.println("Player has been saved!\n");
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            this.remove(MyFrame.editing);
            MyFrame.editing.setVisible(false);
            this.add(MyFrame.initial);
            this.removeAll();
            MyFrame.initial.setVisible(true);

        }
        if (e.getSource()==selectImage){
            JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./img"));
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            avatarpath = new File(fileChooser.getSelectedFile().getAbsolutePath());
            fileLocation=avatarpath.getName();
            createdPlayer.setAvatar(fileLocation);
            System.out.println("The chosen file from JfileChooser->" +fileLocation);
        }

        }

        if (e.getSource()==comboBox){
            //System.out.println("Chosen index on the Combo Box is-> "+ comboBox.getSelectedIndex()); //returns the index of what we selected;
        }

    }
}

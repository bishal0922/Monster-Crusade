import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener{

    public static int current=0;
    public static int hasbeencreated =0;
    static JPanel initial = new JPanel();
    static JPanel editing = new IndexPanel();
    static JPanel image = new JPanel();

    static JPanel guicontent;
    static JPanel gameWindow = new MainPanel();

    //SECTION 1
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem newMenuItem;
    JMenuItem loadMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem exitMenuItem;

    //SECTION 2 - MAIN OPTIONS
    JButton startGame;
    JButton newChar;
    JButton editChar;
    JButton saveChar;
    JButton loadChar;
    JButton cancel;
    JButton confirm;

    //ArrayLists to store weapons, dice and bonus

    ArrayList<ArrayList<String>> Armory = new ArrayList<>();
    ArrayList<String> weapons= new ArrayList<>();
    ArrayList<String> dice= new ArrayList<>();
    ArrayList<String> bonus= new ArrayList<>();

    //The intial screen Character Label picture
    JLabel picLabel;

    JButton selectImage;
    String name;

    int str;
    int dex;
    int con;

    String[] weaponlist;
    String[] dicelist;
    String[] bonuslist;
    Player createdPlayer = new Player();
    static ArrayList<Player> myPlayers = new ArrayList<>();
    public MyFrame() {


        //===============================================================================
        // Initially reading the weapons.csv file and parsing them into
        //            "weaponlist,dicelist,bonuslist"
        //===============================================================================

        String file_line="";
        String path = "weapons.csv";
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
        dicelist = new String[dice.size()];
        for (int i=0; i<dice.size();i++){
            dicelist[i]=dice.get(i);
        }
        bonuslist = new String[bonus.size()];
        for (int i=0; i<bonus.size();i++){
            bonuslist[i]=bonus.get(i);
        }

        //===============================================================================
        //  SECTION 1: MENU BAR WITH "File" -> New, Load, Save and Exit
        //===============================================================================

        menuBar = new JMenuBar(); //Our Menu Bar

        fileMenu = new JMenu("File"); //One Menu on our Menu Bar [FILE].....
        menuBar.add(fileMenu);

        newMenuItem = new JMenuItem("New Character"); //Option 1
        newMenuItem.addActionListener(this);
        newMenuItem.setMnemonic(KeyEvent.VK_N);

        loadMenuItem = new JMenuItem("Load Character"); //Option 2
        loadMenuItem.addActionListener(this);
        loadMenuItem.setMnemonic(KeyEvent.VK_L);

        saveMenuItem = new JMenuItem("Save Character"); //Option 3
        saveMenuItem.addActionListener(this);
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setVisible(false);
        //only show when there is a player in the currentPlayer folder
        if (pictureexits()){
            saveMenuItem.setVisible(true);
        }

        exitMenuItem = new JMenuItem("Exit"); //Option 4
        exitMenuItem.addActionListener(this);
        exitMenuItem.setMnemonic(KeyEvent.VK_E);

        fileMenu.add(newMenuItem); //New
        fileMenu.add(loadMenuItem);//Load
        fileMenu.add(saveMenuItem);//Save
        fileMenu.add(exitMenuItem);//Exit

        //===============================================================================
        //  SECTION 2: Character Skins, If there is a current character then show it
        //===============================================================================

        picLabel = new JLabel("Lobby");
        image.setBounds(225, 20, 600, 400); //size of the imageIcon
        picLabel.setPreferredSize(new Dimension(500, 380));


       //adding Jlabel inside a Jpanel
        image.add(picLabel);
        //if there is a current character then show it
        if (pictureexits()) {
        //updateImage updates the initial frame
        updateImage("current/"+"currentPlayer"+".log");
        }

        //===============================================================================
        //  SECTION 3: Main Menu -> Character Options
        //===============================================================================


        startGame = new JButton("Start Game");
        startGame.setFont(new Font("Sans", Font.BOLD,15));
        startGame.setBounds(200+175,155+240+55,200,50);
        startGame.addActionListener(this);

        newChar = new JButton("New Character");
        newChar.setFont(new Font("Sans", Font.BOLD,15));
        newChar.setBounds(200+175,155+300+50,200,50);
        newChar.addActionListener(this);

        editChar = new JButton("Edit Character");
        editChar.setFont(new Font("Sans", Font.BOLD,15));
        editChar.setBounds(200+175,210+300+50,200,50);
        editChar.addActionListener(this);

        saveChar = new JButton("Save Character");
        saveChar.setFont(new Font("Sans", Font.BOLD,15));
        saveChar.setBounds(200+175,265+300+55+50,200,50);
        saveChar.addActionListener(this);

        loadChar = new JButton("Load Character");
        loadChar.setFont(new Font("Sans", Font.BOLD,15));
        loadChar.setBounds(200+175,265+300+50,200,50);
        loadChar.addActionListener(this);


        //"Cancel" button
        cancel = new JButton("Cancel");
        cancel.setBounds(430,600,150,50);
        cancel.addActionListener(this);

        //"Confirm" button
        confirm = new JButton("Confirm");
        confirm.setBounds(275,600,150,50);
        confirm.addActionListener(this);

        //Adding JButtons to our JFrame
        initial.add(startGame);
        initial.add(newChar);
        initial.add(editChar);
        initial.add(saveChar);
        initial.add(loadChar);
        initial.add(image); //adding JPanel to our Jpanel
        initial.setLayout(null);

        //Only show the character when we have it in our currentPlayer folder
        saveChar.setVisible(false);
        if (pictureexits()){
            saveChar.setVisible(true);
            saveMenuItem.setVisible(true);
        }

        //Adding Jpanel to our JFrame
        this.add(initial);

        //Second menu has Cancel or Confirm for creating Character
        editing.add(cancel);
        editing.add(confirm);

        //Making our JPanel visible
        //J-FRAME settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setTitle("This is the Title!");
        this.setJMenuBar(menuBar);
        this.setBackground(new Color(0xFFFDD0));
        this.setLayout(new CardLayout());
        this.setResizable(true);
        this.setVisible(true);

        System.out.println(myPlayers.toString());
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> myMonsterList = new ArrayList<String>();
        if (e.getSource()==startGame){
            String file_line="";
            String path = "monsters-1.csv";
            try {
                BufferedReader in = new BufferedReader(new FileReader(path));

                while ((file_line = in.readLine()) != null){
                    myMonsterList.add(file_line);
                }
            } catch (IOException et) {
                throw new RuntimeException(et);
            }

            System.out.println(myMonsterList);

            Integer[] options = {1,2,3,4,5};

            int x = JOptionPane.showOptionDialog(null, "Please choose how many monster should be added to the map",
                    "Difficulty Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(x);


            guicontent = new GUIController(x+1);
            this.remove(initial);
            this.add(guicontent);
            this.setTitle("Game has started!");
            this.pack();
            this.validate();

        }
        //Loading the character from the Saved Character Folder
        if (e.getSource()==loadChar||e.getSource() == loadMenuItem) {
            System.out.println("Clicked \"Load\" on the Menu Item bar");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./saved/"));
            int response = fileChooser.showOpenDialog(null);
            File file= null;
            String[] broken=null;
            String finalpath;
            if (response == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                //System.out.println(file.getPath());
            }

            //we have file and we need to make them the player
            Path fileName = Path.of(file.getPath());
            String str = null;
            try {
                str = Files.readString(fileName);
            } catch (IOException ea) {
                throw new RuntimeException(ea);
            }

            // Printing the string from the chosen file
            System.out.println("Loaded character: "+str);

            //Example line: head,50,15,1,3,4,Greataxe,1d12,0,7 - AGhGhP7.png
            String[] parsedString = str.trim().split(",");
            try {
                //send the parsedString array to update all the values in the editing window
                editingUpdater(parsedString);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }


            int j = IndexPanel.comboBox.getSelectedIndex();
            name = IndexPanel.playerName.getText();

            createdPlayer.setAvatar(parsedString[9]);
            createdPlayer.setName(parsedString[0]);
            createdPlayer.setHp(Integer.parseInt(parsedString[1]));
            createdPlayer.setAc(Integer.parseInt(parsedString[2]));
            createdPlayer.setStr(Integer.parseInt(parsedString[3]));
            createdPlayer.setDex(Integer.parseInt(parsedString[4]));
            createdPlayer.setCon(Integer.parseInt(parsedString[5]));
            createdPlayer.setWeapon(new Weapon(parsedString[6], parsedString[7], parsedString[8]));


            String playername = parsedString[0];

           Player holder;
            if (validDatePlayer(createdPlayer)){

                 holder = new Player(createdPlayer);
                myPlayers.add(holder);
                System.out.println("Number of players loaded into the Game-> "+myPlayers.size());
                updateImage("saved/"+playername+".log");
            }


        }
        //Saving the character information into the saved character folder from the editing panel
        if (e.getSource()==saveChar||e.getSource() == saveMenuItem) {
            /*System.out.println("Clicked \"Save\" on the Menu Item");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            int saved = fileChooser.showOpenDialog(null);
            if (saved == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
            */
            //now we have saved the character
            try {
                PrintWriter writer = new PrintWriter(new FileWriter("saved/"+createdPlayer.getName() + ".log", false));
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
        }
        if (e.getSource() == exitMenuItem) {
            System.out.println("Exited out of the program");
            /*File myobj = new File("current/currentPlayer.log");
            myobj.delete();*/
//exit but hasn't been saved then ask for a w  indow
            Path fileName = Path.of("current/currentPlayer.log");
            String str = null;
            try {
                str = Files.readString(fileName);
            } catch (IOException ea) {
                throw new RuntimeException(ea);
            }

            String[] parsedString = str.trim().split(",");
            if (!new File("saved/"+parsedString[0]+".log").exists() || (!new File("saved/"+createdPlayer.getAvatar()+".log").exists())) {
                //if the file doesn't exist
                int a=JOptionPane.showConfirmDialog(this,"Are you sure? Do you want to save?");
                if(a==JOptionPane.YES_OPTION){
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter("saved/"+parsedString[0] + ".log", false));
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
                        System.out.println("Player is saved\n");
                        writer.close();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if(a==JOptionPane.NO_OPTION){
                    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                }
            } else {
                System.exit(0);
            }



        }
        if (e.getSource()==newChar || e.getSource()==newMenuItem){
           //Just changing the window to the Edit window
            if (current ==0) {
                this.remove(initial);
                this.add(editing);
                this.validate();
                current=1;
            }
        }
        if (e.getSource()==editChar){
            //Just Changing the window to the Edit window
            if (current ==0) {
                this.remove(initial);
                this.add(editing);
                this.validate();
                current=1;
            }

        }
       if (e.getSource()==cancel)
        {
            if (current ==1) {
                this.remove(editing);
                this.add(initial);
                editing.setVisible(false);
                initial.setVisible(true);
                current=0;
            } else if (current ==0){
                this.remove(initial);
                this.add(editing);
                current=1;
            }

        }
        if (e.getSource()==confirm){
            //when confirm is clicked couple menus open up
            //they only open when a player is edited or created
            saveChar.setVisible(true);
            saveMenuItem.setVisible(true);

            //getting info from the editing window
            if (IndexPanel.playerName.getText().length() ==0){
                JOptionPane.showMessageDialog(this, "Please fill out the entire box", "Stats Error", JOptionPane.ERROR_MESSAGE);
            }

            str = Integer.parseInt(IndexPanel.strfield.getText());
            dex = Integer.parseInt(IndexPanel.dexfield.getText());
            con = Integer.parseInt(IndexPanel.confield.getText());

            if (str+con+dex > 15){
                JOptionPane.showMessageDialog(this, "STR, DEX & CON add upto over 15", "Stats Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int j = IndexPanel.comboBox.getSelectedIndex();
                name = IndexPanel.playerName.getText();

                createdPlayer.setAvatar(IndexPanel.fileLocation);
                createdPlayer.setName(name);
                createdPlayer.setHp(50);
                createdPlayer.setAc(15);
                createdPlayer.setStr(str);
                createdPlayer.setDex(dex);
                createdPlayer.setCon(con);
                createdPlayer.setWeapon(new Weapon(weaponlist[IndexPanel.j], dicelist[IndexPanel.j], bonuslist[IndexPanel.j]));


                Player holder;
                if (validDatePlayer(createdPlayer)){
                   holder = new Player(createdPlayer);
                    myPlayers.add(holder);
                    System.out.println("Number of players loaded into the Game-> "+myPlayers.size());
                }
            }
            try {
                PrintWriter writer = new PrintWriter(new FileWriter("current/"+"currentPlayer" + ".log", false));
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
                hasbeencreated=1;
                System.out.println("Player is currently in memory, it is now in the currentPlayer directory\n");
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (current ==1) {
                this.remove(editing);
                this.add(initial);
                editing.setVisible(false);
                initial.setVisible(true);
                current=0;
            } else if (current ==0){
                this.remove(initial);
                this.add(editing);
                current=1;
            }
            //updateImage();
            updateImage("current/"+"currentPlayer"+".log");
        }


    }

    public void updateImage(String path){
        //path = path where we are looking for a specific image with a file name
        if (new File(path).exists()) {

            Path fileName = Path.of(path);
            // Now calling Files.readString() method to read the file

            String str = null;
            try {
                str = Files.readString(fileName); //only needs to read a singular line
            } catch (IOException ea) {
                throw new RuntimeException(ea);
            }
            // Printing the string
            System.out.println("Image has been updated --> "+ str);

            //An example line within "str"
            //Samantha,50,15,2,3,4,Greataxe,1d12,0,5 - KDHJaWD.png
            String[] parsedString = str.trim().split(",");

            //avatarfile now contains the file name for the image
            String avatarfile = parsedString[9];

            BufferedImage myPicture = null;
            try {
                myPicture = ImageIO.read(new File("img/"+avatarfile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
            picLabel.setIcon(imageIcon);

            initial.remove(image);
            image.removeAll();
            initial.repaint();
            image.add(picLabel);
            initial.add(image);

            SwingUtilities.updateComponentTreeUI(initial);
        }
    }

    public boolean pictureexits(){
        return new File("current/currentPlayer.log").exists();
    }

    public void editingUpdater(String[] str) throws FileNotFoundException {
        //Example Line- head,50,15,1,3,4,Greataxe,1d12,0,7 - AGhGhP7.png
       IndexPanel.playerName.setText(str[0]);
       IndexPanel.strfield.setText(str[3]);
       IndexPanel.dexfield.setText(str[4]);
       IndexPanel.confield.setText(str[5]);
       IndexPanel.comboBox.setSelectedItem(str[6]);

       //
       //File file = new File("saved/"+str[9]);
      // IndexPanel.fileLocation = file.getPath();
    }

    public boolean validDatePlayer(Player npc){
       // return !npc.getAvatar().equals("Empty right now");
        if (npc.getAvatar().equals("null") || npc.getAvatar().length() >= 20){
            return false;
        }
        return true;
    }


}


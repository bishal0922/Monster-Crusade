import java.io.File;
import java.util.Date;

public class Player extends Creature{

    public Player(Player employee) {
        this.name = employee.getName();
        this.avatar = employee.getAvatar();
    }
    int disarm_count_down;
    //need to add a current player location tag
    int player_x_coordinate;
    int player_y_coordinate;
    String skinCharacter;
    private Weapon weapon = new Weapon();

    public Player(String name, String avatarpath, int i, int i1, int str, int dex, int con, Weapon weapon) {
        super();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    //private String name;
    private int x;
    private int y;
    private int totalMovement = 5;
    private int currentMovement = 5;

    public Player(String name) {
        this.name = name;
        x = 0;
        y = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCurrentMovement() {
        return this.currentMovement;
    }

    public void setCurrentMovement(int movement) {
        this.currentMovement = movement;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;

        System.out.printf("%s moved to (%d, %d)\n", this.name, x, y);
    }
    private final Date creationDate = new Date();


        public static Player loadFromCsv(String inputString) throws CsvReadException {
            ///this will be the csv line


            //means that there are 8 attributes
            try {
                String[] parsedString = inputString.trim().split(",");
                if (parsedString.length ==8 ){
                    String name = parsedString[0];
                    int hp= Integer.parseInt(parsedString[1]);
                    int ac = Integer.parseInt(parsedString[2]);
                   int str = Integer.parseInt(parsedString[3]);
                    int dex = Integer.parseInt(parsedString[4]);
                int con = Integer.parseInt(parsedString[5]);
                    String weaponname = (parsedString[6]);
                    String dicetype= (parsedString[7]);
                    int bonusnum = (Integer.parseInt(parsedString[8]));
                    return new Player(name, hp, ac, str, dex, con, new Weapon(weaponname, dicetype, bonusnum));
                }
            } catch (NumberFormatException e) {
                throw new CsvReadException(inputString);

            }
            return null;
        }

        public Player(){

        }


    public Player(String name, String location, int hp, int ac, int str, int dex, int con){
        this.name = name;
        this.avatar = location;
        this.hp = hp;
        this.ac = ac;
        this.str = str;
        this.dex = dex;
        this.con = con;
    }
    public Player(String name, int hp, int ac, int str, int dex, int con){
        this.name = name;
        this.hp = hp;
        this.ac = ac;
        this.str = str;
        this.dex = dex;
        this.con = con;
    }
        public Player(String name, int hp, int ac, int str, int dex, int con, Weapon charWeapon){
            this.name = name;
            this.hp = hp;
            this.ac = ac;
            this.str = str;
            this.dex = dex;
            this.con = con;
            this.weapon = charWeapon;
        }
    private int rollHit(){
        int total = GameUtility.rollDice("d20")+statsModifier(getDex())+weapon.getBonus_num();
        if (total >0) {
            System.out.println("\nThe roll causes a damage of " + total);
            return total;
        }else {
            System.out.println("\nThe roll causes a damage less than 0");
        }
        return 0;
    }

    public void attack(Creature randlol){

        int damage = rollHit();
        if (damage >= randlol.getAc() && damage>0) {
            System.out.printf("Damage of %d to be taken by %s", damage, randlol.getName());
            randlol.takeDamage(damage);
        } else {
            System.out.println("Missed...The enemy's armor("+randlol.getAc()+") is too strong");
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                //"Creation Date: " + creationDate + "\n" +
                "Hit Points: " + getHp() + "\n"
                + "Armor class: " + getAc() + "\n"
                + "Strength: " + getStr() + "\n"
                + "Dexterity: " + getDex() + "\n"
                + "Constitution: " + getCon() + "\n"
                + "Weapon: " + weapon.toString() + "\n"
                + "Location: " + getAvatar() + "\n";

    }

    public int getDisarm_count_down() {
        return disarm_count_down;
    }

    public void setDisarm_count_down(int disarm_count_down) {
        this.disarm_count_down = disarm_count_down;
    }

    public int getPlayer_x_coordinate() {
        return player_x_coordinate;
    }

    public void setPlayer_x_coordinate(int player_x_coordinate) {
        this.player_x_coordinate = player_x_coordinate;
    }

    public int getPlayer_y_coordinate() {
        return player_y_coordinate;
    }

    public void setPlayer_y_coordinate(int player_y_coordinate) {
        this.player_y_coordinate = player_y_coordinate;
    }

    public String getSkinCharacter() {
        return skinCharacter;
    }

    public void setSkinCharacter(String skinCharacter) {
        this.skinCharacter = skinCharacter;
    }
}

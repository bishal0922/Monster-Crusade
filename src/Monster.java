import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Monster {


    String name;
    int hp;
    int lvl;
    int speed;
    String type;
    int no_of_monsters;

    public ArrayList<ArrayList<String>> listMonster = new ArrayList<>();
    public ArrayList<String> nameMonster = new ArrayList<>();
    public ArrayList<String> hpMonster = new ArrayList<>();
    public ArrayList<String> lvlMonster = new ArrayList<>();
    public ArrayList<String> speedMonster = new ArrayList<>();
    public ArrayList<String> typeMonster = new ArrayList<>();

    public ArrayList<ArrayList<String>> csvReader(String filePath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String file_line = "";

        while ((file_line = in.readLine()) != null){
            String[] arr = file_line.split(",");    // use comma as separator
            this.nameMonster.add(arr[0]);
            this.hpMonster.add(arr[1]);
            this.lvlMonster.add(arr[2]);
            this.speedMonster.add(arr[3]);
            this.typeMonster.add(arr[4]);
            this.no_of_monsters++;

        }
        return this.listMonster;
    }

    public void addToList(){
        this.listMonster.add(nameMonster);
        this.listMonster.add(hpMonster);
        this.listMonster.add(lvlMonster);
        this.listMonster.add(speedMonster);
        this.listMonster.add(typeMonster);
    }

    @Override
    public String toString() {
        return " nameMonster=" + nameMonster + '\n' +
                " hpMonster=" + hpMonster + '\n' +
                " lvlMonster=" + lvlMonster + '\n' +
                " speedMonster=" + speedMonster + '\n' +
                " typeMonster=" + typeMonster + '\n' +
                "NUMBER OF MONSTERS = " + no_of_monsters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNo_of_monsters() {
        return no_of_monsters;
    }

    public void setNo_of_monsters(int no_of_monsters) {
        this.no_of_monsters = no_of_monsters;
    }

}

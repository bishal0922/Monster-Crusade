import java.io.File;

public abstract class Creature implements Comparable{

    /*name : String
hp : int
ac : int
str : int
dex : int
con : int*/


    String name;
    int hp;
    int ac;
    int str;
    int dex;
    int con;



    String avatar;

    public void takeDamage(int inputDamage){

        if((this.getHp()-inputDamage)>0) {
            System.out.printf("The before health is %d\n",  this.getHp());
            this.setHp(this.getHp() - inputDamage);
            System.out.printf("After damage of %d it's is now %d\n\n", inputDamage, this.getHp());
        }if (this.getHp() <= 0) {
            if (this.getHp()==0){
                System.out.println("HP is 0...player is dead");
            }else{
                System.out.println("The damage causes HP to be negative so it's set to 0");
            }
            this.setHp(0);
        }

    }
    public abstract void attack(Creature randCreature);

    public static int statsModifier(int points) {
        return (points < 5) ? (points - 5) : (5 - points);
    }

    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && name.equals(((Creature) o).getName());
    }


    public int compareTo(Object creatureObject){
        Creature temp = (Creature) creatureObject;
        return Integer.compare(getHp(), temp.getHp());
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

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", ac=" + ac +
                ", str=" + str +
                ", dex=" + dex +
                ", con=" + con +
                '}' + "\n";
    }
}


    public class Weapon {
        String weapon_name;
        String dice_type;
        int bonus_num;

        //CONSTRUCTORS
        public Weapon() {
        }

        public Weapon(String weapon_name, String dice_type, int bonus_num) {
            this.weapon_name = weapon_name;
            this.dice_type = dice_type;
            this.bonus_num = bonus_num;
        }

        public Weapon(String weapon_name, String dice_type, String bonus_num) {
            this.weapon_name = weapon_name;
            this.dice_type = dice_type;
            this.bonus_num = Integer.parseInt(bonus_num);
        }

        //METHODS
        public int rollDamage() {
            return GameUtility.rollDice(dice_type + "+" + bonus_num);
        }

        //GETTERS AND SETTERS
        //weapon_name
        public String getWeapon_name() {
            return weapon_name;
        }

        public void setWeapon_name(String weapon_name) {
            this.weapon_name = weapon_name;
        }

        //dice_type
        public String getDice_type() {
            return dice_type;
        }

        public void setDice_type(String dice_type) {
            this.dice_type = dice_type;
        }

        //bonus_num
        public int getBonus_num() {
            return bonus_num;
        }

        public void setBonus_num(int bonus_num) {
            this.bonus_num = bonus_num;
        }

        @Override
        public String toString() {
            return weapon_name + "(" + dice_type + "+" + bonus_num + ")";
        }

    }



import java.text.ParseException;

public class GameUtility {

    public static void validateName(String line){
        /*Must start with a capital letter.
Must not contain numbers or special characters.
Cannot be longer than 24 characters.*/

        try {
            if (!line.matches("^[A-Z]+([A-Za-z]{0,23})$")){
                throw new ParseException("Invalid Name", 1);
            }
        } catch (ParseException e) {
            System.out.println("Parse Exception "+ e.getMessage());
        }

    }
    public static int rollDice(String inputDice) {
        String str = inputDice;

        int num, type, cons;
        String[] token1 = str.split("d");
        if (token1[0].length() == 0) {
            num = 1;
        } else {
            try {
                num = Integer.parseInt(token1[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                return -1;
            }
        }


        if (token1[1].contains("+")) {
            String[] token2 = token1[1].split("[+]");
            type = Integer.parseInt(token2[0]);
            if (token2[1].length() == 0) {
                cons = 0;
            } else {
                cons = Integer.parseInt(token2[1]);
            }

        } else {
            type = Integer.parseInt(token1[1]);
            cons = 0;
        }

        int dsum = 0, dice;
        for (int i = 0; i < num; i++) {
            dice = (int) (Math.random() * type + 1);
            dsum += dice;
        }


        int result = dsum + cons;
        return result;
    }
}

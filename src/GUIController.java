import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIController extends JPanel implements SpriteMoveListener {
    private MainPanel gamePanel;
    private ArrayList<Player> players;

    public GUIController(int x) {
        //JFrame frame = new JFrame();
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setTitle("GridMoveExample");
        gamePanel = new MainPanel();
        gamePanel.addSpriteMoveListener(this);
        this.add(gamePanel);
        this.setVisible(true);

        System.out.println(MyFrame.myPlayers.size() + " players");
        players = new ArrayList<>();
        players.addAll(MyFrame.myPlayers);


        //"list is the monster string array
        Player m1 = new Player("monster");
        Player m2 = new Player("monster2");
        Player m3 = new Player("monster3");
        Player m4 = new Player("monster4");
        Player m5 = new Player("monster5");

        if (x == 1) {
            m1.move(9,0);
            addPlayer(m1, "img/monster2.png");
        }
        if (x == 2) {
            m1.move(9,0);
            m2.move(9,2);
            addPlayer(m1, "img/monster2.png");
            addPlayer(m2, "img/monster3.jpg");
        }
        if (x == 3) {
            m1.move(9,0);
            m2.move(9,2);
            m3.move(9,4);
            addPlayer(m1, "img/monster2.png");
            addPlayer(m2, "img/monster3.jpg");
            addPlayer(m3, "img/monster4.jpg");
        }
        if (x == 4) {
            m1.move(9,0);
            m2.move(9,2);
            m3.move(9,4);
            m4.move(9,6);
            addPlayer(m1, "img/monster2.png");
            addPlayer(m2, "img/monster3.jpg");
            addPlayer(m3, "img/monster4.jpg");
            addPlayer(m4, "img/monster5.jpg");

        }
        if (x == 5) {
            m1.move(9,0);
            m2.move(9,2);
            m3.move(9,4);
            m4.move(9,6);
            m5.move(9,8);
            addPlayer(m1, "img/monster2.png");
            addPlayer(m2, "img/monster3.jpg");
            addPlayer(m3, "img/monster4.jpg");
            addPlayer(m4, "img/monster5.jpg");
            addPlayer(m5, "img/monster6.jpg");

        }


        for (int i = 0; i < MyFrame.myPlayers.size(); i++) {

                players.get(i).move(0+i,0+i);

            addPlayer(players.get(i), "img/"+MyFrame.myPlayers.get(i).getAvatar() );
        }
      /*  Player p1 = new Player("Grog");
        Player p2 = new Player("Percy");
        p2.move(5, 5);
        p2.setCurrentMovement(0);


        addPlayer(p1, "img/2 - S2LVbi7.png");
        addPlayer(p2, "img/11 - X2tTe7M.png");*/
    }

    public void addPlayer(Player p, String assetPath) {
        players.add(p);
        int idx = gamePanel.addSprite(assetPath, p.getX(), p.getY());
    }

    @Override
    public void spriteMoved(int id, int x, int y) {
        Player p = players.get(id);
        p.move(x, y);
    }

    @Override
    public boolean canMove(int id) {
        Player p = players.get(id);

        return p.getCurrentMovement() > 0;
    }

    @Override
    public boolean canMoveTo(int id, int x, int y) {
        // incoming x and y are in relative pixel coordinates, convert to grid coordinates
        Point p = gamePanel.getGridLocation(x, y);

        Player player = players.get(id);
        int dx = Math.abs(p.x - player.getX());
        int dy = Math.abs(p.y - player.getY());
        int min = Math.min(dx, dy);
        int max = Math.max(dx, dy);
        int diagonalSteps = min;
        int straightSteps = max - min;

        int distance = (int) (Math.sqrt(2) * diagonalSteps + straightSteps);

        System.out.println("Distance = " + distance);

        return player.getCurrentMovement() >= distance;
    }


}
public class PlayerUpdater extends Thread{
        public void run()
        {
            try {
                // Displaying the thread that is running
                System.out.println("Number of players: " + MyFrame.myPlayers.size());
                for (int i = 0; i < MyFrame.myPlayers.size(); i++) {
                    System.out.println(MyFrame.myPlayers.get(i).getName());
                }
            }
            catch (Exception e) {
                // Throwing an exception
                System.out.println("Exception is caught");
            }
        }
    }


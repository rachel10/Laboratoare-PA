import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;

public class Player implements Runnable {
    private String username;
    private List<Token> currentSolution;
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public Player(String username) {
        this.username = username;
        currentSolution = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    @Override
    public void run() {
        while (game.getBoard().getNumberOfTokens() > 0) {
            synchronized (game) {
                while (!game.getCurrentPlayer().equals(this.username)) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Jocul s-a terminat!");
                        exit(1);
                    }
                }
                Token token = game.getBoard().getToken();
                if(token!=null) {
                    System.out.println(username + " " + token.getValue());
                    currentSolution.add(token);
                    game.nextPlayer(this.username);
                    game.notifyAll();
                }
                else {
                    game.stop();
                }
            }

        }
    }
}
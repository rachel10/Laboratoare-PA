import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private String currentPlayer;
    private Thread[] threads;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.threads=new Thread[players.size()];
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public synchronized void nextPlayer(String lastUsername){
        for (int i=0;i<players.size();i++){
            if (players.get(i).getUsername().equals(lastUsername)){
                if(i<players.size()-1){
                    currentPlayer=players.get(i+1).getUsername();
                }else {
                    currentPlayer=players.get(0).getUsername();
                }

            }
        }
    }
    public void start(){
        this.setCurrentPlayer(players.get(0).getUsername());
        for (int i=0;i<3;i++){
            players.get(i).setGame(this);
        }
        threads[0]=new Thread(players.get(0));
        threads[1]=new Thread(players.get(1));
        threads[2]=new Thread(players.get(2));
        threads[0].start();
        threads[1].start();
        threads[2].start();
    }
    public void stop(){
        threads[0].interrupt();
        threads[1].interrupt();
        threads[2].interrupt();
    }
}

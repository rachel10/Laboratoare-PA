import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Board board=new Board(7);
        List<Token> tokenList=new ArrayList<>();
        for (int i=0;i<7;i++)
        {
            tokenList.add(new Token(i));
        }
        board.setTokenList(tokenList);

        List<Player>players=new ArrayList<>();
        for (int i=1;i<=3;i++){
            players.add(new Player("player"+i));
        }
        Game game=new Game(board,players);
       game.start();
    }
}

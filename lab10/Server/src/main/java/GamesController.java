import java.util.ArrayList;
import java.util.List;

public class GamesController {
    private static GamesController instance=null;
    private static List<Game> games=null;
    private static boolean free=true;

    public static GamesController getInstance(){
        if (instance==null){
            instance=new GamesController();
        }
        return instance;
    }

    public synchronized List<Game> getGames(){
        return games;
    }

    public synchronized void addGame(Game game){
        if (games==null){
            games=new ArrayList<Game>();
        }
        games.add(game);
    }
    public synchronized void deleteGame(int index){
        games.remove(index);
    }

    public void setFree(){
        free=true;
    }

    public void setLock(){
        free=false;
    }

    public boolean getFree(){
        return free;
    }


}

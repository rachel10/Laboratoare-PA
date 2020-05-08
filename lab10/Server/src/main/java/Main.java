import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            GameServer gameServer=new GameServer(2020);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

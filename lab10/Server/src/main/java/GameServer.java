import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public int port;

    public GameServer(int port) throws IOException {
        this.port = port;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(this.port);

            while (true) {
                System.out.println("waiting for a new client..");
                Socket socket=serverSocket.accept();
                System.out.println("one client accepted..");
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }

    }
}

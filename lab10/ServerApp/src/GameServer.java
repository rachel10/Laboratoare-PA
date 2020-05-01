import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public int port;

    public GameServer(int port) throws IOException {
        this.port = port;
        ServerSocket serverSocket = null;
        int clients = 0;
        try {
            serverSocket = new ServerSocket(this.port);

            while (clients < 2) {
                System.out.println("waiting for a new client..");
                Socket socket=serverSocket.accept();
                System.out.println("one client accepted..");
                new ClientThread(socket).start();
                clients++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }

    }
}

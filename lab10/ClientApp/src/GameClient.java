import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 2020;
        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader keyboardIn=new BufferedReader(new InputStreamReader(System.in));
        ) {

            String fromServer;
            String myResponse;

            fromServer=in.readLine();
            fromServer=fromServer.replaceAll(";n;","\n");
            System.out.println(fromServer);
            do{

                myResponse = keyboardIn.readLine();
                out.println(myResponse);
                fromServer=in.readLine();
                fromServer=fromServer.replaceAll(";n;","\n");
                System.out.println(fromServer);
            }while(!fromServer.startsWith("Jocul s-a terminat."));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

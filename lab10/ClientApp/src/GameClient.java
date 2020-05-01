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

            String request;
            String response;
            do{
                System.out.println("Tastati cererea (comanda stop pt a inchide)");
                request=keyboardIn.readLine();
                out.println(request);
                System.out.println("Am trimis cererea: '"+request+"' ...Astept raspunsul.");

                response=in.readLine();
                System.out.println("Am primit raspunsul: '"+ response+"'");

            }while(!response.equalsIgnoreCase("Serverul s-a oprit."));
            System.out.println();
            System.out.println("Clientul a terminat executia.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

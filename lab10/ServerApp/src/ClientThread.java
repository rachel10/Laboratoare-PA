import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream());
        ) {
            String request;
            String response;
            String waiting;
            do {
                request = in.readLine();

                System.out.println("Am primit cererea clientului: '" + request+"'");
                if(request.equalsIgnoreCase("stop"))
                {
                    response="Serverul s-a oprit.";
                    waiting="";
                }else {
                    response = "Serverul a primit cererea.";
                    waiting="Astept alta cerere...";
                }
                out.println(response);
                out.flush();
                System.out.println("Am trimis raspunsul: '"+response+"'... "+waiting);
            }while(!request.equalsIgnoreCase("stop"));

            System.out.println();
            System.out.println("Serverul a terminat executia.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

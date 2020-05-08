import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientThread extends Thread {
    private Socket socket = null;
    private GamesController gamesController;
    private boolean availableGames = false;
    private Game game;
    private int role;

    public ClientThread(Socket socket) {
        this.socket = socket;
        gamesController = GamesController.getInstance();
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String request;
            String response;
            Boolean waiting = false;
            int indexGame;
            synchronized (gamesController) {
                while (!gamesController.getFree()) {
                    try {
                        gamesController.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gamesController.setLock();
                List<Game> games = gamesController.getGames();
                if (games == null || games.size() == 0) {
                    out.println("Nu sunt jocuri disponibile la care sa va alaturati. ;n; Pentru a crea joc nou, tastati comanda 'create game'");
                    availableGames = false;
                } else {
                    availableGames = true;
                    int key = 0;
                    response = "Jocuri disponibile: ;n;";
                    for (Game game : games) {
                        response += "Key: " + key + " Veti juca cu jucatorul: " + game.getPlayer1()+";n;";
                        key++;
                    }
                    response += "Pentru a va alatura unui joc tastati comanda: 'join game keyGame'- ex: 'join game 1' pentru a va alatura jocului cu cheia 1";
                    response += ";n;Pentru a crea joc nou tastati comanda 'create game'";
                    out.println(response);
                }

                boolean validKey = false;
                while (!validKey) {
                    request = in.readLine();
                    if (request.equalsIgnoreCase("create game")) {
                        role = 1;
                        out.println("Introduceti un username: ");
                        String username = in.readLine();
                        game = new Game(username);
                        game.addPlayer1ToHtml(username);
                        waiting = true;
                        validKey = true;
                    } else {
                        if (request.toLowerCase().startsWith("join game")) {
                            if (!availableGames) {
                                out.println("Nu exista jocuri disponibile, creati dvs unul...");
                            } else {
                                request = request.replaceFirst("join game", " ");
                                request = request.trim();
                                if (request.length() == 0) {
                                    out.println("Comanda invalida...");
                                    validKey = false;
                                } else {
                                     indexGame = Integer.parseInt(request);
                                    if (indexGame > games.size() - 1 || indexGame < 0) {
                                        out.println("Ati introdus o cheie invalida...");
                                        validKey = false;
                                    } else {
                                        game = games.get(indexGame);
                                        out.println("Introduceti un username: ");
                                        String username = in.readLine();
                                        game.setPlayer2(username);
                                        game.addPlayer2ToHtml(username);
                                        gamesController.deleteGame(indexGame);
                                        validKey = true;
                                        role = 2;
                                    }
                                }
                            }
                        } else {
                            out.println("Comanda invalida! Introduceti o comanda valida...");
                            validKey = false;
                        }
                    }
                }
            }
            gamesController.setFree();
            if (waiting) {
                while (game.getPlayer2() == null) {
                }
                waiting = false;
            }
            int row = -1, column = -1;
            int validmove = 0, blackMove = 0, whiteMove = 0;
            response = ("Jocul se va desfasura intre jucatorii: " + game.getPlayer1() + " si " + game.getPlayer2() + ". Succes! ");
            if (role == 1) {
                while (game.getTurn() == 2) {
                }
                response += game.getTable();
                response += " " + "Adauga 2 mutari negre si una alba." + ";n;" + " Comanda va fi de tipul: 'black 0 1' unde 0 este linia si 1 coloana.";
                out.println(response);
                while (validmove < 3) {
                    int color = -1;
                    String mutare = in.readLine();
                    if (mutare.toLowerCase().startsWith("black ")) {
                        blackMove++;
                        color = -1;
                    } else {
                        if (mutare.toLowerCase().startsWith("white ")) {
                            color = -2;
                            whiteMove++;
                        } else {
                            color = -3;
                        }
                    }
                    row = -1;
                    column = -1;
                    if ((color == -2 && whiteMove <= 1) || (color == -1 && blackMove <= 2)) {
                        Pattern pattern = Pattern.compile("\\d+");
                        Matcher matcher = pattern.matcher(mutare);
                        while (matcher.find()) {
                            if (row == -1) {
                                row = Integer.parseInt(matcher.group());
                            } else {
                                if (column == -1) {
                                    column = Integer.parseInt(matcher.group());
                                }
                            }
                        }
                        if (game.validMove(row, column) == 1) {
                            game.setMove(row, column, color);
                            validmove++;
                            if (validmove == 3) {
                                response = "Mutare valida";
                            } else {
                                out.println("Mutare valida");
                            }
                        } else {
                            if (color == -2) {
                                whiteMove--;
                            } else {
                                blackMove--;
                            }
                            out.println("Mutare invalida.");
                        }
                    } else {
                        out.println("*Mutare invalida");

                    }
                }
                game.setTurn(2);
            }
            if (role == 2) {
                validmove = 0;
                while (game.getTurn() == 1) {
                }
                response += game.getTable();
                response += " " + "Alege culoarea cu care joci. In tabela afisata -1=black si -2=white ;n;Tastati culoarea:";
                out.println(response);
                while (validmove < 1) {
                    request = in.readLine();
                    if (request.equalsIgnoreCase("black")) {
                        game.setPlayersInTable(2, 1);
                        validmove++;
                        game.setTurn(1);
                        response = "";
                    } else {
                        if (request.equalsIgnoreCase("white")) {
                            game.setPlayersInTable(1, 2);
                            validmove++;
                            game.setTurn(2);
                            response = "";
                        } else {
                            out.println("Invalid.");
                        }

                    }
                }
            }
            boolean mutareValida;
            while (!game.isWin()) {
                mutareValida = false;

                while (game.getTurn() == 3 - role) {
                }
                if (game.isWin()) {
                    break;
                }
                response = "";
                response += game.getTable() + ";n;" + "Player "+role+" este randul tau. Scrie mutarea ";
                out.println(response);
                while (!mutareValida) {
                    row = -1;
                    column = -1;
                    request = in.readLine();
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(request);
                    while (matcher.find()) {
                        if (row == -1) {
                            row = Integer.parseInt(matcher.group());
                        } else {
                            if (column == -1) {
                                column = Integer.parseInt(matcher.group());
                            }
                        }
                    }
                    if (game.validMove(row, column) == 1) {
                        game.setMove(row, column, role);
                        game.addMoveToHtml(row,column,role);
                        game.setWin(game.victory(row, column));
                        if (game.isWin()) {
                            out.println("Jocul s-a terminat. Ai castigat!");
                            game.addWinnerToHtml(role);
                        }
                        game.setTurn(3 - role);
                        mutareValida = true;
                    } else {
                        out.println("Mutare invalida.");
                        mutareValida = false;
                    }
                }
            }
            if (role == game.getTurn()) {
                out.println("Jocul s-a terminat. Ai pierdut...");
                File file = new File("game.html");
                int index=1;
                while(!file.createNewFile()){
                    file = new File("game"+index+".html");
                    index++;
                }
                FileWriter writer=new FileWriter(file.getName());
                writer.write(game.getRaportHtml());
                writer.close();
                upload(file);
            }
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
    private void upload(File file){
        String server = "127.0.0.1";
        Integer port = 21;
        String user = "user";
        String password = "sftp";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();

            String remoteFile = file.getName();
            InputStream inputStream = new FileInputStream(file);

            System.out.println("-----Start uploading the html raport");
            boolean done = ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("-----The html raport was uploaded successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

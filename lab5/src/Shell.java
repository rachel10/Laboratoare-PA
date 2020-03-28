import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Shell {


    public Shell() {
    }

    public void getCommand() throws IOException, InvalidCatalogException, InvalidDocumentException, InvalidCommand {
        BufferedReader bufferIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type the command and its arguments. The commands are: load, view, list,raportHtml, q");
        String command = "";
        command = bufferIn.readLine();
        Scanner scanCommand = new Scanner(command);
        scanCommand.useDelimiter(" ");

        while (!command.equals("Q") && !command.equals("q")) {

            if (command.startsWith("load")) {
                String buffer;
                if (scanCommand.hasNext()) {
                    buffer = scanCommand.next();
                }
                if (!scanCommand.hasNext()) {
                    System.out.println("Type the path:");
                    buffer = bufferIn.readLine();
                } else {
                    buffer = scanCommand.next();
                }
                Command loadCommand = new LoadCommand();
                loadCommand.solver(buffer);
                System.out.println("Solved! Type another command: ");
            } else {
                if (command.startsWith("list")) {
                    String buffer;
                    if (scanCommand.hasNext()) {
                        buffer = scanCommand.next();
                    }
                    if (!scanCommand.hasNext()) {
                        System.out.println("Type the path:");
                        buffer = bufferIn.readLine();
                    } else {
                        buffer = scanCommand.next();
                    }
                    Command listCommand = new ListCommand();
                    listCommand.solver(buffer);
                    System.out.println("Solved! Type another command: ");

                } else {
                    if (command.startsWith("view")) {
                        String buffer, idDoc;
                        if (scanCommand.hasNext()) {
                            buffer = scanCommand.next();
                        }
                        if (!scanCommand.hasNext()) {
                            System.out.println("Type the path:");
                            buffer = bufferIn.readLine();
                        } else {
                            buffer = scanCommand.next();
                        }
                        if (!scanCommand.hasNext()) {
                            System.out.println("Type the document ID: ");
                            idDoc = bufferIn.readLine();
                        } else {
                            idDoc = scanCommand.next();
                        }
                        Command viewCommand = new ViewCommand(idDoc);
                        viewCommand.solver(buffer);
                        System.out.println("Solved! Type another command: ");
                    } else {
                        if (command.startsWith("raportHtml")) {
                            String buffer;
                            if (scanCommand.hasNext()) {
                                buffer = scanCommand.next();
                            }
                            if (!scanCommand.hasNext()) {
                                System.out.println("Type the path:");
                                buffer = bufferIn.readLine();
                            } else {
                                buffer = scanCommand.next();
                            }
                            Command raportHtmlCommand = new RaportHtmlCommand();
                            raportHtmlCommand.solver(buffer);
                            System.out.println("Solved! You'll find the raport in E:/raport.html . Type another command: ");
                        }else {
                            throw new InvalidCommand(scanCommand.next());
                        }
                    }
                }
            }
            command = bufferIn.readLine();
            scanCommand = new Scanner(command);
            scanCommand.useDelimiter(" ");
        }

    }
}

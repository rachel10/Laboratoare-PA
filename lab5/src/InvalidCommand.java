public class InvalidCommand extends Exception{
    InvalidCommand(String command){
        System.out.println("Command: "+command+" is invalid!");
    }
}

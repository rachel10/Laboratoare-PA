public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String id){
        super("Id-ul: "+ id +" nu este unic!");
    }
}

public class InvalidDocumentException extends Exception {
    public InvalidDocumentException(Exception e) {
        super("Invalid document.", e);
    }
}

package mariiachatbot.exception;
/**
 * Exception thrown when an unknown or invalid command is entered in MariiaChatbot.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
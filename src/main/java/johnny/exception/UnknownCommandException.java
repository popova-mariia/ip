package johnny.exception;
/**
 * Exception thrown when an unknown or invalid command is entered in Johnny bot.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}

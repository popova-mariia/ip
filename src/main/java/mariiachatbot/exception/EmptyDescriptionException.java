package mariiachatbot.exception;
/**
 * Exception thrown when some part of description is missing.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
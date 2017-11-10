/**
 * This exception must be a checked exception because it's an error
 * which may result from client (rather than developer) usage.
 * Errors which may result from client usage must be checked exceptions.
 * Thus, InvalidSquareException must be a checked exception.
 *
 * @author Peter Herman
 * @version 1.0
 */
public class InvalidSquareException extends RuntimeException {

    /**
     * Constructor which makes super call to set message for
     * an InvalidSquareException
     *
     * @param msg - string containing incorrect possible Square
     */
    public InvalidSquareException(String msg) {
        super(msg);
    }
}

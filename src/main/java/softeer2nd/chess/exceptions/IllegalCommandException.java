package softeer2nd.chess.exceptions;

public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {
        super();
    }

    public IllegalCommandException(String message) {
        super(message);
    }
}

package softeer2nd.chess.exceptions;

public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {
        super("입력 형식이 잘못되었습니다.");
    }
}

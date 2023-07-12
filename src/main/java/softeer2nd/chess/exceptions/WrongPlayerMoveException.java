package softeer2nd.chess.exceptions;

public class WrongPlayerMoveException extends IllegalCommandException {
    public WrongPlayerMoveException() {
        super("현재 차례인 기물만 이동할 수 있습니다.");
    }
}

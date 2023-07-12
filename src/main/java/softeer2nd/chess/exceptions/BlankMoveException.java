package softeer2nd.chess.exceptions;

public class BlankMoveException extends IllegalCommandException {
    public BlankMoveException() {
        super("기물이 존재하지 않는 위치입니다.");
    }
}

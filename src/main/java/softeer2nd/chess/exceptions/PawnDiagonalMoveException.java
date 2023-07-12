package softeer2nd.chess.exceptions;

public class PawnDiagonalMoveException extends IllegalCommandException {
    public PawnDiagonalMoveException() {
        super("상대 기물이 존재할 때만 대각선 이동이 가능합니다.");
    }
}

package softeer2nd.chess.exceptions;

public class BlockedByPieceException extends IllegalCommandException{
    public BlockedByPieceException() {
        super("이동 경로 상에 다른 기물이 존재합니다.");
    }
}

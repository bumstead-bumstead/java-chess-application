package softeer2nd.chess.exceptions;

/*
* 유효하지 않은 이동을 시도한 경우
* 보드 밖으로의 이동
* 해당 기물의 행보법으로 이동할 수 없는 위치로의 이동
* */
public class OutOfPieceRangeException extends IllegalCommandException {
    public OutOfPieceRangeException() {
        super("해당 위치로 이동할 수 없습니다.");
    }
}

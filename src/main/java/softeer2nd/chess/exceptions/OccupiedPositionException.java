package softeer2nd.chess.exceptions;

/*
* 이미 아군 기물이 존재하는 위치로의 이동을 시도할 경우
* */
public class OccupiedPositionException extends RuntimeException {
    public OccupiedPositionException() {
        super("아군 기물이 존재하는 위치입니다.");
    }
}

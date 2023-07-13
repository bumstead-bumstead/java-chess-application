package softeer2nd.chess.exceptions;

public class ExceptionMessage {
    public static final String TURN_EXCEPTION_MESSAGE = "현재 차례인 기물만 이동할 수 있습니다.";
    public static final String FORMAT_EXCEPTION_MESSAGE = "입력 형식이 잘못되었습니다.";
    public static final String BLOCKED_EXCEPTION_MESSAGE = "이동 경로 상에 다른 기물이 존재합니다.";
    public static final String PAWN_DIAGONAL_MOVE_EXCEPTION_MESSAGE = "폰은 상대 기물이 존재할 때만 대각선 이동이 가능합니다.";
    public static final String PAWN_VERTICAL_CAPTURE_EXCEPTION_MESSAGE = "폰은 기물이 위치하는 칸으로 이동할 수 없습니다.";
    public static final String NO_PIECE_MOVE_EXCEPTION_MESSAGE = "기물이 존재하지 않는 위치입니다.";
    public static final String CAPTURE_ALLY_EXCEPTION_MESSAGE = "아군 기물이 존재하는 위치입니다.";
    public static final String UNREACHABLE_POSITION_EXCEPTION_MESSAGE = "해당 위치로 이동할 수 없습니다.";

}

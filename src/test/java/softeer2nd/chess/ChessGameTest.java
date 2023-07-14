package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.ExceptionMessage;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.domain.pieces.PieceFactory.*;

public class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
        chessGame.start();
    }

    @Test
    @DisplayName("게임이 시작한 직후에는 점수로 각각 38점을 가져야한다.")
    void calculatePoint() throws Exception {
        chessGame.start();

        assertEquals(38.0, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
        assertEquals(38.0, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
    }

    @Test
    @DisplayName("기물 이동 테스트")
    void move() {
        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");

        chessGame.move(sourcePosition, targetPosition);
        assertEquals(createBlank(), chessGame.findPiece(sourcePosition));
        assertEquals(createWhitePawn(), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("흑색 턴일 때 흰색 기물을 움직이려 하면 예외를 발생시킨다.")
    void verifyTurnBlack() {
        Position sourcePosition = new Position("a2");
        Position targetPosition = new Position("a3");
        chessGame.setTurn(Piece.Color.BLACK);

        Exception exception = assertThrows(IllegalCommandException.class, () -> chessGame.move(sourcePosition, targetPosition));
        assertEquals(exception.getMessage(), ExceptionMessage.TURN_EXCEPTION_MESSAGE);

    }

    @Test
    @DisplayName("흰색 턴일 때 흑색 기물을 움직이려 하면 예외를 발생시킨다.")
    void verifyTurnWhite() {
        chessGame.setTurn(Piece.Color.WHITE);

        Position sourcePosition = new Position("a7");
        Position targetPosition = new Position("a6");

        Exception exception = assertThrows(IllegalCommandException.class, () -> chessGame.move(sourcePosition, targetPosition));
        assertEquals(exception.getMessage(), ExceptionMessage.TURN_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @MethodSource("providePositionsForVerifyPwnDiagonalMove")
    @DisplayName("폰은 상대 기물이 없을 때 대각선 이동이 불가능하다.")
    void verifyPawnDiagonalMoveFail(Position targetPosition) {
        Position sourcePosition = new Position(1, 3);

        chessGame.setTurn(Piece.Color.BLACK);
        Exception exception = assertThrows(IllegalCommandException.class, () -> chessGame.move(sourcePosition, targetPosition));
        assertEquals(exception.getMessage(), ExceptionMessage.PAWN_DIAGONAL_MOVE_EXCEPTION_MESSAGE);
    }

    private static Stream<Arguments> providePositionsForVerifyPwnDiagonalMove() {
        return Stream.of(
                Arguments.of(new Position(2, 4)),
                Arguments.of(new Position(2, 2))
        );
    }

    @Test
    @DisplayName("폰은 상대 기물이 있을 때 대각선 이동이 가능하다.")
    void verifyPawnDiagonalMove() {
        Position sourcePosition = new Position(1, 1);
        Position targetPosition = new Position(2, 0);

        chessGame.setTurn(Piece.Color.WHITE);
        chessGame.move(new Position(6, 0), new Position(5, 0));
        chessGame.setTurn(Piece.Color.WHITE);
        chessGame.move(new Position(5, 0), new Position(4, 0));
        chessGame.setTurn(Piece.Color.WHITE);
        chessGame.move(new Position(4, 0), new Position(3, 0));
        chessGame.setTurn(Piece.Color.WHITE);
        chessGame.move(new Position(3, 0), targetPosition);

        chessGame.move(sourcePosition, targetPosition);
    }

    @Test
    @DisplayName("폰은 이동한 적이 없을 때만 double push할 수 있다.")
    void verifyPawnDoublePush() {
        Position sourcePosition = new Position(1, 3);

        chessGame.setTurn(Piece.Color.BLACK);
        chessGame.move(sourcePosition, new Position(3, 3));
        chessGame.setTurn(Piece.Color.BLACK);
        Exception exception = assertThrows(IllegalCommandException.class, () -> chessGame.move(new Position(3, 3), new Position(5, 3)));
        assertEquals(exception.getMessage(), ExceptionMessage.UNREACHABLE_POSITION_EXCEPTION_MESSAGE);
    }
}

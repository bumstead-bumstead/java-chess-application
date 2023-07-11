package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.exceptions.IllegalCommandException;
import softeer2nd.chess.domain.pieces.Blank;
import softeer2nd.chess.domain.pieces.Pawn;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.utils.ChessPositionParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessGameTest {

    private ChessGame chessGame;
    Piece.Color turn;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
        chessGame.start();
        turn = Piece.Color.BLACK;
    }
    @Test
    @DisplayName("게임이 시작한 직후에는 점수로 각각 38점을 가져야한다.")
    public void caculcatePoint() throws Exception {
        chessGame.start();

        assertEquals(38.0, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
        assertEquals(38.0, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
    }
//    @Test
//    @DisplayName("같은 열에 폰이 두 개 이상 있는 경우 개당 0.5점을 갖는다.")
//    public void calculatePointWithMultiplePawns() throws Exception {
//        chessGame.start();
//
//        chessGame.move("a2", "b7", turn);
//
//        assertEquals(37, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
//        assertEquals(37, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
//    }

    @Test
    @DisplayName("기물 이동 테스트")
    public void move() throws Exception {

        Position sourcePosition = ChessPositionParser.parse("b7");
        Position targetPosition = ChessPositionParser.parse("b6");

        chessGame.move(sourcePosition, targetPosition, turn);
        assertEquals(Blank.create(), chessGame.findPiece(sourcePosition));
        assertEquals(Pawn.createBlack(), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("흑색 턴일 때 흰색 기물을 움직이려 하면 예외를 발생시킨다.")
    void verifyTurnBlack() {
        Position sourcePosition = ChessPositionParser.parse("a2");
        Position targetPosition = ChessPositionParser.parse("a3");

        assertThrows(IllegalCommandException.class, () -> chessGame.move(sourcePosition, targetPosition, turn));
    }

    @Test
    @DisplayName("흰색 턴일 때 흑색 기물을 움직이려 하면 예외를 발생시킨다.")
    void verifyTurnWhite() {
        turn = Piece.Color.WHITE;
        Position sourcePosition = ChessPositionParser.parse("a7");
        Position targetPosition = ChessPositionParser.parse("a6");

        assertThrows(IllegalCommandException.class, () -> chessGame.move(sourcePosition, targetPosition, turn));
    }
}

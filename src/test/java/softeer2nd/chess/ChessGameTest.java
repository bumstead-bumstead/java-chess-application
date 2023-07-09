package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Blank;
import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
        chessGame.start();
    }
    @Test
    @DisplayName("게임이 시작한 직후에는 점수로 각각 38점을 가져야한다.")
    public void caculcatePoint() throws Exception {
        chessGame.start();

        assertEquals(38.0, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
        assertEquals(38.0, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
    }
    @Test
    @DisplayName("같은 열에 폰이 두 개 이상 있는 경우 개당 0.5점을 갖는다.")
    public void calculatePointWithMultiplePawns() throws Exception {
        chessGame.start();

        chessGame.move("a2", "b7");

        assertEquals(37, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
        assertEquals(37, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
    }

    @Test
    @DisplayName("기물 이동 테스트")
    public void move() throws Exception {

        String sourcePosition = "b2";
        String targetPosition = "b3";

        chessGame.move(sourcePosition, targetPosition);
        assertEquals(Blank.create(new Position(sourcePosition)), chessGame.findPiece(sourcePosition));
        assertEquals(Pawn.createWhite(new Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

}

package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
        board.initialize();
    }

    @Test
    @DisplayName("초기화된 보드의 상태를 확인한다.")
    public void create() throws Exception {
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("초기화된 상태의 보드의 검정색 폰의 개수는 8개를 반환해야한다.")
    public void countBlackPawn() {
        int numberOfBlackPawn = board.count(Piece.Color.BLACK, Piece.Type.PAWN);
        assertEquals(numberOfBlackPawn, 8);
    }

    @Test
    @DisplayName("초기화된 상태의 보드의 흰색 룩의 개수는 2개를 반환해야한다.")
    public void countWhiteRook() {
        int numberOfWhiteRook = board.count(Piece.Color.WHITE, Piece.Type.ROOK);
        assertEquals(numberOfWhiteRook, 2);
    }
}
package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    Piece whitePiece;
    Piece blackPiece;
    Position testPosition;

    @BeforeEach
    void init() {
        testPosition = new Position(0, 0);
        whitePiece = Bishop.createWhite(testPosition);
        blackPiece = Bishop.createBlack(testPosition);
    }

    @Test
    @DisplayName("생성된 기물의 field가 유효해야한다.")
    public void create_piece() {
        verifyPiece(Bishop.createBlack(testPosition), Piece.Color.BLACK, Piece.Type.BISHOP);
        verifyPiece(Rook.createBlack(testPosition), Piece.Color.BLACK, Piece.Type.ROOK);
        verifyPiece(King.createBlack(testPosition), Piece.Color.BLACK, Piece.Type.KING);
        verifyPiece(Knight.createBlack(testPosition), Piece.Color.BLACK, Piece.Type.KNIGHT);
        verifyPiece(Pawn.createBlack(testPosition), Piece.Color.BLACK, Piece.Type.PAWN);
        verifyPiece(Queen.createBlack(testPosition), Piece.Color.BLACK, Piece.Type.QUEEN);
        verifyPiece(Bishop.createWhite(testPosition), Piece.Color.WHITE, Piece.Type.BISHOP);
        verifyPiece(Rook.createWhite(testPosition), Piece.Color.WHITE, Piece.Type.ROOK);
        verifyPiece(King.createWhite(testPosition), Piece.Color.WHITE, Piece.Type.KING);
        verifyPiece(Knight.createWhite(testPosition), Piece.Color.WHITE, Piece.Type.KNIGHT);
        verifyPiece(Pawn.createWhite(testPosition), Piece.Color.WHITE, Piece.Type.PAWN);
        verifyPiece(Queen.createWhite(testPosition), Piece.Color.WHITE, Piece.Type.QUEEN);
    }

    @Test
    @DisplayName("기물의 색이 흰 색이면 isWhite()는 true를 반환해야 한다.")
    public void isWhiteTrue() {
        assertTrue(whitePiece.hasColor(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("기물의 색이 검은 색이면 isWhite는 false를 반환해야 한다.")
    public void isWhiteFalse() {
        assertFalse(blackPiece.hasColor(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("기물의 색이 검정 색이면 isBlack()는 true를 반환해야 한다.")
    public void isBlackTrue() {
        assertTrue(blackPiece.hasColor(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("기물의 색이 흰 색이면 isBlack은 false를 반환해야 한다.")
    public void isBlackFalse() {
        assertFalse(whitePiece.hasColor(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("흰색 폰이면 p를, 검정색 폰이면 P를 가져야한다.")
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }


}

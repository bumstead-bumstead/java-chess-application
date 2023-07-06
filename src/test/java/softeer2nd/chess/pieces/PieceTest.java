package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    Piece whitePiece;
    Piece blackPiece;

    @BeforeEach
    void init() {
        whitePiece = Piece.createWhiteBishop();
        blackPiece = Piece.createBlackBishop();
    }

    @Test
    @DisplayName("생성된 기물의 field가 유효해야한다.")
    public void create_piece() {
        verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP);
        verifyPiece(Piece.createBlackRook(), Piece.Color.BLACK, Piece.Type.ROOK);
        verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Type.KING);
        verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT);
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN);
        verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN);
        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP);
        verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK);
        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING);
        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT);
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN);
        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN);
    }

    @Test
    @DisplayName("기물의 색이 흰 색이면 isWhite()는 true를 반환해야 한다.")
    public void isWhiteTrue() {
        assertTrue(whitePiece.isWhite());
    }

    @Test
    @DisplayName("기물의 색이 검은 색이면 isWhite는 false를 반환해야 한다.")
    public void isWhiteFalse() {
        assertFalse(blackPiece.isWhite());
    }

    @Test
    @DisplayName("기물의 색이 검정 색이면 isBlack()는 true를 반환해야 한다.")
    public void isBlackTrue() {
        assertTrue(blackPiece.isBlack());
    }

    @Test
    @DisplayName("기물의 색이 흰 색이면 isBlack은 false를 반환해야 한다.")
    public void isBlackFalse() {
        assertFalse(whitePiece.isBlack());
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

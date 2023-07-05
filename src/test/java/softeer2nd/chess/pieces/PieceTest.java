package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.PieceColor;
import softeer2nd.chess.PieceRepresentation;
import softeer2nd.chess.PieceType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("생성된 기물의 field가 유효해야한다.")
    public void create_piece() {
        verifyPiece(Piece.createBlackBishop(), PieceColor.BLACK, PieceType.BISHOP, PieceRepresentation.BLACK_BISHOP);
        verifyPiece(Piece.createBlackRook(), PieceColor.BLACK, PieceType.ROOK, PieceRepresentation.BLACK_ROOK);
        verifyPiece(Piece.createBlackKing(), PieceColor.BLACK, PieceType.KING, PieceRepresentation.BLACK_KING);
        verifyPiece(Piece.createBlackKnight(), PieceColor.BLACK, PieceType.KNIGHT, PieceRepresentation.BLACK_KNIGHT);
        verifyPiece(Piece.createBlackPawn(), PieceColor.BLACK, PieceType.PAWN, PieceRepresentation.BLACK_PAWN);
        verifyPiece(Piece.createBlackQueen(), PieceColor.BLACK, PieceType.QUEEN, PieceRepresentation.BLACK_QUEEN);
        verifyPiece(Piece.createWhiteBishop(), PieceColor.WHITE, PieceType.BISHOP, PieceRepresentation.WHITE_BISHOP);
        verifyPiece(Piece.createWhiteRook(), PieceColor.WHITE, PieceType.ROOK, PieceRepresentation.WHITE_ROOK);
        verifyPiece(Piece.createWhiteKing(), PieceColor.WHITE, PieceType.KING, PieceRepresentation.WHITE_KING);
        verifyPiece(Piece.createWhiteKnight(), PieceColor.WHITE, PieceType.KNIGHT, PieceRepresentation.WHITE_KNIGHT);
        verifyPiece(Piece.createWhitePawn(), PieceColor.WHITE, PieceType.PAWN, PieceRepresentation.WHITE_PAWN);
        verifyPiece(Piece.createWhiteQueen(), PieceColor.WHITE, PieceType.QUEEN, PieceRepresentation.WHITE_QUEEN);
    }

    @Test
    @DisplayName("기물의 색이 흰 색이면 isWhite()는 true를 반환해야 한다.")
    public void isWhiteTrue() {
        Piece whitePiece = Piece.createWhiteBishop();

        assertTrue(whitePiece.isWhite());
    }

    @Test
    @DisplayName("기물의 색이 검은 색이면 isWhite는 false를 반환해야 한다.")
    public void isWhiteFalse() {
        Piece blackPiece = Piece.createBlackBishop();

        assertFalse(blackPiece.isWhite());
    }

    @Test
    @DisplayName("기물의 색이 검정 색이면 isBlack()는 true를 반환해야 한다.")
    public void isBlackTrue() {
        Piece blackPiece = Piece.createBlackBishop();

        assertTrue(blackPiece.isBlack());
    }

    @Test
    @DisplayName("기물의 색이 흰 색이면 isBlack은 false를 반환해야 한다.")
    public void isBlackFalse() {
        //given
        Piece whitePiece = Piece.createWhiteBishop();

        assertFalse(whitePiece.isBlack());
    }


    private void verifyPiece(final Piece piece, final PieceColor pieceColor, final PieceType pieceType, final PieceRepresentation pieceRepresentation) {
        assertEquals(pieceColor, piece.getPieceColor());
        assertEquals(pieceRepresentation, piece.getRepresentation());
        assertEquals(pieceType, piece.getPieceType());
    }
}

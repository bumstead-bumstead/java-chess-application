package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.PieceColor;
import softeer2nd.chess.PieceRepresentation;
import softeer2nd.chess.PieceType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private void verifyPiece(final Piece piece, final PieceColor pieceColor, final PieceType pieceType, final PieceRepresentation pieceRepresentation) {
        assertEquals(pieceColor, piece.getPieceColor());
        assertEquals(pieceRepresentation, piece.getRepresentation());
        assertEquals(pieceType, piece.getPieceType());
    }
}

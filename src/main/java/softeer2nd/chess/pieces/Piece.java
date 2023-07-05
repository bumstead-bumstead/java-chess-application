package softeer2nd.chess.pieces;

import softeer2nd.chess.PieceColor;
import softeer2nd.chess.PieceType;

public class Piece {
    public final static String COLOR_WHITE = "white";
    public final static String COLOR_BLACK = "black";
    public final static char REPRESENTATION_WHITE = 'p';
    public final static char REPRESENTATION_BLACK = 'P';

    private final PieceColor pieceColor;
    private final PieceType pieceType;

    private Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public static Piece createPiece(PieceColor pieceColor, PieceType pieceType) {
        return new Piece(pieceColor, pieceType);
    }

    public String getColor() {
        return this.pieceColor.getColor();
    }

    public char getRepresentation() {
        return this.pieceColor.getRepresentation();
    }

    @Override
    public String toString() {
        return String.valueOf(pieceColor.getRepresentation());
    }
}

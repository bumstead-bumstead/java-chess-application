package softeer2nd.chess.pieces;

import softeer2nd.chess.PieceColor;

public class Piece {
    public final static String COLOR_WHITE = "white";
    public final static String COLOR_BLACK = "black";
    public final static char REPRESENTATION_WHITE = 'p';
    public final static char REPRESENTATION_BLACK = 'P';

    private PieceColor pieceColor;

    public Piece() {
        this.pieceColor = PieceColor.valueOf(COLOR_WHITE.toUpperCase());
    }

    public Piece(String color) {
        this.pieceColor = PieceColor.valueOf(color.toUpperCase());
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

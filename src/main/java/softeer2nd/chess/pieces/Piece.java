package softeer2nd.chess.pieces;

import softeer2nd.chess.PieceColor;
import softeer2nd.chess.PieceRepresentation;
import softeer2nd.chess.PieceType;

public class Piece {
    public final static String COLOR_WHITE = "white";
    public final static String COLOR_BLACK = "black";
    public final static char REPRESENTATION_WHITE = 'p';
    public final static char REPRESENTATION_BLACK = 'P';

    private final PieceColor pieceColor;
    private final PieceType pieceType;
    private final PieceRepresentation representation;


    private Piece(PieceColor pieceColor, PieceType pieceType, PieceRepresentation representation) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.representation = representation;
    }

    private static Piece createPiece(PieceColor pieceColor, PieceType pieceType, PieceRepresentation pieceRepresentation) {
        return new Piece(pieceColor, pieceType, pieceRepresentation);
    }

    public static Piece createWhitePawn() {
        return new Piece(PieceColor.WHITE, PieceType.PAWN, PieceRepresentation.WHITE_PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(PieceColor.WHITE, PieceType.KNIGHT, PieceRepresentation.WHITE_KNIGHT);
    }

    public static Piece createWhiteKing() {
        return new Piece(PieceColor.WHITE, PieceType.KING, PieceRepresentation.WHITE_KING);
    }

    public static Piece createWhiteQueen() {
        return new Piece(PieceColor.WHITE, PieceType.QUEEN, PieceRepresentation.WHITE_QUEEN);
    }

    public static Piece createWhiteRook() {
        return new Piece(PieceColor.WHITE, PieceType.ROOK, PieceRepresentation.WHITE_ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(PieceColor.WHITE, PieceType.BISHOP, PieceRepresentation.WHITE_BISHOP);
    }

    public static Piece createBlackPawn() {
        return new Piece(PieceColor.BLACK, PieceType.PAWN, PieceRepresentation.BLACK_PAWN);
    }

    public static Piece createBlackKnight() {
        return new Piece(PieceColor.BLACK, PieceType.KNIGHT, PieceRepresentation.BLACK_KNIGHT);
    }

    public static Piece createBlackKing() {
        return new Piece(PieceColor.BLACK, PieceType.KING, PieceRepresentation.BLACK_KING);
    }

    public static Piece createBlackQueen() {
        return new Piece(PieceColor.BLACK, PieceType.QUEEN, PieceRepresentation.BLACK_QUEEN);
    }

    public static Piece createBlackRook() {
        return new Piece(PieceColor.BLACK, PieceType.ROOK, PieceRepresentation.BLACK_ROOK);
    }

    public static Piece createBlackBishop() {
        return new Piece(PieceColor.BLACK, PieceType.BISHOP, PieceRepresentation.BLACK_BISHOP);
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceRepresentation getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return representation.getRepresentation().toString();
    }
}

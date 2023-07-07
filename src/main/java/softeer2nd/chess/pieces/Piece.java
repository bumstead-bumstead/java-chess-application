package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1), ROOK('r', 5), KNIGHT('n', 2.5), BISHOP('b', 3), QUEEN('q', 9), KING('k', 0), NO_PIECE('.', 0);

        private char representation;
        private double score;

        Type(char representation, double score) {
            this.representation = representation;
            this.score = score;
        }

        public double getScore() {
            return score;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }

    private final Color color;
    private final Type type;
    private final Position position;



    private Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    private static Piece createWhite(Type type, Position position) {
        return new Piece(Color.WHITE, type, position);
    }

    public Position getPosition() {
        return position;
    }

    private static Piece createBlack(Type type, Position position) {
        return new Piece(Color.BLACK, type, position);
    }

    public static Piece createWhitePawn(Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlack(Type.PAWN, position);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createBlackKing(Position position) {
        return createBlack(Type.KING, position);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createBlackRook(Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createBlank(Position position) {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    public static Piece createMovedPiece(Piece piece, Position position) {
        return new Piece(piece.color, piece.type, position);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if (color.equals(Color.WHITE)) {
            return type.getWhiteRepresentation();
        }

        return type.getBlackRepresentation();
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }

    public boolean isPawn() {
        return this.type == Type.PAWN;
    }

    public boolean hasType(Type type) {
        return this.type == type;
    }

    public boolean isEmptyPiece() {
        return this.type == Type.NO_PIECE;
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                ", position=" + position +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }
}

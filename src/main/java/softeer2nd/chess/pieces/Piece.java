package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static softeer2nd.chess.Board.isValidPosition;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1),
        ROOK('r', 5),
        KNIGHT('n', 2.5),
        BISHOP('b', 3),
        QUEEN('q', 9),
        KING('k', 0),
        NO_PIECE('.', 0);

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

    public enum Direction {
        NORTH(0, 1),
        NORTHEAST(1, 1),
        EAST(1, 0),
        SOUTHEAST(1, -1),
        SOUTH(0, -1),
        SOUTHWEST(-1, -1),
        WEST(-1, 0),
        NORTHWEST(-1, 1),

        NNE(1, 2),
        NNW(-1, 2),
        SSE(1, -2),
        SSW(-1, -2),
        EEN(2, 1),
        EES(2, -1),
        WWN(-2, 1),
        WWS(-2, -1);

        private int xDegree;
        private int yDegree;

        private Direction(int xDegree, int yDegree) {
            this.xDegree = xDegree;
            this.yDegree = yDegree;
        }

        public int getXDegree() {
            return xDegree;
        }

        public int getYDegree() {
            return yDegree;
        }

        public static List<Direction> linearDirection() {
            return Arrays.asList(NORTH, EAST, SOUTH, WEST);
        }

        public static List<Direction> diagonalDirection() {
            return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
        }

        public static List<Direction> everyDirection() {
            return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
        }

        public static List<Direction> knightDirection() {
            return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
        }

        public static List<Direction> blackPawnDirection() {
            return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
        }

        public static List<Direction> whitePawnDirection() {
            return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
        }
    }

    protected final Color color;
    protected final Type type;
    protected final Position position;

    protected Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public abstract Piece createMovedPiece(Position position);

    public Position getPosition() {
        return position;
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

    public boolean hasType(Type type) {
        return this.type == type;
    }

    public boolean isEmptyPiece() {
        return this.type == Type.NO_PIECE;
    }

    //abstract void verifyMovePosition(Position position) throws RuntimeException;

    public void verifyMovePosition(Position targetPosition) throws RuntimeException {
        if (!isValidPosition(targetPosition) || !isReachablePosition(targetPosition)) {
            throw new OutOfPieceRangeException();
        }
    }

    abstract boolean isReachablePosition(Position targetPosition);

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

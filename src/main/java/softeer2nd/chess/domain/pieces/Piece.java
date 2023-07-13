package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.exceptions.ExceptionMessage;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static softeer2nd.chess.domain.Board.isValidPosition;

public abstract class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;

        public Color getReverseColor() {
            if (this == BLACK) {
                return WHITE;
            }
            if (this == WHITE) {
                return BLACK;
            }
            return NOCOLOR;
        }
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
        NORTH_TWICE(0, 2),
        NORTHEAST(1, 1),
        EAST(1, 0),
        SOUTHEAST(1, -1),
        SOUTH(0, -1),
        SOUTH_TWICE(0, -2),
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
        WWS(-2, -1),
        NO_DIRECTION(0, 0);

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
            return Arrays.asList(NORTH, NORTHEAST, NORTHWEST, NORTH_TWICE);
        }

        public static List<Direction> whitePawnDirection() {
            return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST, SOUTH_TWICE);
        }

        public static List<Direction> noDirection() {
            return List.of(NO_DIRECTION);
        }
    }

    protected final Color color;
    protected final Type type;
    protected final AvailableDirections availableDirections;


    public Piece(Color color, Type type, AvailableDirections availableDirections) {
        this.color = color;
        this.type = type;
        this.availableDirections = availableDirections;
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

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isEmptyPiece() {
        return this.type == Type.NO_PIECE;
    }

    public void verifyAlly(Piece piece) {
        if (this.isSameColor(piece.color)) {
            throw new IllegalCommandException(ExceptionMessage.CAPTURE_ALLY_EXCEPTION_MESSAGE);
        }
    }
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) throws RuntimeException {
        if (!isValidPosition(targetPosition) || !isReachablePosition(sourcePosition, targetPosition)) {
            throw new IllegalCommandException(ExceptionMessage.UNREACHABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    public abstract boolean isReachablePosition(Position sourcePosition, Position targetPosition);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}

package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;
import softeer2nd.chess.domain.Position;

import java.util.List;

public class Pawn extends NonSlidingPiece {
    private boolean hasMoved;

    public Pawn(Color color) {
        super(color, Type.PAWN, new AvailableDirections(getDirectionListByColor(color)));
        this.hasMoved = false;
    }

    private static List<Direction> getDirectionListByColor(Color color) {
        if (color == Color.WHITE) {
            return Direction.whitePawnDirection();
        }
        return Direction.blackPawnDirection();
    }

    public static void reflectMove(Piece piece) {
        Pawn pawn = (Pawn) piece;

        pawn.setHasMoved();
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        List<Direction> directions = Piece.Direction.blackPawnDirection();

        if (this.color == Color.WHITE) directions = Piece.Direction.whitePawnDirection();

        boolean isReachable = directions.stream()
                .anyMatch(direction -> canMoveToTargetPosition(sourcePosition, targetPosition, direction));

        return isReachable;
    }

    private boolean canMoveToTargetPosition(Position sourcePosition, Position targetPosition, Direction direction) {
        int row = sourcePosition.getRow() + direction.getYDegree();
        int column = sourcePosition.getColumn() + direction.getXDegree();
        Position possiblePosition = new Position(row, column);

        if (isPositionSame(targetPosition, possiblePosition) && !canNotDoublePawnPush(direction)) {
            return true;
        }
        return false;
    }

    private boolean canNotDoublePawnPush(Direction direction) {
        return hasMoved && isDoubleMove(direction);
    }

    private static boolean isPositionSame(Position targetPosition, Position possiblePosition) {
        return possiblePosition.equals(targetPosition);
    }

    private void setHasMoved() {
        this.hasMoved = true;
    }

    private static boolean isDoubleMove(Direction direction) {
        return direction == Direction.NORTH_TWICE || direction == Direction.SOUTH_TWICE;
    }
}

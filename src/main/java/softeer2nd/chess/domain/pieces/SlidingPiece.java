package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;
import softeer2nd.chess.domain.Position;

import static softeer2nd.chess.domain.Board.isValidPosition;

public class SlidingPiece extends Piece {
    protected SlidingPiece(Color color, Type type, AvailableDirections availableDirections) {
        super(color, type, availableDirections);
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        for (Piece.Direction direction : availableDirections.get()) {
            if (isReachableInDirection(sourcePosition, targetPosition, direction)) return true;
        }
        return false;
    }

    private boolean isReachableInDirection(Position sourcePosition, Position targetPosition, Direction direction) {
        int row = sourcePosition.getRow() + direction.getYDegree();
        int column = sourcePosition.getColumn() + direction.getXDegree();
        Position possiblePosition = new Position(row, column);

        while (isValidPosition(possiblePosition)) {
            if (possiblePosition.equals(targetPosition)) return true;
            row += direction.getYDegree();
            column += direction.getXDegree();

            possiblePosition = new Position(row, column);
        }
        return false;
    }
}

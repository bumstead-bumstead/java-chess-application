package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;
import softeer2nd.chess.domain.Position;

public class NonSlidingPiece extends Piece {
    protected NonSlidingPiece(Color color, Type type, AvailableDirections availableDirections) {
        super(color, type, availableDirections);
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        for (Direction direction : availableDirections.getAvailableDirections()) {
            int row = sourcePosition.getRow() + direction.getYDegree();
            int column = sourcePosition.getColumn() + direction.getXDegree();
            Position possiblePosition = new Position(row, column);

            if (possiblePosition.equals(targetPosition)) return true;
        }
        return false;
    }
}

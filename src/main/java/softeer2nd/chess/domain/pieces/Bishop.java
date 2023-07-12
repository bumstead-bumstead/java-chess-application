package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

import static softeer2nd.chess.domain.Board.isValidPosition;

public class Bishop extends Piece {

    protected Bishop(Piece.Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public Piece createMovedPiece() {
        return new Bishop(this.getColor());
    }
    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        List<Direction> directions = Piece.Direction.diagonalDirection();

        for (Piece.Direction direction : directions) {
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

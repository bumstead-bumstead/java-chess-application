package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

import static softeer2nd.chess.domain.Board.isValidPosition;

public class Rook extends Piece {
    public Rook(Piece.Color color) {
        super(color, Piece.Type.ROOK);
    }

    @Override
    public Piece createMovedPiece() {
        return new Rook(this.getColor());
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        List<Piece.Direction> directions = Piece.Direction.linearDirection();

        for (Piece.Direction direction : directions) {
            if (isReachableInDirection(sourcePosition, targetPosition, direction)) return true;
        }
        return false;
    }

    private boolean isReachableInDirection(Position sourcePosition, Position targetPosition, Piece.Direction direction) {
        int row = sourcePosition.getRow() + direction.getYDegree();
        int column = sourcePosition.getColumn() + direction.getXDegree();

        Position possiblePosition = new Position(row, column);

        while (isValidPosition(possiblePosition)) {
            if (possiblePosition.equals(targetPosition)) return true;

            possiblePosition = new Position(row += direction.getYDegree(), column += direction.getXDegree());
        }
        return false;
    }
}

package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

public class King extends Piece {
    public King(Color color) {
        super(color, Type.KING);
    }

    @Override
    public Piece createMovedPiece() {
        return new King(this.getColor());
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        List<Direction> directions = Piece.Direction.everyDirection();

        for (Direction direction : directions) {
            int row = sourcePosition.getRow() + direction.getYDegree();
            int column = sourcePosition.getColumn() + direction.getXDegree();
            Position possiblePosition = new Position(row, column);

            if (possiblePosition.equals(targetPosition)) return true;
        }
        return false;
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }

    public static King createBlack() {
        return new King(Color.BLACK);
    }
}

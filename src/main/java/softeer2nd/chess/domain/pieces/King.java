package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    public Piece createMovedPiece(Position position) {
        return new King(this.getColor(), position);
    }

    protected boolean isReachablePosition(Position targetPosition) {
        List<Direction> directions = Piece.Direction.everyDirection();

        for (Direction direction : directions) {
            int row = this.position.getRow() + direction.getYDegree();
            int column = this.position.getColumn() + direction.getXDegree();
            Position possiblePosition = new Position(row, column);

            if (possiblePosition.equals(targetPosition)) return true;
        }
        return false;
    }

    public static King createWhite(Position position) {
        return new King(Color.WHITE, position);
    }

    public static King createBlack(Position position) {
        return new King(Color.BLACK, position);
    }
}

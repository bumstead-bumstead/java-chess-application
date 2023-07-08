package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.List;

import static softeer2nd.chess.Board.isValidPosition;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    Piece createMovedPiece(Position position) {
        return new King(this.getColor(), position);
    }

    @Override
    boolean verifyMovePosition(Position targetPosition) {
        return isValidPosition(targetPosition) && isReachablePosition(targetPosition);
    }


    private boolean isReachablePosition(Position targetPosition) {
        List<Direction> directions = Piece.Direction.everyDirection();

        for (Direction direction : directions) {
            int row = targetPosition.getRow() + direction.getYDegree();
            int column = targetPosition.getColumn() + direction.getXDegree();
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

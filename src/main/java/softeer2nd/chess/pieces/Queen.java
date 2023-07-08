package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.List;

import static softeer2nd.chess.Board.isValidPosition;

public class Queen extends Piece {
    public Queen(Piece.Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    @Override
    Piece createMovedPiece(Position position) {
        return new Queen(this.getColor(), position);
    }

    @Override
    boolean verifyMovePosition(Position targetPosition) {
        return isValidPosition(targetPosition) && isReachablePosition(targetPosition);
    }

    private boolean isReachablePosition(Position targetPosition) {
        List<Piece.Direction> directions = Piece.Direction.everyDirection();

        for (Piece.Direction direction : directions) {
            if (isReachableInDirection(targetPosition, direction)) return true;
        }
        return false;
    }

    private boolean isReachableInDirection(Position targetPosition, Direction direction) {
        int row = targetPosition.getRow() + direction.getYDegree();
        int column = targetPosition.getColumn() + direction.getXDegree();
        Position possiblePosition = new Position(row, column);

        while (isValidPosition(possiblePosition)) {
            if (possiblePosition.equals(targetPosition)) return true;

            possiblePosition = new Position(row += direction.getYDegree(), column += direction.getXDegree());
        }
        return false;
    }

    public static Queen createWhite(Position position) {
        return new Queen(Piece.Color.WHITE, position);
    }

    public static Queen createBlack(Position position) {
        return new Queen(Piece.Color.BLACK, position);
    }
}

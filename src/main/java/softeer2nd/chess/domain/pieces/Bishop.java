package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

import static softeer2nd.chess.domain.Board.isValidPosition;

public class Bishop extends Piece {

    public Bishop(Piece.Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    @Override
    public Piece createMovedPiece(Position position) {
        return new Bishop(this.getColor(), position);
    }
    @Override
    protected boolean isReachablePosition(Position targetPosition) {
        List<Direction> directions = Piece.Direction.diagonalDirection();

        for (Piece.Direction direction : directions) {
            if (isReachableInDirection(targetPosition, direction)) return true;
        }
        return false;
    }

    private boolean isReachableInDirection(Position targetPosition, Direction direction) {
        int row = this.position.getRow() + direction.getYDegree();
        int column = this.position.getColumn() + direction.getXDegree();
        Position possiblePosition = new Position(row, column);

        while (isValidPosition(possiblePosition)) {
            if (possiblePosition.equals(targetPosition)) return true;

            possiblePosition = new Position(row += direction.getYDegree(), column += direction.getXDegree());
        }
        return false;
    }

    public static Bishop createWhite(Position position) {
        return new Bishop(Piece.Color.WHITE, position);
    }

    public static Bishop createBlack(Position position) {
        return new Bishop(Piece.Color.BLACK, position);
    }
}

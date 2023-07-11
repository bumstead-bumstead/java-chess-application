package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

import static softeer2nd.chess.domain.Board.isValidPosition;

public class Queen extends Piece {
    public Queen(Piece.Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public Piece createMovedPiece() {
        return new Queen(this.getColor());
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        List<Piece.Direction> directions = Piece.Direction.everyDirection();

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

            possiblePosition = new Position(row += direction.getYDegree(), column += direction.getXDegree());
        }
        return false;
    }

    public static Queen createWhite() {
        return new Queen(Piece.Color.WHITE);
    }

    public static Queen createBlack() {
        return new Queen(Piece.Color.BLACK);
    }
}

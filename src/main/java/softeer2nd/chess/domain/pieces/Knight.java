package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight(Piece.Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public Piece createMovedPiece() {
        return new Knight(this.getColor());
    }


    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        List<Direction> directions = Piece.Direction.knightDirection();

        for (Direction direction : directions) {
            int row = sourcePosition.getRow() + direction.getYDegree();
            int column = sourcePosition.getColumn() + direction.getXDegree();
            Position possiblePosition = new Position(row, column);

            if (possiblePosition.equals(targetPosition)) return true;
        }
        return false;
    }

    public static Knight createWhite() {
        return new Knight(Piece.Color.WHITE);
    }

    public static Knight createBlack() {
        return new Knight(Piece.Color.BLACK);
    }
}

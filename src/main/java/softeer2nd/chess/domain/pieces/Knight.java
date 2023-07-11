package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight(Piece.Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    @Override
    public Piece createMovedPiece(Position position) {
        return new Knight(this.getColor(), position);
    }


    protected boolean isReachablePosition(Position targetPosition) {
        List<Direction> directions = Piece.Direction.knightDirection();

        for (Direction direction : directions) {
            int row = this.position.getRow() + direction.getYDegree();
            int column = this.position.getColumn() + direction.getXDegree();
            Position possiblePosition = new Position(row, column);

            if (possiblePosition.equals(targetPosition)) return true;
        }
        return false;
    }

    public static Knight createWhite(Position position) {
        return new Knight(Piece.Color.WHITE, position);
    }

    public static Knight createBlack(Position position) {
        return new Knight(Piece.Color.BLACK, position);
    }
}

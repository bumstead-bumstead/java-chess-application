package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.List;

import static softeer2nd.chess.Board.isValidPosition;

public class Rook extends Piece {
    public Rook(Piece.Color color, Position position) {
        super(color, Piece.Type.ROOK, position);
    }

    @Override
    public Piece createMovedPiece(Position position) {
        return new Rook(this.getColor(), position);
    }


    protected boolean isReachablePosition(Position targetPosition) {
        List<Piece.Direction> directions = Piece.Direction.linearDirection();

        for (Piece.Direction direction : directions) {
            if (isReachableInDirection(targetPosition, direction)) return true;
        }
        return false;
    }

    private boolean isReachableInDirection(Position targetPosition, Piece.Direction direction) {
        int row = this.position.getRow() + direction.getYDegree();
        int column = this.position.getColumn() + direction.getXDegree();

        Position possiblePosition = new Position(row, column);

        while (isValidPosition(possiblePosition)) {
            if (possiblePosition.equals(targetPosition)) return true;

            possiblePosition = new Position(row += direction.getYDegree(), column += direction.getXDegree());
        }
        return false;
    }

    public static Rook createWhite(Position position) {
        return new Rook(Piece.Color.WHITE, position);
    }

    public static Rook createBlack(Position position) {
        return new Rook(Piece.Color.BLACK, position);
    }

}

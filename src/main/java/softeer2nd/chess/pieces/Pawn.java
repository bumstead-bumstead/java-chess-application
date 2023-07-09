package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.List;

public class Pawn extends Piece {
    /*
    * todo
    *  1. 앙파상, 프로모션 행마법 구현
    *  2. 기물 잡기 구현 -> (board단에서 확인해야할 듯)
    *  3. 첫 단계 두 개 뛰기 구현
    * */
    public Pawn(Piece.Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    @Override
    public Piece createMovedPiece(Position position) {
        return new Pawn(this.getColor(), position);
    }

    protected boolean isReachablePosition(Position targetPosition) {
        List<Direction> directions = Piece.Direction.blackPawnDirection();
        if (this.color == Color.WHITE) directions = Piece.Direction.whitePawnDirection();

        for (Direction direction : directions) {
            int row = this.position.getRow() + direction.getYDegree();
            int column = this.position.getColumn() + direction.getXDegree();
            Position possiblePosition = new Position(row, column);

            if (possiblePosition.equals(targetPosition)) return true;
        }
        return false;
    }

    public static Pawn createWhite(Position position) {
        return new Pawn(Piece.Color.WHITE, position);
    }

    public static Pawn createBlack(Position position) {
        return new Pawn(Piece.Color.BLACK, position);
    }
}

package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.exceptions.BlankMoveException;

public class Blank extends Piece {
    protected Blank(Color color, Type type) {
        super(color, type);
    }

    @Override
    public Piece createMovedPiece() {
        return null;
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition)throws RuntimeException {
        throw new BlankMoveException();
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        return false;
    }

    public static Blank create() {
        return new Blank(Color.NOCOLOR, Type.NO_PIECE);
    }
}

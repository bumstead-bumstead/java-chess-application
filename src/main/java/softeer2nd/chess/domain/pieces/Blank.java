package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.exceptions.BlankMoveException;

import java.util.Collections;

public class Blank extends NonSlidingPiece {

    protected Blank() {
        super(Color.NOCOLOR, Type.NO_PIECE, new AvailableDirections(Collections.singletonList(Direction.NO_DIRECTION)));
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition)throws RuntimeException {
        throw new BlankMoveException();
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        return false;
    }
}

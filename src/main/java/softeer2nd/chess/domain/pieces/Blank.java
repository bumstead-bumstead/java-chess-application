package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.exceptions.ExceptionMessage;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.Collections;

public class Blank extends NonSlidingPiece {

    protected Blank() {
        super(Color.NOCOLOR, Type.NO_PIECE, new AvailableDirections(Collections.singletonList(Direction.NO_DIRECTION)));
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition)throws RuntimeException {
        throw new IllegalCommandException(ExceptionMessage.NO_PIECE_MOVE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isReachablePosition(Position sourcePosition, Position targetPosition) {
        return false;
    }
}

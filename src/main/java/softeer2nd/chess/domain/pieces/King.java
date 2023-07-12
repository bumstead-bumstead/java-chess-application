package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;

public class King extends NonSlidingPiece {
    public King(Color color) {
        super(color, Type.KING, new AvailableDirections(Direction.everyDirection()));
    }
}

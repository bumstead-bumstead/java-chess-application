package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;

public class Queen extends SlidingPiece {
    public Queen(Piece.Color color) {
        super(color, Type.QUEEN, new AvailableDirections(Direction.everyDirection()));
    }
}

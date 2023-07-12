package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;

public class Bishop extends SlidingPiece {

    protected Bishop(Piece.Color color) {
        super(color, Type.BISHOP, new AvailableDirections(Direction.diagonalDirection()));
    }
}

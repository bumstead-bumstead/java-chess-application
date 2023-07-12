package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;

public class Rook extends SlidingPiece {
    public Rook(Piece.Color color) {
        super(color, Piece.Type.ROOK, new AvailableDirections(Direction.linearDirection()));
    }
}

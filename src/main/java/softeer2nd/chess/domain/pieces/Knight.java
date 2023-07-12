package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.AvailableDirections;

public class Knight extends NonSlidingPiece {
    public Knight(Piece.Color color) {
        super(color, Type.KNIGHT, new AvailableDirections(Direction.knightDirection()));
    }
}

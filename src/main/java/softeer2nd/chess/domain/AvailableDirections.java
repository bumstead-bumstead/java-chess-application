package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public class AvailableDirections {
    List<Piece.Direction> availableDirections;

    public AvailableDirections(List <Piece.Direction> list) {
        this.availableDirections = list;
    }

    public List<Piece.Direction> getAvailableDirections() {
        return availableDirections;
    }

    public List<Piece.Direction> get() {
        return availableDirections;
    }
}

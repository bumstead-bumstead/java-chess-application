package softeer2nd.chess;

public enum PieceRepresentation {
    WHITE_KING('k'),
    WHITE_QUEEN('q'),
    WHITE_ROOK('r'),
    WHITE_BISHOP('b'),
    WHITE_KNIGHT('n'),
    WHITE_PAWN('p'),
    BLACK_KING('K'),
    BLACK_QUEEN('Q'),
    BLACK_ROOK('R'),
    BLACK_BISHOP('B'),
    BLACK_KNIGHT('N'),
    BLACK_PAWN('P');

    PieceRepresentation(Character representation) {
        this.representation = representation;
    }

    public Character getRepresentation() {
        return representation;
    }

    private final Character representation;
}

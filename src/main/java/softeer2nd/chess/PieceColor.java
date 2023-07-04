package softeer2nd.chess;

public enum PieceColor {
    WHITE("white", 'p'), BLACK("black", 'P');

    private final String color;
    private final char representation;

    PieceColor(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }
}

package softeer2nd.chess.pieces;

public class Pawn {
    public final static String COLOR_WHITE = "white";
    public final static String COLOR_BLACK = "black";

    private String color;

    public Pawn() {
        this.color = COLOR_WHITE;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Pawn(String color) {
        this.color = color;
    }
}

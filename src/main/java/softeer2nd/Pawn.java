package softeer2nd;

public class Pawn {
    final static String COLOR_WHITE = "white";
    final static String COLOR_BLACK = "black";

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

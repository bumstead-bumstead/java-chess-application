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

    //todo : white, black이 아닌 입력에 대한 예외처리?
    public void setColor(String color) {
        this.color = color;
    }

    public Pawn(String color) {
        this.color = color;
    }
}

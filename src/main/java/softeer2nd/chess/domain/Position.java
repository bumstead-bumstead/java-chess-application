package softeer2nd.chess.domain;

import softeer2nd.chess.utils.ChessPositionParser;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position(String position) {
        this.row = ChessPositionParser.parseRank(position.charAt(1));
        this.column = ChessPositionParser.parseFile(position.charAt(0));
    }

    public void transfer(int rowDifference, int columnDifference) {
        row += rowDifference;
        column += columnDifference;
    }

    public boolean isDiagonal(Position targetPosition) {
        int rowDifference = Math.abs(this.row - targetPosition.row);
        int columnDifference = Math.abs(this.column - targetPosition.column);

        return rowDifference == columnDifference && rowDifference > 0;
    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isVertical(Position targetPosition) {
        int rowDifference = Math.abs(this.row - targetPosition.row);
        int columnDifference = Math.abs(this.column - targetPosition.column);

        return rowDifference == 0 || columnDifference == 0;
    }
}


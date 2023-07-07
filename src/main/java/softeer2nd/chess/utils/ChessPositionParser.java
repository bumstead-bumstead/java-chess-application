package softeer2nd.chess.utils;

import softeer2nd.chess.Position;

public class ChessPositionParser {
    private final static int BOARD_LENGTH = 8;

    public static Position parse(String position) {
        int row = parseRank(position.charAt(1));
        int column = parseFile(position.charAt(0));

        return new Position(row, column);
    }
    public static int parseRank(char characterRank) {
        int rank = Character.getNumericValue(characterRank);
        return BOARD_LENGTH - rank;
    }

    public static int parseFile(char file) {
        return file - 'a';
    }
}

package softeer2nd.chess.utils;

public class ChessCoordinationParser {
    private final static int BOARD_LENGTH = 8;
    public static int parseRank(char characterRank) {
        int rank = Character.getNumericValue(characterRank);
        return BOARD_LENGTH - rank;
    }

    public static int parseFile(char file) {
        return file - 'a';
    }
}

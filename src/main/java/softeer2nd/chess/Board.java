package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final static int BOARD_LINE_UNIT = 8;
    private final static int BLACK_PAWN_LINE_NUMBER = 1;
    private final static int WHITE_PAWN_LINE_NUMBER = 6;
    private List<Pawn> pawns;

    public Board() {
        this.pawns = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < 64; i++) {
            int currentLineNumber = i / BOARD_LINE_UNIT;

            fillCurrentLocation(currentLineNumber);
        }
    }

    public String print() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 64; i++) {
            result.append(getRepresentationOfCurrentLocation(i));

            if (isEndOfLine(i)) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int i) {
        return pawns.get(i);
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public void setPawns(List<Pawn> pawns) {
        this.pawns = pawns;
    }

    public String getWhitePawnsResult() {
        StringBuilder result = new StringBuilder();
        int startLocation = getWhitePawnStartLocation();

        for (int i = startLocation; i < startLocation + BOARD_LINE_UNIT; i++) {
            result.append(pawns.get(i).getRepresentation());
        }

        return result.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder result = new StringBuilder();
        int startLocation = getBlackPawnStartLocation();

        for (int i = startLocation; i < startLocation + BOARD_LINE_UNIT; i++) {
            result.append(pawns.get(i).getRepresentation());
        }

        return result.toString();
    }

    private static int getWhitePawnStartLocation() {
        return WHITE_PAWN_LINE_NUMBER * BOARD_LINE_UNIT;
    }

    private static int getBlackPawnStartLocation() {
        return BLACK_PAWN_LINE_NUMBER * BOARD_LINE_UNIT;
    }

    private char getRepresentationOfCurrentLocation(int location) {
        Pawn pawn = pawns.get(location);

        if (pawn == null) {
            return '.';
        }

        return pawn.getRepresentation();
    }

    private static boolean isEndOfLine(int i) {
        return i % BOARD_LINE_UNIT == 7;
    }

    private void fillCurrentLocation(int currentLineNumber) {
        if (currentLineNumber == BLACK_PAWN_LINE_NUMBER) {
            pawns.add(new Pawn(Pawn.COLOR_BLACK));
            return;
        }

        if (currentLineNumber == WHITE_PAWN_LINE_NUMBER) {
            pawns.add(new Pawn(Pawn.COLOR_WHITE));
            return;
        }
        pawns.add(null);
    }
}

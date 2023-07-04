package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final static int BOARD_LINE_UNIT = 8;
    private final static int BLACK_PAWN_LINE_NUMBER = 1;
    private final static int WHITE_PAWN_LINE_NUMBER = 6;
    private List<List<Pawn>> pawns;

    public Board() {
        this.pawns = new ArrayList<>();
    }

    public void initialize() {
        pawns.clear();

        for (int i = 0; i < BOARD_LINE_UNIT; i++) {
            fillCurrentRow(i, getCurrentPawn(i));
        }
    }

    private void fillCurrentRow(int i, Pawn pawn) {
        List<Pawn> line = new ArrayList<>();

        for (int j = 0; j < BOARD_LINE_UNIT; j++) {
            line.add(pawn);
        }

        pawns.add(line);
    }

    private static Pawn getCurrentPawn(int i) {
        Pawn pawn = null;
        if (i == BLACK_PAWN_LINE_NUMBER) {
            pawn = new Pawn(Pawn.COLOR_BLACK);
        }
        if (i == WHITE_PAWN_LINE_NUMBER) {
            pawn = new Pawn(Pawn.COLOR_WHITE);
        }

        return pawn;
    }

    public String print() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < BOARD_LINE_UNIT; i++) {
            List<Pawn> line = pawns.get(i);

            result.append(joinListToString(line));
            result.append("\n");
        }

        return result.toString();
    }

    private String joinListToString(List<Pawn> line) {
        StringBuilder result = new StringBuilder();

        for (Pawn pawn : line) {
            if (pawn == null) {
                result.append(".");
                continue;
            }
            result.append(pawn);
        }

        return result.toString();
    }

    public int size() {
        return pawns.size();
    }

    public List<List<Pawn>> getPawns() {
        return pawns;
    }

    public String getWhitePawnsResult() {
        StringBuilder result = new StringBuilder();
        List<Pawn> whitePawnRow = pawns.get(WHITE_PAWN_LINE_NUMBER);

        whitePawnRow.forEach(result::append);

        return result.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder result = new StringBuilder();
        List<Pawn> blackPawnRow = pawns.get(BLACK_PAWN_LINE_NUMBER);

        blackPawnRow.forEach(result::append);

        return result.toString();
    }
}

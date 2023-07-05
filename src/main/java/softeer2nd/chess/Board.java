package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final static int BOARD_LINE_UNIT = 8;
    private final static int BLACK_PAWN_LINE_NUMBER = 1;
    private final static int WHITE_PAWN_LINE_NUMBER = 6;
    private List<List<Piece>> pieces;

    public Board() {
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        pieces.clear();

        for (int i = 0; i < BOARD_LINE_UNIT; i++) {
            fillCurrentRow(i, getCurrentPiece(i));
        }
    }

    private void fillCurrentRow(int i, Piece piece) {
        List<Piece> line = new ArrayList<>();

        for (int j = 0; j < BOARD_LINE_UNIT; j++) {
            line.add(piece);
        }

        pieces.add(line);
    }

    private static Piece getCurrentPiece(int i) {
        Piece piece = null;
        if (i == BLACK_PAWN_LINE_NUMBER) {
            piece = new Piece(Piece.COLOR_BLACK);
        }
        if (i == WHITE_PAWN_LINE_NUMBER) {
            piece = new Piece(Piece.COLOR_WHITE);
        }

        return piece;
    }

    public String print() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < BOARD_LINE_UNIT; i++) {
            List<Piece> line = pieces.get(i);

            result.append(appendNewLine(joinListToString(line)));
            result.append("\n");
        }

        return result.toString();
    }

    private String joinListToString(List<Piece> line) {
        StringBuilder result = new StringBuilder();

        for (Piece piece : line) {
            if (piece == null) {
                result.append(".");
                continue;
            }
            result.append(piece);
        }

        return result.toString();
    }

    public int size() {
        return pieces.size();
    }

    public List<List<Piece>> getPieces() {
        return pieces;
    }

    public String getWhitePawnsResult() {
        StringBuilder result = new StringBuilder();
        List<Piece> whitePawnRow = pieces.get(WHITE_PAWN_LINE_NUMBER);

        whitePawnRow.forEach(result::append);

        return result.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder result = new StringBuilder();
        List<Piece> blackPawnRow = pieces.get(BLACK_PAWN_LINE_NUMBER);

        blackPawnRow.forEach(result::append);

        return result.toString();
    }
}

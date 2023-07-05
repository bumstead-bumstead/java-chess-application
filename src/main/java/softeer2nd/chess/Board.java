package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final static int BOARD_LENGTH = 8;
    private final static int BLACK_PAWN_LINE_NUMBER = 1;
    private final static int WHITE_PAWN_LINE_NUMBER = 6;
    private List<List<Piece>> pieces;


    public Board() {
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        pieces.clear();

        fillFirstBlackRow();
        fillOneRow(Piece.createBlackPawn());
        fillOneRow(null);
        fillOneRow(null);
        fillOneRow(null);
        fillOneRow(null);
        fillOneRow(Piece.createWhitePawn());
        fillFirstWhiteRow();
    }

    private void fillFirstBlackRow() {
        List<Piece> row = new ArrayList<>();
        row.add(Piece.createBlackRook());
        row.add(Piece.createBlackKnight());
        row.add(Piece.createBlackBishop());
        row.add(Piece.createBlackQueen());
        row.add(Piece.createBlackKing());
        row.add(Piece.createBlackBishop());
        row.add(Piece.createBlackKnight());
        row.add(Piece.createBlackRook());

        pieces.add(row);
    }

    private void fillFirstWhiteRow() {
        List<Piece> row = new ArrayList<>();
        row.add(Piece.createWhiteRook());
        row.add(Piece.createWhiteKnight());
        row.add(Piece.createWhiteBishop());
        row.add(Piece.createWhiteQueen());
        row.add(Piece.createWhiteKing());
        row.add(Piece.createWhiteBishop());
        row.add(Piece.createWhiteKnight());
        row.add(Piece.createWhiteRook());

        pieces.add(row);
    }

    private void fillOneRow(Piece piece) {
        List<Piece> row = new ArrayList<>();

        for (int index = 0; index < BOARD_LENGTH; index++) {
            row.add(piece);
        }

        pieces.add(row);
    }

    public String print() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < BOARD_LENGTH; i++) {
            List<Piece> line = pieces.get(i);

            result.append(appendNewLine(joinListToString(line)));
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

    public String showBoard() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < BOARD_LENGTH; i++) {
            List<Piece> line = pieces.get(i);

            result.append(appendNewLine(joinListToString(line)));
        }

        return result.toString();

    }

    public int pieceCount() {
        int numberOfPieces = 0;

        for (int row = 0; row < BOARD_LENGTH; row++) {
            numberOfPieces += getNumberOfPiecesOfOneRow(row);
        }

        return numberOfPieces;
    }

    private int getNumberOfPiecesOfOneRow(int row) {
        int numberOfPieces = 0;
        for (int column = 0; column < BOARD_LENGTH; column++) {
            if (pieces.get(row).get(column) == null) {
                continue;
            }
            numberOfPieces++;
        }
        return numberOfPieces;
    }
}

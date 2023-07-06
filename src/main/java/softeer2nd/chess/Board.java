package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.utils.ChessCoordinationParser;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {

    private final static int BOARD_LENGTH = 8;
    private List<List<Piece>> pieces;


    public Board() {
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        pieces.clear();

        fillFirstBlackRow();
        fillOneRow(Piece.createBlackPawn());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createWhitePawn());
        fillFirstWhiteRow();
    }

    public void initializeEmpty() {
        pieces.clear();

        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
        fillOneRow(Piece.createBlank());
    }

    public void move(String request, Piece piece) {
        Position position = ChessCoordinationParser.parse(request);

        pieces.get(position.getRow()).set(position.getColumn(), piece);
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

    //각 Rank를 클래스로 래핑하게 되면, 행 별 count를 Rank 객체에서 유지하는 것이 좋을 것 같다.

    public int count(Piece.Color color, Piece.Type type) {
        int number = 0;

        for (int rank = 0; rank < BOARD_LENGTH; rank++) {
            number += countOneRank(color, type, rank);
        }

        return number;
    }

    public Piece findPiece(String request) {
        Position position = ChessCoordinationParser.parse(request);
        return pieces.get(position.getRow()).get(position.getColumn());
    }

    public double calculatePoint(Piece.Color targetColor) {
        double totalPoint = 0;

        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int column = 0; column < BOARD_LENGTH; column++) {
                Piece targetPiece = pieces.get(row).get(column);
                totalPoint += calculatePointOfPiece(targetPiece, targetColor, column);
            }
        }

        return totalPoint;
    }

    private double calculatePointOfPiece(Piece piece, Piece.Color targetColor, int column) {
        double point = 0;

        if (isSameColor(targetColor, piece)) {
            point = piece.getType().getScore();
        }
        if (isPawn(piece) && hasMultiplePawnsInColumn(targetColor, column)) {
            point = 0.5;
        }

        return point;
    }

    private boolean hasMultiplePawnsInColumn(Piece.Color color, int column) {
        int pawnCount = 0;

        for (int row = 0; row < BOARD_LENGTH; row++) {
            Piece targetPiece = pieces.get(row).get(column);

            if (isPawn(targetPiece) && isSameColor(color, targetPiece)) {
                pawnCount++;
            }
        }

        return pawnCount > 1;
    }

    private static boolean isSameColor(Piece.Color color, Piece targetPiece) {
        return targetPiece.getColor() == color;
    }

    private static boolean isPawn(Piece targetPiece) {
        return targetPiece.getType() == Piece.Type.PAWN;
    }

    private int countOneRank(Piece.Color color, Piece.Type type, int rank) {
        int number = 0;

        for (int file = 0; file < BOARD_LENGTH; file++) {
            Piece piece = pieces.get(rank).get(file);

            if (piece.getType().equals(type) && piece.getColor().equals(color)) {
                number++;
            }
        }
        return number;
    }

    private int getNumberOfPiecesOfOneRow(int row) {
        int numberOfPieces = 0;
        for (int column = 0; column < BOARD_LENGTH; column++) {
            if (pieces.get(row).get(column).getType().equals(Piece.Type.NO_PIECE)) {
                continue;
            }
            numberOfPieces++;
        }
        return numberOfPieces;
    }

    private String joinListToString(List<Piece> line) {
        StringBuilder result = new StringBuilder();

        for (Piece piece : line) {
            result.append(piece.getRepresentation());
        }

        return result.toString();
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
}

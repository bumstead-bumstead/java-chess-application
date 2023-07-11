package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class Rank {

    private List<Piece> pieces;
    private int rankNumber;

    private Rank(List<Piece> rank, int rankNumber) {
        this.pieces = rank;
        this.rankNumber = rankNumber;
    }

    public static Rank createFirstBlackRank() {
        int rankNumber = 0;
        List<Piece> list = new ArrayList<>();

        list.add(Rook.createBlack());
        list.add(Knight.createBlack());
        list.add(Bishop.createBlack());
        list.add(Queen.createBlack());
        list.add(King.createBlack());
        list.add(Bishop.createBlack());
        list.add(Knight.createBlack());
        list.add(Rook.createBlack());

        return new Rank(list, rankNumber);
    }

    public static Rank createFirstWhiteRank() {
        int rankNumber = 7;
        List<Piece> list = new ArrayList<>();

        list.add(Rook.createWhite());
        list.add(Knight.createWhite());
        list.add(Bishop.createWhite());
        list.add(Queen.createWhite());
        list.add(King.createWhite());
        list.add(Bishop.createWhite());
        list.add(Knight.createWhite());
        list.add(Rook.createWhite());

        return new Rank(list, rankNumber);
    }

    public static Rank createSecondBlackRank() {
        int rankNumber = 1;
        List<Piece> list = new ArrayList<>();

        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(Pawn.createBlack());
        }

        return new Rank(list, rankNumber);
    }

    public static Rank createSecondWhiteRank() {
        int rankNumber = 6;
        List<Piece> list = new ArrayList<>();

        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(Pawn.createWhite());
        }

        return new Rank(list, rankNumber);
    }

    public static Rank createBlankRank(int rankNumber) {
        List<Piece> list = new ArrayList<>();


        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(Blank.create());
        }

        return new Rank(list, rankNumber);
    }

    public void set(int column, Piece piece) {
        pieces.set(column, piece);
    }

    public Piece get(int column) {
        return pieces.get(column);
    }

    public List<Piece> collectPieces(Piece.Color color) {
        List<Piece> result = new ArrayList<>();

        pieces.stream()
                .filter(piece -> piece.hasColor(color))
                .forEach(result::add);

        return result;
    }

    public String concat() {
        StringBuilder result = new StringBuilder();

        pieces.forEach(piece -> result.append(piece.getRepresentation()));

        return result.toString();
    }

    public int count() {
        int pieceCount = (int) pieces.stream()
                .filter(piece -> !piece.isEmptyPiece())
                .count();

        return pieceCount;
    }

    public int count(Piece.Color color, Piece.Type type) {
        int pieceCount = (int) pieces.stream()
                .filter(piece -> piece.hasType(type) && piece.hasColor(color))
                .count();

        return pieceCount;
    }
}

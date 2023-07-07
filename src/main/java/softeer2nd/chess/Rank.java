package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.Board.BOARD_LENGTH;

public class Rank {

    private List<Piece> list;
    private int rankNumber;

    private Rank(List<Piece> rank, int rankNumber) {
        this.list = rank;
        this.rankNumber = rankNumber;
    }

    public static Rank createFirstBlackRank() {
        int rankNumber = 0;
        List<Piece> list = new ArrayList<>();

        list.add(Piece.createBlackRook(new Position(rankNumber, 0)));
        list.add(Piece.createBlackKnight(new Position(rankNumber, 1)));
        list.add(Piece.createBlackBishop(new Position(rankNumber, 2)));
        list.add(Piece.createBlackQueen(new Position(rankNumber, 3)));
        list.add(Piece.createBlackKing(new Position(rankNumber, 4)));
        list.add(Piece.createBlackBishop(new Position(rankNumber, 5)));
        list.add(Piece.createBlackKnight(new Position(rankNumber, 6)));
        list.add(Piece.createBlackRook(new Position(rankNumber, 7)));

        return new Rank(list, rankNumber);
    }

    public static Rank createFirstWhiteRank() {
        int rankNumber = 7;
        List<Piece> list = new ArrayList<>();

        list.add(Piece.createWhiteRook(new Position(rankNumber, 0)));
        list.add(Piece.createWhiteKnight(new Position(rankNumber, 1)));
        list.add(Piece.createWhiteBishop(new Position(rankNumber, 2)));
        list.add(Piece.createWhiteQueen(new Position(rankNumber, 3)));
        list.add(Piece.createWhiteKing(new Position(rankNumber, 4)));
        list.add(Piece.createWhiteBishop(new Position(rankNumber, 5)));
        list.add(Piece.createWhiteKnight(new Position(rankNumber, 6)));
        list.add(Piece.createWhiteRook(new Position(rankNumber, 7)));

        return new Rank(list, rankNumber);
    }

    public static Rank createSecondBlackRank() {
        int rankNumber = 1;
        List<Piece> list = new ArrayList<>();

        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(Piece.createBlackPawn(new Position(rankNumber, file)));
        }

        return new Rank(list, rankNumber);
    }

    public static Rank createSecondWhiteRank() {
        int rankNumber = 6;
        List<Piece> list = new ArrayList<>();

        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(Piece.createWhitePawn(new Position(rankNumber, file)));
        }

        return new Rank(list, rankNumber);
    }

    //todo : position 객체 추가해서 넣기
    public static Rank createBlankRank(int rankNumber) {
        List<Piece> list = new ArrayList<>();


        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(Piece.createBlank(new Position(rankNumber, file)));
        }

        return new Rank(list, rankNumber);
    }

    public void set(int column, Piece piece) {
        list.set(column, piece);
    }

    public Piece get(int column) {
        return list.get(column);
    }

    public List<Piece> collectPieces(Piece.Color color) {
        List<Piece> result = new ArrayList<>();

        for (Piece piece : list) {
            if (isSameColor(color, piece)) {
                result.add(piece);
            }
        }

        return result;
    }

    public String concat() {
        StringBuilder result = new StringBuilder();

        list.forEach(piece -> result.append(piece.getRepresentation()));

        return result.toString();
    }

    public int count() {
        int pieceCount = (int) list.stream()
                .filter(piece -> !isEmpty(piece))
                .count();

        return pieceCount;
    }

    public int count(Piece.Color color, Piece.Type type) {
        int pieceCount = (int) list.stream()
                .filter(piece -> isSameType(type, piece) && isSameColor(color, piece))
                .count();

        return pieceCount;
    }

    private static boolean isSameColor(Piece.Color color, Piece piece) {
        return piece.getColor() == color;
    }

    private static boolean isSameType(Piece.Type type, Piece piece) {
        return piece.getType() == type;
    }

    private static boolean isEmpty(Piece piece) {
        return piece.getType() != Piece.Type.NO_PIECE;
    }
}

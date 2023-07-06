package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private List<Piece> list;
    private static final int RANK_LENGTH = 8;

    private Rank() {
        this.list = new ArrayList<>();
    }

    private Rank(List<Piece> rank) {
        this.list = rank;
    }

    public static Rank createFirstBlackRank() {
        List<Piece> list = new ArrayList<>();
        list.add(Piece.createBlackRook());
        list.add(Piece.createBlackKnight());
        list.add(Piece.createBlackBishop());
        list.add(Piece.createBlackQueen());
        list.add(Piece.createBlackKing());
        list.add(Piece.createBlackBishop());
        list.add(Piece.createBlackKnight());
        list.add(Piece.createBlackRook());

        return new Rank(list);
    }

    public static Rank createFirstWhiteRank() {
        List<Piece> list = new ArrayList<>();

        list.add(Piece.createWhiteRook());
        list.add(Piece.createWhiteKnight());
        list.add(Piece.createWhiteBishop());
        list.add(Piece.createWhiteQueen());
        list.add(Piece.createWhiteKing());
        list.add(Piece.createWhiteBishop());
        list.add(Piece.createWhiteKnight());
        list.add(Piece.createWhiteRook());

        return new Rank(list);
    }

    public static Rank createRank(Piece piece) {
        List<Piece> list = new ArrayList<>();

        for (int index = 0; index < RANK_LENGTH; index++) {
            list.add(piece);
        }

        return new Rank(list);
    }

    public void set(int column, Piece piece) {
        list.set(column, piece);
    }

    public Piece get(int column) {
        return list.get(column);
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

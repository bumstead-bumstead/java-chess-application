package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;
import static softeer2nd.chess.domain.pieces.PieceFactory.*;

public class Rank {

    private List<Piece> pieces;

    private Rank(List<Piece> rank) {
        this.pieces = rank;
    }

    public static Rank createFirstBlackRank() {
        List<Piece> list = new ArrayList<>();

        list.add(createBlackRook());
        list.add(createBlackKnight());
        list.add(createBlackBishop());
        list.add(createBlackQueen());
        list.add(createBlackKing());
        list.add(createBlackBishop());
        list.add(createBlackKnight());
        list.add(createBlackRook());

        return new Rank(list);
    }

    public static Rank createFirstWhiteRank() {
        List<Piece> list = new ArrayList<>();

        list.add(createWhiteRook());
        list.add(createWhiteKnight());
        list.add(createWhiteBishop());
        list.add(createWhiteQueen());
        list.add(createWhiteKing());
        list.add(createWhiteBishop());
        list.add(createWhiteKnight());
        list.add(createWhiteRook());

        return new Rank(list);
    }

    public static Rank createSecondBlackRank() {
        List<Piece> list = new ArrayList<>();

        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(createBlackPawn());
        }

        return new Rank(list);
    }

    public static Rank createSecondWhiteRank() {
        List<Piece> list = new ArrayList<>();

        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(createWhitePawn());
        }

        return new Rank(list);
    }

    public static Rank createBlankRank() {
        List<Piece> list = new ArrayList<>();


        for (int file = 0; file < BOARD_LENGTH; file++) {
            list.add(createBlank());
        }

        return new Rank(list);
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

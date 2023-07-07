package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.utils.ChessPositionParser;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public final static int BOARD_LENGTH = 8;
    public static final double PAWN_HALF_SCORE = 0.5;
    private List<Rank> pieces;

    public Board() {
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        pieces.clear();

        pieces.add(Rank.createFirstBlackRank());
        pieces.add(Rank.createSecondBlackRank());
        pieces.add(Rank.createBlankRank(2));
        pieces.add(Rank.createBlankRank(3));
        pieces.add(Rank.createBlankRank(4));
        pieces.add(Rank.createBlankRank(5));
        pieces.add(Rank.createSecondWhiteRank());
        pieces.add(Rank.createFirstWhiteRank());
    }

    public void initializeEmpty() {
        pieces.clear();

        pieces.add(Rank.createBlankRank(0));
        pieces.add(Rank.createBlankRank(1));
        pieces.add(Rank.createBlankRank(2));
        pieces.add(Rank.createBlankRank(3));
        pieces.add(Rank.createBlankRank(4));
        pieces.add(Rank.createBlankRank(5));
        pieces.add(Rank.createBlankRank(6));
        pieces.add(Rank.createBlankRank(7));
    }

    public void setPiece(String request, Piece piece) {
        Position position = ChessPositionParser.parse(request);

        Rank rank = pieces.get(position.getRow());

        rank.set(position.getColumn(), piece);
    }



    public List<Rank> getPieces() {
        return pieces;
    }

    public int pieceCount() {
        int numberOfPieces = 0;

        for (Rank rank : pieces) {
            numberOfPieces += rank.count();
        }

        return numberOfPieces;
    }

    public int count(Piece.Color color, Piece.Type type) {
        int number = 0;

        for (Rank rank : pieces) {
            number += rank.count(color, type);
        }

        return number;
    }

    public Piece findPiece(String request) {
        Position position = ChessPositionParser.parse(request);

        Rank rank = pieces.get(position.getRow());

        return rank.get(position.getColumn());
    }

    public boolean hasMultiplePawnsInColumn(Piece.Color color, int column) {
        int pawnCount = 0;

        for (int row = 0; row < BOARD_LENGTH; row++) {
            Piece targetPiece = pieces.get(row).get(column);

            if (targetPiece.isPawn() && targetPiece.hasColor(color)) {
                pawnCount++;
            }
        }

        return pawnCount > 1;
    }

    public List<Piece> getSortedPiecesAscending(Piece.Color color) {
        List<Piece> result = collectPieces(color);

        result.sort((piece1, piece2) -> (int) (piece1.getType().getScore() - piece2.getType().getScore()));

        return result;
    }

    public List<Piece> getSortedPiecesDescending(Piece.Color color) {
        List<Piece> result = collectPieces(color);

        result.sort((piece1, piece2) -> (int) (piece2.getType().getScore() - piece1.getType().getScore()));

        return result;
    }

    private List<Piece> collectPieces(Piece.Color color) {
        List<Piece> result = new ArrayList<>();

        for (Rank rank : pieces) {
            result.addAll(rank.collectPieces(color));
        }

        return result;
    }
}

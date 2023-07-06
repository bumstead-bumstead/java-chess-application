package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.utils.ChessPositionParser;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

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
        pieces.add(Rank.createRank(Piece.createBlackPawn()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createWhitePawn()));
        pieces.add(Rank.createFirstWhiteRank());
    }

    public void initializeEmpty() {
        pieces.clear();

        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
        pieces.add(Rank.createRank(Piece.createBlank()));
    }

    public void move(String request, Piece piece) {
        Position position = ChessPositionParser.parse(request);

        Rank rank = pieces.get(position.getRow());

        rank.set(position.getColumn(), piece);
    }

    public String showBoard() {
        StringBuilder result = new StringBuilder();

        for (Rank rank : pieces) {
            result.append(appendNewLine(rank.concat()));
        }

        return result.toString();
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

    public double calculatePoint(Piece.Color targetColor) {
        double totalPoint = 0;

        for (Rank rank : pieces) {
            for (int column = 0; column < BOARD_LENGTH; column++) {
                Piece targetPiece = rank.get(column);
                totalPoint += calculatePointOfPiece(targetPiece, targetColor, column);
            }
        }

        return totalPoint;
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

    private double calculatePointOfPiece(Piece piece, Piece.Color targetColor, int column) {
        double point = 0;

        if (isSameColor(targetColor, piece)) {
            point = piece.getType().getScore();
        }
        if (isPawn(piece) && hasMultiplePawnsInColumn(targetColor, column)) {
            point = PAWN_HALF_SCORE;
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
}

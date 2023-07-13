package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.domain.pieces.PieceFactory.createBlank;

public class Board {

    public final static int BOARD_LENGTH = 8;
    private List<Rank> pieces;

    public List<Rank> getPieces() {
        return pieces;
    }

    public Board() {
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        pieces.clear();

        pieces.add(Rank.createFirstBlackRank());
        pieces.add(Rank.createSecondBlackRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createSecondWhiteRank());
        pieces.add(Rank.createFirstWhiteRank());
    }

    public void initializeEmpty() {
        pieces.clear();

        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
        pieces.add(Rank.createBlankRank());
    }

    public void setPiece(Position position, Piece piece) {
        Rank rank = pieces.get(position.getRow());

        rank.set(position.getColumn(), piece);
    }

    public void removePiece(Position position) {
        Rank rank = pieces.get(position.getRow());

        rank.set(position.getColumn(), createBlank());
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

    public Piece findPiece(Position position) {
        Rank rank = pieces.get(position.getRow());

        return rank.get(position.getColumn());
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

        for (Rank rank : pieces) result.addAll(rank.collectPieces(color));

        return result;
    }

    public List<Piece> getPiecesOfColumn(Piece.Color targetColor, int column) {
        List<Piece> piecesOfColumn = new ArrayList<>();

        for (Rank rank : pieces) {
            Piece piece = rank.get(column);

            if (!piece.hasColor(targetColor)) continue;

            piecesOfColumn.add(piece);
        }

        return piecesOfColumn;
    }

    public static boolean isValidPosition(Position targetPosition) {
        return targetPosition.getRow() < Board.BOARD_LENGTH &&
                targetPosition.getRow() > -1 &&
                targetPosition.getColumn() < Board.BOARD_LENGTH &&
                targetPosition.getColumn() > -1;
    }

    public void verifyBlockedByPiece(Position sourcePosition, Position targetPosition) {
        Position temporalPosition = new Position(sourcePosition.getRow(), sourcePosition.getColumn());

        int rowDifference = targetPosition.getRow() - sourcePosition.getRow();
        int columnDifference = targetPosition.getColumn() - sourcePosition.getColumn();

        if (isLMove(rowDifference, columnDifference)) {
            return;
        }

        int rowDirection = calculateRowDirection(rowDifference);
        int columnDirection = calculateColumnDirection(columnDifference);

        while (rowDifference != 0 || columnDifference != 0) {
            if (rowDifference != 0) {
                temporalPosition.transfer(rowDirection, 0);
                rowDifference -= rowDirection;
            }
            if (columnDifference != 0) {
                temporalPosition.transfer(0, columnDirection);
                columnDifference -= columnDirection;
            }

            if (temporalPosition.equals(targetPosition)) {
                break;
            }

            if (!findPiece(temporalPosition).isEmptyPiece()) {
                throw new IllegalCommandException("이동 경로 상에 다른 기물이 존재합니다.");
            }
        }
    }

    private static boolean isLMove(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) - Math.abs(columnDifference) == 1;
    }

    private static int calculateColumnDirection(int columnDifference) {
        if (columnDifference != 0) {
            return columnDifference / Math.abs(columnDifference);
        } else {
            return 0;
        }
    }

    private static int calculateRowDirection(int rowDifference) {
        if (rowDifference != 0) {
            return rowDifference / Math.abs(rowDifference);
        } else {
            return 0;
        }
    }

    public void verifyPawnCapture(Position sourcePosition, Position targetPosition) {
        Piece targetPiece = findPiece(targetPosition);
        Piece sourcePiece = findPiece(sourcePosition);

        if (sourcePosition.isDiagonal(targetPosition)) {
            if (!targetPiece.hasColor(sourcePiece.getColor().getReverseColor())) {
                throw new IllegalCommandException("폰은 상대 기물이 존재할 때만 대각선 이동이 가능합니다.");
            }
        }

        if (sourcePosition.isVertical(targetPosition)) {
            if (!targetPiece.hasColor(Piece.Color.NOCOLOR)) {
                throw new IllegalCommandException("폰은 기물이 위치하는 칸으로 이동할 수 없습니다.");
            }
        }
    }
}

package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.ExceptionMessage;
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


    public List<Piece> getPiecesOfColumn(Piece.Color targetColor, int column) {
        List<Piece> piecesOfColumn = new ArrayList<>();

        for (Rank rank : pieces) {
            Piece piece = rank.get(column);

            if (!piece.isSameColor(targetColor)) {
                continue;
            }

            piecesOfColumn.add(piece);
        }

        return piecesOfColumn;
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
                throw new IllegalCommandException(ExceptionMessage.BLOCKED_EXCEPTION_MESSAGE);
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

    public static boolean isValidPosition(Position targetPosition) {
        return targetPosition.getRow() < Board.BOARD_LENGTH &&
                targetPosition.getRow() > -1 &&
                targetPosition.getColumn() < Board.BOARD_LENGTH &&
                targetPosition.getColumn() > -1;
    }

    public void verifyPawnCapture(Position sourcePosition, Position targetPosition) {
        Piece targetPiece = findPiece(targetPosition);
        Piece sourcePiece = findPiece(sourcePosition);

        if (sourcePosition.isDiagonal(targetPosition)) {
            if (!targetPiece.isSameColor(sourcePiece.getColor().getReverseColor())) {
                throw new IllegalCommandException(ExceptionMessage.PAWN_DIAGONAL_MOVE_EXCEPTION_MESSAGE);
            }
        }

        if (sourcePosition.isVertical(targetPosition)) {
            if (!targetPiece.isSameColor(Piece.Color.NOCOLOR)) {
                throw new IllegalCommandException(ExceptionMessage.PAWN_VERTICAL_CAPTURE_EXCEPTION_MESSAGE);
            }
        }
    }
}

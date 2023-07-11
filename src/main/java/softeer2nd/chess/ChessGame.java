package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.exceptions.IllegalCommandException;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class ChessGame {
    public static final double PAWN_HALF_SCORE = 0.5;

    private Board board;

    public ChessGame() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void start() {
        board.initialize();
    }

    public void move(Position sourcePosition, Position targetPosition, Piece.Color turn) throws RuntimeException {
        Piece oldPiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);

        verifyTurn(oldPiece, turn);
        oldPiece.verifySameColor(targetPiece);
        oldPiece.verifyMovePosition(sourcePosition, targetPosition);
        board.verifyBlockedByPiece(sourcePosition, targetPosition);

        board.removePiece(sourcePosition);
        board.setPiece(targetPosition, oldPiece);
    }

    private void verifyTurn(Piece oldPiece, Piece.Color turn) {
        if (!oldPiece.hasColor(turn)) {
            throw new IllegalCommandException();
        }
    }

    public double calculatePoint(Piece.Color targetColor) {
        double totalPoint = 0;

        for (int column = 0; column < BOARD_LENGTH; column++) {
            List<Piece> pieces = board.getPiecesOfColumn(targetColor, column);

            totalPoint += calculateTotalPointOfColumn(pieces);
        }

        return totalPoint;
    }

    private double calculateTotalPointOfColumn(List<Piece> pieces) {
        int pawnCount = 0;
        double totalPoint = 0;

        for (Piece piece : pieces) {
            if (piece.hasType(Piece.Type.PAWN)) pawnCount++;
            totalPoint += piece.getType().getScore();
        }

        if (pawnCount > 1) totalPoint -= pawnCount * PAWN_HALF_SCORE;

        return totalPoint;
    }

    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }
}

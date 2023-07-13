package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Pawn;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.ExceptionMessage;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.List;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class ChessGame {
    public static final double PAWN_HALF_SCORE = 0.5;

    private Board board;

    private Piece.Color turn;
    private boolean isGameOver;

    public ChessGame() {
        this.board = new Board();
        turn = Piece.Color.NOCOLOR;
        this.isGameOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public void start() {
        board.initialize();
        this.turn = Piece.Color.WHITE;
    }

    //for test
    public void setTurn(Piece.Color turn) {
        this.turn = turn;
    }

    public void move(Position sourcePosition, Position targetPosition) throws RuntimeException {
        Piece oldPiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);

        verifyMove(sourcePosition, targetPosition);

        board.removePiece(sourcePosition);
        board.setPiece(targetPosition, oldPiece);

        if (isKingCaptured(targetPiece)) {
            finishGame();
        }

        changeTurn();
    }


    private void verifyMove(Position sourcePosition, Position targetPosition) throws RuntimeException {
        Piece movePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);

        verifyTurn(movePiece);
        movePiece.verifyAlly(targetPiece);
        movePiece.verifyMovePosition(sourcePosition, targetPosition);
        board.verifyBlockedByPiece(sourcePosition, targetPosition);

        if (movePiece.isSameType(Piece.Type.PAWN)) {
            board.verifyPawnCapture(sourcePosition, targetPosition);
            Pawn.reflectMove(movePiece);
        }
    }

    private void verifyTurn(Piece oldPiece) {
        if (!oldPiece.isSameColor(turn) && !oldPiece.isEmptyPiece()) {
            throw new IllegalCommandException(ExceptionMessage.TURN_EXCEPTION_MESSAGE);
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void finishGame() {
        isGameOver = true;
    }

    private boolean isKingCaptured(Piece targetPiece) {
        return targetPiece.isSameType(Piece.Type.KING);
    }

    private void changeTurn() {
        if (turn == Piece.Color.BLACK) {
            turn = Piece.Color.WHITE;
            return;
        }
        if (turn == Piece.Color.WHITE) {
            turn = Piece.Color.BLACK;
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
            if (piece.isSameType(Piece.Type.PAWN)) {
                pawnCount++;
            }
            totalPoint += piece.getType().getScore();
        }

        if (pawnCount > 1) {
            totalPoint -= pawnCount * PAWN_HALF_SCORE;
        }

        return totalPoint;
    }

    public Piece.Color getTurn() {
        return turn;
    }

    //for test
    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }
}

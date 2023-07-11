package softeer2nd.chess;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessConsoleView;
import softeer2nd.chess.view.ChessView;

public class ChessApp {
    private ChessGame chessGame = new ChessGame();
    private ChessConsoleView chessView = new ChessConsoleView();
    private static Piece.Color turn = Piece.Color.BLACK;

    public void run() throws RuntimeException {
        chessView.printStartMessage();
        chessGame.start();
        chessView.showBoard(chessGame.getBoard());

        while (processTurn()) {
            changeTurn();
        }
        ;
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

    private boolean processTurn() {
        try {
            String command = chessView.getCommandInput();

            if (command.startsWith(ChessView.MOVE)) {
                moveRoutine(command, turn);
                return true;
            }
            if (command.equals(ChessView.SCORE)) {
                scoreRoutine();
            }
            if (command.equals(ChessView.END)) {
                endRoutine();
                return false;
            }
        } catch (RuntimeException e) {
            chessView.printErrorMessage(e);
            return processTurn();
        }
        return true;
    }

    private void scoreRoutine() {
        double blackScore = chessGame.calculatePoint(Piece.Color.BLACK);
        double whiteScore = chessGame.calculatePoint(Piece.Color.WHITE);

        chessView.getBlackScore(blackScore);
        chessView.getWhiteScore(whiteScore);
    }

    private void endRoutine() {
        chessView.printEndMessage();
    }

    private void moveRoutine(String command, Piece.Color turn) {
        String[] commandArray = command.split(" ");
        Position sourcePosition = new Position(commandArray[1]);
        Position targetPosition = new Position(commandArray[2]);

        chessGame.move(sourcePosition, targetPosition, turn);

        chessView.printMoveMessage(commandArray[1], commandArray[2]);
        chessView.showBoard(chessGame.getBoard());
    }
}

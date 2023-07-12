package softeer2nd.chess;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessConsoleView;
import softeer2nd.chess.view.ChessView;

public class ChessApp {
    private ChessGame chessGame = new ChessGame();
    private ChessConsoleView chessView = new ChessConsoleView();

    public void run() throws RuntimeException {
        chessView.printStartMessage();
        chessGame.start();
        chessView.showBoard(chessGame.getBoard());

        while (true) {
            if (!processTurn()) break;
        }
    }

    private boolean processTurn() {
        try {
            String command = chessView.getCommandInput();

            if (command.startsWith(ChessView.MOVE)) {
                moveRoutine(command);
            }
            if (command.equals(ChessView.SCORE)) {
                scoreRoutine();
            }
            if (command.equals(ChessView.END)) {
                gameExitRoutine();
                return false;
            }
            if (chessGame.isGameOver()) {
                gameOverRoutine();
                return false;
            }
        } catch (RuntimeException e) {
            chessView.printErrorMessage(e);
        }
        return true;
    }

    private void gameOverRoutine() {
        chessView.showBoard(chessGame.getBoard());
        chessView.printWinMessage(chessGame.getTurn().getReverseColor());
        chessView.printEndMessage();
    }

    private void scoreRoutine() {
        double blackScore = chessGame.calculatePoint(Piece.Color.BLACK);
        double whiteScore = chessGame.calculatePoint(Piece.Color.WHITE);

        chessView.getBlackScore(blackScore);
        chessView.getWhiteScore(whiteScore);
    }

    private void gameExitRoutine() {
        scoreRoutine();
        chessView.printEndMessage();
    }

    private void moveRoutine(String command) {
        String[] commandArray = command.split(" ");
        Position sourcePosition = new Position(commandArray[1]);
        Position targetPosition = new Position(commandArray[2]);

        chessGame.move(sourcePosition, targetPosition);

        chessView.printMoveMessage(commandArray[1], commandArray[2]);
        chessView.showBoard(chessGame.getBoard());

        if (chessGame.isGameOver()) {

        }
    }
}

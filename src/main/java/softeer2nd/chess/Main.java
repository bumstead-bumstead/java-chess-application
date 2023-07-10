package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

public class Main {
    private static ChessGame chessGame = new ChessGame();
    private static ChessView chessView = new ChessView();

    public static void main(String[] args) {
         run();
    }

    private static void run() throws RuntimeException {
        System.out.println(chessView.getStartMessage());
        chessGame.start();
        System.out.println(chessView.showBoard(chessGame.getBoard()));

        while (processTurn());
    }

    private static boolean processTurn() {
        try {
            String command = chessView.getCommandInput();

            if (command.startsWith("move")) {
                moveRoutine(command);
                return true;
            }
            if (command.equals("score")) {
                scoreRoutine();
            }
            if (command.equals("end")) {
                endRoutine();
                return false;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return processTurn();
        }
        return true;
    }

    private static void scoreRoutine() {
        double blackScore = chessGame.calculatePoint(Piece.Color.BLACK);
        double whiteScore = chessGame.calculatePoint(Piece.Color.WHITE);

        System.out.println(chessView.getBlackScore(blackScore));
        System.out.println(chessView.getWhiteScore(whiteScore));
    }

    private static void endRoutine() {
        System.out.println(chessView.getEndMessage());
    }

    private static void moveRoutine(String command) {
        String[] commandArray = command.split(" ");
        String sourcePosition = commandArray[1];
        String targetPosition = commandArray[2];

        chessGame.move(sourcePosition, targetPosition);

        System.out.println(chessView.getMoveMessage(sourcePosition, targetPosition));
        System.out.println(chessView.showBoard(chessGame.getBoard()));
    }
}
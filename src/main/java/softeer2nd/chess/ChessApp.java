package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

public class ChessApp {
    private ChessGame chessGame = new ChessGame();
    private ChessView chessView = new ChessView();
    private static Piece.Color turn = Piece.Color.BLACK;

    public void run() throws RuntimeException {
        System.out.println(chessView.getStartMessage());
        chessGame.start();
        System.out.println(chessView.showBoard(chessGame.getBoard()));

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
            System.out.println(e.getMessage());
            return processTurn();
        }
        return true;
    }

    private void scoreRoutine() {
        double blackScore = chessGame.calculatePoint(Piece.Color.BLACK);
        double whiteScore = chessGame.calculatePoint(Piece.Color.WHITE);

        System.out.println(chessView.getBlackScore(blackScore));
        System.out.println(chessView.getWhiteScore(whiteScore));
    }

    private void endRoutine() {
        System.out.println(chessView.getEndMessage());
    }

    private void moveRoutine(String command, Piece.Color turn) {
        String[] commandArray = command.split(" ");
        Position sourcePosition = new Position(commandArray[1]);
        Position targetPosition = new Position(commandArray[2]);

        chessGame.move(sourcePosition, targetPosition, turn);

        System.out.println(chessView.getMoveMessage(commandArray[1], commandArray[2]));
        System.out.println(chessView.showBoard(chessGame.getBoard()));
    }
}

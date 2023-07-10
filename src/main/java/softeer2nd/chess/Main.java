package softeer2nd.chess;

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
                String[] commandArray = command.split(" ");
                String sourcePosition = commandArray[1];
                String targetPosition = commandArray[2];

                chessGame.move(sourcePosition, targetPosition);

                System.out.println(chessView.getMoveMessage(sourcePosition, targetPosition));
                System.out.println(chessView.showBoard(chessGame.getBoard()));
                return true;
            }

            if (command.equals("end")) {
                System.out.println(chessView.getEndMessage());
                return false;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return processTurn();
        }

        return true;
    }
}
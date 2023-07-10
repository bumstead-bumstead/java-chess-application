package softeer2nd.chess;

import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.Scanner;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class ChessView {

    public static final String START_MESSAGE = "게임을 시작합니다.";
    private static final String MOVE_MESSAGE = "기물을 이동합니다. : ";
    private static final String BOARD_UPPER_LINE = "--BOARD--";
    private static final String BOARD_UNDER_LINE = "--------";
    public static final String END_MESSAGE = "게임을 종료합니다.";

    Scanner scanner = new Scanner(System.in);

    public String getMoveMessage(String sourcePosition, String targetPosition) {
        return MOVE_MESSAGE + sourcePosition + " to " + targetPosition;
    }

    public String getEndMessage() {
        return END_MESSAGE;
    }

    public String getStartMessage() {
        return START_MESSAGE;
    }

    public String getErrorMessage(RuntimeException e) {
        return e.getMessage();
    }

    public String showBoard(Board board) {
        StringBuilder result = new StringBuilder();

        result.append(appendNewLine(BOARD_UPPER_LINE));

        for (Rank rank : board.getPieces()) {
            result.append(appendNewLine(rank.concat()));
        }

        result.append(appendNewLine(BOARD_UNDER_LINE));

        return result.toString();
    }
    public String getCommandInput() {
        String input = scanner.nextLine();

        veryfyCommandInput(input);

        return input;
    }

    //for test

    public String getCommandInput(Scanner scanner) {
        String input = scanner.nextLine();

        veryfyCommandInput(input);

        return input;
    }

    private static void veryfyCommandInput(String input) {
        verifyCommandType(input);

        if (input.startsWith("move")) {
            verifyMoveCommand(input);
            return;
        }

        verifyEndCommand(input);
    }

    private static void verifyCommandType(String input) {
        if (!input.startsWith("move") && !input.equals("end")) {
            throw new IllegalCommandException();
        }
    }

    private static void verifyEndCommand(String input) {
        if (!input.equals("end")) {
            throw new IllegalCommandException();
        }
    }

    private static void verifyMoveCommand(String input) {
        String[] inputArray = input.split(" ");

        if (!hasCorrectArgumentNumber(inputArray)) {
            throw new IllegalCommandException();
        }

        String startPosition = inputArray[1];
        String targetPosition = inputArray[2];

        if (!hasCorrectPositionFormat(startPosition) || !hasCorrectPositionFormat(targetPosition)) {
            throw new IllegalCommandException();
        }
    }

    private static boolean hasCorrectPositionFormat(String startPosition) {
        return startPosition.matches("[a-h][1-8]");
    }

    private static boolean hasCorrectArgumentNumber(String[] inputArray) {
        return inputArray.length == 3;
    }
}

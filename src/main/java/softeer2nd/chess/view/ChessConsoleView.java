package softeer2nd.chess.view;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Rank;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.Scanner;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class ChessConsoleView implements ChessView {

    private static final String START_MESSAGE = "게임을 시작합니다.";
    private static final String MOVE_MESSAGE = "기물을 이동합니다. : ";
    private static final String BOARD_UPPER_LINE = "--BOARD--";
    private static final String BOARD_UNDER_LINE = "--------";
    private static final String END_MESSAGE = "게임을 종료합니다.";
    private static final String BLACK_SCORE_MESSAGE = "흑색 점수 : ";
    private static final String WHITE_SCORE_MESSAGE = "백색 점수 : ";
    private static final String WINNER_MESSAGE = "의 승리입니다.";

    Scanner scanner = new Scanner(System.in);

    public void printWinMessage(Piece.Color winner) {
        System.out.println(winner + WINNER_MESSAGE);
    }

    public void printMoveMessage(String sourcePosition, String targetPosition) {
        System.out.println(MOVE_MESSAGE + sourcePosition + " to " + targetPosition);
    }

    public void printEndMessage() {
        System.out.println(END_MESSAGE);
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void showBoard(Board board) {
        StringBuilder result = new StringBuilder();

        result.append(appendNewLine(BOARD_UPPER_LINE));

        for (Rank rank : board.getPieces()) {
            result.append(appendNewLine(rank.concat()));
        }

        result.append(appendNewLine(BOARD_UNDER_LINE));

        System.out.println(result);
    }
    public String getCommandInput() {
        String input = scanner.nextLine();

        veryfyCommandInput(input);

        System.out.println(input);
        return input;
    }

    private static void veryfyCommandInput(String input) {
        verifyCommandType(input);

        if (input.startsWith(MOVE)) {
            verifyMoveCommand(input);
        }
    }

    private static void verifyCommandType(String input) {
        if (!input.startsWith(MOVE) && !input.equals(END) && !input.equals(SCORE)) {
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

    public void getWhiteScore(double whiteScore) {
        System.out.println(WHITE_SCORE_MESSAGE + whiteScore);
    }

    public void getBlackScore(double blackScore) {
        System.out.println(BLACK_SCORE_MESSAGE + blackScore);
    }

    //for test
    public String getCommandInput(Scanner scanner) {
        String input = scanner.nextLine();

        veryfyCommandInput(input);

        return input;
    }
}

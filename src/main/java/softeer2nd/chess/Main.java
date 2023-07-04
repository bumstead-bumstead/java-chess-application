package softeer2nd.chess;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Board board = new Board();
    public static void main(String[] args) {

        try {
            run();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void run() throws RuntimeException {
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("start")) {
                System.out.println("게임을 시작합니다.");
                board.initialize();
                System.out.println(board.print());
                continue;
            }
            if (command.equals("end")) {
                System.out.println("게임을 종료합니다.");
                return;
            }

            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
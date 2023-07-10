package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ChessViewTest {

    ChessView chessView;

    @BeforeEach
    void setup() {
        chessView = new ChessView();
    }

    @ParameterizedTest
    @ValueSource(strings = {"move i1 a1", "move a1 i1", "move a10 a12", "move a9 a1", "move a9 c5", "move", "end 1", " ", "move a1 a2 a3", "move a0, a3"})
    @DisplayName("유효하지 않은 입력에 대해 예외를 발생시켜야한다.")
    void getCommandInputInvalid(String input) {
        InputStream inputStream = getInputStream(input);
        System.setIn(inputStream);
        assertThrows(IllegalCommandException.class, () -> chessView.getCommandInput(new Scanner(System.in)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a2 a1", "move b4 g6", "end"})
    @DisplayName("유효한 입력에 대해 예외를 발생시키지 않는다..")
    void getCommandInputValid(String input) {
        InputStream inputStream = getInputStream(input);
        System.setIn(inputStream);
        chessView.getCommandInput(new Scanner(System.in));
    }

    private static ByteArrayInputStream getInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}
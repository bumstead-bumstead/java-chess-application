package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {


    private Knight knight;
    @BeforeEach
    void init() {
        knight = Knight.createBlack(new Position(2, 2));
    }

    @Test
    @DisplayName("BOARD 외부로의 이동은 불가능하다.")
    void verifyMoveToOutOfBoard() {
        boolean result = knight.verifyMovePosition(new Position(-1, 0));
        assertFalse(result);
        result = knight.verifyMovePosition(new Position(0, -1));
        assertFalse(result);

        result = knight.verifyMovePosition(new Position(8, 0));
        assertFalse(result);

        result = knight.verifyMovePosition(new Position(0, 8));
        assertFalse(result);
    }

    @Test
    @DisplayName("나이트의 행보법 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        boolean result = knight.verifyMovePosition(new Position(0, 1));
        assertTrue(result);
        result = knight.verifyMovePosition(new Position(1, 0));
        assertTrue(result);
        result = knight.verifyMovePosition(new Position(4, 3));
        assertTrue(result);
        result = knight.verifyMovePosition(new Position(3, 4));
        assertTrue(result);

        result = knight.verifyMovePosition(new Position(4, 4));
        assertFalse(result);
        result = knight.verifyMovePosition(new Position(2, 3));
        assertFalse(result);
    }
}
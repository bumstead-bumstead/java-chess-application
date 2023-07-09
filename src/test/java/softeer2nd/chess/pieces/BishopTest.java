package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {


    private Bishop bishop;
    @BeforeEach
    void init() {
        bishop = Bishop.createBlack(new Position(1, 1));
    }

    @Test
    @DisplayName("BOARD 외부로의 이동은 불가능하다.")
    void verifyMoveToOutOfBoard() {
        boolean result = bishop.verifyMovePosition(new Position(-1, 0));
        assertFalse(result);
        result = bishop.verifyMovePosition(new Position(0, -1));
        assertFalse(result);

        result = bishop.verifyMovePosition(new Position(8, 0));
        assertFalse(result);

        result = bishop.verifyMovePosition(new Position(0, 8));
        assertFalse(result);
    }

    @Test
    @DisplayName("대각선 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        boolean result = bishop.verifyMovePosition(new Position(0, 0));
        assertTrue(result);

        result = bishop.verifyMovePosition(new Position(7, 7));
        assertTrue(result);

        result = bishop.verifyMovePosition(new Position(0, 2));
        assertTrue(result);

        result = bishop.verifyMovePosition(new Position(1, 2));
        assertFalse(result);

        result = bishop.verifyMovePosition(new Position(3, 4));
        assertFalse(result);
    }

}
package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    private Queen queen;
    @BeforeEach
    void init() {
        queen = Queen.createBlack(new Position(3, 3));
    }

    @Test
    @DisplayName("BOARD 외부로의 이동은 불가능하다.")
    void verifyMoveToOutOfBoard() {
        boolean result = queen.verifyMovePosition(new Position(-1, 0));
        assertFalse(result);
        result = queen.verifyMovePosition(new Position(0, -1));
        assertFalse(result);

        result = queen.verifyMovePosition(new Position(8, 0));
        assertFalse(result);

        result = queen.verifyMovePosition(new Position(0, 8));
        assertFalse(result);
    }

    @Test
    @DisplayName("상하좌우 대각선 이외에 이동은 불가능하다.")
    void verifyMovePosition() {
        boolean result = queen.verifyMovePosition(new Position(1, 1));
        assertTrue(result);
        result = queen.verifyMovePosition(new Position(3, 7));
        assertTrue(result);
        result = queen.verifyMovePosition(new Position(7, 3));
        assertTrue(result);
        result = queen.verifyMovePosition(new Position(5, 1));
        assertTrue(result);

        result = queen.verifyMovePosition(new Position(5, 2));
        assertFalse(result);
        result = queen.verifyMovePosition(new Position(5, 4));
        assertFalse(result);
    }

}
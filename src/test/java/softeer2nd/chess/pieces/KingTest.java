package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

    private King king;
    @BeforeEach
    void init() {
        king = King.createBlack(new Position(1, 1));
    }

    @Test
    @DisplayName("BOARD 외부로의 이동은 불가능하다.")
    void verifyMoveToOutOfBoard() {
        boolean result = king.verifyMovePosition(new Position(-1, 0));
        assertFalse(result);
        result = king.verifyMovePosition(new Position(0, -1));
        assertFalse(result);

        result = king.verifyMovePosition(new Position(8, 0));
        assertFalse(result);

        result = king.verifyMovePosition(new Position(0, 8));
        assertFalse(result);
    }

    @Test
    @DisplayName("주변 한 칸 이상의 이동은 불가능하다.")
    void verifyMovePosition() {
        boolean result = king.verifyMovePosition(new Position(0, 0));
        assertTrue(result);

        result = king.verifyMovePosition(new Position(2, 2));
        assertTrue(result);

        result = king.verifyMovePosition(new Position(3, 2));
        assertFalse(result);
    }
}

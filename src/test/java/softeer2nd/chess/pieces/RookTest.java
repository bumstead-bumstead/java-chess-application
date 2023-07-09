package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {


    private Rook rook;
    @BeforeEach
    void init() {
        rook = Rook.createBlack(new Position(2, 2));
    }

    @Test
    @DisplayName("BOARD 외부로의 이동은 불가능하다.")
    void verifyMoveToOutOfBoard() {
        boolean result = rook.verifyMovePosition(new Position(-1, 0));
        assertFalse(result);
        result = rook.verifyMovePosition(new Position(0, -1));
        assertFalse(result);

        result = rook.verifyMovePosition(new Position(8, 0));
        assertFalse(result);

        result = rook.verifyMovePosition(new Position(0, 8));
        assertFalse(result);
    }

    @Test
    @DisplayName("상하좌우 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        boolean result = rook.verifyMovePosition(new Position(0, 2));
        assertTrue(result);
        result = rook.verifyMovePosition(new Position(2, 0));
        assertTrue(result);
        result = rook.verifyMovePosition(new Position(6, 2));
        assertTrue(result);
        result = rook.verifyMovePosition(new Position(2, 6));
        assertTrue(result);

        result = rook.verifyMovePosition(new Position(4, 4));
        assertFalse(result);
        result = rook.verifyMovePosition(new Position(0, 1));
        assertFalse(result);
    }


}
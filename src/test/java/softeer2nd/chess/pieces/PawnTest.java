package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {


    private Pawn pawn;
    @BeforeEach
    void init() {
        pawn = Pawn.createBlack(new Position(2, 2));
    }

    @Test
    @DisplayName("BOARD 외부로의 이동은 불가능하다.")
    void verifyMoveToOutOfBoard() {
        boolean result = pawn.verifyMovePosition(new Position(-1, 0));
        assertFalse(result);
        result = pawn.verifyMovePosition(new Position(0, -1));
        assertFalse(result);

        result = pawn.verifyMovePosition(new Position(8, 0));
        assertFalse(result);

        result = pawn.verifyMovePosition(new Position(0, 8));
        assertFalse(result);
    }

    @Test
    @DisplayName("black pawn - 전방 세 칸 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        boolean result = pawn.verifyMovePosition(new Position(3, 2));
        assertTrue(result);
        result = pawn.verifyMovePosition(new Position(3, 1));
        assertTrue(result);
        result = pawn.verifyMovePosition(new Position(3, 3));
        assertTrue(result);

        result = pawn.verifyMovePosition(new Position(4, 4));
        assertFalse(result);
        result = pawn.verifyMovePosition(new Position(5, 3));
        assertFalse(result);
    }
}
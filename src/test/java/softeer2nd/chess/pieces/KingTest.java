package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {

    private King king;
    @BeforeEach
    void init() {
        king = King.createBlack(new Position(1, 1));
    }

    @Test
    @DisplayName("주변 한 칸 이상의 이동은 불가능하다.")
    void verifyMovePosition() {
        king.verifyMovePosition(new Position(0, 0));
        king.verifyMovePosition(new Position(2, 2));

        assertThrows(OutOfPieceRangeException.class, () -> king.verifyMovePosition(new Position(3, 2)));
    }
}

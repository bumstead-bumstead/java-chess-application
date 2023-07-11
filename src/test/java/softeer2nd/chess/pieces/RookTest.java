package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Rook;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {


    private Rook rook;
    private Position testPosition;
    @BeforeEach
    void init() {
        rook = Rook.createBlack();
        testPosition = new Position(2, 2);
    }

    @Test
    @DisplayName("상하좌우 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        rook.verifyMovePosition(testPosition, new Position(0, 2));
        rook.verifyMovePosition(testPosition, new Position(2, 0));
        rook.verifyMovePosition(testPosition, new Position(6, 2));
        rook.verifyMovePosition(testPosition, new Position(2, 6));

        assertThrows(OutOfPieceRangeException.class, () -> rook.verifyMovePosition(testPosition, new Position(4, 4)));
        assertThrows(OutOfPieceRangeException.class, () -> rook.verifyMovePosition(testPosition, new Position(0, 1)));
    }
}
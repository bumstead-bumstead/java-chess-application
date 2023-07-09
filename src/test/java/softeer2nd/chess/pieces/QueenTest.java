package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    private Queen queen;
    @BeforeEach
    void init() {
        queen = Queen.createBlack(new Position(3, 3));
    }

    @Test
    @DisplayName("상하좌우 대각선 이외에 이동은 불가능하다.")
    void verifyMovePosition() {
        queen.verifyMovePosition(new Position(1, 1));
        queen.verifyMovePosition(new Position(3, 7));
        queen.verifyMovePosition(new Position(7, 3));
        queen.verifyMovePosition(new Position(5, 1));

        assertThrows(OutOfPieceRangeException.class, () -> queen.verifyMovePosition(new Position(5, 2)));
        assertThrows(OutOfPieceRangeException.class, () -> queen.verifyMovePosition(new Position(5, 4)));
    }

}
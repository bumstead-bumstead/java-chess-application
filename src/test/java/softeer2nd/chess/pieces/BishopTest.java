package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {


    private Bishop bishop;
    @BeforeEach
    void init() {
        bishop = Bishop.createBlack(new Position(1, 1));
    }

    @Test
    @DisplayName("대각선 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        bishop.verifyMovePosition(new Position(0, 0));
        bishop.verifyMovePosition(new Position(7, 7));
        bishop.verifyMovePosition(new Position(0, 2));
        assertThrows(OutOfPieceRangeException.class, () -> bishop.verifyMovePosition(new Position(1, 2)));
        assertThrows(OutOfPieceRangeException.class, () -> bishop.verifyMovePosition(new Position(3, 4)));
    }

}
package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Knight;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {


    private Knight knight;
    @BeforeEach
    void init() {
        knight = Knight.createBlack(new Position(2, 2));
    }

    @Test
    @DisplayName("나이트의 행보법 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        knight.verifyMovePosition(new Position(0, 1));
        knight.verifyMovePosition(new Position(1, 0));
        knight.verifyMovePosition(new Position(4, 3));
        knight.verifyMovePosition(new Position(3, 4));

        assertThrows(OutOfPieceRangeException.class, () -> knight.verifyMovePosition(new Position(4, 4)));
        assertThrows(OutOfPieceRangeException.class, () -> knight.verifyMovePosition(new Position(2, 3)));
    }
}
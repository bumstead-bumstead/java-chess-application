package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.PieceFactory;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest {


    private Piece knight;
    private Position testPosition;
    @BeforeEach
    void init() {
        knight = PieceFactory.createBlackKnight();
        testPosition = new Position(2, 2);
    }

    @Test
    @DisplayName("나이트의 행보법 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        knight.verifyMovePosition(testPosition, new Position(0, 1));
        knight.verifyMovePosition(testPosition, new Position(1, 0));
        knight.verifyMovePosition(testPosition, new Position(4, 3));
        knight.verifyMovePosition(testPosition, new Position(3, 4));

        assertThrows(OutOfPieceRangeException.class, () -> knight.verifyMovePosition(testPosition, new Position(4, 4)));
        assertThrows(OutOfPieceRangeException.class, () -> knight.verifyMovePosition(testPosition, new Position(2, 3)));
    }
}
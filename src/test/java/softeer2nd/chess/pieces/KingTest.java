package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.PieceFactory;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {

    private Piece king;
    private Position testPosition;
    @BeforeEach
    void init() {
        king = PieceFactory.createBlackKing();
        testPosition = new Position(1, 1);
    }

    @Test
    @DisplayName("주변 한 칸 이상의 이동은 불가능하다.")
    void verifyMovePosition() {
        king.verifyMovePosition(testPosition, new Position(0, 0));
        king.verifyMovePosition(testPosition, new Position(2, 2));

        assertThrows(OutOfPieceRangeException.class, () -> king.verifyMovePosition(testPosition, new Position(3, 2)));
    }
}

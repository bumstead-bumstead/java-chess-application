package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.PieceFactory;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueenTest {
    private Piece queen;
    private Position testPosition;
    @BeforeEach
    void init() {
        queen = PieceFactory.createBlackQueen();
        testPosition = new Position(3, 3);
    }

    @Test
    @DisplayName("상하좌우 대각선 이외에 이동은 불가능하다.")
    void verifyMovePosition() {
        queen.verifyMovePosition(testPosition, new Position(1, 1));
        queen.verifyMovePosition(testPosition, new Position(3, 7));
        queen.verifyMovePosition(testPosition, new Position(7, 3));
        queen.verifyMovePosition(testPosition, new Position(5, 1));

        assertThrows(OutOfPieceRangeException.class, () -> queen.verifyMovePosition(testPosition, new Position(5, 2)));
        assertThrows(OutOfPieceRangeException.class, () -> queen.verifyMovePosition(testPosition, new Position(5, 4)));
    }

}
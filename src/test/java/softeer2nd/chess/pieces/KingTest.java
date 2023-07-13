package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.PieceFactory;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {

    private Piece king;
    private Position testPosition;

    @BeforeEach
    void setup() {
        king = PieceFactory.createBlackKing();
        testPosition = new Position(1, 1);
    }

    @ParameterizedTest(name = "[1, 1] to [{0}, {1}]")
    @DisplayName("킹 행보_성공")
    @MethodSource("providePositionForVerifyMoveSuccess")
    void verifyKingMove(int row, int column) {
        king.verifyMovePosition(testPosition, new Position(row, column));
    }

    @ParameterizedTest(name = "[1, 1] to [{0}, {1}]")
    @DisplayName("킹 행보_실패")
    @MethodSource("providePositionForVerifyMoveFail")
    void verifyNonKingMove(int row, int column) {
        assertThrows(IllegalCommandException.class, () -> king.verifyMovePosition(testPosition, new Position(row, column)));
    }

    private static Stream<Arguments> providePositionForVerifyMoveSuccess() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(0, 1),
                Arguments.of(0, 2),
                Arguments.of(1, 0),
                Arguments.of(1, 2),
                Arguments.of(2, 0),
                Arguments.of(2, 1),
                Arguments.of(2, 2)
        );
    }

    private static Stream<Arguments> providePositionForVerifyMoveFail() {
        return Stream.of(
                Arguments.of(2, 3),
                Arguments.of(1, 1),
                Arguments.of(3, 3),
                Arguments.of(5, 6)
        );
    }
}

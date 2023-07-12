package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.PieceFactory;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueenTest {
    private Piece queen;
    private Position testPosition;

    @BeforeEach
    void init() {
        queen = PieceFactory.createBlackQueen();
        testPosition = new Position(3, 3);
    }

    @ParameterizedTest(name = "[3, 3] to [{0}, {1}]")
    @DisplayName("퀸 행보_성공")
    @MethodSource("providePositionForVerifyMoveSuccess")
    void verifyQueenMove(int row, int column) {
        queen.verifyMovePosition(testPosition, new Position(row, column));
    }

    @ParameterizedTest(name = "[3, 3] to [{0}, {1}]")
    @DisplayName("퀸 행보_실패")
    @MethodSource("providePositionForVerifyMoveFail")
    void verifyNonQueenMove(int row, int column) {
        assertThrows(OutOfPieceRangeException.class, () -> queen.verifyMovePosition(testPosition, new Position(row, column)));
    }

    private static Stream<Arguments> providePositionForVerifyMoveSuccess() {
        return Stream.of(
                Arguments.of(2, 2),
                Arguments.of(4, 4),
                Arguments.of(1, 1),
                Arguments.of(0, 0),
                Arguments.of(5, 5),
                Arguments.of(6, 6),
                Arguments.of(7, 7),
                Arguments.of(3, 2),
                Arguments.of(3, 1),
                Arguments.of(3, 0),
                Arguments.of(2, 3),
                Arguments.of(1, 3),
                Arguments.of(0, 3),
                Arguments.of(3, 4),
                Arguments.of(3, 5),
                Arguments.of(3, 6),
                Arguments.of(3, 7),
                Arguments.of(4, 3),
                Arguments.of(5, 3),
                Arguments.of(6, 3),
                Arguments.of(7, 3)
        );
    }

    private static Stream<Arguments> providePositionForVerifyMoveFail() {
        return Stream.of(
                Arguments.of(2, 6),
                Arguments.of(0, 1),
                Arguments.of(1, 0),
                Arguments.of(7, 4),
                Arguments.of(7, 5),
                Arguments.of(6, 2)
        );
    }

}
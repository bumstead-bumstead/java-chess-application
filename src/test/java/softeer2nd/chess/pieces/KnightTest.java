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

class KnightTest {


    private Piece knight;
    private Position testPosition;

    @BeforeEach
    void init() {
        knight = PieceFactory.createBlackKnight();
        testPosition = new Position(2, 2);
    }

    @ParameterizedTest(name = "[2, 2] to [{0}, {1}]")
    @DisplayName("나이트 행보_성공")
    @MethodSource("providePositionForVerifyMoveSuccess")
    void verifyKnightMove(int row, int column) {
        knight.verifyMovePosition(testPosition, new Position(row, column));
    }

    @ParameterizedTest(name = "[2, 2] to [{0}, {1}]")
    @DisplayName("나이트 행보_실패")
    @MethodSource("providePositionForVerifyMoveFail")
    void verifyNonKnightMove(int row, int column) {
        assertThrows(OutOfPieceRangeException.class, () -> knight.verifyMovePosition(testPosition, new Position(row, column)));
    }

    private static Stream<Arguments> providePositionForVerifyMoveSuccess() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(0, 3),
                Arguments.of(1, 0),
                Arguments.of(1, 4),
                Arguments.of(3, 0),
                Arguments.of(3, 4),
                Arguments.of(4, 1),
                Arguments.of(4, 3)
        );
    }

    private static Stream<Arguments> providePositionForVerifyMoveFail() {
        return Stream.of(
                Arguments.of(2, 2),
                Arguments.of(0, 2),
                Arguments.of(0, 4),
                Arguments.of(1, 1),
                Arguments.of(1, 2),
                Arguments.of(1, 3),
                Arguments.of(3, 1),
                Arguments.of(4, 2)
        );
    }
}
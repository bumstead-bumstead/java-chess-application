package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopTest {


    private Bishop bishop;

    @BeforeEach
    void init() {
        bishop = Bishop.createBlack(new Position(1, 1));
    }

    @ParameterizedTest
    @DisplayName("대각선 상의 이동_성공")
    @MethodSource("providePositionForVerifyDiagonalMove")
    void verifyDiagonalMove(int row, int column) {
        bishop.verifyMovePosition(new Position(row, column));
    }

    @ParameterizedTest
    @DisplayName("대각선 외의 이동_실패")
    @MethodSource("providePositionForVerifyNondiagonalMove")
    void verifyNondiagonalMove(int row, int column) {
        assertThrows(OutOfPieceRangeException.class, () -> bishop.verifyMovePosition(new Position(row, column)));
    }

    private static Stream<Arguments> providePositionForVerifyDiagonalMove() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(2, 2),
                Arguments.of(0, 2),
                Arguments.of(2, 0),
                Arguments.of(3, 3),
                Arguments.of(0, 0)
        );
    }

    private static Stream<Arguments> providePositionForVerifyNondiagonalMove() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(3, 4),
                Arguments.of(7, 6),
                Arguments.of(5, 6)
        );
    }
}
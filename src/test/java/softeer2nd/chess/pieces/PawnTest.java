package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.PieceFactory;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest {


    private Piece pawn;
    private Position testPosition;
    @BeforeEach
    void init() {
        pawn = PieceFactory.createBlackPawn();
        testPosition = new Position(2, 2);
    }

    @Test
    @DisplayName("black pawn - 전방 세 칸 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        pawn.verifyMovePosition(testPosition, new Position(3, 2));
        pawn.verifyMovePosition(testPosition, new Position(3, 1));
        pawn.verifyMovePosition(testPosition, new Position(3, 3));

        assertThrows(OutOfPieceRangeException.class, () -> pawn.verifyMovePosition(testPosition, new Position(4, 4)));
        assertThrows(OutOfPieceRangeException.class, () -> pawn.verifyMovePosition(testPosition, new Position(5, 3)));
    }

    @ParameterizedTest(name = "[2, 2] to [{0}, {1}]")
    @DisplayName("black pawn 행보_성공")
    @MethodSource("providePositionForVerifyMoveSuccessBlackPawn")
    void verifyBlackPawnMove(int row, int column) {
        pawn.verifyMovePosition(testPosition, new Position(row, column));
    }

    @ParameterizedTest(name = "[2, 2] to [{0}, {1}]")
    @DisplayName("black pawn 행보_실패")
    @MethodSource("providePositionForVerifyMoveFailBlackPawn")
    void verifyNonBlackPawnMove(int row, int column) {
        assertThrows(OutOfPieceRangeException.class, () -> pawn.verifyMovePosition(testPosition, new Position(row, column)));
    }

    private static Stream<Arguments> providePositionForVerifyMoveSuccessBlackPawn() {
        return Stream.of(
                Arguments.of(3, 2),
                Arguments.of(3, 3),
                Arguments.of(3, 1)
        );
    }

    private static Stream<Arguments> providePositionForVerifyMoveFailBlackPawn() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(1, 1),
                Arguments.of(1, 0),
                Arguments.of(2, 3),
                Arguments.of(2, 1)
        );
    }

    @ParameterizedTest(name = "[2, 2] to [{0}, {1}]")
    @DisplayName("black pawn 행보_성공")
    @MethodSource("providePositionForVerifyMoveSuccessWhitePawn")
    void verifyDiagonalMove(int row, int column) {
        pawn = PieceFactory.createWhitePawn();
        pawn.verifyMovePosition(testPosition, new Position(row, column));
    }

    @ParameterizedTest(name = "[2, 2] to [{0}, {1}]")
    @DisplayName("black pawn 행보_실패")
    @MethodSource("providePositionForVerifyMoveFailWhitePawn")
    void verifyNondiagonalMove(int row, int column) {
        pawn = PieceFactory.createWhitePawn();
        assertThrows(OutOfPieceRangeException.class, () -> pawn.verifyMovePosition(testPosition, new Position(row, column)));
    }

    private static Stream<Arguments> providePositionForVerifyMoveSuccessWhitePawn() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(1, 3),
                Arguments.of(1, 1)
        );
    }

    private static Stream<Arguments> providePositionForVerifyMoveFailWhitePawn() {
        return Stream.of(
                Arguments.of(3, 2),
                Arguments.of(3, 1),
                Arguments.of(3, 0),
                Arguments.of(2, 3),
                Arguments.of(2, 1)
        );
    }
}
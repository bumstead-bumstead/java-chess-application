package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.IllegalCommandException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.domain.pieces.PieceFactory.*;

public class PieceTest {
    Piece whitePiece;
    Piece blackPiece;
    Position testPosition;

    @BeforeEach
    void setup() {
        testPosition = new Position(0, 0);
        whitePiece = createWhiteBishop();
        blackPiece = createBlackBishop();
    }

    @ParameterizedTest
    @MethodSource("providePieceAndColorAndTypeForCreatePiece")
    void create_piece(Piece piece, Piece.Color color, Piece.Type type) {
        verifyPiece(piece, color, type);
    }

    private static Stream<Arguments> providePieceAndColorAndTypeForCreatePiece() {
        Position testPosition = new Position(0, 0);

        return Stream.of(
                Arguments.of(createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP),
                Arguments.of(createBlackRook(), Piece.Color.BLACK, Piece.Type.ROOK),
                Arguments.of(createBlackKing(), Piece.Color.BLACK, Piece.Type.KING),
                Arguments.of(createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT),
                Arguments.of(createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN),
                Arguments.of(createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN),
                Arguments.of(createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP),
                Arguments.of(createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK),
                Arguments.of(createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING),
                Arguments.of(createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT),
                Arguments.of(createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN),
                Arguments.of(createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN)
        );
    }

    @ParameterizedTest
    @MethodSource("provideColorForHasColor")
    void hasColor(Piece piece, Piece.Color color, boolean result) {
        assertEquals(result, piece.isSameColor(color));
    }

    private static Stream<Arguments> provideColorForHasColor() {
        Piece whitePiece = createWhiteBishop();
        Piece blackPiece = createBlackBishop();

        return Stream.of(
                Arguments.of(whitePiece, Piece.Color.WHITE, true),
                Arguments.of(whitePiece, Piece.Color.BLACK, false),
                Arguments.of(blackPiece, Piece.Color.WHITE, false),
                Arguments.of(blackPiece, Piece.Color.BLACK, true)
        );
    }

    @Test
    @DisplayName("흰색 폰이면 p를, 검정색 폰이면 P를 가져야한다.")
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }

    @ParameterizedTest
    @MethodSource("providePiecesForVerifySameColor")
    @DisplayName("verifySameColor 테스트")
    void verifySameColor(Piece piece, Piece targetPiece) {
        assertThrows(IllegalCommandException.class, () -> piece.verifyAlly(targetPiece));
    }

    private static Stream<Arguments> providePiecesForVerifySameColor() {
        return Stream.of(
                Arguments.of(createBlackPawn(), createBlackPawn()),
                Arguments.of(createWhitePawn(), createWhitePawn())
        );
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }
}

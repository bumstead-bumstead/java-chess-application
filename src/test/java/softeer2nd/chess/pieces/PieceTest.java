package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    Piece whitePiece;
    Piece blackPiece;
    Position testPosition;

    @BeforeEach
    void init() {
        testPosition = new Position(0, 0);
        whitePiece = Bishop.createWhite();
        blackPiece = Bishop.createBlack();
    }

    @ParameterizedTest
    @MethodSource("providePieceAndColorAndTypeForCreatePiece")
    void create_piece(Piece piece, Piece.Color color, Piece.Type type) {
        verifyPiece(piece, color, type);
    }

    private static Stream<Arguments> providePieceAndColorAndTypeForCreatePiece() {
        Position testPosition = new Position(0, 0);

        return Stream.of(
                Arguments.of(Bishop.createBlack(), Piece.Color.BLACK, Piece.Type.BISHOP),
                Arguments.of(Rook.createBlack(), Piece.Color.BLACK, Piece.Type.ROOK),
                Arguments.of(King.createBlack(), Piece.Color.BLACK, Piece.Type.KING),
                Arguments.of(Knight.createBlack(), Piece.Color.BLACK, Piece.Type.KNIGHT),
                Arguments.of(Pawn.createBlack(), Piece.Color.BLACK, Piece.Type.PAWN),
                Arguments.of(Queen.createBlack(), Piece.Color.BLACK, Piece.Type.QUEEN),
                Arguments.of(Bishop.createWhite(), Piece.Color.WHITE, Piece.Type.BISHOP),
                Arguments.of(Rook.createWhite(), Piece.Color.WHITE, Piece.Type.ROOK),
                Arguments.of(King.createWhite(), Piece.Color.WHITE, Piece.Type.KING),
                Arguments.of(Knight.createWhite(), Piece.Color.WHITE, Piece.Type.KNIGHT),
                Arguments.of(Pawn.createWhite(), Piece.Color.WHITE, Piece.Type.PAWN),
                Arguments.of(Queen.createWhite(), Piece.Color.WHITE, Piece.Type.QUEEN)
        );
    }

    @ParameterizedTest
    @MethodSource("provideColorForHasColor")
    void hasColor(Piece piece, Piece.Color color, boolean result) {
        assertEquals(result, piece.hasColor(color));
    }
    private static Stream<Arguments> provideColorForHasColor() {
        Piece whitePiece = Bishop.createWhite();
        Piece blackPiece = Bishop.createBlack();

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

    private void verifyPiece(final Piece piece, final Piece.Color color, final Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }
}

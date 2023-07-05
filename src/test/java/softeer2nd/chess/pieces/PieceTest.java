package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Test;
import softeer2nd.chess.PieceColor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {


    @Test
    public void create_기본생성자() throws Exception {
        Piece piece = new Piece();
        assertEquals(Piece.COLOR_WHITE, piece.getColor());
        assertEquals(Piece.REPRESENTATION_WHITE, piece.getRepresentation());
    }

    @Test
    public void create() {
        verifyPawn(Piece.COLOR_WHITE);
        verifyPawn(Piece.COLOR_BLACK);
    }

    void verifyPawn(final String color) {
        Piece piece = new Piece(color);
        assertEquals(color, piece.getColor());
        assertEquals(PieceColor.valueOf(color.toUpperCase()).getRepresentation(), piece.getRepresentation());
    }
}

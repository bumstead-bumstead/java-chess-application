package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.PieceColor;
import softeer2nd.chess.pieces.Pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {


    @Test
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.COLOR_WHITE, pawn.getColor());
        assertEquals(Pawn.REPRESENTATION_WHITE, pawn.getRepresentation());
    }

    @Test
    public void create() {
        verifyPawn(Pawn.COLOR_WHITE);
        verifyPawn(Pawn.COLOR_BLACK);
    }

    void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertEquals(color, pawn.getColor());
        assertEquals(PieceColor.valueOf(color.toUpperCase()).getRepresentation(), pawn.getRepresentation());
    }
}

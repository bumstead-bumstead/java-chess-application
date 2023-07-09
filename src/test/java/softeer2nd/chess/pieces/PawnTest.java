package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.OutOfPieceRangeException;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {


    private Pawn pawn;
    @BeforeEach
    void init() {
        pawn = Pawn.createBlack(new Position(2, 2));
    }

    @Test
    @DisplayName("black pawn - 전방 세 칸 이외의 이동은 불가능하다.")
    void verifyMovePosition() {
        pawn.verifyMovePosition(new Position(3, 2));
        pawn.verifyMovePosition(new Position(3, 1));
        pawn.verifyMovePosition(new Position(3, 3));

        assertThrows(OutOfPieceRangeException.class, () -> pawn.verifyMovePosition(new Position(4, 4)));
        assertThrows(OutOfPieceRangeException.class, () -> pawn.verifyMovePosition(new Position(5, 3)));
    }
}
package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {
    private Board board;

    @BeforeEach
    public void init() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {
        verifyInsertion(board, Pawn.COLOR_WHITE, 0);
        verifyInsertion(board, Pawn.COLOR_BLACK, 1);
    }

    private static void verifyInsertion(Board board, String color, int sequenceNumber) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);
        assertEquals(sequenceNumber + 1, board.size());
        assertEquals(pawn, board.findPawn(sequenceNumber));
    }

    @Test
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }
}
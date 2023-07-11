package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {
    private Board board;
    private ChessView chessView;

    @BeforeEach
    public void setup() {
        board = new Board();
        chessView = new ChessView();
        board.initialize();
    }

    @Test
    @DisplayName("초기화된 보드의 상태를 확인한다.")
    public void create() throws Exception {
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals( appendNewLine("--BOARD--") +
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr") +
                appendNewLine("--------"),
                chessView.showBoard(board));
    }

    @Test
    @DisplayName("초기화된 상태의 보드의 검정색 폰의 개수는 8개를 반환해야한다.")
    public void countBlackPawn() {
        int numberOfBlackPawn = board.count(Piece.Color.BLACK, Piece.Type.PAWN);
        assertEquals(numberOfBlackPawn, 8);
    }

    @Test
    @DisplayName("초기화된 상태의 보드의 흰색 룩의 개수는 2개를 반환해야한다.")
    public void countWhiteRook() {
        int numberOfWhiteRook = board.count(Piece.Color.WHITE, Piece.Type.ROOK);
        assertEquals(numberOfWhiteRook, 2);
    }

    @Test
    @DisplayName("초기화된 상태에서 a8 : 검정색 룩, h8 : 검정색 룩, a1 : 흰색 룩, h1 : 흰색 룩이 위치해야한다.")
    public void findPiece() throws Exception {
        assertEquals(Rook.createBlack(new Position("a8")), board.findPiece("a8"));
        assertEquals(Rook.createBlack(new Position("h8")), board.findPiece("h8"));
        assertEquals(Rook.createWhite(new Position("a1")), board.findPiece("a1"));
        assertEquals(Rook.createWhite(new Position("h1")), board.findPiece("h1"));
    }

    @Test
    @DisplayName("b5에 검정색 룩을 놓으면 board에 반영되어야 한다.")
    public void setPiece() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Rook.createBlack(new Position("b5"));
        board.setPiece(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(chessView.showBoard(board));
    }

    @Test
    @DisplayName("정렬 기능 테스트_오름차순")
    public void sort() {
        board.initializeEmpty();

        addPiece("f1", Pawn.createBlack(new Position("f1")));
        addPiece("a2", Bishop.createBlack(new Position("a2")));
        addPiece("b3", Rook.createBlack(new Position("b3")));
        addPiece("d3", Queen.createBlack(new Position("d3")));

        List<Piece> expectedResult = List.of(Pawn.createBlack(new Position("f1")),
                Bishop.createBlack(new Position("a2")),
                Rook.createBlack(new Position("b3")),
                Queen.createBlack(new Position("d3")));

        List<Piece> actualResult = board.getSortedPiecesAscending(Piece.Color.BLACK);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("정렬 기능 테스트_내림차순")
    public void sortDescending() {
        board.initializeEmpty();

        addPiece("f1", Pawn.createBlack(new Position("f1")));
        addPiece("a2", Bishop.createBlack(new Position("a2")));
        addPiece("b3", Rook.createBlack(new Position("b3")));
        addPiece("d3", Queen.createBlack(new Position("d3")));

        List<Piece> expectedResult = List.of(Queen.createBlack(new Position("d3")),
                Rook.createBlack(new Position("b3")),
                Bishop.createBlack(new Position("a2")),
                Pawn.createBlack(new Position("f1"))
                );

        List<Piece> actualResult = board.getSortedPiecesDescending(Piece.Color.BLACK);

        assertEquals(actualResult, expectedResult);
    }

    private void addPiece(String position, Piece piece) {
        board.setPiece(position, piece);
    }
}
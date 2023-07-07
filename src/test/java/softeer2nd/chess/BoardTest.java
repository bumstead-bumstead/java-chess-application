package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
        board.initialize();
    }

    @Test
    @DisplayName("초기화된 보드의 상태를 확인한다.")
    public void create() throws Exception {
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
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
        assertEquals(Piece.createBlackRook(new Position("a8")), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(new Position("h8")), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(new Position("a1")), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(new Position("h1")), board.findPiece("h1"));
    }

    @Test
    @DisplayName("b5에 검정색 룩을 move하면 board에 반영되어야 한다.")
    public void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook(new Position("b5"));
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("점수 계산 테스트")
    public void caculcatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(new Position("b6")));
        addPiece("e6", Piece.createBlackQueen(new Position("e6")));
        addPiece("b8", Piece.createBlackKing(new Position("b8")));
        addPiece("c8", Piece.createBlackRook(new Position("c8")));

        addPiece("f2", Piece.createWhitePawn(new Position("f2")));
        addPiece("g2", Piece.createWhitePawn(new Position("g2")));
        addPiece("e1", Piece.createWhiteRook(new Position("e1")));
        addPiece("f1", Piece.createWhiteKing(new Position("f1")));

        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);
        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("같은 열에 폰이 두 개 이상 있는 경우 개당 0.5점을 갖는다.")
    public void calculatePointWithMultiplePawns() throws Exception {
        board.initializeEmpty();

        addPiece("f1", Piece.createBlackPawn(new Position("f1")));
        addPiece("f2", Piece.createBlackPawn(new Position("f2")));
        addPiece("f3", Piece.createBlackPawn(new Position("f3")));

        assertEquals(1.5, board.calculatePoint(Piece.Color.BLACK), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("정렬 기능 테스트_오름차순")
    public void sort() {
        board.initializeEmpty();

        addPiece("f1", Piece.createBlackPawn(new Position("f1")));
        addPiece("a2", Piece.createBlackBishop(new Position("a2")));
        addPiece("b3", Piece.createBlackRook(new Position("b3")));
        addPiece("d3", Piece.createBlackQueen(new Position("d3")));

        List<Piece> expectedResult = List.of(Piece.createBlackPawn(new Position("f1")),
                Piece.createBlackBishop(new Position("a2")),
                Piece.createBlackRook(new Position("b3")),
                Piece.createBlackQueen(new Position("d3")));

        List<Piece> actualResult = board.getSortedPiecesAscending(Piece.Color.BLACK);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("정렬 기능 테스트_내림차순")
    public void sortDescending() {
        board.initializeEmpty();

        addPiece("f1", Piece.createBlackPawn(new Position("f1")));
        addPiece("a2", Piece.createBlackBishop(new Position("a2")));
        addPiece("b3", Piece.createBlackRook(new Position("b3")));
        addPiece("d3", Piece.createBlackQueen(new Position("d3")));

        List<Piece> expectedResult = List.of(Piece.createBlackQueen(new Position("d3")),
                Piece.createBlackRook(new Position("b3")),
                Piece.createBlackBishop(new Position("a2")),
                Piece.createBlackPawn(new Position("f1"))
                );

        List<Piece> actualResult = board.getSortedPiecesDescending(Piece.Color.BLACK);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("기물 이동 테스트")
    public void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(new Position(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(new Position(targetPosition)), board.findPiece(targetPosition));
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
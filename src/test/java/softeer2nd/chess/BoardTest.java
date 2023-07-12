package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.exceptions.BlockedByPieceException;
import softeer2nd.chess.utils.ChessPositionParser;
import softeer2nd.chess.view.ChessConsoleView;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.domain.pieces.PieceFactory.*;

class BoardTest {
    private Board board;
    private ChessConsoleView chessView;

    @BeforeEach
    public void setup() {
        board = new Board();
        chessView = new ChessConsoleView();
        board.initialize();
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

    //todo : parameterized test 적용
    @Test
    @DisplayName("초기화된 상태에서 a8 : 검정색 룩, h8 : 검정색 룩, a1 : 흰색 룩, h1 : 흰색 룩이 위치해야한다.")
    public void findPiece() throws Exception {
        assertEquals(createBlackRook(), board.findPiece(new Position("a8")));
        assertEquals(createBlackRook(), board.findPiece(new Position("h8")));
        assertEquals(createWhiteRook(), board.findPiece(new Position("a1")));
        assertEquals(createWhiteRook(), board.findPiece(new Position("h1")));
    }

    @Test
    @DisplayName("b5에 검정색 룩을 놓으면 board에 반영되어야 한다.")
    public void setPiece() throws Exception {
        board.initializeEmpty();

        Position position = ChessPositionParser.parse("b5");
        Piece piece = createBlackRook();
        board.setPiece(position, piece);

        assertEquals(piece, board.findPiece(position));
    }

    @Test
    @DisplayName("정렬 기능 테스트_오름차순")
    public void sort() {
        board.initializeEmpty();

        addPiece(new Position("f1"), createBlackPawn());
        addPiece(new Position("a2"), createBlackBishop());
        addPiece(new Position("b3"), createBlackRook());
        addPiece(new Position("d3"), createBlackQueen());

        List<Piece> expectedResult = List.of(createBlackPawn(),
                createBlackBishop(),
                createBlackRook(),
                createBlackQueen());

        List<Piece> actualResult = board.getSortedPiecesAscending(Piece.Color.BLACK);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("정렬 기능 테스트_내림차순")
    public void sortDescending() {
        board.initializeEmpty();

        addPiece(new Position("f1"), createBlackPawn());
        addPiece(new Position("a2"), createBlackBishop());
        addPiece(new Position("b3"), createBlackRook());
        addPiece(new Position("d3"), createBlackQueen());

        List<Piece> expectedResult = List.of(createBlackQueen(),
                createBlackRook(),
                createBlackBishop(),
                createBlackPawn()
                );

        List<Piece> actualResult = board.getSortedPiecesDescending(Piece.Color.BLACK);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("경로 중간에 기물이 존재하지 않으면 예외를 발생시키지 않는다.")
    void verifyBlockedByPieceSuccess() {
        board.initializeEmpty();
        Position sourcePosition = new Position(4, 4);
        Position targetPosition = new Position(5, 5);
        board.setPiece(sourcePosition, createBlackBishop());
        board.setPiece(targetPosition, createWhitePawn());

        board.verifyBlockedByPiece(sourcePosition, targetPosition);

    }

    @ParameterizedTest(name = "{0} : [4, 4] to {1} blocked by {2}")
    @MethodSource("ProvidePositionsForVerifyBlockedByPiece")
    @DisplayName("경로 중간에 기물이 존재하면 예외를 발생시킨다.")
    void verifyBlockedByPiece(Piece piece, Position targetPosition, Position blockedPosition) {
        //given
        board.initializeEmpty();
        Position sourcePosition = new Position(4, 4);
        board.setPiece(sourcePosition, piece);
        board.setPiece(blockedPosition, createBlackPawn());

        //when, then
        assertThrows(BlockedByPieceException.class, () -> board.verifyBlockedByPiece(sourcePosition, targetPosition));
    }

    private static Stream<Arguments> ProvidePositionsForVerifyBlockedByPiece() {
        return Stream.of(
                Arguments.of(createBlackBishop(), new Position(7, 7), new Position(5, 5)),
                Arguments.of(createBlackBishop(), new Position(7, 7), new Position(6, 6)),
                Arguments.of(createBlackBishop(), new Position(0, 0), new Position(3, 3)),
                Arguments.of(createBlackBishop(), new Position(0, 0), new Position(2, 2)),
                Arguments.of(createBlackBishop(), new Position(0, 0), new Position(1, 1)),
                Arguments.of(createBlackBishop(), new Position(7, 1), new Position(5, 3)),
                Arguments.of(createBlackBishop(), new Position(7, 1), new Position(6, 2)),
                Arguments.of(createBlackBishop(), new Position(1, 7), new Position(3, 5)),
                Arguments.of(createBlackBishop(), new Position(1, 7), new Position(2, 6)),

                Arguments.of(createBlackQueen(), new Position(7, 7), new Position(5, 5)),
                Arguments.of(createBlackQueen(), new Position(7, 7), new Position(6, 6)),
                Arguments.of(createBlackQueen(), new Position(0, 0), new Position(3, 3)),
                Arguments.of(createBlackQueen(), new Position(0, 0), new Position(2, 2)),
                Arguments.of(createBlackQueen(), new Position(0, 0), new Position(1, 1)),
                Arguments.of(createBlackQueen(), new Position(7, 1), new Position(5, 3)),
                Arguments.of(createBlackQueen(), new Position(7, 1), new Position(6, 2)),
                Arguments.of(createBlackQueen(), new Position(1, 7), new Position(3, 5)),
                Arguments.of(createBlackQueen(), new Position(1, 7), new Position(2, 6)),
                Arguments.of(createBlackQueen(), new Position(7, 4), new Position(5, 4)),
                Arguments.of(createBlackQueen(), new Position(7, 4), new Position(6, 4)),
                Arguments.of(createBlackQueen(), new Position(4, 7), new Position(4, 5)),
                Arguments.of(createBlackQueen(), new Position(4, 7), new Position(4, 6)),
                Arguments.of(createBlackQueen(), new Position(4, 0), new Position(4, 3)),
                Arguments.of(createBlackQueen(), new Position(4, 0), new Position(4, 2)),
                Arguments.of(createBlackQueen(), new Position(4, 0), new Position(4, 1)),
                Arguments.of(createBlackQueen(), new Position(0, 4), new Position(1, 4)),
                Arguments.of(createBlackQueen(), new Position(0, 4), new Position(2, 4)),
                Arguments.of(createBlackQueen(), new Position(0, 4), new Position(3, 4)),

                Arguments.of(createBlackRook(), new Position(7, 4), new Position(5, 4)),
                Arguments.of(createBlackRook(), new Position(7, 4), new Position(6, 4)),
                Arguments.of(createBlackRook(), new Position(4, 7), new Position(4, 5)),
                Arguments.of(createBlackRook(), new Position(4, 7), new Position(4, 6)),
                Arguments.of(createBlackRook(), new Position(4, 0), new Position(4, 3)),
                Arguments.of(createBlackRook(), new Position(4, 0), new Position(4, 2)),
                Arguments.of(createBlackRook(), new Position(4, 0), new Position(4, 1)),
                Arguments.of(createBlackRook(), new Position(0, 4), new Position(1, 4)),
                Arguments.of(createBlackRook(), new Position(0, 4), new Position(2, 4)),
                Arguments.of(createBlackRook(), new Position(0, 4), new Position(3, 4))
        );
    }
    private void addPiece(Position position, Piece piece) {
        board.setPiece(position, piece);
    }
}
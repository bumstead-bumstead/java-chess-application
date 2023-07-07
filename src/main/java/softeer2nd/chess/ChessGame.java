package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.utils.ChessPositionParser;

import static softeer2nd.chess.Board.BOARD_LENGTH;
import static softeer2nd.chess.Board.PAWN_HALF_SCORE;

public class ChessGame {
    /*
    * todo : board의 초기화 부분을 제외한 기능을 포함한다.
    * */

    private Board board;

    public ChessGame() {
        this.board = new Board();
    }

    public void start() {
        board.initialize();
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece oldPiece = board.findPiece(sourcePosition);

        Position parsedSourcePosition = ChessPositionParser.parse(sourcePosition);
        Position parsedTargetPosition = ChessPositionParser.parse(targetPosition);

        board.setPiece(sourcePosition, Piece.createBlank(parsedSourcePosition));
        board.setPiece(targetPosition, Piece.createMovedPiece(oldPiece, parsedTargetPosition));
    }
    
    public double calculatePoint(Piece.Color targetColor) {
        double totalPoint = 0;

        for (Rank rank : board.getPieces()) {
            for (int column = 0; column < BOARD_LENGTH; column++) {
                Piece targetPiece = rank.get(column);
                totalPoint += calculatePointOfPiece(targetPiece, targetColor, column);
            }
        }

        return totalPoint;
    }

    private double calculatePointOfPiece(Piece piece, Piece.Color targetColor, int column) {
        double point = 0;

        if (piece.hasColor(targetColor)) {
            point = piece.getType()
                    .getScore();
        }
        if (piece.isPawn() && board.hasMultiplePawnsInColumn(targetColor, column)) {
            point = PAWN_HALF_SCORE;
        }

        return point;
    }





}

package softeer2nd.chess.domain.pieces;

public class PieceFactory {
    public static Piece createBlackPawn() {
        return new Pawn(Piece.Color.BLACK);
    }

    public static Piece createBlackBishop() {
        return new Bishop(Piece.Color.BLACK);
    }

    public static Piece createBlackRook() {
        return new Rook(Piece.Color.BLACK);
    }

    public static Piece createBlackKing() {
        return new King(Piece.Color.BLACK);
    }

    public static Piece createBlackQueen() {
        return new Queen(Piece.Color.BLACK);
    }

    public static Piece createBlackKnight() {
        return new Knight(Piece.Color.BLACK);
    }

    public static Piece createWhitePawn() {
        return new Pawn(Piece.Color.WHITE);
    }

    public static Piece createWhiteBishop() {
        return new Bishop(Piece.Color.WHITE);
    }

    public static Piece createWhiteRook() {
        return new Rook(Piece.Color.WHITE);
    }

    public static Piece createWhiteKing() {
        return new King(Piece.Color.WHITE);
    }

    public static Piece createWhiteQueen() {
        return new Queen(Piece.Color.WHITE);
    }

    public static Piece createWhiteKnight() {
        return new Knight(Piece.Color.WHITE);
    }

    public static Piece createBlank() {
        return new Blank();
    }
}

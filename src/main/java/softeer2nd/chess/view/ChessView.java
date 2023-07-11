package softeer2nd.chess.view;

import softeer2nd.chess.domain.Board;

public interface ChessView {
    String MOVE = "move";
    String END = "end";
    String SCORE = "score";

    void showBoard(Board board);
    String getCommandInput();


}

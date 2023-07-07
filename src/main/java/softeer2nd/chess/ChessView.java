package softeer2nd.chess;

import java.util.List;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class ChessView {

    public String showBoard(List<Rank> board) {
        StringBuilder result = new StringBuilder();

        for (Rank rank : board) {
            result.append(appendNewLine(rank.concat()));
        }

        return result.toString();
    }
}

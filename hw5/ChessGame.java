import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;
/**
 *
 * @author Peter Herman
 * @version 1.0
 */
public class ChessGame {

    private List<Move> moves;

    /**
     * Creates new instance of ChessGame and assigns  given
     * List of moves to moves instance
     *
     * @param moves - List of moves to be assigned to moves field
     */
    public ChessGame(List<? extends Move> moves) {
        this.moves = (List) moves;
    }

    /**
     * Returns unfiltered list of moves
     *
     * @return unfiltered list of moves
     */
    public List<? extends Move> getMoves() {
        List<Move> res = new ArrayList<>();
        for (Move move : moves) {
            res.add(move);
        }
        return res;
    }

    /**
     * Return the nth move
     *
     * @param n - index of move that is to be returned
     * @return move at index n
     */
    public Move getMove(int n) {
        int i = 0;
        for (Move move : moves) {
            if (i == n) {
                return move;
            }
            i++;
        }
        return null;
    }

    /**
     * Returns a list filtered by the predicate.
     *
     * @param filter - the filter for the list
     * @return a new list of moves filtered by the predicate
     */
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> res = new ArrayList<>();
        for (Move move : moves) {
            if (filter.test(move)) {
                res.add(move);
            }
        }
        return res;
    }

    /**
     * Returns list of moves w/ comments (if either or both
     * Ply has a comment).
     *
     * @return  a new list of moves w/ comments filtered by
     * the predicate
     */
    public List<Move> getMovesWithComment() {
        return filter(move -> {
                if (move.getWhitePly().getComment().isPresent()
                    || move.getBlackPly().getComment().isPresent()) {
                    return true;
                }
                return false;
            });
    }

    /**
     * Returns a list of moves w/out comments
     *
     * @return a new list of moves w/out comments filtered by
     * the predicate
     */
    public List<Move> getMovesWithoutComment() {
        return filter(new Predicate<Move>() {
                public boolean test(Move move) {
                    if (! move.getWhitePly().getComment().isPresent()
                        && ! move.getBlackPly().getComment().isPresent()) {
                        return true;
                    }
                    return false;
                }
            });
    }


    /**
     * Returns list of moves w/ a piece of this type (color of the
     * piece doesn't matter)
     *
     * @param p the piece to be searched for
     * @return a list of moves w/ given piece
     */
    public List<Move> getMovesWithPiece(Piece p) {
        class MovesWithPiece implements Predicate<Move> {
            public boolean test(Move move) {
                if (move.getWhitePly().getPiece().algebraicName()
                    .equals(p.algebraicName())
                    || move.getBlackPly().getPiece().algebraicName()
                    .equals(p.algebraicName())) {
                    return true;
                }
                return false;
            }
        }
        MovesWithPiece pred = new MovesWithPiece();
        return filter(pred);

    }

}

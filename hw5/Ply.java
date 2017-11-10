import java.util.Optional;

/**
 *
 * @author Peter Herman
 * @version 1.0
 */
public class Ply {

    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;

    /**
     * Creates new instance of Ply, and assigns values to all
     * instance fields (all of which are passed as arguments
     *
     * @param piece - the type of piece which is being moved
     * @param from - Square on board from where piece is moving
     * @param to - Square on board to which piece is moving
     * @param comment - field which holds optional comment of move
     */
    public Ply (Piece piece, Square from,
                Square to, Optional<? extends String> comment) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.comment = (Optional)comment;

    }

    /**
     * Returns piece being moved
     *
     * @return Piece being moved
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Returns Square from which piece is moving
     *
     * @return Square from which piece is moving
     */
    public Square getFrom() {
        return from;
    }

    /**
     * Returns Square to which piece is moving
     *
     * @return Square to which piece is moving
     */
    public Square getTo() {
        return to;
    }

    /**
     * Returns comment on move being made
     *
     * @return String - comment on move being made
     */
    public Optional<String> getComment() {
        return comment;
    }

}

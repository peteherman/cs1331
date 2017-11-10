/**
 * Represents a King game piece
 *
 * @author pherman3
 * @version 1.0
 */
public class King extends Piece {

    /**
     * Constructor used to make King piece of a given color
     *
     * @param color color from ENUM
     */
    public King(Color color) {
        super(color);
    }
    /**
     * Get the algebraic name of the piece in PGN
     *
     * @return String with algebraic name of piece in PGN
     */
    public String algebraicName() {
        return "K";
    }
    /**
     * Get FEN name of piece depending on its color
     *
     * @return String containing FEN name of piece based on its color and type
     */
    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "K" : "k";
    }
    /**
     * Creates list of possible squares King can move to based on
     * a given location from which the king is moving
     *
     * @param square the position from which the king is moving
     * @return Square[] - array of squares king can move to
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (isInBoard((char) (file + c), (char) (rank + r))) {
                    sq[counter++] = new Square((char) (file + c),
                                               (char) (rank + r));
                }
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}

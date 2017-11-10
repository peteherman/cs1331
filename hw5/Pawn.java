/**
 * Represents a Pawn game piece
 *
 * @author pherman3
 * @version 1.0
 */
public class Pawn extends Piece {
    /**
     * Constructor used to make Pawn  piece of a given color
     *
     * @param color color from ENUM
     */
    public Pawn(Color color) {
        super(color);
    }
    /**
     * Get the algebraic name of the piece in PGN
     *
     * @return String with algebraic name of piece in PGN
     */
    public String algebraicName() {
        return "";
    }
    /**
     * Get FEN name of piece depending on its color
     *
     * @return String containing FEN name of piece based on its color and type
     */
    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "P" : "p";
    }
    /**
     * Creates list of possible squares Pawn  can move to based on
     * a given location from which the pawn  is moving
     *
     * @param square the position from which the pawn  is moving
     * @return Square[] - array of squares pawn can move to
     */
    public Square[] movesFrom(Square square) {
        char rank = square.getRank();
        char file = square.getFile();
        if (getColor() == Color.WHITE) {
            if (rank == '8') {
                return new Square[0];
            } else if (rank == '2') {
                return new Square[]{new Square(file, '4'),
                                    new Square(file, '3')};
            } else {
                return new Square[]{new Square(file,
                                               (char) (rank + 1))};
            }
        } else {
            if (rank == '1') {
                return new Square[0];
            } else if (rank == '7') {
                return new Square[]{new Square(file, '5'),
                                    new Square(file, '6')};
            } else {
                return new Square[]{new Square(file,
                                               (char) (rank - 1))};
            }
        }
    }
}

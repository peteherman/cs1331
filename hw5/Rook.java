/**
 * Represents a Rook game piece
 *
 * @author pherman3
 * @version 1.0
 */
public class Rook extends Piece {

    /**
     * Constructor used to make Rook piece of a given color
     *
     * @param color color from ENUM
     */
    public Rook(Color color) {
        super(color);
    }
    /**
     * Get the algebraic name of the piece in PGN
     *
     * @return String with algebraic name of piece in PGN
     */
    public String algebraicName() {
        return "R";
    }
    /**
     * Get FEN name of piece depending on its color
     *
     * @return String containing FEN name of piece based on its color and type
     */
    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "R" : "r";
    }
    /**
     * Creates list of possible squares Rook can move to based on
     * a given location from which the rook is moving
     *
     * @param square the position from which the rook is moving
     * @return Square[] - array of squares rook can move to
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], rank)) {
                sq[counter++] = new Square(files[0], rank);
            }
            if (isInBoard(files[1], rank)) {
                sq[counter++] = new Square(files[1], rank);
            }
            if (isInBoard(file, ranks[0])) {
                sq[counter++] = new Square(file, ranks[0]);
            }
            if (isInBoard(file, ranks[1])) {
                sq[counter++] = new Square(file, ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}

/**
 * Represents a Knight game piece
 *
 * @author pherman3
 * @version 1.0
 */
public class Knight extends Piece {

    /**
     * Constructor used to create Knight of a given color
     *
     * @param color a Color from color Enum
     */
    public Knight(Color color) {
        super(color);
    }
    /**
     * Get algebraic name of Knight
     *
     * @return string of algebraic name for KNIGHT in PGN
     */
    public String algebraicName() {
        return "N";
    }
    /**
     * Get FEN notation for knight based on its color
     *
     * @return String containig FEN notation for knight based on
     * its color
     */
    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "N" : "n";
    }
    /**
     * Creates list of possible squares Knight can move to based on a given
     * location from which the knight is moving
     *
     * @param square the position from which the knight is moving
     * @return Square[] - array of squares knight can move to
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        char[] ranks = new char[]{(char) (rank - 2),
                                  (char) (rank - 1), (char) (rank + 1),
                                  (char) (rank + 2)};
        char[] files = new char[]{(char) (file - 2),
                                  (char) (file - 1), (char) (file + 1),
                                  (char) (file + 2)};

        if (isInBoard(files[0], ranks[1])) {
            sq[counter++] = new Square(files[0], ranks[1]);
        }
        if (isInBoard(files[0], ranks[2])) {
            sq[counter++] = new Square(files[0], ranks[2]);
        }
        if (isInBoard(files[1], ranks[0])) {
            sq[counter++] = new Square(files[1], ranks[0]);
        }
        if (isInBoard(files[1], ranks[3])) {
            sq[counter++] = new Square(files[1], ranks[3]);
        }
        if (isInBoard(files[2], ranks[0])) {
            sq[counter++] = new Square(files[2], ranks[0]);
        }
        if (isInBoard(files[2], ranks[3])) {
            sq[counter++] = new Square(files[2], ranks[3]);
        }
        if (isInBoard(files[3], ranks[1])) {
            sq[counter++] = new Square(files[3], ranks[1]);
        }
        if (isInBoard(files[3], ranks[2])) {
            sq[counter++] = new Square(files[3], ranks[2]);
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}

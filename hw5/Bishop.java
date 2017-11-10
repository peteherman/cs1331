/**
 * Represents a bishop game piece
 *
 * @author pherman3
 * @version 1.0
 */
public class Bishop extends Piece {

    /**
     * Constructor used to create Bishop of a given color
     *
     * @param color a color of Enum Color
     */
    public Bishop(Color color) {
        super(color);
    }
    /**
     * Returns algebraic name of a piece in PGN
     *
     * @return String with algebraic name of piece
     */
    public String algebraicName() {
        return "B";
    }

    /**
     * Returns piece in FEN based on color
     *
     * @return a string containing FEN name for a piece based
     * on its color
     */
    public String fenName() {
        return getColor() == Color.WHITE ? "B" : "b";
    }
    /**
     * Lists all possible squares the bishop can move to
     *
     * @param square the square the bishop is moving from
     * @return Square[] array of possible Squares bishop can move to
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], ranks[0])) {
                sq[counter++] = new Square(files[0], ranks[0]);
            }
            if (isInBoard(files[1], ranks[0])) {
                sq[counter++] = new Square(files[1], ranks[0]);
            }
            if (isInBoard(files[0], ranks[1])) {
                sq[counter++] = new Square(files[0], ranks[1]);
            }
            if (isInBoard(files[1], ranks[1])) {
                sq[counter++] = new Square(files[1], ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}

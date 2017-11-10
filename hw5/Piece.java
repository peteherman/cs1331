/**
 * Represents a chess game piece
 *
 * @author pherman3
 * @version 1.0
 */
public abstract class Piece {

    private Color color;

    /**
     * creates a piece from a given color
     *
     * @param color the color (from Color enum)  of the piece being created
     */
    public Piece(Color color) {
        this.color = color;
    }
    /**
     * Returns the color of a given piece
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Determines if a square is valid
     *
     * @param file - char file of piece
     * @param rank - char rank of piece
     * @return true if piece is in board
     */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }
    /**
     * Gets board row index of given square
     *
     * @param square a board square
     * @return index of row of square
     */
    public int getCurrentRow(Square square) {
        return Square.POS_RANKS.indexOf(square.getRank());
    }
    /**
     * Gets board column index of given square
     *
     * @param square a board square
     * @return index of column of square
     */
    public int getCurrentCol(Square square) {
        return Square.POS_FILES.indexOf(square.getFile());
    }

    /**
     * Return the algebraic Name for a piece
     *
     * @return the algebraic Name for a piece in PGN
     */
    public abstract String algebraicName();
    /**
     * Return string representation of a piece in FEN
     *
     * @return string representation of  a piece in FEN
     */
    public abstract String fenName();
    /**
     * Get a list of possible final squares to which a piece can move
     *
     * @param square the inital position square from which the piece is moving
     * @return an array of squares to which the piece can move
     */
    public abstract Square[] movesFrom(Square square);
}

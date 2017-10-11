/**
 * Represents a chess game piece
 *
 * @author pherman3
 */
public abstract class Piece {

    private Color color;

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
     * Creates String array from string of possible
     * end position squares
     *
     * @param posSquares a string consisting of all possible
     * final square positions for a piece
     * @return posSquares split into a String array
     */
    public Square[] arrPosSquares(String posSquares) {
        posSquares = posSquares.trim();
        String[] posArr = posSquares.split(" ");
        Square[] endSquares = new Square[posArr.length];
        for (int i = 0; i < posArr.length; i++) {
                endSquares[i] = new Square(posArr[i]);
        }
        return endSquares;
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

/**
 * Represents a square on a chess board
 *
 * @author pherman3
 * @version 1.0
 */
public class Square {

    public static final String POS_RANKS = "12345678";
    public static final String POS_FILES = "abcdefgh";
    public static final int MAX_IND_POS = 8;
    private char file;
    private char rank;
    private String name;
    /**
     * Creates board square of given file and rank
     *
     * @param file - the file for the square to be created
     * @param rank - the rank for the square to be created
     */
    public Square(char file, char rank) throws InvalidSquareException {
        this("" + file + rank);
    }
    /**
     * Creates board using string containing rank and file
     *
     * @param name - string containing square's rank and file
     */
    public Square(String name) throws InvalidSquareException {
        if (!(this.isValid(name))) {
            throw new InvalidSquareException(name);
        }
        this.name = name;
        file = name.charAt(0);
        rank = name.charAt(1);
    }
    /**
     * method determines if a given value for a square is valid
     *
     * @param value - value for a square consisting of rank and file
     * @return boolean - true if value is valid
     */
    public boolean isValid(String value) {
        if (value.length() != 2) {
            return false;
        }
        if (POS_FILES.indexOf(value.charAt(0)) < 0
            || POS_RANKS.indexOf(value.charAt(1)) < 0) {
            return false;
        }
        return true;
    }

    /**
     * Returns a square's name (file and rank)
     *
     * @return String - a square's file and rank
     */
    public String getName() {
        return name;
    }
    /**
     * Get char representing square file
     *
     * @return the file of a square
     */
    public char getFile() {
        return file;
    }
    /**
     * Get char representing square Rank
     *
     * @return the rank of a square
     */
    public char getRank() {
        return rank;
    }
    /**
     * Get string representation of a square (rank + file)
     *
     * @return a string containing the rank and file of the square
     */
    public String toString() {
        return name;
    }
    /**
     * Determine if two squares are the same based upon the file and rank
     *
     * @param obj another square object
     * @return a boolean value of whether or not the two squares
     * rank and files are the same
     */
    public boolean equals(Object obj) {
        if (this == obj)  {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof Square)) {
            return false;
        }
        return (this.file == ((Square) obj).file
                && this.rank == ((Square) obj).rank);
    }
    /**
     * return hashCode
     *
     * @return value from super class hashCode method
     */
    public int hashCode() {
        return getFile() - 'a' + Integer.parseInt("" + getRank());
    }
}

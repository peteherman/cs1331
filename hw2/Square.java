public class Square {

    public static final String POS_RANKS = "12345678";
    public static final String POS_FILES = "abcdefgh";
    public static final int MAX_IND_POS = 8;
    private char file;
    private char rank;
    private String name;

    public Square(char file, char rank) {
        this("" + file + rank);
    }

    public Square(String name) {
        if(name.length() > 0) {
            this.name = name;
            if (POS_FILES.indexOf(name.charAt(0)) >= 0
                && POS_RANKS.indexOf(name.charAt(1)) >= 0) {
                file = name.charAt(0);
                rank = name.charAt(1);
            }
        }
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
        return super.hashCode();
    }

}

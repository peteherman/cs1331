public class Square {

    public static final String POS_FILES = "12345678";
    public static final String POS_RANKS = "abcdefgh";
    public static final int MAX_IND_POS = 8;
    private char file;
    private char rank;
    private String name;
    public Square(char rank, char file) {
        this("" + rank + file);
    }

    public Square(String name) {
        this.name = name;
        if (POS_RANKS.indexOf(name.charAt(0)) >= 0
            && POS_FILES.indexOf(name.charAt(1)) >= 0) {

            rank = name.charAt(0);
            file = name.charAt(1);
        }
    }

    public char getFile() {
        return file;
    }

    public char getRank() {
        return rank;
    }
    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        if (this == ((Square) obj)) {
            return true;
        }
        return (this.file == ((Square) obj).file
                && this.rank == ((Square) obj).rank);
    }

    public int hashCode() {
        return super.hashCode();
    }
    public static void main(String[] args) {
        Square s1 = new Square('7', 'a');
        System.out.println(s1);
        Square s2 = new Square("a7");
        System.out.println(s2);
        System.out.println(s1.equals(s2));
    }
}

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "R";
    }

    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "B" : "b";
    }

    public Square[] movesFrom(Square square) {
        int row = getCurrentRow(square);
        int col = getCurrentCol(square);
        String posSquares = "";

        for (int i = 1; (row + i) < Square.MAX_IND_POS
                 && (col + i) < Square.MAX_IND_POS; i++) {
            posSquares += " " + Square.POS_RANKS.charAt(col + i)
                + Square.POS_FILES.charAt(row + i);
        }

        for (int i = 1; (row - i) >= 0 && (col - i) >= 0; i++) {
            posSquares += " " + Square.POS_RANKS.charAt(col - i)
                + Square.POS_FILES.charAt(row - i);
        }

        for (int i = 1; (row - i) >= 0
                 && (col + i) < Square.MAX_IND_POS; i++) {
            posSquares += " " + Square.POS_RANKS.charAt(col + i)
                + Square.POS_FILES.charAt(row - i);
        }

        for (int i = 1; (row + i) < Square.MAX_IND_POS
                 && (col - 1) >= 0; i++) {
            posSquares += " " + Square.POS_RANKS.charAt(col - i)
                + Square.POS_FILES.charAt(row + i);
        }
        return arrPosSquares(posSquares);
    }

    public static void main(String[] args) {
        Piece bishop = new Bishop(Color.BLACK);
        assert bishop.algebraicName().equals("B");
        assert bishop.fenName().equals("b");
        Square[] attackedSquares = bishop.movesFrom(new Square("f6"));
        // test that attackedSquares contains e8, g8, etc.
        for (Square s : attackedSquares) {
            System.out.println("" + s.getRank() + s.getFile());
        }
        Square a1 = new Square("a1");
        Square otherA1 = new Square('a', '1');
        Square h8 = new Square("h8");
        assert a1.equals(otherA1);
        assert !a1.equals(h8);
    }
}

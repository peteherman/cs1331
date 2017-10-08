public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "R";
    }

    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "R" : "r";
    }

    public Square[] movesFrom(Square square) {
        int row = getCurrentRow(square);
        int col = getCurrentCol(square);
        String posSquares = "";

        for (int i = 0; i < Square.MAX_IND_POS; i++) {
            if (!(" " + Square.POS_RANKS.charAt(i)
                 + square.getFile()).equals(" "
                                            + square.getRank()
                                            + square.getFile())) {
                posSquares += " " + Square.POS_RANKS.charAt(i)
                    + square.getFile();
            }
            if (!(" " + square.getRank()
                  + + Square.POS_FILES.charAt(i)).equals(" "
                                                         + square.getRank()
                                                         + square.getFile())) {
                posSquares += " " + square.getRank()
                    + Square.POS_FILES.charAt(i);
            }
        }
        return arrPosSquares(posSquares);
    }

    public static void main(String[] args) {
        Piece rook = new Rook(Color.BLACK);
        assert rook.algebraicName().equals("R");
        assert rook.fenName().equals("r");
        Square[] attackedSquares = rook.movesFrom(new Square("f6"));
        // test that attackedSquares contains e8, g8, etc.
        Square a1 = new Square("a1");
        for (Square s : attackedSquares) {
            System.out.println("" + s.getRank() + s.getFile());
        }
        Square otherA1 = new Square('a', '1');
        Square h8 = new Square("h8");
        assert a1.equals(otherA1);
        assert !a1.equals(h8);
    }
}

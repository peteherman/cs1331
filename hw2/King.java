public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "K";
    }

    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "K" : "k";
    }

    public Square[] movesFrom(Square square) {
        int indCurrRow = getCurrentRow(square);
        int indCurrCol = getCurrentCol(square);
        String posSquares = "";
        if (indCurrRow + 1 < Square.MAX_IND_POS) {
            posSquares += " " + square.getRank()
                + Square.POS_FILES.charAt(indCurrRow + 1);
            if (indCurrCol + 1 < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_RANKS.charAt(indCurrCol + 1)
                    + Square.POS_FILES.charAt(indCurrRow + 1);
            }
        }
        if (indCurrCol + 1 < Square.MAX_IND_POS) {
            posSquares += " " + Square.POS_RANKS.charAt(indCurrCol + 1)
                + square.getFile();
            if (indCurrRow - 1 >= 0) {
                posSquares += " " + Square.POS_RANKS.charAt(indCurrCol + 1)
                    + Square.POS_FILES.charAt(indCurrRow - 1);
            }
        }
        if (indCurrRow - 1 >= 0) {
            posSquares += " " + square.getRank()
                + Square.POS_FILES.charAt(indCurrRow - 1);
            if (indCurrCol - 1 >= 0) {
                posSquares += " " + Square.POS_RANKS.charAt(indCurrCol - 1)
                    + Square.POS_FILES.charAt(indCurrRow - 1);
            }
        }
        if (indCurrCol - 1 >= 0) {
            posSquares += " " + Square.POS_RANKS.charAt(indCurrCol - 1)
                + square.getFile();
            if (indCurrRow + 1 < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_RANKS.charAt(indCurrCol - 1)
                    + Square.POS_FILES.charAt(indCurrRow + 1);
            }
        }
        return arrPosSquares(posSquares);
    }
    public static void main(String[] args) {
        Piece king = new King(Color.BLACK);
        assert king.algebraicName().equals("K");
        assert king.fenName().equals("k");
        Square[] attackedSquares = king.movesFrom(new Square("a8"));
        Square g7 = new Square("g7");
        Square otherG7 = new Square('g', '7');
        Square e5 = new Square("e5");
        assert g7.equals(otherG7);
        assert !g7.equals(e5);
        for (Square s : attackedSquares) {
            System.out.println("" + s.getRank() + s.getFile());
        }
    }
}

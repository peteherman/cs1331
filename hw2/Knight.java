public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "N";
    }

    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "N" : "n";
    }

    public Square[] movesFrom(Square square) {
        int row = getCurrentRow(square);
        int col = getCurrentCol(square);
        String posSquares = "";

        if ((row - 2) >= 0) {
            if ((col + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_RANKS.charAt(col + 1)
                    + Square.POS_FILES.charAt(row - 2);
            }
            if ((col - 1) >= 0) {
                posSquares += " " + Square.POS_RANKS.charAt(col + 1)
                    + Square.POS_FILES.charAt(row - 2);
            }
        }
        if ((col - 2) >= 0) {
            if ((row - 1) >= 0) {
                posSquares += " " + Square.POS_RANKS.charAt(col - 2)
                    + Square.POS_FILES.charAt(row - 1);
            }
            if ((row + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_RANKS.charAt(col - 2)
                    + Square.POS_FILES.charAt(row + 1);
            }
        }
        if ((row + 2) < Square.MAX_IND_POS) {
            if ((col - 1) >= 0) {
                posSquares += " " + Square.POS_RANKS.charAt(col - 1)
                    + Square.POS_FILES.charAt(row + 2);
            }
            if ((col + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_RANKS.charAt(col + 1)
                    + Square.POS_FILES.charAt(row + 2);
            }
        }
        if ((col + 2) < Square.MAX_IND_POS) {
            if ((row - 1) >= 0) {
                posSquares += " " + Square.POS_RANKS.charAt(col + 2)
                    + Square.POS_FILES.charAt(row - 1);
            }
            if ((row + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_RANKS.charAt(col + 2)
                    + Square.POS_FILES.charAt(row + 1);
            }
        }
        return arrPosSquares(posSquares);
    }

    public static void main(String[] args) {
        Piece knight = new Knight(Color.BLACK);
        assert knight.algebraicName().equals("N");
        assert knight.fenName().equals("n");
        Square[] attackedSquares = knight.movesFrom(new Square("f6"));
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

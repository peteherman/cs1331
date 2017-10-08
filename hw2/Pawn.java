public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "";
    }

    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "P" : "p";
    }

    public Square[] movesFrom(Square square) {
        int row = getCurrentRow(square);
        int col = getCurrentCol(square);
        String posSquares = "";

        if (getColor().equals(Color.WHITE)) {
            if (square.getFile() == '2'
                && (row + 2) < Square.MAX_IND_POS) {
                posSquares += " " + square.getRank()
                    + Square.POS_FILES.charAt(row + 2);
            }
            if (row + 1 < Square.MAX_IND_POS) {
                posSquares += " " + square.getRank()
                    + Square.POS_FILES.charAt(row + 1);
                if (col + 1 < Square.MAX_IND_POS) {
                    posSquares += " " + Square.POS_RANKS.charAt(col + 1)
                        + Square.POS_FILES.charAt(row + 1);
                }
                if (col - 1 >= 0) {
                    posSquares += " " + Square.POS_RANKS.charAt(col - 1)
                        + Square.POS_FILES.charAt(row + 1);
                }
            }
        } else {
            if (square.getFile() == '7'
                && (row - 2) >= 0) {
                posSquares += " " + square.getRank()
                    + Square.POS_FILES.charAt(row - 2);
            }
            if (row - 1 >= 0) {
                posSquares += " " + square.getRank()
                    + Square.POS_FILES.charAt(row - 1);
                if (col - 1 >= 0) {
                    posSquares += " " + Square.POS_RANKS.charAt(col - 1)
                        + Square.POS_FILES.charAt(row - 1);
                }
                if (col + 1 < Square.MAX_IND_POS) {
                    posSquares += " " + Square.POS_RANKS.charAt(col + 1)
                        + Square.POS_FILES.charAt(row - 1);
                }
            }
        }
        return arrPosSquares(posSquares);
    }

    public static void main(String[] args) {
        Pawn pawn = new Pawn(Color.BLACK);
        System.out.println(pawn.algebraicName());
        System.out.println(pawn.fenName());
        Square[] attackedSquares = pawn.movesFrom(new Square("a7"));
        for (Square s : attackedSquares) {
            System.out.println("" + s.getRank() + s.getFile());
        }
    }
}

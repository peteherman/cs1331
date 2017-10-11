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
            posSquares += " " + square.getFile()
                + Square.POS_RANKS.charAt(indCurrRow + 1);
            if (indCurrCol + 1 < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_FILES.charAt(indCurrCol + 1)
                    + Square.POS_RANKS.charAt(indCurrRow + 1);
            }
        }
        if (indCurrCol + 1 < Square.MAX_IND_POS) {
            posSquares += " " + Square.POS_FILES.charAt(indCurrCol + 1)
                + square.getRank();
            if (indCurrRow - 1 >= 0) {
                posSquares += " " + Square.POS_FILES.charAt(indCurrCol + 1)
                    + Square.POS_RANKS.charAt(indCurrRow - 1);
            }
        }
        if (indCurrRow - 1 >= 0) {
            posSquares += " " + square.getFile()
                + Square.POS_RANKS.charAt(indCurrRow - 1);
            if (indCurrCol - 1 >= 0) {
                posSquares += " " + Square.POS_FILES.charAt(indCurrCol - 1)
                    + Square.POS_RANKS.charAt(indCurrRow - 1);
            }
        }
        if (indCurrCol - 1 >= 0) {
            posSquares += " " + Square.POS_FILES.charAt(indCurrCol - 1)
                + square.getRank();
            if (indCurrRow + 1 < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_FILES.charAt(indCurrCol - 1)
                    + Square.POS_RANKS.charAt(indCurrRow + 1);
            }
        }
        return arrPosSquares(posSquares);
    }
}

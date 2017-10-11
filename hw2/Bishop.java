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
            posSquares += " " + Square.POS_FILES.charAt(col + i)
                + Square.POS_RANKS.charAt(row + i);
        }

        for (int i = 1; (row - i) >= 0 && (col - i) >= 0; i++) {
            posSquares += " " + Square.POS_FILES.charAt(col - i)
                + Square.POS_RANKS.charAt(row - i);
        }

        for (int i = 1; (row - i) >= 0
                 && (col + i) < Square.MAX_IND_POS; i++) {
            posSquares += " " + Square.POS_FILES.charAt(col + i)
                + Square.POS_RANKS.charAt(row - i);
        }

        for (int i = 1; (row + i) < Square.MAX_IND_POS
                 && (col - 1) >= 0; i++) {
            posSquares += " " + Square.POS_FILES.charAt(col - i)
                + Square.POS_RANKS.charAt(row + i);
        }
        return arrPosSquares(posSquares);
    }
}

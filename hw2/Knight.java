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
                posSquares += " " + Square.POS_FILES.charAt(col + 1)
                    + Square.POS_RANKS.charAt(row - 2);
            }
            if ((col - 1) >= 0) {
                posSquares += " " + Square.POS_FILES.charAt(col - 1)
                    + Square.POS_RANKS.charAt(row - 2);
            }
        }
        if ((col - 2) >= 0) {
            if ((row - 1) >= 0) {
                posSquares += " " + Square.POS_FILES.charAt(col - 2)
                    + Square.POS_RANKS.charAt(row - 1);
            }
            if ((row + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_FILES.charAt(col - 2)
                    + Square.POS_RANKS.charAt(row + 1);
            }
        }
        if ((row + 2) < Square.MAX_IND_POS) {
            if ((col - 1) >= 0) {
                posSquares += " " + Square.POS_FILES.charAt(col - 1)
                    + Square.POS_RANKS.charAt(row + 2);
            }
            if ((col + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_FILES.charAt(col + 1)
                    + Square.POS_RANKS.charAt(row + 2);
            }
        }
        if ((col + 2) < Square.MAX_IND_POS) {
            if ((row - 1) >= 0) {
                posSquares += " " + Square.POS_FILES.charAt(col + 2)
                    + Square.POS_RANKS.charAt(row - 1);
            }
            if ((row + 1) < Square.MAX_IND_POS) {
                posSquares += " " + Square.POS_FILES.charAt(col + 2)
                    + Square.POS_RANKS.charAt(row + 1);
            }
        }
        return arrPosSquares(posSquares);
    }
}

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
            if (square.getRank() == '2'
                && (row + 2) < Square.MAX_IND_POS) {
                posSquares += " " + square.getFile()
                    + Square.POS_RANKS.charAt(row + 2);
            }
            if (row + 1 < Square.MAX_IND_POS) {
                posSquares += " " + square.getFile()
                    + Square.POS_RANKS.charAt(row + 1);
                if (col + 1 < Square.MAX_IND_POS) {
                    posSquares += " " + Square.POS_FILES.charAt(col + 1)
                        + Square.POS_RANKS.charAt(row + 1);
                }
                if (col - 1 >= 0) {
                    posSquares += " " + Square.POS_FILES.charAt(col - 1)
                        + Square.POS_RANKS.charAt(row + 1);
                }
            }
        } else {
            if (square.getRank() == '7'
                && (row - 2) >= 0) {
                posSquares += " " + square.getFile()
                    + Square.POS_RANKS.charAt(row - 2);
            }
            if (row - 1 >= 0) {
                posSquares += " " + square.getFile()
                    + Square.POS_RANKS.charAt(row - 1);
                if (col - 1 >= 0) {
                    posSquares += " " + Square.POS_FILES.charAt(col - 1)
                        + Square.POS_RANKS.charAt(row - 1);
                }
                if (col + 1 < Square.MAX_IND_POS) {
                    posSquares += " " + Square.POS_FILES.charAt(col + 1)
                        + Square.POS_RANKS.charAt(row - 1);
                }
            }
        }
        return arrPosSquares(posSquares);
    }
}

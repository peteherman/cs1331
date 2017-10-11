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
            if (!(" " + Square.POS_FILES.charAt(i)
                 + square.getRank()).equals(" "
                                            + square.getFile()
                                            + square.getRank())) {
                posSquares += " " + Square.POS_FILES.charAt(i)
                    + square.getRank();
            }
            if (!(" " + square.getFile()
                  + + Square.POS_RANKS.charAt(i)).equals(" "
                                                         + square.getFile()
                                                         + square.getRank())) {
                posSquares += " " + square.getFile()
                    + Square.POS_RANKS.charAt(i);
            }
        }
        return arrPosSquares(posSquares);
    }
}

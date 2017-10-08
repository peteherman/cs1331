public abstract class Piece {

    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getCurrentRow(Square square) {
        return Square.POS_FILES.indexOf(square.getFile());
    }

    public int getCurrentCol(Square square) {
        return Square.POS_RANKS.indexOf(square.getRank());
    }

    public Square[] arrPosSquares(String posSquares) {
        posSquares = posSquares.trim();
        String[] posArr = posSquares.split(" ");
        Square[] endSquares = new Square[posArr.length];
        for (int i = 0; i < posArr.length; i++) {
            endSquares[i] = new Square(posArr[i]);
        }
        return endSquares;
    }
    public abstract String algebraicName();

    public abstract String fenName();

    public abstract Square[] movesFrom(Square square);
}

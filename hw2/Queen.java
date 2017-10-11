public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    public String algebraicName() {
        return "Q";
    }

    public String fenName() {
        return (getColor().equals(Color.WHITE)) ? "Q" : "q";
    }

    public Square[] movesFrom(Square square) {
        Bishop bishop = new Bishop(getColor());
        Rook rook = new Rook(getColor());
        Square[] bishopSquares = bishop.movesFrom(square);
        Square[] rookSquares = rook.movesFrom(square);
        Square[] posSquares = new Square[bishopSquares.length
                                         + rookSquares.length];
        for (int i = 0; i < rookSquares.length; i++) {
            posSquares[i] = rookSquares[i];
        }
        for (int i = rookSquares.length; i < posSquares.length; i++) {
            posSquares[i] = bishopSquares[i - rookSquares.length];
        }
        return posSquares;
    }
}

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

    public static void main(String[] args) {
        Piece queen = new Queen(Color.BLACK);
        assert queen.algebraicName().equals("N");
        assert queen.fenName().equals("n");
        Square[] attackedSquares = queen.movesFrom(new Square("f6"));
        // test that attackedSquares contains e8, g8, etc.
        for (Square s : attackedSquares) {
            System.out.println("" + s.getRank() + s.getFile());
        }
        Square a1 = new Square("a1");
        Square otherA1 = new Square('a', '1');
        Square h8 = new Square("h8");
        assert a1.equals(otherA1);
        assert !a1.equals(h8);
    }
}

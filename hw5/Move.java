/**
 *
 * @author Peter Herman
 * @version 1.0
 */
public class Move {

    private Ply whitePly;
    private Ply blackPly;

    /**
     * Creates new instance of Move and assigns instance variables
     * to params given
     *
     * @param whitePly - the white half of the move
     * @param blackPly - the black half of the move
     */
    public Move (Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }

    /**
     * Return whitePly
     *
     * @return the white Ply
     */
    public Ply getWhitePly() {
        return whitePly;
    }

    /**
     * Return blackPly
     *
     * @return the black Ply
     */
    public Ply getBlackPly() {
        return blackPly;
    }
}

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Provides structure for a ChessGame, contains meta data surrounding
 * game and the moves which occurred in the game.
 * @author Chris Simpkins
 * @version 1.0
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;

    /**
     * Creates a chessgame using given metadata
     *
     * @param event name of chess event
     * @param site where chess match took place
     * @param date of the match
     * @param white player who was playing with white pieces
     * @param black player who was playing with black pieces
     * @param result who won the game
     */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        moves = new ArrayList<>();
    }

    /**
     * Adds a new move to the game
     *
     * @param move the move to be added to the game
     */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
     * returns move in game from certain index
     *
     * @param n index of move in game
     * @return the move which occurred at this index
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }

    /**
     * Returns event in which game was played
     *
     * @return the event in which the game was played
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * Returns location of match
     *
     * @return location of match
     */
    public String getSite() {
        return site.get();
    }
    /**
     * Returns date of match
     *
     * @return date of match
     */
    public String getDate() {
        return date.get();
    }
    /**
     * Returns white player of match
     *
     * @return white player of match
     */
    public String getWhite() {
        return white.get();
    }
    /**
     * Returns black player of match
     *
     * @return black player of match
     */
    public String getBlack() {
        return black.get();
    }
    /**
     * Returns result of match
     *
     * @return result of match
     */
    public String getResult() {
        return result.get();
    }
    /**
     * Returns moves from match
     *
     * @return a List containing the moves from the match
     */
    public List<String> getMoves() {
        return moves;
    }
}

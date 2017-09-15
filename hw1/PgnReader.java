import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    /**
     * Accept a string containing unformatted tag value, return just the
     * tag value, without: tag name, brackets, quotes
     *
     * @param tagName the name of the tag whose value you want
     * @param raw unformatted string
     * @return tag value without tag name, brackets or quotes
     */
    public static String formatValue(String tagName, String raw) {
        String result = "";
        String[] splitRaw = raw.split(tagName, 2);
        for (int i = 0; i <  splitRaw.length; i++) {
            if (splitRaw[i].indexOf("\"") > -1) {
                for (int j = splitRaw[i].indexOf("\"");
                     j < splitRaw[i].length(); j++) {
                    if (splitRaw[i].charAt(j) != '"') {
                        result += splitRaw[i].charAt(j);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
        String[] splitGame = game.split("]", 20);
        if (game.indexOf(tagName) < 0) {
            return "NOT GIVEN";
        }
        for (int i = 0; i < splitGame.length; i++) {
            if (splitGame[i].indexOf(tagName) > -1) {
                return formatValue(tagName, splitGame[i]);
            }
        }
        return "NOT GIVEN";
    }

    /**
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `Strring` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String finalPosition(String game) {
        return "";
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));

    }
}

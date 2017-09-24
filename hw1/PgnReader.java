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
        if (game.indexOf(tagName) < 0) {
            return "NOT GIVEN";
        }
        String[] splitGame = game.split("]", 20);
        for (int i = 0; i < splitGame.length; i++) {
            if (splitGame[i].indexOf(tagName) > -1) {
                return formatValue(tagName, splitGame[i]);
            }
        }
        return "NOT GIVEN";
    }
    /**
     * Return string of just the moves, no Tag Names
     *
     * @param game a 'String' containing a PGN-formatted chess game or opening
     * @return a string containing all of the PGN-formatted moves
     */
    public static String[] getMoves(String game) {
        String moveStart = "1. ";
        String lineOfMoves = game.substring(game.indexOf(moveStart));
        lineOfMoves = lineOfMoves.substring(moveStart.length());
        System.out.println(lineOfMoves);
        String[] moves = lineOfMoves.split("\\. ");
        for (String move : moves) {
            move = move.substring(0, move.length());
            System.out.println(move);
        }

        return moves;
    }
    /**
     * return array containing final position of pawn promotion
     *
     * @param board current game board
     * @param move the desired move to be made
     * @return updated gameboard
     */
    public static char[][] promotion(char[][] board, String move) {
        move = move.substring(1);
        boolean isWhite = false;
        boolean isCapture = false;
        char[] moveArr = move.toCharArray();
        String ranks = "abcdefgh";
        String files = "12345678";
        char newType = ' ';
        char startRank = move.charAt(0);
        System.out.println("start rank: "  + startRank);
        char endRank = ' ';
        char endFile = ' ';
        for (int i = 0; i < moveArr.length - 1; i++) {
            if (moveArr[i] == 'x') {
                isCapture = true;
            }
            if (moveArr[i] == '8') {
                isWhite = true;
                endRank = moveArr[i - 1];
                endFile = '8';
            } else if (moveArr[i] == '1') {
                endRank = moveArr[i - 1];
                endFile = '1';
            }
            if (moveArr[i] == '=' && isWhite) {
                newType = Character.toLowerCase(moveArr[i + 1]);
            } else if (moveArr[i] == '=') {
                newType = moveArr[i + 1];
            }
        }
        if (isCapture) {
            board[files.indexOf(endFile) - 1][ranks.indexOf(startRank)] = ' ';
            board[files.indexOf(endFile)][ranks.indexOf(endRank)] = newType;
        } else {
            board[files.indexOf(endFile) - 1][ranks.indexOf(startRank)] = ' ';
            board[files.indexOf(endFile)][ranks.indexOf(endRank)] = newType;
        }
        return board;
    }
    /**
     * Check to make sure move is viable and if so alter game board
     *
     * @param board current game board to check and alter
     * @param move the desired white and black moves to be made
     * @return the altered or unaltered board
     */
    public static char[][] makeMove(char[][] board, String move) {
        String whiteMove = "";
        String blackMove = "";
        String files = "12345678";
        String ranks = "abcdefg";
        int castlingVal = -2;
        int nextMove = move.indexOf(" ");
        if (nextMove >= 0) {
            whiteMove = move.substring(0, nextMove);
            blackMove = move.substring(nextMove + 1, move.length() - 1);
        } else {
            whiteMove = move.substring(0, move.indexOf("\n"));
        }
        System.out.println(whiteMove);
        System.out.println(blackMove);
        if (whiteMove.length() > 0) {
            if (ranks.indexOf(whiteMove.charAt(0)) >= 0) {
                whiteMove = "p" + whiteMove;
            } else {
                whiteMove = whiteMove.toLowerCase();
            }
            if (whiteMove.indexOf('=') >= 0) {
                System.out.println("PROMOTION: " + move);
                // whiteRes = promotion(board, whiteMove);
                return promotion(board, whiteMove);
            }
            int[] wRes = checkBoard(board, whiteMove);
            if (wRes[0] >= 0) {
                board[wRes[2]][wRes[3]] = board[wRes[0]][wRes[1]];
                board[wRes[0]][wRes[1]] = ' ';
            } else if (wRes[1] == castlingVal) {
                System.out.println("CASTLING MOVE");
                int oCount = 0;
                int kingRow = 0;
                int kingRank = 4;
                for (int i = 0; i < whiteMove.length(); i++) {
                    if (whiteMove.charAt(i) == 'o') {
                        oCount++;
                    }
                }
                if (oCount < 3) {
                    board[kingRow][kingRank] = 'r';
                    board[kingRow][board.length - 1] = 'k';
                } else {
                    board[kingRow][kingRank] = 'r';
                    board[kingRow][0] = 'k';
                }
            }

        }

        if (blackMove.length() > 0) {
            if (ranks.indexOf(blackMove.charAt(0)) >= 0) {
                blackMove = "P" + blackMove;
            }
            int[] bRes = checkBoard(board, blackMove);
            System.out.println();
            if (bRes[0] >= 0) {
                board[bRes[2]][bRes[3]] = board[bRes[0]][bRes[1]];
                board[bRes[0]][bRes[1]] = ' ';
                //return board;
            } else if (bRes[1] == castlingVal) {
                int oCount = 0;
                int kingRow = board.length - 1;
                int kingRank = 4;
                for (int i = 0; i < blackMove.length(); i++) {
                    if (blackMove.charAt(i) == 'o') {
                        oCount++;
                    }
                }
                if (oCount < 3) {
                    board[kingRow][kingRank] = board[kingRow][board.length - 1];
                    board[kingRow][board.length - 1] = 'K';
                } else {
                    board[kingRow][kingRank] = 'R';
                    board[kingRow][0] = 'K';
                }
            }
        }
        return board;
    }
    /**
     * Search for pieces, determine if whether move is viable or not
     *
     * @param board the current board containing previous moves
     * @param move white or black move, not both
     * @return int[] of location of old position and new position
     */
    public static int[] checkBoard(char[][] board, String move) {
        String possibleMoves = "";
        char piece = move.charAt(0);
        int[] results = {-1, -1, -1, -1};
        String files = "12345678";
        String ranks = "abcdefgh";
        String endPos;
        int castlingVal = -2;
        if (move.length() > 1 && move.charAt(0) == 'o') {
            System.out.println("CASTLING MOVE FOUND");
            results[0] = castlingVal;
            results[1] = castlingVal;
            results[2] = castlingVal;
            results[3] = castlingVal;
        }
        if (move.length() > 1 && move.toLowerCase().charAt(0) == 'p') {
            if (move.length() >= 2 && move.charAt(2) == 'x') {
                System.out.println("PAWN CAPTURE: " + move);
                results = pawnCapture(board, move);
                return results;
            }
            move = move.trim();
        }
        System.out.println("Move: " + move);
        if (move.length() > 1 && move.charAt(1) == 'x') {
            move = move.substring(0, 1) + move.substring(2);
            System.out.println("Capture move: " + move);
        }
        if (move.length() > 1 && (move.charAt(move.length() - 1) == '#'
                                || move.charAt(move.length() - 1) == '+')) {
            move = move.substring(0, move.length() - 1);
        }
        if (results[0] == -2) {
            System.out.println("RESULTS = -2");
        }
        endPos = move.substring(1);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == piece) {
                    possibleMoves = posMoves(board, piece, i, j);
                    if (endPos.length() > 1) {
                        if (possibleMoves.indexOf(endPos) > 0) {
                            int newRank = ranks.indexOf(endPos.charAt(0));
                            int newFile = files.indexOf(endPos.charAt(1));
                            results[0] = i;
                            results[1] = j;
                            results[2] = newFile;
                            results[3] = newRank;
                            System.out.print("Loop results: ");
                            for (int result : results) {
                                System.out.print(result + ", ");
                            }
                            System.out.println();
                            return results;
                        }
                    }
                }
            }
        }

        return results;
    }
    /**
     * Give new indexes on board for a simulated pawn capture
     *
     * @param board board containing current position of pieces
     * @param move the capture move to be made by a pawn
     * @return an array containing a new position for a pawn
     */
    public static int[] pawnCapture(char[][] board, String move) {
        int[] results = {-1, -1, -1, -1};
        String ranks = "abcdefgh";
        String files = "12345678";
        int len = move.length();
        if (move.charAt(len - 1) == '+'
            || move.charAt(len - 1) == '#') {
            move = move.substring(0, len - 1);
        }
        char startRank = move.charAt(1);
        char endRank = move.charAt(move.indexOf('x') + 1);
        char endFile = move.charAt(move.length() - 1);
        int oldCol = ranks.indexOf(startRank);
        int newCol = ranks.indexOf(endRank);
        int newRow = files.indexOf(endFile);
        System.out.println(startRank + " " + endRank + " " + endFile);
        results[0] = newRow - 1;
        results[1] = oldCol;
        results[2] = newRow;
        results[3] = newCol;
        for (int res : results) {
            System.out.println(res);
        }
        return results;
    }
    /**
     * Generate a String of possible moves for bishop and current board
     * @param board board containing current position of pieces
     * @param pos the current position of bishop
     * @return a String containing all possible moves for given bishop
     */
    public static String bishopMoves(char[][] board, int r, int c) {
        char[] files = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] ranks = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String posMoves = "";
        int i = 0;
        boolean rowIB = (r + i) < board.length;
        boolean colIB = (c + i) < board[i].length;
        while (rowIB && colIB) {
            posMoves += " " + ranks[c + i] + files[r + i];
            i++;
            rowIB = (r + i) < board.length;
            colIB = (c + i) < board[i].length;
        }

        i = 0;
        rowIB = (r - i) > 0;
        colIB = (c - i) > 0;
        while (rowIB && colIB) {
            posMoves += " " + ranks[c - i] + files[r - i];
            i++;
            rowIB = (r - i) >= 0;
            colIB = (c - i) >= 0;
        }

        i = 0;
        rowIB = (r - i) > 0;
        colIB = (c + i) < board[r].length;
        while (rowIB && colIB) {
            posMoves += " " + ranks[c + i] + files[r - i];
            i++;
            rowIB = (r - i) > 0;
            colIB = (c + i) < board[i].length;
        }

        i = 0;
        rowIB = (r + i) < board.length;
        colIB = (c - i) > 0;
        while (rowIB && colIB) {
            posMoves += " " + ranks[c - i] + files[r + i];
            i++;
            rowIB = (r + i) < board.length;
            colIB = (c - i) > 0;
        }
        return posMoves;
    }
    /**
     * Generate a String of all possible Knight moves for current board
     * @param board board containing current position of pieces
     * @param pos the current position of Knight
     * @return a String containing all possible moves for given knights
     */
    public static String knightMoves(char[][] board, int r, int c) {
        char[] files = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] ranks = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String posMoves = "";
        //Knights can move 2 spots in one direction and 1 in another
        if ((r - 2) >= 0) {
            if ((c + 1) < board[r].length) {
                posMoves += " " + ranks[c + 1] + files[r - 2];
            }
            if ((c - 1) >= 0) {
                posMoves += " " + ranks[c - 1] + files[r - 2];
            }

            //posMoves += files[r - 2];
        }
        if ((c - 2) >= 0) {
            if ((r - 1) >= 0) {
                posMoves += " " + ranks[c - 2] + files[r - 1];
            }
            if ((r + 1) < board.length) {
                posMoves += " " + ranks[c - 2] + files[r + 1];
            }
        }
        if ((r + 2) < board.length) {
            if ((c + 1) < board.length) {
                posMoves += " " + ranks[c + 1] + files[r + 2];
            }
            if ((c - 1) >= 0) {
                posMoves += " " + ranks[c - 1] + files[r + 2];
            }
        }
        if ((c + 2) < board[r].length) {

            if ((r - 1) >= 0) {
                posMoves += " " + ranks[c + 2] + files[r - 1];
            }
            if ((r + 1) < board.length) {
                posMoves += " " + ranks[c + 2] + files[r + 1];
            }
        }
        return posMoves;
    }
    /**
     * Generate a String of all possible moves for a given Rook
     * @param board board containing current position of pieces
     * @param pos the current position of rook
     * @return a String containing all possible moves for given rook
     */
    public static String rookMoves(char[][] board, int r, int c) {
        char[] files = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] ranks = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String posMoves = "";
        int i = 0;
        while (i < board[r].length) {
            posMoves += " " + ranks[i] + files[r];
            i++;
        }
        i = 0;
        while (i < board.length) {
            posMoves += " " + ranks[c] + files[i];
            i++;
        }
        return posMoves;
    }
    /**
     * Generate a String of all possible moves for a given queen
     * @param board board containing current position of pieces
     * @param pos the current position of queen
     * @return a String containing all possible moves for given queen
     */
    public static String queenMoves(char[][] board, int r, int c) {
        String posMoves;
        posMoves = rookMoves(board, r, c) + bishopMoves(board, r, c);
        System.out.println(posMoves);
        return posMoves;
    }
    /**
     * Generate a String of all possible moves for a given king
     * @param board board containing current position of pieces
     * @param pos the current position of king
     * @return a String containing all possible moves for given king
     */
    public static String kingMoves(char[][] board, int r, int c) {
        char[] files = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] ranks = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String posMoves = "";
        //kings can move at most, one position from their current position
        if (r + 1 < board.length) {
            posMoves += " " + ranks[c] + files[r + 1];
            if (c + 1 < board[r + 1].length) {
                posMoves += " " + ranks[c + 1] + files[r + 1];
            }
        }
        if (c + 1 < board[r].length) {
            posMoves += " " + ranks[c + 1] + files[r];
            if (r - 1 >= 0) {
                posMoves += " " + ranks[c + 1] + files[r - 1];
            }
        }
        if (r - 1 >= 0) {
            posMoves += " " + ranks[c] + files[r - 1];
            if (c - 1 >= 0) {
                posMoves += " " + ranks[c - 1] + files[r - 1];
            }
        }
        if (c - 1 >= 0) {
            posMoves += " " + ranks[c - 1] + files[r];
            if (r - 1 >= 0) {
                posMoves += " " + ranks[c - 1] + files[r - 1];
            }
        }
        return posMoves;
    }

    /**
     * Generate a String  of all possible moves for a given piece
     * and board
     *
     * @param board board containing current position of pieces
     * @param piece chess piece in PGN notation
     * @param pos the current position of given piece
     * @return String containing all possible moves for given piece
     */
    public static String posMoves(char[][] board, char piece, int r, int c) {
        int wPawnRow = 1;
        int bPawnRow = 6;
        char[] files = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] ranks = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String posMoves = "";
        if (piece == 'p') {
            if (r == wPawnRow) {
                posMoves += " " + ranks[c] + files[r + 2];
            }
            posMoves += " " + ranks[c] + files[r + 1];
        } else if (piece == 'P') {
            if (r == bPawnRow) {
                posMoves += " " + ranks[c]  + files[r - 2];
            }
            posMoves += " " + ranks[c] + files[r - 1];
        } else if (piece == 'r' || piece == 'R') {
            posMoves = rookMoves(board, r, c);
        } else if (piece == 'b' || piece == 'B') {
            posMoves = bishopMoves(board, r, c);
        } else if (piece == 'n' || piece == 'N') {
            posMoves = knightMoves(board, r, c);
        } else if (piece == 'q' || piece == 'Q') {
            posMoves = queenMoves(board, r, c);
        } else if (piece == 'k' || piece == 'K') {
            posMoves = kingMoves(board, r, c);
        }

        return posMoves;
    }
    /**
     * Create chess board containing white and black pieces in accordance
     * to PGN format
     *
     * @return a 2D String array representing a chess board in PGN format
     */
    public static char[][] makeBoard() {
        char[][] board = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };
        return board;
    }

    /**
     * Create FEN formatted String of gameboard
     *
     * @param board a char[][] containing the current location of game pieces
     * @return a  FEN formatted String of gameboard
     */
    public static String fenFormat(char[][] board) {
        String result = "";
        int counter;
        for (int i = 0; i < board.length; i++) {
            counter = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (counter > 0 && board[i][j] != ' ') {
                    result += counter + "" + board[i][j];
                    counter = 0;
                } else if (board[i][j] != ' ') {
                    result += board[i][j];
                } else {
                    counter++;
                }
            }
            if (counter > 0) {
                result += counter;
            }
            if (i < board.length - 1) {
                result += '/';
            }
        }
        boardToString(board);
        return result;
    }
    /**
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `String` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String finalPosition(String game) {
        char[][] board = makeBoard();
        String[] moves = getMoves(game);
        for (String move : moves) {
            board = makeMove(board, move);
        }
        return fenFormat(board);
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
    /**
     * print board in readable format
     *
     * @param board board containing current position of pieces
     */
    public static void boardToString(char[][] board) {
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    System.out.print("* ");
                }
                System.out.print(board[i][j] + "     ");
            }
            System.out.println();
        }
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

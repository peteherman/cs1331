import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Creates a user interface for navigating a chess database
 *
 * @author Peter Herman
 * @version 1.0
 */
public class ChessGui extends Application {

    @Override
    public void start(Stage stage) {
        Button viewBtn = new Button("View Game");
        Button dismissBtn = new Button("Dismiss");
        Button searchBtn = new Button("Search");
        ChessDb chessDb = new ChessDb();
        TextField searchField = new TextField("Search");


        TableView<ChessGame> tableView = createTableView(pullData(chessDb));

        viewBtn.disableProperty()
            .bind(Bindings.isNull(tableView
                                  .getSelectionModel().selectedItemProperty()));
        viewBtn.setOnAction(e -> {
                ChessGame game = tableView
                    .getSelectionModel().getSelectedItem();
                viewGame(game);
            });
        dismissBtn.setOnAction(e -> Platform.exit());
        searchBtn.setOnAction(e -> {
                tableView.getSelectionModel().select(-1);
                String query = searchField.getText();
                if (!search(query, tableView)) {
                    noResults(query);
                }
            });


        HBox hbox = new HBox();
        hbox.getChildren().addAll(viewBtn, dismissBtn);
        HBox searchHBox = new HBox();
        searchHBox.getChildren().addAll(searchField, searchBtn);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView, hbox, searchHBox);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setWidth(1100);
        stage.setTitle("Chess Gui");
        stage.show();
    }
    /**
     * creates observable list of chess games from given chess database
     *
     * @param db a chess database containing multiple chess games
     * @return an observable list of chess games to be used to populate
     * a tableview
     */
    public ObservableList<ChessGame> pullData(ChessDb db) {
        return FXCollections.observableArrayList(db.getGames());
    }

    /**
     * Creates a new tableview appends needed columns to tableview
     * and connects each property of chess game to its respective column
     *
     * @param games - observable list of chess games used to populate rows
     * of the tableview
     * @return a tableview containing chessgames from given observable list
     */
    private TableView<ChessGame>
        createTableView(ObservableList<ChessGame> games) {
        TableView<ChessGame> res =  new TableView<>(games);

        TableColumn eventCol = new TableColumn("Event");
        eventCol
            .setCellValueFactory(
                                 new PropertyValueFactory<
                                 ChessGame, StringProperty>("Event"));
        TableColumn siteCol = new TableColumn("Site");
        siteCol
            .setCellValueFactory(
                                 new PropertyValueFactory<
                                 ChessGame, StringProperty>("Site"));
        TableColumn dateCol = new TableColumn("Date");
        dateCol
            .setCellValueFactory(
                                 new PropertyValueFactory<
                                 ChessGame, StringProperty>("Date"));
        TableColumn whiteCol = new TableColumn("White");
        whiteCol
            .setCellValueFactory(
                                 new PropertyValueFactory<
                                 ChessGame, StringProperty>("White"));
        TableColumn blackCol = new TableColumn("Black");
        blackCol
            .setCellValueFactory(
                                 new PropertyValueFactory<
                                 ChessGame, StringProperty>("Black"));
        TableColumn resultCol = new TableColumn("Result");
        resultCol
            .setCellValueFactory(
                                 new PropertyValueFactory<
                                 ChessGame, StringProperty>("Result"));
        res.getColumns().addAll(eventCol, siteCol, dateCol,
                                whiteCol, blackCol, resultCol);
        return res;
    }

    /**
     * Creates popup containing meta data of game and game's moves
     *
     * @param game selected game from tableview to be inspected
     */
    private void viewGame(ChessGame game) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(game.getEvent());
        alert.setHeaderText(String.format("Event: %s%nSite: %s%n"
                                          + "Date: %s%nWhite: %s%n"
                                          + "Black: %s%nResult: %s%n",
                                          game.getEvent(), game.getSite(),
                                          game.getDate(), game.getWhite(),
                                          game.getBlack(), game.getResult()));


        ListView<String> moves = new
            ListView(FXCollections.observableArrayList(game.getMoves()));
        alert.getDialogPane().setContent(moves);
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.setContentText("");
        alert.showAndWait();
    }

    /**
     * Creates invalid search query popup if query is invalid
     *
     * @param query the invalid query entered
     */
    private void invalidQuery(String query) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("INVALID SEARCH QUERY");
        alert.setHeaderText(String.format("The query you entered: \"%s\", is "
                                          + "not valid", query));
        alert.setContentText("Please try searching again with a "
                             + "different search query");
        alert.showAndWait();
    }

    /**
     * Creates alert telling user their search returned no results
     *
     * @param query the query which returned no results
     *
     */
    private void noResults(String query) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NO RESULTS");
        alert.setHeaderText(String.format("The query you entered: \"%s\", is "
                                          + "didn't return "
                                          + "any results", query));
        alert.setContentText("Please try searching again with a "
                             + "different search query");
        alert.showAndWait();
    }
    /**
     * Searches tableview for games matching query from search field
     * Selects first game that matches query
     *
     * @param query - search string typed by user
     * @param table - tableview to be searched
     * @return true if query was found
     */
    public boolean search(String query, TableView<ChessGame> table) {
        query = query.trim().toLowerCase();
        if (query.length() < 2) {
            invalidQuery(query);
        }
        ObservableList<ChessGame> games = table.getItems();
        boolean queryFound = false;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getEvent().toLowerCase().contains(query)) {
                queryFound = true;
            }
            if (games.get(i).getSite().toLowerCase().contains(query)) {
                queryFound = true;
            }
            if (games.get(i).getDate().toLowerCase().contains(query)) {
                queryFound = true;
            }
            if (games.get(i).getWhite().toLowerCase().contains(query)) {
                queryFound = true;
            }
            if (games.get(i).getBlack().toLowerCase().contains(query)) {
                queryFound = true;
            }
            if (queryFound) {
                table.requestFocus();
                table.getSelectionModel().select(i);
                return queryFound;
            }
        }
        return false;
    }
}

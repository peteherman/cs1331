import javafx.scene.control.ButtonType;
import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.property.StringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.beans.binding.Bindings;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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

        HBox hbox = new HBox();
        hbox.getChildren().addAll(viewBtn, dismissBtn);
        HBox searchHBox = new HBox();
        searchHBox.getChildren().addAll(searchField, searchBtn);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView, hbox, searchHBox);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setWidth(1000);
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
     *
     *
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
}

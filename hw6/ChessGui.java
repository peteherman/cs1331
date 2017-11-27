import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.property.StringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        GridPane gridpane = new GridPane();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        ChessDb chessDb = new ChessDb();

        TableView<ChessGame> tableView = new TableView<>();
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
        tableView.getColumns().addAll(eventCol, siteCol, dateCol,
                                      whiteCol, blackCol, resultCol);

        tableView.setItems(pullData(new ChessDb()));

        //gridpane.add(tableView, 0, 0, 10, 1);
        gridpane.setConstraints(viewBtn, 0, 1); // col 0, row 1
        gridpane.setConstraints(dismissBtn, 1, 1);
        gridpane.getChildren().addAll(viewBtn, dismissBtn);
        hbox.getChildren().addAll(viewBtn, dismissBtn);
        vbox.getChildren().addAll(tableView, hbox);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setTitle("Chess Gui");
        stage.show();
    }

    public ObservableList<ChessGame> pullData(ChessDb db) {
        return FXCollections.observableArrayList(db.getGames());
    }
}

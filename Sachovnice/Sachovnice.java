package Sachovnice;

import Sachovnice.GUI.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sachovnice extends Application {
    public static String[] namesOfFigures = new String[]{"pawn", "horse", "king", "queen", "shooter", "tower"};
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox(10);

        GridPane chessBoard = ChessBoard.createChessBoard();
        GridPane informationPanel = InformationPanel.createPanel();
        GridPane userControl = ImageHintPanel.createPanel();
        GridPane figureBar = FiguresRender.createFiguresBar();

        vbox.setMinWidth(400);
        userControl.setMinWidth(400);
        informationPanel.setMinWidth(400);
        figureBar.setMinHeight(100);

        HBox hbox1 = createBox(new GridPane(), figureBar, new GridPane());
        HBox hbox2 = createBox(informationPanel, chessBoard, userControl);
        vbox.getChildren().addAll(hbox1, hbox2);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static HBox createBox(GridPane pane1, GridPane pane2, GridPane pane3) {
        HBox hbox = new HBox(10);
        hbox.setPrefWidth(10000);

        pane1.prefWidthProperty().bind(hbox.widthProperty().divide(3));
        pane2.prefWidthProperty().bind(hbox.widthProperty().divide(3));
        pane3.prefWidthProperty().bind(hbox.widthProperty().divide(3));

        hbox.getChildren().addAll(pane1, pane2, pane3);
        return hbox;
    }

    public static void main(String[] args) {
        launch();
    }
}

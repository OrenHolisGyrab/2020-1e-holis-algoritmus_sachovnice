package Sachovnice.GUI;

import Sachovnice.Sachovnice;
import Sachovnice.calculations.CalculateFigurePathing;
import Sachovnice.calculations.figureDataTypes.InsertedFigure;
import Sachovnice.calculations.types.Positions;
import Sachovnice.calculations.types.Result;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ChessBoard {
    public static class ChessField extends Button {
        public int y;
        public int x;
    }

    public static int figureNumber = 1;
    public static InsertedFigure figure1;
    public static InsertedFigure figure2;
    private static final GridPane chessboardPane = new GridPane();
    private static Node placedFigure1;
    private static Node placedFigure2;

    public static GridPane createChessBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                ChessField button = new ChessField();
                String color = (x + y) % 2 == 1 ? "white" : "black";

                button.setStyle("-fx-background-color: " + color + ";");
                button.x = x;
                button.y = y;
                button.setPrefSize(50, 50);
                DragNDropEvents.setDropZone(button, "chessBoard", "");

                chessboardPane.add(button, y, x);
            }
        }
        return chessboardPane;
    }

    public static void removeFigureFromButton(Node el, int figureNumberRemoved) {
        if (figureNumberRemoved == 1) {
            figure1 = null;
            figureNumber = 2;
        } else if (figureNumberRemoved == 2) {
            figure2 = null;
            figureNumber = 2;
        }
        ((Button) el).setGraphic(null);
    }

    public static void reset(boolean figuresCleared) {
        ChessBoard.clearChessBoard();
        if (!figuresCleared) {
            if (placedFigure1 != null)
                removeFigureFromButton(placedFigure1, 1);
            if (placedFigure2 != null)
                removeFigureFromButton(placedFigure2, 2);
        }
    }

    /**
     * Sets figure on button
     */
    public static void setFigureAtButton(Node el, String figureName) {
        if (figureNumber == 3)
            return;

        try {
            Button button = (Button) el;
            ImageView imageView = FiguresRender.createFigure(figureName, 30, 30);
            button.setGraphic(imageView);
            DragNDropEvents.setDragNDrop(button, figureName);

            if (figureNumber == 1) {
                figure1 = createFigure(button, figureName, figureNumber);
                placedFigure1 = el;
            } else if (figure1 == null && figureNumber == 2) {
                figure1 = createFigure(button, figureName, 1);
                placedFigure1 = el;
                startCalculation();
            } else if (figureNumber == 2) {
                figure2 = createFigure(button, figureName, figureNumber);
                placedFigure2 = el;
                startCalculation();
            }
            figureNumber++;
        } catch (IOException ignored) {}
    }

    public static InsertedFigure createFigure(Button button, String source, int numberFigure) {
        ChessField chessField = (ChessField) button;
        String typ = "";
        for (String figureName : Sachovnice.namesOfFigures) {
            if (source.contains(figureName)) {
                typ = figureName;
                break;
            }
        }

        return new InsertedFigure(
            new Positions(chessField.x, chessField.y),
            numberFigure,
            typ
        );
    }

    /**
     * Clears everything on chessboard
     */
    public static void clearChessBoard() {
        for (Node node : chessboardPane.getChildren()) {
            ChessField field = (ChessField) node;
            String color = (field.x + field.y) % 2 == 1 ? "white" : "black";
            node.setStyle("-fx-background-color:" + color);
        }
    }

    public static void startCalculation() {
        CalculateFigurePathing virtualChess = new CalculateFigurePathing(8, 8, figure1, figure2);
        Result result = virtualChess.countFigureMoves();
        if (result == null) {
            InformationPanel.showMessage("Figury se nikdy nevyhodí");
            return;
        }

        ChessBoardFieldRendering.renderResult(chessboardPane, result.figure1Way, "blue");
        ChessBoardFieldRendering.renderResult(chessboardPane, result.figure2Way, "green");
        ChessBoardFieldRendering.renderCrossField(chessboardPane, result.crossField);

        String msgInflection = result.pocetTahu == 1 ? " tah." : " tahy.";
        InformationPanel.showMessage("Figury se vyhodí na " + result.pocetTahu + msgInflection);
    }
}

package Sachovnice.GUI;

import Sachovnice.calculations.types.Field;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ChessBoardFieldRendering {

    public static void renderResult(GridPane pane, ArrayList<Field> figureWay, String color) {
        for (Field field : figureWay)
            renderField(pane, field, color);
    }

    /**
     *
     * @param pane
     * @param field
     */
    public static void renderCrossField(GridPane pane, Field field) {
        renderField(pane, field, "orange");
    }

    /**
     * Renders one assigned field
     * @param pane - Chessboard
     * @param field - Assigned field
     * @param color - Color which will be field highlited
     */
    private static void renderField(GridPane pane, Field field, String color) {
        for (Node node : pane.getChildren()) {
            if (GridPane.getRowIndex(node) == field.x && GridPane.getColumnIndex(node) == field.y) {
                node.setStyle("-fx-background-color:" + color);
                break;
            }
        }
    }
}

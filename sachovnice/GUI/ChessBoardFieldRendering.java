package sachovnice.GUI;

import sachovnice.calculations.types.Field;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ChessBoardFieldRendering {
    /**
     * Renders path of figure to cross field
     *
     * @param pane - ChessBoard
     * @param figureWay - ArrayList with list of field positions where figure entered
     * @param color - Color which will be way colored
     */
    public static void renderResult(GridPane pane, ArrayList<Field> figureWay, String color) {
        for (Field field : figureWay)
            renderField(pane, field, color);
    }

    /**
     * Renders field where figures crossed each other
     *
     * @param pane - Chessboard
     * @param field - Field of cross
     */
    public static void renderCrossField(GridPane pane, Field field) {
        renderField(pane, field, "orange");
    }

    /**
     * Renders one assigned field
     * @param pane - Chessboard
     * @param field - Assigned field
     * @param color - Color which will be field highlighted
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

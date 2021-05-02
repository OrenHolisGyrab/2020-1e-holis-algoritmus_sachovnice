package sachovnice.calculations.calculations;

import sachovnice.calculations.figureDataTypes.FigureInField;
import sachovnice.calculations.types.Field;

import java.util.ArrayList;

public class VirtualChessField {
    /**
     * Generates a virtual chessboard
     * @param width - Width of chessboard
     * @param height - Height of chessboard
     * @return Virtual chessboard
     */
    public static ArrayList<ArrayList<Field>> generateVirtualChessboard(int width, int height) {
        ArrayList<ArrayList<Field>> virtualChessboard = new ArrayList<>();

        for (int y = 0; y <= width - 1; y++) {
            virtualChessboard.add(new ArrayList<>());

            for (int x = 0; x <= height - 1; x++) {
                Field field = new Field(x, y, createFigure(), createFigure());
                virtualChessboard.get(y).add(field);
            }

        }

        return virtualChessboard;
    }

    /**
     * Generates figure with:
     *      - visited (boolean)          - false
     *      - number of move (int)       - -1
     *      - previous field (String)    - x1
     *      - figureOnField (boolean)    - false
     * @return Generated figure object
     */
    public static FigureInField createFigure() {
        return new FigureInField(false, -1, "x1", false);
    }
}

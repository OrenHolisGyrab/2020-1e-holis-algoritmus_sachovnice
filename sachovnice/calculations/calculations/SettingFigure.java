package sachovnice.calculations.calculations;

import sachovnice.calculations.figureDataTypes.FigureInField;
import sachovnice.calculations.figureDataTypes.InsertedFigure;
import sachovnice.calculations.types.Field;
import sachovnice.calculations.types.VisitedFields;

import java.util.ArrayList;

public class SettingFigure {
    /**
     * Sets figure on virtual chessBoard
     *
     * @param figure - Data about figure which will be placed
     * @param chessboardFields - Virtual chessboard
     * @param visitedFields - Fields which was visited by figure
     */
    public SettingFigure(InsertedFigure figure, ArrayList<ArrayList<Field>> chessboardFields, VisitedFields visitedFields) {
        Field fieldWhereIsFigure = chessboardFields.get(figure.position.y).get(figure.position.x);

        setFigureEntry(
            figure.figureNumber == 1 ? fieldWhereIsFigure.figure1 : fieldWhereIsFigure.figure2
        );

        if (figure.figureNumber == 1) {
            visitedFields.figure1.add(fieldWhereIsFigure);
        } else if (figure.figureNumber == 2) {
            visitedFields.figure2.add(fieldWhereIsFigure);
        }
    }

    /**
     * Sets figure entry on field
     *
     * @param figure - Figure which entered
     */
    private static void setFigureEntry(FigureInField figure) {
        figure.visited = true;
        figure.actualFigurePosition = true;
    }
}

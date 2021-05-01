package Sachovnice.calculations.calculations;

import Sachovnice.calculations.figureDataTypes.FigureInField;
import Sachovnice.calculations.figureDataTypes.InsertedFigure;
import Sachovnice.calculations.types.Field;
import Sachovnice.calculations.types.VisitedFields;

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
        Field aktualniPoleFigury = chessboardFields.get(figure.position.y).get(figure.position.x);

        setFigureEntry(
            figure.figureNumber == 1 ? aktualniPoleFigury.figure1 : aktualniPoleFigury.figure2
        );

        if (figure.figureNumber == 1) {
            visitedFields.figure1.add(aktualniPoleFigury);
        } else if (figure.figureNumber == 2) {
            visitedFields.figure2.add(aktualniPoleFigury);
        }
    }

    /**
     * Sets figure entry on field
     *
     * @param figure - Figure which entered
     */
    private static void setFigureEntry(FigureInField figure) {
        figure.visited = true;
        figure.aktualniPoziceFigury = true;
    }
}

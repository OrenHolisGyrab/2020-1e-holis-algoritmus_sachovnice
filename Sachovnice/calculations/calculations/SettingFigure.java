package Sachovnice.calculations.calculations;

import Sachovnice.calculations.figureDataTypes.FigureInField;
import Sachovnice.calculations.figureDataTypes.InsertedFigure;
import Sachovnice.calculations.types.Field;
import Sachovnice.calculations.types.VisitedFields;

import java.util.ArrayList;

public class SettingFigure {

    /**
     *
     * @param figure
     * @param chessboardFields
     * @param visitedFields
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
     *
     * @param figure
     */
    private static void setFigureEntry(FigureInField figure) {
        figure.visited = true;
        figure.aktualniPoziceFigury = true;
    }
}

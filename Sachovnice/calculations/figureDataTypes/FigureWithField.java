package Sachovnice.calculations.figureDataTypes;

import Sachovnice.calculations.types.Field;

public class FigureWithField extends Figure {
    public Field field;

    public FigureWithField(int x, int y, int figureNumber, Field field) {
        super(x, y, figureNumber);
        this.field = field;
    }
}

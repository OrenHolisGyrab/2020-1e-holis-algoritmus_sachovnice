package sachovnice.calculations.figureDataTypes;

import sachovnice.calculations.types.Field;

public class FigureWithField extends Figure {
    public Field field;

    public FigureWithField(int x, int y, int figureNumber, Field field) {
        super(x, y, figureNumber);
        this.field = field;
    }
}

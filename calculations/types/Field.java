package Sachovnice.calculations.types;

import Sachovnice.calculations.figureDataTypes.FigureInField;

public class Field {
    public int x;
    public int y;
    public FigureInField figure1;
    public FigureInField figure2;

    public Field(int x, int y, FigureInField figure1, FigureInField figure2) {
        this.x = x;
        this.y = y;
        this.figure1 = figure1;
        this.figure2 = figure2;
    }
}

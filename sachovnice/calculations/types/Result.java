package sachovnice.calculations.types;

import java.util.ArrayList;

public class Result {
    public int numberOfMoves;
    public boolean isCrossed;
    public Field crossField;
    public ArrayList<Field> figure1Way;
    public ArrayList<Field> figure2Way;

    public Result(int numberOfMoves, boolean isCrossed, Field crossField, ArrayList<Field> figure1Way, ArrayList<Field> figure2Way) {
        this.numberOfMoves = numberOfMoves;
        this.isCrossed = isCrossed;
        this.crossField = crossField;
        this.figure1Way = figure1Way;
        this.figure2Way = figure2Way;
    }
}

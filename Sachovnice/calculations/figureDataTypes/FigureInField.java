package Sachovnice.calculations.figureDataTypes;

public class FigureInField {
    public boolean visited;
    public int moveNumber;
    public String fieldBefore;
    public boolean actualFigurePosition;

    public FigureInField(Boolean visited, int moveNumber, String fieldBefore, boolean actualFigurePosition) {
        this.visited = visited;
        this.moveNumber = moveNumber;
        this.fieldBefore = fieldBefore;
        this.actualFigurePosition = actualFigurePosition;
    }
}

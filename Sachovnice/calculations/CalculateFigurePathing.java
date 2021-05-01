package Sachovnice.calculations;

import Sachovnice.calculations.calculations.SettingFigure;
import Sachovnice.calculations.calculations.VirtualChessField;
import Sachovnice.calculations.calculations.AvailableFigureMoves;
import Sachovnice.calculations.figureDataTypes.Figure;
import Sachovnice.calculations.figureDataTypes.FigureInField;
import Sachovnice.calculations.figureDataTypes.FigureWithField;
import Sachovnice.calculations.figureDataTypes.InsertedFigure;
import Sachovnice.calculations.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalculateFigurePathing extends AvailableFigureMoves {
    protected InsertedFigure figure1;
    protected InsertedFigure figure2;

    private final Sizing size;

    private final Result result;

    protected ArrayList<ArrayList<Field>> fields;

    protected VisitedFields visitedFields;

    public CalculateFigurePathing(int height, int width, InsertedFigure figure1, InsertedFigure figure2) {
        if(width <= 0)
            throw new Error("Wrong width");

        if(height <= 0)
            throw new Error("Wrong height");

        this.size = new Sizing(width, height);

        this.fields = VirtualChessField.generateVirtualChessboard(this.size.width, this.size.height);

        this.visitedFields = new VisitedFields(new ArrayList<>(), new ArrayList<>());

        this.result = new Result(0, false, null, new ArrayList<>(), new ArrayList<>());

        this.figure1 = figure1;
        this.figure2 = figure2;
        new SettingFigure(figure1, this.fields, this.visitedFields);
        new SettingFigure(figure2, this.fields, this.visitedFields);
    }

    /**
     * Calculates number of played moves.
     * Repeat until finds solution or there is any fields where can figures go.
     *
     * @return Result with count of moves and way of first and second figure to cross field
     */
    public Result countFigureMoves() {
        if (this.figure1 == null)
            throw new Error("Figure one is not assigned.");

        if (this.figure2 == null)
            throw new Error("Figure two is not assigned.");

        while (this.visitedFields.figure1.size() != 0 && this.visitedFields.figure2.size() != 0 && !this.result.isCrossed) {
            this.result.numberOfMoves++;
            goThrowVisitedFields(this.visitedFields.figure1, 1);

            if (!this.result.isCrossed)
                goThrowVisitedFields(this.visitedFields.figure2, 2);
        }

        if (this.result.isCrossed)
            return this.result;

        return null;
    }

    /**
     * Go throw all visited fields
     *
     * @param visitedFields - List of visited fields in last
     * @param numberOfFigure - Number of figure
     */
    private void goThrowVisitedFields(ArrayList<Field> visitedFields, int numberOfFigure) {
        if (numberOfFigure == 1) {
            this.visitedFields.figure1 = new ArrayList<>();
        } else if (numberOfFigure == 2) {
            this.visitedFields.figure2 = new ArrayList<>();
        }

        for (int i = 0; i < visitedFields.size() && !this.result.isCrossed; i++) {
            calculateAvailableFieldsForFigure(
                new Figure(visitedFields.get(i).x, visitedFields.get(i).y, numberOfFigure)
            );
        }
    }

    /**
     * Calculates all available fields from field where figure stand at this time.
     *
     * @param data Information about figure - position - x - Figures x
     *                                                 - y - Figures y
     *                                      - figureNumber - Number of figure {1 | 2}
     */
    private void calculateAvailableFieldsForFigure(Figure data) {
        HashMap<String, InsertedFigure> actuallyPlayedFigure = new HashMap<>();
        actuallyPlayedFigure.put("figure1", this.figure1);
        actuallyPlayedFigure.put("figure2", this.figure2);
        List<Positions> availableFields = calculateFigureAvailableFields(
            actuallyPlayedFigure.get("figure" + data.figureNumber).typ,
            new Positions(data.x, data.y),
            this.size,
            data.figureNumber,
            this.result.numberOfMoves
        );

        for (int i = 0; availableFields.size() > i && !this.result.isCrossed; i++) {
            int x = data.x + availableFields.get(i).x;
            int y = data.y + availableFields.get(i).y;

            if (x >= 0 && x < this.size.width && y >= 0 && y < this.size.height) {
                Field evaluatedPlayField = this.foundPlayField(x, y);

                HashMap<Object, FigureInField> evaluatedPlayFieldFigureHash = new HashMap<>();
                evaluatedPlayFieldFigureHash.put("figure1", evaluatedPlayField.figure1);
                evaluatedPlayFieldFigureHash.put("figure2", evaluatedPlayField.figure2);

                FigureInField figure = evaluatedPlayFieldFigureHash.get("figure" + data.figureNumber);

                if (figure.fieldBefore.equals("x1") && !figure.actualFigurePosition) {
                    markFieldFigureEntered(
                        new FigureWithField(data.x, data.y, data.figureNumber, evaluatedPlayField),
                        !(actuallyPlayedFigure.get("figure" + data.figureNumber).typ.equals("pawn") && availableFields.get(i).y != 0)
                    );
                }
            }
        }
    }

    /**
     * Marks field where figure entered.
     * At field is marked which figure it was and from which field she came.
     *
     * @param field - Information about field on which is decided figures cross
     *                  - {Field} field - Field where is cross evaluated
     *                  - fieldBefore - Field from where figure came
     *                  - figureNumber - Figure number {1 | 2}
     * @param mark - If field will be marked (Only used for pawns)
     */
    private void markFieldFigureEntered(FigureWithField field, boolean mark) {
        evaluateFigureWaysCross(field);

        if (!this.result.isCrossed && mark) {
            HashMap<Object, FigureInField> calculatedFieldHash = new HashMap<>();
            calculatedFieldHash.put("poleFigure1", field.field.figure1);
            calculatedFieldHash.put("poleFigure2", field.field.figure2);

            HashMap<Object, ArrayList<Field>> figureWhichCrossed = new HashMap<>();
            figureWhichCrossed.put("visitedFields1", this.visitedFields.figure1);
            figureWhichCrossed.put("visitedFields2", this.visitedFields.figure2);

            FigureInField fieldFigure = calculatedFieldHash.get("poleFigure" + field.figureNumber);
            fieldFigure.visited = true;
            figureWhichCrossed.get("visitedFields" + field.figureNumber).add(field.field);

            if (!fieldFigure.actualFigurePosition && fieldFigure.fieldBefore.equals("x1")) {
                fieldFigure.fieldBefore = field.x + "-" + field.y;
                fieldFigure.moveNumber = this.result.numberOfMoves;
            }
        }
    }

    /**
     * Evaluates if figures crossed each other on the field
     *
     * @param field - Information about field on which is decided figures cross
     *                  - {Field} field - Field where is cross evaluated
     *                  - fieldBefore - Field from where figure came
     *                  - figureNumber - Figure number {1 | 2}
     */
    private void evaluateFigureWaysCross(FigureWithField field) {
        HashMap<Object, FigureInField> evaluatedField = new HashMap<>();
        evaluatedField.put("figure1", field.field.figure1);
        evaluatedField.put("figure2", field.field.figure2);

        if (evaluatedField.get("figure" + (field.figureNumber == 1 ? 2 : 1)).visited &&
            !evaluatedField.get("figure" + field.figureNumber).actualFigurePosition
        ) {
            if (field.figureNumber == 1) {
                this.visitedFields.figure1 = new ArrayList<>();
            } else if (field.figureNumber == 2) {
                this.visitedFields.figure2 = new ArrayList<>();
            }
            FigureInField evaluatedFieldFigure = evaluatedField.get("figure" + field.figureNumber);
            evaluatedFieldFigure.fieldBefore = field.x + "-" + field.y;
            this.result.crossField = field.field;
            calculateFigurePath();
            this.result.isCrossed = true;
        }
    }

    /**
     * Calculates route of first and second figure
     */
    private void calculateFigurePath() {
        HashMap<String, String> crossFieldBeforeField = new HashMap<>();
        crossFieldBeforeField.put("figure1", this.result.crossField.figure1.fieldBefore);
        crossFieldBeforeField.put("figure2", this.result.crossField.figure2.fieldBefore);

        for (int i = 1; i <= 2; i++) {
            String beforeFieldId = crossFieldBeforeField.get("figure" + i);
            while (beforeFieldId != null && !beforeFieldId.equals("x1")) {
                String[] fieldBeforeId = beforeFieldId.split("-");
                int x = Integer.parseInt(fieldBeforeId[0]);
                int y = Integer.parseInt(fieldBeforeId[1]);

                Field pole = this.fields.get(y).get(x);
                HashMap<String, FigureInField> actualField = new HashMap<>();
                actualField.put("figure1", pole.figure1);
                actualField.put("figure2", pole.figure2);

                if (!actualField.get("figure" + i).actualFigurePosition) {
                    beforeFieldId = actualField.get("figure" + i).fieldBefore;
                    if (i == 1) {
                        this.result.figure1Way.add(pole);
                    } else {
                        this.result.figure2Way.add(pole);
                    }
                } else {
                    beforeFieldId = null;
                }
            }
        }
    }

    /**
     * Fiends field in virtual chessBoard
     * @param x - Fields x
     * @param y - Fields y
     * @return Field from virtual chessBoard
     */
    private Field foundPlayField(int x, int y) {
        return this.fields.get(y).get(x);
    }
}

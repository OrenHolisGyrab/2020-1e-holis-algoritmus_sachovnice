package Sachovnice.calculations;

import Sachovnice.calculations.figureDataTypes.InsertedFigure;
import Sachovnice.calculations.types.Positions;
import Sachovnice.calculations.types.Result;

public class CalculationTests {
    private static int testNumber = 0;

    private static void pawn() {
        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(7, 0, 2, "pawn"),
            "3"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(1, 1, 2, "pawn"),
            "1"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(7, 1, 2, "pawn"),
            "3"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(7, 2, 2, "pawn"),
            "Nikdy se nevyhodí"
        );
    }

    public static void horse() {
        createTest(
            createFigure(0, 0,1, "horse"),
            createFigure(7, 7, 2, "queen"),
            "1"
        );
    }

    public static void king() {

    }

    public static void shooter() {

    }

    public static void queen() {

    }

    public static void tower() {
        createTest(
            createFigure(0, 0,1, "tower"),
            createFigure(7, 7, 2, "tower"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "tower"),
            createFigure(0, 1, 2, "tower"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "tower"),
            createFigure(7, 7, 2, "king"),
            "2"
        );

        createTest(
            createFigure(0, 0,1, "tower"),
            createFigure(2, 1, 2, "queen"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "tower"),
            createFigure(3, 1, 2, "shooter"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "tower"),
            createFigure(7, 7, 2, "king"),
            "2"
        );
    }

    public static void main(String[] args) {
        System.out.println("-----------------------Pawn-----------------------------");
        pawn();
        System.out.println("-----------------------Tower-----------------------------");
        tower();
        System.out.println("-----------------------King-----------------------------");
        king();
        System.out.println("-----------------------Queen-----------------------------");
        queen();
        System.out.println("-----------------------Horse-----------------------------");
        horse();
        System.out.println("-----------------------Shooter-----------------------------");
        shooter();

        createTest(
            createFigure(0, 0, 1, "horse"),
            createFigure(6, 3, 2, "horse"),
            "2"
        );

        createTest(
            createFigure(1, 1, 1, "shooter"),
            createFigure(7, 7, 2, "pawn"),
            "1"
        );

        createTest(
            createFigure(1, 0, 1, "tower"),
            createFigure(4, 4, 2, "tower"),
            "1"
        );


        createTest(
            createFigure(1, 0, 1, "shooter"),
            createFigure(1,1, 2, "shooter"),
            "Nikdy se nevyhodí"
        );
    }

    private static InsertedFigure createFigure(int x, int y, int figureNumber, String typ) {
        return new InsertedFigure(new Positions(x, y), figureNumber, typ);
    }

    private static void createTest(InsertedFigure figure1, InsertedFigure figure2, String expectedResult) {
        try {
            testNumber++;
            CalculateFigurePathing virtualChess = new CalculateFigurePathing(8, 8, figure1, figure2);
            Result result = virtualChess.countFigureMoves();
            if (result == null && !expectedResult.equals("Nikdy se nevyhodí")) {
                throw new Exception("Chyba");
            } else if (result != null && Integer.parseInt(expectedResult) != result.numberOfMoves) {
                throw new Exception("chyba");
            }
            System.out.println("test " + testNumber  + " passed");
        } catch (Exception ex) {
            System.out.println("fail");
            System.out.println("test " + testNumber);
        }
    }
}

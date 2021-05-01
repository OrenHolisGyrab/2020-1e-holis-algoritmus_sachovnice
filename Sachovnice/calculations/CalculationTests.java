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

        createTest(
            createFigure(1, 1, 1, "pawn"),
            createFigure(1, 2, 2, "pawn"),
            "Nikdy se nevyhodí"
        );

        createTest(
            createFigure(7, 0, 1, "pawn"),
            createFigure(7, 7, 2, "king"),
            "Nikdy se nevyhodí"
        );

        createTest(
                createFigure(0, 0, 1, "pawn"),
                createFigure(7, 7, 2, "king"),
                "Nikdy se nevyhodí"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(7, 6, 2, "king"),
            "6"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(2, 7, 2, "tower"),
            "1"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(3, 7, 2, "tower"),
            "2"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(4, 7, 2, "queen"),
            "2"
        );

        createTest(
            createFigure(0, 0, 1, "pawn"),
            createFigure(2, 6, 2, "shooter"),
            "3"
        );

        createTest(
            createFigure(4, 6, 1, "pawn"),
            createFigure(0, 0, 2, "horse"),
            "Nikdy se nevyhodí"
        );

        createTest(
            createFigure(4, 5, 1, "pawn"),
            createFigure(0, 0, 2, "horse"),
            "3"
        );
    }

    public static void horse() {
        createTest(
                createFigure(0, 0,1, "horse"),
                createFigure(7, 7, 2, "horse"),
                "3"
        );

        createTest(
            createFigure(0, 1,1, "horse"),
            createFigure(0, 3, 2, "horse"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "horse"),
            createFigure(7, 0, 2, "king"),
            "3"
        );

        createTest(
            createFigure(0, 0,1, "horse"),
            createFigure(4, 6, 2, "pawn"),
            "3"
        );

        createTest(
            createFigure(3, 2,1, "horse"),
            createFigure(0, 0, 2, "pawn"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "horse"),
            createFigure(4, 7, 2, "tower"),
            "2"
        );

        createTest(
            createFigure(0, 1,1, "horse"),
            createFigure(4, 7, 2, "shooter"),
            "2"
        );

        createTest(
            createFigure(0, 0,1, "horse"),
            createFigure(7, 6, 2, "shooter"),
            "1"
        );
    }

    public static void king() {
        createTest(
            createFigure(0, 0,1, "king"),
            createFigure(7, 7, 2, "king"),
            "4"
        );

        createTest(
            createFigure(6, 0,1, "king"),
            createFigure(7, 7, 2, "pawn"),
            "6"
        );

        createTest(
            createFigure(7, 0,1, "king"),
            createFigure(7, 7, 2, "pawn"),
            "Nikdy se nevyhodí"
        );

        createTest(
                createFigure(0, 0,1, "king"),
                createFigure(6, 6, 2, "tower"),
                "2"
        );

        createTest(
                createFigure(1, 6,1, "king"),
                createFigure(4, 0, 2, "queen"),
                "2"
        );

        createTest(
                createFigure(0, 1,1, "king"),
                createFigure(2, 6, 2, "shooter"),
                "2"
        );

        createTest(
            createFigure(0, 0,1, "king"),
            createFigure(7, 7, 2, "horse"),
            "3"
        );
    }

    public static void shooter() {
        createTest(
            createFigure(0, 1,1, "shooter"),
            createFigure(0, 0, 2, "shooter"),
            "Nikdy se nevyhodí"
        );

        createTest(
                createFigure(0, 0,1, "shooter"),
                createFigure(5, 5, 2, "shooter"),
                "1"
        );

        createTest(
            createFigure(0, 0,1, "shooter"),
            createFigure(7, 3, 2, "king"),
            "2"
        );

        createTest(
            createFigure(0, 0,1, "shooter"),
            createFigure(1, 0, 2, "pawn"),
            "2"
        );

        createTest(
            createFigure(0, 0,1, "shooter"),
            createFigure(6, 4, 2, "tower"),
            "1"
        );

        createTest(
            createFigure(0, 0,1, "shooter"),
            createFigure(4, 4, 2, "queen"),
            "1"
        );

        createTest(
            createFigure(0, 1,1, "shooter"),
            createFigure(4, 0, 2, "horse"),
            "2"
        );
    }

    public static void queen() {
        createTest(
                createFigure(1, 6,1, "queen"),
                createFigure(1, 4, 2, "queen"),
                "1"
        );

        createTest(
            createFigure(0, 0,1, "queen"),
            createFigure(7, 2, 2, "pawn"),
            "2"
        );

        createTest(
                createFigure(0, 0,1, "queen"),
                createFigure(7, 6, 2, "pawn"),
                "1"
        );

        createTest(
                createFigure(1, 6,1, "queen"),
                createFigure(1, 4, 2, "tower"),
                "1"
        );

        createTest(
                createFigure(0, 3,1, "queen"),
                createFigure(7, 0, 2, "horse"),
                "2"
        );
        createTest(
                createFigure(0, 3,1, "queen"),
                createFigure(7, 3, 2, "shooter"),
                "1"
        );

        createTest(
                createFigure(0, 1,1, "queen"),
                createFigure(7, 3, 2, "king"),
                "2"
        );
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

        createTest(
                createFigure(1, 6,1, "tower"),
                createFigure(6, 4, 2, "pawn"),
                "2"
        );

        createTest(
                createFigure(0, 1,1, "tower"),
                createFigure(7, 4, 2, "pawn"),
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
            System.out.println(ex);
        }
    }
}

package Sachovnice.calculations.calculations;

import Sachovnice.calculations.types.Positions;
import Sachovnice.calculations.types.Sizing;

import java.util.ArrayList;
import java.util.List;

public class AvailableFigureMoves {

    private static Direction pawn1Direction = null;
    private static Direction pawn2Direction = null;

    /**
     * Resets pawns
     */
    public static void resetPawns() {
        pawn1Direction = null;
        pawn2Direction = null;
    }

    /**
     * Calculates all accessible field for figure
     *      - king
     *      - queen
     *      - shooter
     *      - pawn
     *      - horse
     *      - tower
     *
     * @param figureName        - name of figure
     * @param figurePosition    - position of figure x and y
     * @param chessboardSizing  - sizing of chess board
     * @return {[{}]} All accessible field which can figure enter
     */
    public static List<Positions> calculateFigureAvailableFields(String figureName, Positions figurePosition, Sizing chessboardSizing, int figureNumber, int numberOfMove) {
        List<Positions> availableFields = new ArrayList<>();

        switch (figureName) {
            case "tower":
                availableFields.addAll(decideAvailableFieldsTower(chessboardSizing.width, chessboardSizing.height, figurePosition));
                break;
            case "pawn":
                calculatePawnMovement(chessboardSizing.height, figurePosition, numberOfMove, figureNumber, availableFields);
                break;
            case "queen":
                availableFields.addAll(decideAvailableFieldsShooter(chessboardSizing.width, chessboardSizing.height, figurePosition));
                availableFields.addAll(decideAvailableFieldsTower(chessboardSizing.width, chessboardSizing.height, figurePosition));
                break;
            case "horse":
                createPosition(+1, -2, availableFields);
                createPosition(+2, -1, availableFields);
                createPosition(+2, +1, availableFields);
                createPosition(+1, +2, availableFields);
                createPosition(-1, +2, availableFields);
                createPosition(-2, +1, availableFields);
                createPosition(-2, -1, availableFields);
                createPosition(-1, -2, availableFields);
                break;
            case "shooter":
                availableFields.addAll(decideAvailableFieldsShooter(chessboardSizing.width, chessboardSizing.height, figurePosition));
                break;
            case "king":
                createPosition(+1, +0, availableFields);
                createPosition(+0, +1, availableFields);
                createPosition(-1, +0, availableFields);
                createPosition(+0, -1, availableFields);
                createPosition(+1, +1, availableFields);
                createPosition(+1, -1, availableFields);
                createPosition(-1, +1, availableFields);
                createPosition(-1, -1, availableFields);
                break;
        }

        return availableFields;
    }

    /**
     * Creating position and adding it to list of them
     *
     * @param x             - position x
     * @param y             - position y
     * @param positionsList - List of where will be position added
     */
    private static void createPosition(int x, int y, List<Positions> positionsList) {
        positionsList.add(new Positions(x, y));
    }

    private static void calculatePawnMovement(int height, Positions positions, int numberOfMove, int figureNumber, List<Positions> availableFields) {
        if (height - positions.x > positions.x && figureNumber == 1 && numberOfMove == 1) {
            pawn1Direction = Direction.DOWN;
        } else if (height - positions.x <= positions.x  && figureNumber == 1 && numberOfMove == 1) {
            pawn1Direction = Direction.UP;
        }

        if (pawn1Direction != null && numberOfMove == 1) {
            pawn2Direction = pawn1Direction == Direction.UP ? Direction.DOWN : Direction.UP;
        } else if (figureNumber == 2) {
            if (height - positions.x > positions.x && numberOfMove == 1) {
                pawn2Direction = Direction.DOWN;
            } else if (height - positions.x <= positions.x && numberOfMove == 1) {
                pawn2Direction = Direction.UP;
            }
        }

        if (figureNumber == 1) {
            decideDirection(pawn1Direction, numberOfMove, availableFields);
        } else {
            decideDirection(pawn2Direction, numberOfMove, availableFields);
        }
    }

    /**
     * Enum for deciding which direction will pawn move
     */
    enum Direction {
        UP,
        DOWN
    }

    private static void decideDirection(Direction direction, int numberOfMove, List<Positions> availableFields) {
        switch (direction) {
            case DOWN:
                if (numberOfMove == 1) {
                    createPosition(+2, 0, availableFields);
                }
                createPosition(+1, +0, availableFields);
                createPosition(+1, +1, availableFields);
                createPosition(+1, -1, availableFields);
                break;
            case UP:
                if (numberOfMove == 1) {
                    createPosition(-2, 0, availableFields);
                }
                createPosition(-1, +0, availableFields);
                createPosition(-1, +1, availableFields);
                createPosition(-1, -1, availableFields);
                break;
        }
    }

    /**
     * Calculates which directions and how many fields can shooter on chessboard go.
     *
     * @param width    - Width of chessboard
     * @param height   - Height of chessboard
     * @param position - Position of figure x and y
     * @return {[{}]} All accessible filed which can shooter enter
     */
    private static List<Positions> decideAvailableFieldsShooter(int width, int height, Positions position) {
        List<Positions> field = new ArrayList<>();
        if (position.x - 1 >= 0 && position.y - 1 >= 0) {
            int x = position.x > 0 ? position.x : 1;
            field.addAll(calculateAvailableFields(
                Math.min(x, position.y),
                "shooter",
                -1,
                -1
            ));
        }

        if (position.x + 1 < width && position.y + 1 < height) {
            field.addAll(calculateAvailableFields(
                width - (position.x + 1),
                "shooter",
                +1,
                +1
            ));
        }

        if (position.x + 1 < width && position.y - 1 >= 0) {
            int y = position.y > 0 ? position.y : 1;
            field.addAll(calculateAvailableFields(
                Math.min(position.x, y),
                "shooter",
                +1,
                -1
            ));
        }

        if (position.x - 1 >= 0 && position.y + 1 < height) {
            field.addAll(calculateAvailableFields(
                height - (position.y + 1),
                "shooter",
                -1,
                +1
            ));
        }

        return field;
    }

    /**
     * Calculates which directions and how many fields can tower on chessboard go.
     *
     * @param width    - Width of chessboard
     * @param height   - Height of chessboard
     * @param position - Position of figure x and y
     * @return {[{}]} All accessible filed which can tower enter
     */
    private static List<Positions> decideAvailableFieldsTower(int width, int height, Positions position) {
        List<Positions> field = new ArrayList<>();
        if (position.x - 1 >= 0) {
            field.addAll(calculateAvailableFields(
                position.x,
                "x",
                -1,
                -1
            ));
        }

        if (position.y - 1 >= 0) {
            field.addAll(calculateAvailableFields(
                position.y,
                "y",
                -1,
                -1
            ));
        }

        if (position.x + 1 < width) {
            field.addAll(calculateAvailableFields(
                width - (position.x + 1),
                "x",
                +1,
                +1
            ));
        }

        if (position.y + 1 < height) {
            field.addAll(calculateAvailableFields(
                width - (position.y + 1),
                "y",
                +1,
                +1
            ));
        }

        return field;
    }

    /**
     * Calculates all available fields depending on countOfMoves, typeOfGame, operator1 and operator2
     *
     * @param countOfMoves - Maximal count of moves
     * @param typeOfGame   - Decides if plays like tower where is important if it is x or y or play as shooter
     * @param operator1    - Operator of x
     * @param operator2    - Operator of  y
     * @return {[{}]} Counted moves
     */
    private static List<Positions> calculateAvailableFields(int countOfMoves, String typeOfGame, int operator1, int operator2) {
        List<Positions> field = new ArrayList<>();
        for (int i = 0; i < countOfMoves; i++) {
            switch (typeOfGame) {
                case "x":
                    field.add(decideLogicalOperator(i + 1, 0, operator1, operator1));
                    break;
                case "y":
                    field.add(decideLogicalOperator(0, i + 1, operator1, operator1));
                    break;
                case "shooter":
                    field.add(decideLogicalOperator(i + 1, i + 1, operator1, operator2));
                    break;
            }
        }
        return field;
    }

    /**
     * Creates position with right logical operators
     *
     * @param x         - Number x
     * @param y         - Number y
     * @param operator1 - Operator of x
     * @param operator2 - Operator of  y
     * @return {{y: number, x: number}}
     */
    private static Positions decideLogicalOperator(int x, int y, int operator1, int operator2) {
        return new Positions(x * operator1, y * operator2);
    }
}

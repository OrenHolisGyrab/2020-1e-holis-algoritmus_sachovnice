package Sachovnice.calculations.types;

import java.util.ArrayList;

public class Result {
    public int pocetTahu;
    public boolean isCrossed;
    public Field crossField;
    public ArrayList<Field> figure1Way;
    public ArrayList<Field> figure2Way;

    public Result(int pocetTahu, boolean setkani, Field mistoSetkani, ArrayList<Field> figure1Way, ArrayList<Field> figure2Way) {
        this.pocetTahu = pocetTahu;
        this.isCrossed = setkani;
        this.crossField = mistoSetkani;
        this.figure1Way = figure1Way;
        this.figure2Way = figure2Way;
    }
}

package Sachovnice.calculations.figureDataTypes;

public class FigureInField {
    public boolean visited;
    public int cisloTahu;
    public String predchoziPole;
    public boolean aktualniPoziceFigury;

    public FigureInField(Boolean navstiveno, int cisloTahu, String predchoziPole, boolean aktualniPoziceFigury) {
        this.visited = navstiveno;
        this.cisloTahu = cisloTahu;
        this.predchoziPole = predchoziPole;
        this.aktualniPoziceFigury = aktualniPoziceFigury;
    }
}

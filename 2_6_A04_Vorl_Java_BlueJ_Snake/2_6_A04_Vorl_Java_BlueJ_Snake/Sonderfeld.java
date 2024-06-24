
public abstract class Sonderfeld
{
    protected Spiel spiel;
    protected SonderSymbol symbol;
    public Sonderfeld(Spiel s) {
        spiel = s;
    }

    public int XPositionGeben() {
        return symbol.XPositionGeben();
    }

    public int YPositionGeben() {
        return symbol.YPositionGeben();
    }

    public void Aktion() {
        //amgus, Methode wird von Unterklassen überschrieben
    }

    public void ZufälligPositionieren(){
        int x;
        int y;
        do {
        x = spiel.XKoordinateZufälligGeben();
        y = spiel.XKoordinateZufälligGeben();
        }
        while(!spiel.IstFrei(x,y));
        symbol.PositionSetzen(x,y);
    }
}

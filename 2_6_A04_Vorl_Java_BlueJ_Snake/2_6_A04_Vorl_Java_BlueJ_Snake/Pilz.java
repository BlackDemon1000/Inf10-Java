
public class Pilz extends Sonderfeld
{

    public Pilz(Spiel s)
    {
        super(s);
        symbol = new PilzSymbol();
        ZufälligPositionieren();
    }

    @Override
    public void Aktion() {
        spiel.PunkteÄndern(200);
        ZufälligPositionieren();
    }

}

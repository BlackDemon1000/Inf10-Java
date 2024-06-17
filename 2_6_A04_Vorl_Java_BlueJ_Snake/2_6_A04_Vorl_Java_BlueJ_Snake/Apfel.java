
public class Apfel extends Sonderfeld
{

    public Apfel(Spiel s)
    {
        super(s);
        symbol = new ApfelSymbol();
        ZufälligPositionieren();
    }

    @Override
    public void Aktion() {
        spiel.PunkteÄndern(200);
        ZufälligPositionieren();
    }
}

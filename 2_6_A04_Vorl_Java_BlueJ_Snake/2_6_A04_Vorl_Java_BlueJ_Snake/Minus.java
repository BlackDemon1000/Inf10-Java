
public class Minus extends Sonderfeld
{

    public Minus(Spiel s)
    {
        super(s);
        symbol = new MinusSymbol();
        ZufälligPositionieren();
    }

    @Override
    public void Aktion() {
        spiel.PunkteÄndern(200);
        ZufälligPositionieren();
        //sich selber entferenen
    }

}

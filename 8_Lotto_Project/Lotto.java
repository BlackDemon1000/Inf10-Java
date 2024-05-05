//import java.util.*;
public class Lotto
{
    private int[] gezogeneZahlen;
    private int[] tipps;
    int[] insekten;
    public Lotto()
    {
        tipps = new int[6];
        gezogeneZahlen = new int[6];
        insekten = new int
        //random = new Random();
    }

    public int[] tippen(int Zahl1, int Zahl2, int Zahl3, int Zahl4, int Zahl5, int Zahl6) {
        tipps[0] = Zahl1;
        tipps[1] = Zahl2;
        tipps[2] = Zahl3;
        tipps[3] = Zahl4;
        tipps[4] = Zahl5;
        tipps[5] = Zahl6;
        
        return tipps;
    }

    /*public void ZahlenZiehen() {
        gezogeneZahlen.clear();
        int anzahlZahlen = 0;
        while (anzahlZahlen<=6) {
            int zufallszahlen = random.ZufallszahlErzeugen();
            if(gezogeneZahlen.IstEnthalten(zufallszahlen)) {}
            else{
                gezogeneZahlen[anzahlZahlen]= zufallszahlen;
                anzahlZahlen = anzahlZahlen++ ;
            }

        }
    }*/

    public boolean IstEnthalten(int Zahl) {
        for(int i = 0; i < gezogeneZahlen.length; i++) {
            if(gezogeneZahlen[i] == Zahl) {
                return true;
            }
        }
    return false;
    }

}

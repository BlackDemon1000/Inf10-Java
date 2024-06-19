
import java.awt.event.*;
import java.util.*;
/**
 * Rahmenklasse des Spiels
 * 
 * @author Albert Wiedemann
 * @version 1.0
 */
class Spiel extends EreignisBehandlung
{
    private ArrayList <Sonderfeld> sonderfelder;
    /** Die Schlange.*/
    private Schlange schlange;
    /** Das Spielfeld.*/
    private RandSymbol spielfeldrand;
    /** Der Punktestand */
    private int punkte;
    /** Verlängerungsintervall */
    private final static int maxSchritte = 10;
    /** Aktuelle Schrittzahl für Verlängerung */
    private int aktSchritte;
    /** Zufallsgeneror für das Spiel */
    private Random zzGenerator;
    /** Restschritte für Sonderverlängerung der Schlange.*/
    private int sonderZähler;
    /** Restschritte für Bremsen */
    private int bremsZähler;
    // Attribut zum Verwalten der Sonderfelder
    
    /**
     * Baut die Basiselemente auf.
     */
    Spiel()
    {
        sonderfelder = new ArrayList<Sonderfeld>();
        //sonderfelder.add(new Apfel());
        char richtung;
        int xStart, yStart;
        zzGenerator = new Random();
        switch (zzGenerator.nextInt(4))
        {
            case 0:
                richtung = 'O';
                break;
            case 1:
                richtung = 'N';
                break;
            case 2:
                richtung = 'W';
                break;
            default:
                richtung = 'S';
                break;
        }
        xStart = -5 + zzGenerator.nextInt(10);
        yStart = -4 + zzGenerator.nextInt(8);
        schlange = new Schlange(xStart, yStart, richtung);
        spielfeldrand = new RandSymbol();
        punkte = 0;
        aktSchritte = maxSchritte;
        sonderZähler = 0;
        bremsZähler = 0;
        TaktdauerSetzen(500);
        // Anlegen der Sonderfelder
    }

    /**
     * Erzeugt eine zufällige x-Koordinate auf den Spielfeld.
     * @return zufällige x-Koordinate
     */
    int XKoordinateZufälligGeben()
    {
        return zzGenerator.nextInt(spielfeldrand.XMaxGeben() - spielfeldrand.XMinGeben() + 1) + spielfeldrand.XMinGeben();
    }
    
    /**
     * Erzeugt eine zufällige y-Koordinate auf den Spielfeld.
     * @return zufällige y-Koordinate
     */
    int YKoordinateZufälligGeben()
    {
        return zzGenerator.nextInt(spielfeldrand.YMaxGeben() - spielfeldrand.YMinGeben() + 1) + spielfeldrand.YMinGeben();
    }
    
    /**
     * Testet, ob eine gegebene Position noch frei ist.
     * @param x x-Koordinate der Position
     * @param y y-Koordinate der Position
     * @return wahr, wenn die gegebene Position weder von der Schlange noch anderen Symbolen belegt ist.
     */
    boolean IstFrei(int x, int y)
    {
        //Berücksichtigung der Sonderfelder einbauen
        if ((schlange.XPositionGeben() == x) && (schlange.YPositionGeben() == y))
        {
            return false;
        }
        if (schlange.PunktInRumpf(x, y))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Verändert den Punktestand um den angegebenen Wert.
     * @param delta die Punktestandsänderung
     */
    void PunkteÄndern(int delta)
    {
        punkte += delta;
    }
    
    /**
     * Startet bzw.verlängert das Sonderwachstum der Schlange.
     */
    void Sonderwachstum()
    {
        sonderZähler += 10;
    }
    
    /**
     * Aktiviert die Verzögerung des Ablaufs.
     */
    void Bremsen()
    {
        bremsZähler = 100;
        TaktdauerSetzen(1000);
        spielfeldrand.SonderanzeigeSetzen("+");
    }
    
    // Methode zum Entfernen eines Sonderfelds
    

    /**
     * Bewegt die Schlange.
     */
   @Override void TaktImpulsAusführen()
    {
        if ((schlange.XPositionGeben() <= spielfeldrand.XMaxGeben()) && (schlange.XPositionGeben() >= spielfeldrand.XMinGeben()) &&
            (schlange.YPositionGeben() <= spielfeldrand.YMaxGeben()) && (schlange.YPositionGeben() >= spielfeldrand.YMinGeben()) &&
            ! schlange.KopfInRumpf())
        {
            aktSchritte -= 1;
            if (aktSchritte <= 0)
            {
                schlange.Bewegen(true);
                aktSchritte = maxSchritte;
            }
            else if (sonderZähler > 0)
            {
                schlange.Bewegen(true);
                sonderZähler -= 1;
            }
            else
            {
                schlange.Bewegen(false);
            }
            punkte += 1;
            if (bremsZähler > 0)
            {
                if (bremsZähler == 1)
                {
                    TaktdauerSetzen(500);
                    spielfeldrand.SonderanzeigeSetzen("");
                }
                else if (bremsZähler == 10)
                {
                    spielfeldrand.SonderanzeigeSetzen("!");
                }
                bremsZähler -= 1;
            }
            for (SonderFeld s: (ArrayList<SonderFeld>)sonderfelder.clone())
            {
                if ((s != null) &&
                    (schlange.XPositionGeben() == s.XPositionGeben()) &&
                    (schlange.YPositionGeben() == s.YPositionGeben()))
                {
                    s.Aktion();
                }
            }
            spielfeldrand.PunkteSetzen(punkte);
        }
        else
        {
            Anhalten();
            if (schlange.KopfInRumpf())
            {
                spielfeldrand.EndemeldungSetzen("Game Over - Schlange hat sich selbst gebissen");
            }
            else
            {
                spielfeldrand.EndemeldungSetzen("Game Over - Schlange über Spielfeldrand");
            }
        }
    }
    
    /**
     * Wertet die Tasteneingaben aus.
     * @param welche Tastencode
     */
    @Override void TasteGedrückt(char welche)
    {
        switch (welche)
        {
          case 'S':
          case 's':
            Starten();
            break;
          case 'P':
          case 'p':
            Anhalten();
            break;
          case KeyEvent.VK_DOWN:
          case KeyEvent.VK_KP_DOWN:
            schlange.RichtungSetzen('S');
            break;
          case KeyEvent.VK_LEFT:
            schlange.RichtungSetzen('W');
            break;
          case KeyEvent.VK_RIGHT:
            schlange.RichtungSetzen('O');
            break;
          case KeyEvent.VK_UP:
            schlange.RichtungSetzen('N');
            break;
          default:
            //System.out.println("Taste: " + (0 + (int) welche));
        }
    }
}

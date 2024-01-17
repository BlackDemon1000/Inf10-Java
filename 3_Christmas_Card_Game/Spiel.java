public class Spiel {
    private karte karte1;
    private karte karte2;
    private karte karte3;
    private int punkteDealer
    private int punkteSpieler;
        public Spiel(){
        karte1 = new karte("Baum","Baum");
        karte2 = new karte("Baum", "leer");
        karte3 = new karte ("leer", "leer");
    }
    
    public int playgame(int Anzahl) {
        punkteDealer = Anzahl * 2/3;
        return punkteDealer;
    }
}

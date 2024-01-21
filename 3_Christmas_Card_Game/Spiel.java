public class Spiel {
    private karte karte1;
    private karte karte2;
    private karte karte3;
    private int punkteDealer;
    private int punkteSpieler;
    private int card;
    private final int min = 1;
    private final int max = 3;
        public Spiel(){
        karte1 = new karte("Baum","Baum");
        karte2 = new karte("Baum", "leer");
        karte3 = new karte ("leer", "leer");
    }
    
     public void createCards() {
        karte1 = new karte("Baum", "Baum");
        karte2 = new karte("Baum", "leer");
        karte3 = new karte("leer", "leer");

    }

    /*public void radomCard() {
        card = zufall.GanzzahlGeben(max - min);
        System.out.println(card);
    }*/

    public String simulateGame(int Plays) {
        for(int i=0;i<Plays;i++) {
            createCards();
            if(karte1.getVorderseite() == karte1.getRückseite()) {
                punkteDealer += 1;
            } else{
                punkteSpieler += 1;
            }            
            if(karte2.getVorderseite() == karte2.getRückseite()) {
                punkteDealer += 1;
            } else{
                punkteSpieler += 1;
            }            
            if(karte3.getVorderseite() == karte3.getRückseite()) {
                punkteDealer += 1;
            } else{
                punkteSpieler += 1;
            }
        }
       return("Punkte des Spielers: " + punkteSpieler + " Punkte des Dealers: " + punkteDealer);
    }
}

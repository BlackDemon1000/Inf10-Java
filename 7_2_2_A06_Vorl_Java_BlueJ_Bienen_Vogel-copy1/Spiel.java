public class Spiel extends Ereignisbehandlung
{
  //private Insekt insekt1;
  private Himmel himmel1;
  private Wiese wiese1;
  private Vogel vogel1;
  //private Insekt insekt2;
  private Text punktzahl;
  private Text verloren;
  String Punktzahl = "0";
  Insekt[] insekten;
  Berge[] berge;
  
  
  public Spiel(){
    himmel1 = new Himmel();
    wiese1 = new Wiese();
    vogel1 = new Vogel();
    berge = new Berge[2];
    for(int i = 0; i<2;i++) {
      berge[i] = new Berge();
    }

    /*insekt1 = new Insekt(1);
    insekt2 = new Insekt(1);
    */
   insekten = new Insekt[3];
   for(int i = 0; i<3;i++) {
    insekten[i] = new Insekt(1);
   }
   /*insekten[0] = new Insekt(1);
   insekten[1] = new Insekt(1);
   insekten[2] = new Insekt(1);*/
    punktzahl = new Text();
    verloren = new Text();
    berge[0].PositionSetzen(270, 315);
    insekten[0].PositionSetzen(435, 192);
    berge[1].PositionSetzen(533, 400);
    insekten[1].PositionSetzen(637, 221);
    insekten[2].PositionSetzen(400, 221);
    punktzahl.PositionSetzen(731,31);
    wiese1.NachHintenBringen();
    himmel1.NachHintenBringen();
    punktzahl.TextSetzen("0");
    punktzahl.TextGrößeSetzen(30);
  }
  @Override
  public void TaktImpulsAusführen(){
      berge[0].Bewegen();
      berge[1].Bewegen();
      punktzahl.TextSetzen(Punktzahl);
      for(Insekt ins :insekten) {
        ins.Bewegen();
      }
      for(int i = 0; i<3;i++) { 
        if(vogel1.Berührt(insekten[i])) {
          Punktzahl = "10";
          punktzahl.TextSetzen(Punktzahl);
          insekten[i].PositionSetzen(800, insekten[i].YPositionGeben());
        }
      }
      for(int i = 0; i<2;i++) { 
        if(vogel1.Berührt(berge[i])) {
          verloren.PositionSetzen(300, 200);
          verloren.TextGrößeSetzen(70);
          verloren.TextSetzen("Verloren");
          Anhalten();
        }
      }
      /*
      insekt1.Bewegen();
      insekt2.Bewegen();
      if(vogel1.Berührt(insekt1)) {
        Punktzahl = "10";
        punktzahl.TextSetzen(Punktzahl);
        insekt1.PositionSetzen(800, insekt1.YPositionGeben());
      }
      if(vogel1.Berührt(berg1) ||vogel1.Berührt(berg2)){
        Text t = new Text();
        t.TextSetzen("Spiel beendet");
        t.TextGrößeSetzen(50);
        t.PositionSetzen(200, 100);
        Anhalten();
      }
      */
  }
  public void insektenVerdoppeln() {
    Insekt[] insekten2 = new Insekt[6];
    for(int i=0; i < insekten.length; i++) {
      insekten2[i] = insekten[i];
    }
    insekten = insekten2;
    for(int i=3; i < insekten.length; i++){
      insekten[i] = new Insekt(1);
      insekten[i].PositionSetzen(insekten[i-3].XPositionGeben(), insekten[i-3].YPositionGeben());
    }
  }

  /*public void BergeVerdoppeln() {
    Berge[]
  }*/

  public void verdoppeln() {
    insektenVerdoppeln();
    //BergeVerdoppeln();
  }
}

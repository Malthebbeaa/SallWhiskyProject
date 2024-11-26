package application.model;

public class Fad {
    int fadId;
    int størrelse;
    String materiale;
    FadLeverandør fadLeverandør;
    String tidligereIndhold;
    int alder;
    int antalGangeBrugt;

    public Fad(int størrelse, String materiale, FadLeverandør fadLeverandør, String tidligereIndhold, int alder, int antalGangeBrugt) {
        this.størrelse = størrelse;
        this.materiale = materiale;
        this.fadLeverandør = fadLeverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.alder = alder;
        this.antalGangeBrugt = antalGangeBrugt;
    }

    public FadLeverandør getFadLeverandør() {
        return fadLeverandør;
    }

    public int getFadId() {
        return fadId;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }
    public String getLand(){
        return getFadLeverandør().land;
    }
}

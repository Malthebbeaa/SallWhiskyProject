package application.model;

public class Fad {
    private int fadId;
    private int størrelse;
    private String materiale;
    private FadLeverandør fadLeverandør;
    private String tidligereIndhold;
    private int alder;
    private int antalGangeBrugt;

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
        return getFadLeverandør().getLand();
    }
}

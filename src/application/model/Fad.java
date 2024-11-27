package application.model;

public class Fad {
    private static int nextFadId = 1;
    private int fadId;
    private int størrelse;
    private String materiale;
    private FadLeverandør fadLeverandør;
    private String tidligereIndhold;
    private int alder;
    private int antalGangeBrugt;
    private double mængdeVæskeIFad;
    private boolean fyldt;

    public Fad(int størrelse, String materiale, FadLeverandør fadLeverandør, String tidligereIndhold, int alder, int antalGangeBrugt) {
        this.størrelse = størrelse;
        this.materiale = materiale;
        this.fadLeverandør = fadLeverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.alder = alder;
        this.antalGangeBrugt = antalGangeBrugt;
        this.fyldt = false;
        this.mængdeVæskeIFad = 0;

        this.fadId = nextFadId++;
    }

    public void påfyldVæske(double påfyldningsVæskeMængde){
        if (mængdeVæskeIFad + påfyldningsVæskeMængde <= størrelse){
            mængdeVæskeIFad += påfyldningsVæskeMængde;
        } else {
            throw new RuntimeException("Ikke tilstrækkeligt plads i fadet");
        }
    }

    public void setFyldt(boolean fyldt) {
        this.fyldt = fyldt;
    }

    public boolean isFyldt() {
        return fyldt;
    }

    public double getMængdeVæskeIFad() {
        return mængdeVæskeIFad;
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

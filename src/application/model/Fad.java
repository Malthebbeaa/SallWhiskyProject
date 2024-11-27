package application.model;

public class Fad {
    private static int IdCount = 1;
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
        this.fadId = IdCount++;
        this.størrelse = størrelse;
        this.materiale = materiale;
        this.fadLeverandør = fadLeverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.alder = alder;
        this.antalGangeBrugt = antalGangeBrugt;
        this.fyldt = false;
        this.mængdeVæskeIFad = 0;
    }

    public void påfyldVæske(double påfyldningsVæskeMængde){
        if (påfyldningsVæskeMængde < 0){
            throw new RuntimeException("Mængden skal være positiv");
        }

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

    public void AftapWhisky(Double literAftappet) {
        if (mængdeVæskeIFad == 0) {
            throw new IllegalStateException("Fadet er tomt");
        }

        if (literAftappet <= mængdeVæskeIFad) {
            mængdeVæskeIFad -= literAftappet;
            if (mængdeVæskeIFad == 0) {
                antalGangeBrugt++;
            }
        } else {
            System.out.println();
        }
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

    public void AntalGangeBrugt() {
        antalGangeBrugt++;
    }


}

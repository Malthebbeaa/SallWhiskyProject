package application.model;

import java.util.ArrayList;
import java.util.List;

public class Fad {
    private static int IdCount = 1;
    private int fadId;
    private int størrelse;
    private String materiale;
    private FadLeverandør fadLeverandør;
    private String tidligereIndhold;
    private int alder;
    private int antalGangeBrugt;
    private double mængdeFyldtPåFad;
    private List<Påfyldning> påfyldninger;
    private List<Aftapning> aftapninger;


    public Fad(int størrelse, String materiale, FadLeverandør fadLeverandør, String tidligereIndhold, int alder, int antalGangeBrugt) {
        this.fadId = IdCount++;
        this.størrelse = størrelse;
        this.materiale = materiale;
        this.fadLeverandør = fadLeverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.alder = alder;
        this.antalGangeBrugt = antalGangeBrugt;
        this.mængdeFyldtPåFad = 0;
        this.påfyldninger = new ArrayList<>();
        this.aftapninger = new ArrayList<>();
    }


    public void tilføjPåfyldning(Påfyldning påfyldning) {
        double mængdeEfterTilføjelse = mængdeFyldtPåFad + påfyldning.getLiterPåfyldt();

        if (mængdeEfterTilføjelse <= størrelse) {
            påfyldninger.add(påfyldning);
            mængdeFyldtPåFad = mængdeEfterTilføjelse;
            påfyldning.setFad(this);
        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
    }

    public void tilføjAftapning(Aftapning aftapning) {
        double mængdeEfterAftapning = mængdeFyldtPåFad - aftapning.getLiterAftappet();


        if (mængdeEfterAftapning >= 0) {
            aftapninger.add(aftapning);
            mængdeFyldtPåFad = mængdeEfterAftapning;
            aftapning.setFad(this);
        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
    }

    public double getMængdeFyldtPåFad() {
        return mængdeFyldtPåFad;
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

    public String getLand() {
        return getFadLeverandør().getLand();
    }

    public void AntalGangeBrugt() {
        antalGangeBrugt++;
    }

    public List<Påfyldning> getPåfyldninger() {
        return påfyldninger;
    }

    @Override
    public String toString() {
        return "FadID: " + fadId +
                "\nStørrelse: " + størrelse +
                "\nMateriale: " + materiale +
                "\nLeverandør: " + fadLeverandør +
                "\nTidligere indhold: " + tidligereIndhold + '\'' +
                "\nAlder: " + alder +
                "\nBrugt " + antalGangeBrugt + " gang(e)" +
                "\nLiter i fad: " + mængdeFyldtPåFad;
    }

    public String toString2() {
        return "FadID: " + fadId;
    }

    public int getAlder() {
        return alder;
    }

    public String getMateriale() {
        return materiale;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public int getAntalGangeBrugt() {
        return antalGangeBrugt;
    }

    public String toString3() {
        return "FadId: " + fadId + ", kapacitet: " + størrelse + "L, " + materiale + ", Land: " + fadLeverandør.getLand() + ", Tidliger indhold: " + tidligereIndhold;
    }
}

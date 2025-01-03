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
    private Plads plads;
    private int lagringstid;

    public Fad(int størrelse, String materiale, FadLeverandør fadLeverandør, String tidligereIndhold, int alder, int antalGangeBrugt) {
        this.fadId = IdCount++;
        this.størrelse = størrelse;
        this.materiale = materiale;
        this.fadLeverandør = fadLeverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.alder = alder;
        this.antalGangeBrugt = antalGangeBrugt;
        this.mængdeFyldtPåFad = 0;
        this.lagringstid = 0;
        this.påfyldninger = new ArrayList<>();
        this.aftapninger = new ArrayList<>();
    }

    /***
     *
     * @param mængde
     * @return true hvis tilføjelsen af væske overskrider fadets størrelse
     * false hvis tilføjelsen ikke overgår størrelsen
     */
    public boolean påFyldningOvergårGrænse(double mængde) {
        return mængde + mængdeFyldtPåFad > størrelse;
    }

    /***
     * hvis påfyldningens mængde ikke overgår størrelsen på fadet
     * tilføjes påfyldningen til listen
     * mængden på påfyldningen tilføjes mængden på fadet
     * mængderne på påfyldningen trækkes fra deres destilleringer
     * @param påfyldning
     */

    public void tilføjPåfyldning(Påfyldning påfyldning) {
        if (!påFyldningOvergårGrænse(påfyldning.getLiterPåfyldt())) {
            påfyldninger.add(påfyldning);
            mængdeFyldtPåFad = mængdeFyldtPåFad + påfyldning.getLiterPåfyldt();

            //først her bliver mængden trukket fra destilleringen
            for (Mængde mængde : påfyldning.getMængderPåfyldt()) {
                mængde.getDestillering().tilføjMængdeGivet(mængde);
            }

        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
    }

    /***
     * bruges på Påfyldning klassen til at opdatere mængden på fadet
     * @param aftapning
     */
    public void aftapVæskePåFad(Aftapning aftapning){
        mængdeFyldtPåFad -= aftapning.getLiterAftappet();
    }

    public double getMængdeFyldtPåFad() {
        return mængdeFyldtPåFad;
    }

    public void setFadLeverandør(FadLeverandør fadLeverandør) {
        this.fadLeverandør = fadLeverandør;
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
    public List<Påfyldning> getPåfyldninger() {
        return påfyldninger;
    }

    @Override
    public String toString() {
        return "FadID: " + fadId +
                "\nStørrelse: " + størrelse +
                "\nMateriale: " + materiale +
                "\nLeverandør: " + fadLeverandør +
                "\nTidligere indhold: " + tidligereIndhold +
                "\nAlder: " + alder +
                "\nBrugt " + antalGangeBrugt + (antalGangeBrugt==1? " gang" : " gange") +
                "\nLiter i fad: " + mængdeFyldtPåFad;
    }

    public Plads getPlads() {
        return plads;
    }

    public void setPlads(Plads plads) {
        if (this.plads != plads) {
            if(this.plads != null) {
                Plads oldPlads = this.plads;
                oldPlads.setLedig(true);
                oldPlads.setFad(null);
            }
            this.plads = plads;
            plads.setLedig(false);
            plads.setFad(this);
        }
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
}

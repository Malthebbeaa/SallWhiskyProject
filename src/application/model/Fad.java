package application.model;

import application.controller.Controller;

import java.time.LocalDate;
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
    private List<PåfyldningsComponent> påfyldningsComponenter;
    private List<PåfyldningsComponent> TidligerepåfyldningsComponenter;
    private List<Aftapning> aftapninger;
    private Plads plads;
    private int lagringstid;
    private PåfyldningsComponent væskeMix;

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
        this.påfyldningsComponenter = new ArrayList<>();
        this.TidligerepåfyldningsComponenter = new ArrayList<>();
        this.aftapninger = new ArrayList<>();
    }

    public void tilføjVæske(LocalDate dato, PåfyldningsComponent påfyldningsComponent){
        if(mængdeFyldtPåFad + påfyldningsComponent.getVæskeMængde() <= størrelse){
            if(påfyldningsComponenter.size() > 0){
                opretVæskemix(dato, påfyldningsComponent);
            }
            else {
                påfyldningsComponenter.add(påfyldningsComponent);
                mængdeFyldtPåFad += påfyldningsComponent.getVæskeMængde();
            }
        }
        else{
            throw new RuntimeException("Kan ikke påfyldes, Overskrider fadets kapacitet");
        }
    }

    public boolean overskriderFadKapacitet(double mængde){
        if(mængde + mængdeFyldtPåFad > størrelse){
            return true;
        }
        return false;
    }

    public void opretVæskemix(LocalDate dato, PåfyldningsComponent pc) {
        if (påfyldningsComponenter.isEmpty()) {
            throw new RuntimeException("Ingen væsker tilgængelige for at oprette væskemix.");
        }
        væskeMix = new VæskeMix(dato, this);
        for (PåfyldningsComponent påfyldningsComponent : påfyldningsComponenter) {
            væskeMix.add(påfyldningsComponent);
        }
        væskeMix.add(pc);
        påfyldningsComponenter.clear();
        påfyldningsComponenter.add(væskeMix);
        mængdeFyldtPåFad = væskeMix.getVæskeMængde();
    }
    /***
     * bruges på Påfyldning klassen til at opdatere mængden på fadet
     * @param aftapning
     */
    public void aftapVæskePåFad(Aftapning aftapning){
        mængdeFyldtPåFad -= aftapning.getLiterAftappet();
        if(mængdeFyldtPåFad <= 0){
            TidligerepåfyldningsComponenter.addAll(påfyldningsComponenter);
            påfyldningsComponenter.clear();
        }
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

    public List<PåfyldningsComponent> getPåfyldningsComponenter() {
        return påfyldningsComponenter;
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

    public PåfyldningsComponent getVæskeMix() {
        return væskeMix;
    }

    public void flytDelAfVæskeMixTilFadHjælper(Fad andetFad, PåfyldningsComponent valgtMix, double mængde){
        double totalVæske = valgtMix.getVæskeMængde();
        flytDelAfVæskeMixTilFad(andetFad, valgtMix, mængde, totalVæske);
    }

    public void flytDelAfVæskeMixTilFad(Fad andetFad, PåfyldningsComponent valgtMix, double mængde, double totalVæske) {
        if (valgtMix == null) {
            throw new IllegalArgumentException("Ingen væskemix valgt.");
        }

        if (mængde <= 0 || mængde > valgtMix.getVæskeMængde()) {
            throw new IllegalArgumentException("Mængden skal være positiv og ikke overstige væskemixets mængde.");
        }

        if (andetFad.overskriderFadKapacitet(mængde)) {
            throw new RuntimeException("Kan ikke flytte væskemix, da det overskrider kapaciteten af det nye fad.");
        }
        VæskeMix nytMix = new VæskeMix(LocalDate.now(), valgtMix.getPåfyldningsDato(), andetFad);
        for (PåfyldningsComponent pc : valgtMix.getPåfyldningsComponenter()) {
            double andel = pc.getVæskeMængde() / totalVæske;
            double flyttetMængde = andel * mængde;

            if (pc instanceof Væske) {
                Væske originalVæske = (Væske) pc;
                Væske nyVæske = new Væske(originalVæske.getDestillering(), flyttetMængde);
                nytMix.add(nyVæske);
                originalVæske.setMængde(originalVæske.getVæskeMængde() - flyttetMængde);

            } else if (pc instanceof VæskeMix) {
                VæskeMix originalMix = (VæskeMix) pc;
                flytDelAfVæskeMixTilFad(andetFad, originalMix, mængde, totalVæske);
            }
        }
        andetFad.tilføjVæske(valgtMix.getPåfyldningsDato(), nytMix);
        valgtMix.setLiterPåfyldt(valgtMix.getLiterPåfyldt() - mængde);
        valgtMix.getFad().opdaterMængdeFyldtPåFad();
        andetFad.opdaterMængdeFyldtPåFad();
    }

    public void opdaterMængdeFyldtPåFad() {
        mængdeFyldtPåFad = beregnMængdeFraKomponenter(påfyldningsComponenter);
    }

    private double beregnMængdeFraKomponenter(List<PåfyldningsComponent> komponenter) {
        double totalMængde = 0;

        for (PåfyldningsComponent component : komponenter) {
            if (component instanceof Væske) {
                totalMængde += component.getVæskeMængde();
            } else if (component instanceof VæskeMix) {
                totalMængde += beregnMængdeFraKomponenter(((VæskeMix) component).getPåfyldningsComponenter());
            }
        }

        return totalMængde;
    }
}

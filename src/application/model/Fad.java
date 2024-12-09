package application.model;

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
    private List<PåfyldningsComponent> tidligerePåfyldningsComponenter;
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
        this.tidligerePåfyldningsComponenter = new ArrayList<>();
        this.aftapninger = new ArrayList<>();
    }

    /**
     * Tjekker påfyldningscomponenter og hvis der ligger enten en væske eller væskemix i den opretter den et nyt væskemix
     * med de parametre. Ellers tilføjer den væsken til listen og opdaterer mængdeFyldtPåFad.
     * @param dato - Dato for tilføjelse af væske til fadet
     * @param påfyldningsComponent - væske eller væskemix du ønsker at tilføje fadet.
     */
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

    /**
     * Tjekker om mængde du tilfører lagt til den mængde der findes i forvejen overskrider fadets størrelse
     * @param mængde - Du tilfører til fadet.
     * @return sandt eller falsk
     */
    public boolean overskriderFadKapacitet(double mængde){
        if(mængde + mængdeFyldtPåFad > størrelse){
            return true;
        }
        return false;
    }

    /**
     * Bruges af tilføjVæske metoden, kan ikke kaldes.
     * tilføjer de væsker/væskemix der findes i påfyldningscomponenter og tilføjer dem til et nyoprettet væskemixs
     * træstruktur. Derefter tømmer den listen og tilføjer det nye væskemix til listen og opdaterer mængdeFyldtPåFad
     * @param dato
     * @param pc
     */
    private void opretVæskemix(LocalDate dato, PåfyldningsComponent pc) {
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
            tidligerePåfyldningsComponenter.addAll(påfyldningsComponenter);
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

    /**
     * Bruges som hjælpe metode til flytDelAfVæskeMixTilFad, sørger for hele tiden at give korrekt mængde med til kald af metoden.
     * Metoden er til at omhælde fad - fra et til et andet.
     * @param andetFad - Fad det skal omhældes til
     * @param valgtMix - Mix der skal omhældes.
     * @param mængde - mængde der ønskes omhældt
     */
    public void flytDelAfVæskeMixTilFad(Fad andetFad, PåfyldningsComponent valgtMix, double mængde, LocalDate omhældningsDato){
        double totalVæske = valgtMix.getVæskeMængde();
        flytDelAfVæskeMixTilFadHjælper(andetFad, valgtMix, mængde, totalVæske, omhældningsDato);
    }

    /**
     * Tjekker først for exceptions.
     * Så opretter den et nyt væskeMix.
     * Looper igennem valgte mix træstruktur og leder efter et væskecomponent.
     * Hvis det ikke er et væskecomponent, så kalder den metoden igen rekursivt. Med det væskemix den har fat i.'
     * Hvis den har fat i et væskecomponent, så laver den et nyt objekt med den mængde som er blevet udregnet i metoden med andel og flyttetmængde
     * til sidst trækker den denne mængde fra originalt objekt og opdaterer begge fades mængder.
     * @param andetFad - Fad du ønsker at flytte til.
     * @param valgtMix - Det mix du ønsker at omhælde til andetFad
     * @param mængde - mængde du ønsker at omhælde
     * @param totalVæske - Total mængde af væske i fadet.
     */
    public void flytDelAfVæskeMixTilFadHjælper(Fad andetFad, PåfyldningsComponent valgtMix, double mængde, double totalVæske, LocalDate omhældningsDato) {
        if (valgtMix == null) {
            throw new IllegalArgumentException("Ingen væskemix valgt.");
        }

        if (mængde <= 0 || mængde > valgtMix.getVæskeMængde()) {
            throw new IllegalArgumentException("Mængden skal være positiv og ikke overstige væskemixets mængde.");
        }

        if (andetFad.overskriderFadKapacitet(mængde)) {
            throw new RuntimeException("Kan ikke flytte væskemix, da det overskrider kapaciteten af det nye fad.");
        }
        VæskeMix nytMix = new VæskeMix(omhældningsDato, valgtMix.getPåfyldningsDato(), andetFad);
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
                flytDelAfVæskeMixTilFadHjælper(andetFad, originalMix, mængde, totalVæske, omhældningsDato);
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

    /**
     * Kaldes via opdaterMængdeFyldtPåFad.
     * Går rekursivt igennem Påfyldningscomponenter som er tilføjet til fadet og finder de componenter som er væske
     * og tilføjer dem til total mængde. Efter endt rekursion returneres totale mængde.
     * @param komponenter
     * @return - total mængde på fadet.
     */

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

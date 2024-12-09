package application.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class VæskeMix extends PåfyldningsComponent {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private Fad fad;
    private List<Aftapning> aftapninger;
    private List<PåfyldningsComponent> påfyldningsComponenter;
    private LocalDate omhældningsDatoer;

    public VæskeMix(LocalDate påfyldningsDato, Fad fad) {
        this.påfyldningsDato = påfyldningsDato;
        this.literPåfyldt = 0;
        this.fad = fad;
        aftapninger = new ArrayList<>();
        påfyldningsComponenter = new ArrayList<>();
    }

    public VæskeMix(LocalDate omhældningsDato, LocalDate påfyldningsDato, Fad fad){
        this.påfyldningsComponenter = new ArrayList<>();
        this.literPåfyldt = 0;
        this.omhældningsDatoer = omhældningsDato;
        this.påfyldningsDato = påfyldningsDato;
        this.fad = fad;
        getVæskeMængde();
        System.out.println(this.omhældningsDatoer);
    }


    /***
     * Kalder rekursivt gennem Påfyldningskomponenterne på VæskeMix
     * der beregnes for hvert væskemix hvor stor en andel den udgør af hele aftapningen
     * sender andelen videre indtil vi rammer en Leaf (Væske) og her aftappes væsken
     * sidste komponent tager den resterende mængde væske så vi undgår afrundningsfejl
     * @param mængde
     */
    @Override
    public void aftap(double mængde) {
        if (getVæskeMængde() < mængde) {
            throw new RuntimeException("Der er ikke nok væske til aftapningen");
        }

        if (mængde < 1){
            throw new RuntimeException("Mængden skal være positiv");
        }

        double totalAftappet = 0;  // Holder styr på, hvor meget vi har aftappet
        int i = 0;  // Tæller iterationer
        int size = påfyldningsComponenter.size();  // Antal komponenter i mixet

        for (PåfyldningsComponent component : påfyldningsComponenter) {
            i++;
            double mængdeTilAftapning;

            if (i == size) {
                // Sidste komponent får resten
                mængdeTilAftapning = mængde - totalAftappet;
            } else {
                // Fordel mængden proportionelt for alle andre komponenter
                double andel = component.getVæskeMængde() / getVæskeMængde();
                mængdeTilAftapning = andel * mængde;
                totalAftappet += mængdeTilAftapning;  // Opdater total aftappet
            }

            // Aftap den beregnede mængde fra komponenten
            component.aftap(mængdeTilAftapning);
        }

        this.setMængde(getVæskeMængde() - mængde);
    }


    public boolean mængdenOverskriderFadKapacitet(double mængde){
        return fad.overskriderFadKapacitet(mængde);
    }

    /***
     *
     * @param mængde
     * @return true hvis aftapningen gør at total mængde går i minus
     */
    public boolean aftapningGårIMinus(double mængde) {
        double fremtidigeTotal = literPåfyldt - mængde;
        return fremtidigeTotal < 0;
    }


    public void setFad(Fad fad){
        if (fad != null){
            this.fad = fad;
        }
    }

    public void tilføjAftapning(Aftapning aftapning){
        if (!aftapninger.contains(aftapning)){
            aftapninger.add(aftapning);
        }
    }

    /***
     *
     * @return String med år, måneder og dage Påfyldningen har lagret
     */
    public String getAntalÅrMånederDage(){
        return antalÅrPåFad(LocalDate.now()).getYears() + " år, " + antalÅrPåFad(LocalDate.now()).getMonths() + " mnd og " + antalÅrPåFad(LocalDate.now()).getDays() + " dage";
    }
    public Period antalÅrPåFad(LocalDate date){
        Period periode = Period.between(påfyldningsDato,date);
        return periode;
    }

    /***
     *
     * @param date dato til sammenligning med påfyldningsdatoen
     * @return om det er over 3 eller flere år siden væsken er påfyldt
     */
    public boolean klarTilAftapning(LocalDate date){
        return antalÅrPåFad(date).getYears() > 2;
    }

    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public double getLiterPåfyldt() {
        return literPåfyldt;
    }

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    public Fad getFad() {
        return fad;
    }

    @Override
    public String toString() {
        double alkoholProcent = 0;
        int counter = 0;
        for (PåfyldningsComponent påfyldningsComponent : påfyldningsComponenter) {
            if (påfyldningsComponent instanceof Væske){
                alkoholProcent += påfyldningsComponent.getDestillering().getAlkoholProcent();
                counter++;
            }
        }
        return "FadId" +fad.getFadId() + ", " + getVæskeMængde() + " L, " + alkoholProcent / counter + " %";
    }

    @Override
    public void add(PåfyldningsComponent påfyldningsComponent) {
        påfyldningsComponenter.add(påfyldningsComponent);
        getVæskeMængde();
    }

    @Override
    public void remove(PåfyldningsComponent påfyldningsComponent) {
        if (påfyldningsComponenter.contains(påfyldningsComponent)){
            påfyldningsComponenter.remove(påfyldningsComponent);
        }
    }

    @Override
    public PåfyldningsComponent getChild(int i) {
        return påfyldningsComponenter.get(i);
    }

    @Override
    public double getVæskeMængde() {
        double total = 0.0;
        for (PåfyldningsComponent pc : påfyldningsComponenter) {
            total += pc.getVæskeMængde();
        }
        literPåfyldt = total;
        return total;
    }

    @Override
    public void setMængde(double mængde){
        literPåfyldt = mængde;
    }

    public List<PåfyldningsComponent> getPåfyldningsComponenter() {
        return påfyldningsComponenter;
    }

    public void setLiterPåfyldt(double literPåfyldt) {
        this.literPåfyldt = literPåfyldt;
    }

    public LocalDate getOmhældningsDatoer() {
        return omhældningsDatoer;
    }
}



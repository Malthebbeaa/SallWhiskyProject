package application.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class VæskeMix implements PåfyldningsComponent {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private Fad fad;
    private List<PåfyldningsComponent> påfyldningsComponenter;

    public VæskeMix(LocalDate påfyldningsDato, Fad fad) {
        this.påfyldningsDato = påfyldningsDato;
        this.fad = fad;
        this.literPåfyldt = 0;
        påfyldningsComponenter = new ArrayList<>();
    }

    /**
     * metode tjekker om aftapningen er mulig på mængden i fadet (påfyldning)
     * så trækker den væsken fra på påfyldningen
     * skaber forbindelsen mellem Påfyldning og Aftapning
     * til sidst aftapper den væsken som er væsken på påfyldningen
     * @param aftapning
     */
    public void aftapVæske(Aftapning aftapning){
        if (!aftapningGårIMinus(aftapning.getLiterAftappet())){
            literPåfyldt -= aftapning.getLiterAftappet();
            tilføjAftapning(aftapning);
            //TODO
            //aftapning.setPåfyldning(this);
            fad.aftapVæskePåFad(aftapning);
        } else {
            throw new RuntimeException("Du aftapper for meget fra fadet");
        }
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

    /***
     * Mængde klasse input
     * @param væske
     * @return true hvis tilføjelsen overgår fadets grænse
     */
    public boolean mængdenOverskriderFadKapacitet(Væske væske){
        return fad.påFyldningOvergårGrænse(væske.getMængde());
    }

    /***
     * double input
     * @param mængde
     * @return true hvis tilføjelsen overgår fadets grænse
     */
    public boolean mængdenOverskriderFadKapacitet(double mængde){
        return fad.påFyldningOvergårGrænse();
    }

    /***
     * Forbindelsen mellem Mængde og Påfyldning realiseres
     * mængdens væske tilføjes til påfyldningens totale væske
     * @param væske
     */
    public void tilføjVæske(Væske væske){
        if (!mængdenOverskriderFadKapacitet(væske)){
            væske.setPåfyldning(this);
            påfyldningsComponenter.add(væske);
            literPåfyldt += væske.getMængde();
        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
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

    public List<Væske> getVæskerFyldtPå() {
        return (List<Væske>) påfyldningsComponenter;
    }
    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public double getLiterPåfyldt() {
        return literPåfyldt;
    }

    public void setLiterPåfyldt(double literPåfyldt) {
        this.literPåfyldt = literPåfyldt;
    }

    public Fad getFad() {
        return fad;
    }

    @Override
    public String toString() {
        return "FadId" +fad.getFadId() + ", " + literPåfyldt + " L, evt år lagret";
    }

    @Override
    public void add(PåfyldningsComponent påfyldningsComponent) {
        if (!mængdenOverskriderFadKapacitet(påfyldningsComponent)){
            påfyldningsComponent.setPåfyldning(this);
            påfyldningsComponent.add(påfyldningsComponent);
            literPåfyldt += påfyldningsComponent.getMængde();
        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
    }

    @Override
    public void remove(PåfyldningsComponent påfyldningsComponent) {
        //TODO
    }

    @Override
    public PåfyldningsComponent getChild(int i) {
        //TODO
        return null;
    }
}

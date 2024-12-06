package application.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Påfyldning {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private Fad fad;
    private List<Mængde> mængderPåfyldt;
    private List<Aftapning> aftapninger;

    public Påfyldning(LocalDate påfyldningsDato, Fad fad) {
        this.påfyldningsDato = påfyldningsDato;
        this.fad = fad;
        this.literPåfyldt = 0;
        mængderPåfyldt = new ArrayList<>();
        aftapninger = new ArrayList<>();
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
            aftapning.setPåfyldning(this);
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
     * @param mængde
     * @return true hvis tilføjelsen overgår fadets grænse
     */
    public boolean mængdenOverskriderFadKapacitet(Mængde mængde){
        return fad.påFyldningOvergårGrænse(mængde.getMængde());
    }

    /***
     * double input
     * @param mængde
     * @return true hvis tilføjelsen overgår fadets grænse
     */
    public boolean mængdenOverskriderFadKapacitet(double mængde){
        return fad.påFyldningOvergårGrænse(mængde);
    }

    /***
     * Forbindelsen mellem Mængde og Påfyldning realiseres
     * mængdens væske tilføjes til påfyldningens totale væske
     * @param mængde
     */
    public void tilføjMængde(Mængde mængde){
        if (!mængdenOverskriderFadKapacitet(mængde)){
            mængde.setPåfyldning(this);
            mængderPåfyldt.add(mængde);
            literPåfyldt += mængde.getMængde();
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

    public List<Mængde> getMængderPåfyldt() {
        return mængderPåfyldt;
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

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    @Override
    public String toString() {
        return "FadId" +fad.getFadId() + ", " + literPåfyldt + " L, evt år lagret";
    }
}

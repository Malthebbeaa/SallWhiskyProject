package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillering {
    private int antalDestilleringer;
    private LocalDate startDato, slutDato;
    private double væskeMængde;
    private double alkoholProcent;
    private Maltbatch maltbatch;
    private Kommentar kommentar;
    private List<Mængde> mængderGivet;

    public Destillering(int antalDestilleringer, LocalDate startDato, LocalDate slutDato,
                        double væskeMængde, double alkoholProcent, Maltbatch maltbatch) {
        this.antalDestilleringer = antalDestilleringer;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.væskeMængde = væskeMængde;
        this.alkoholProcent = alkoholProcent;
        this.maltbatch = maltbatch;
        this.mængderGivet = new ArrayList<>();
    }

    public Mængde afgivVæske(double påfyldningsMængde){
        if (væskeMængde - påfyldningsMængde >= 0){
            Mængde mængde = new Mængde(påfyldningsMængde, this);
            væskeMængde -= påfyldningsMængde;
            mængderGivet.add(mængde);
            return mængde;
        } else {
            throw new RuntimeException("Ikke tilstrækkeligt væske i destilleringen");
        }
    }

    /***
     * tilføjer en evt kommentar til destilleringen
     * @param kommentar evt kommentar til destillering
     */
    public void setKommentar(Kommentar kommentar){
        this.kommentar = kommentar;
    }

    public int getAntalDestilleringer() {
        return antalDestilleringer;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getVæskeMængde() {
        return væskeMængde;
    }
    public void setVæskeMængde(double nyMængde){
        this.væskeMængde = nyMængde;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(double nyAlkoholProcent){
        this.alkoholProcent = nyAlkoholProcent;
    }

    public Maltbatch getMaltbatch() {
        return maltbatch;
    }
    public String getBatchNummer(){return maltbatch.getBatchNummer();}

    public Kommentar getKommentar() {
        return kommentar;
    }

    public List<Mængde> getMængderGivet() {return mængderGivet;}

    @Override
    public String toString() {
        return væskeMængde + "L, " + alkoholProcent + "%, MaltbatchNr: " + maltbatch.getBatchNummer();
    }
}

package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillering {
    private int antalDistilleringer;
    private LocalDate startDato, slutDato;
    private double væskeMængde;
    private double alkoholProcent;
    private Maltbatch maltbatch;
    private Kommentar kommentar;
    private List<Påfyldning> påfyldninger;

    public Destillering(int antalDistilleringer, LocalDate startDato, LocalDate slutDato,
                        double væskeMængde, double alkoholProcent, Maltbatch maltbatch) {
        this.antalDistilleringer = antalDistilleringer;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.væskeMængde = væskeMængde;
        this.alkoholProcent = alkoholProcent;
        this.maltbatch = maltbatch;
        this.påfyldninger = new ArrayList<>();
    }

    public void lavPåfyldning(Fad fad, LocalDate påFyldningsDato, double påfyldningsMængde){
        Påfyldning påfyldning = new Påfyldning(påFyldningsDato, påfyldningsMængde);
        påfyldning.fyldPåFad(fad);
        påfyldninger.add(påfyldning);
    }

    /***
     * tilføjer en evt kommentar til distilleringen
     * @param kommentar evt kommentar til distillering
     */
    public void setKommentar(Kommentar kommentar){
        this.kommentar = kommentar;
    }

    public int getAntalDistilleringer() {
        return antalDistilleringer;
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

    public Kommentar getKommentar() {
        return kommentar;
    }
}

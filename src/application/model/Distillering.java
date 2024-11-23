package application.model;

import java.time.LocalDate;
import java.util.List;

public class Distillering {
    private int antalDistilleringer;
    private LocalDate startDato, slutDato;
    private double væskeMængde;
    private double alkoholProcent;
    private List<Maltbatch> maltbatches;
    private Kommentar kommentar;

    public Distillering(int antalDistilleringer, LocalDate startDato, LocalDate slutDato,
                        double væskeMængde, double alkoholProcent, List<Maltbatch> maltbatches) {
        this.antalDistilleringer = antalDistilleringer;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.væskeMængde = væskeMængde;
        this.alkoholProcent = alkoholProcent;
        this.maltbatches = maltbatches;
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

    public List<Maltbatch> getMaltbatches() {
        return maltbatches;
    }

    public Kommentar getKommentar() {
        return kommentar;
    }
}

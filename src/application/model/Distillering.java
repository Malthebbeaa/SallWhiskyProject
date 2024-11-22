package application.model;

import java.time.LocalDate;

public class Distillering {
    private int antalDistilleringer;
    private LocalDate startDato, slutDato;
    private double væskeMængde;
    private double alkoholProcent;
    private Maltbatch maltBatch;
    private Kommentar kommentar;

    public Distillering(int antalDistilleringer, LocalDate startDato, LocalDate slutDato,
                        double væskeMængde, double alkoholProcent, Maltbatch maltBatch) {
        this.antalDistilleringer = antalDistilleringer;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.væskeMængde = væskeMængde;
        this.alkoholProcent = alkoholProcent;
        this.maltBatch = maltBatch;
    }

    /***
     * tilføjer en evt kommentar til distilleringen
     * @param kommentar evt kommentar til distillering
     */
    public void setKommentar(Kommentar kommentar){
        this.kommentar = kommentar;
    }

}

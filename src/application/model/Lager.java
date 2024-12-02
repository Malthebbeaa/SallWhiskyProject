package application.model;

import application.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Lager {
    private String navn;
    private String vejnavn;
    private String postnummer;
    private String by;
    private int reolCounter = 1;
    private ObservableList<Reol> reoler;

    public Lager(String navn, String vejnavn, String postnummer, String by) {
        this.navn = navn;
        this.vejnavn = vejnavn;
        this.postnummer = postnummer;
        this.by = by;
        reoler = FXCollections.observableArrayList();
    }

    public Reol tilføjReol() {
        Reol reol = new Reol(reolCounter, this);
        reoler.add(reol);
        reolCounter++;
        return reol;
    }

    public ObservableList<Reol> getReoler() {
        return FXCollections.unmodifiableObservableList(reoler);
    }

    /**
     * Looper igennem hele lageret, spørger alle pladser om de er ledige.
     * Hver gang den finder en ledig plads tæller den en op.
     * @return int antal ledige pladser
     */
    public int ledigePladser() {
        int count = 0;
        for (Reol reol : reoler) {
            for (Hylde hylde : reol.getHylder()) {
                for (Plads plads : hylde.getPladser()) {
                    if (plads.isLedig()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return navn;
    }

}

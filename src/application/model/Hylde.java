package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNummer;
    private ObservableList<Plads> pladser;
    private Reol reol;

    public Hylde(int hyldeNummer, Reol reol) {
        this.hyldeNummer = hyldeNummer;
        pladser = FXCollections.observableArrayList();
        this.reol = reol;
    }

    //Create Metode
    public void tilføjPlads(int antal){
        for (int i = 1; i <= antal ; i++) {
            Plads plads = new Plads(i,true,this);
            pladser.add(plads);
        }
    }

    public int getHyldeNummer() {
        return hyldeNummer;
    }

    public ObservableList<Plads> getPladser() {
        return FXCollections.unmodifiableObservableList(pladser);
    }

    @Override
    public String toString() {
        return "Hylde: " + hyldeNummer;
    }

    public Reol getReol() {
        return reol;
    }
}

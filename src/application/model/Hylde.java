package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNummer;
    private ObservableList<Plads> pladser;

    public Hylde(int hyldeNummer) {
        this.hyldeNummer = hyldeNummer;
        pladser = FXCollections.observableArrayList();
    }

    public void tilføjPlads(int antal){
        for (int i = 1; i <= antal ; i++) {
            Plads plads = new Plads(i,true);
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
        return "Hylde nummer: " + hyldeNummer;
    }
}

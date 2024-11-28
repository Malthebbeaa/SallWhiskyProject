package application.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Reol {
    private int reolNummer;
    private int hyldeCounter = 1;
    private ObservableList<Hylde> hylder;

    public Reol(int reolNummer) {
        this.reolNummer = reolNummer;
        hylder = FXCollections.observableArrayList();
    }

    public void tilføjHylde(int antalHylder, int antalPladser){
        for (int i = 1; i <= antalHylder; i++) {
            Hylde hylde = new Hylde(hyldeCounter);
            hyldeCounter++;
            hylder.add(hylde);
            hylde.tilføjPlads(antalPladser);
        }
    }

    public int getReolNummer() {
        return reolNummer;
    }

    public ObservableList<Hylde> getHylder() {
        return FXCollections.unmodifiableObservableList(hylder);
    }

    @Override
    public String toString() {
        return "Reol nummer: " + reolNummer;
    }
}

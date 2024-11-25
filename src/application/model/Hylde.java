package application.model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNummer;
    private ArrayList<Plads> pladser;

    public Hylde(int hyldeNummer) {
        this.hyldeNummer = hyldeNummer;
        pladser = new ArrayList<>();
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

    public ArrayList<Plads> getPladser() {
        return pladser;
    }

    @Override
    public String toString() {
        return "Hylde nummer: " + hyldeNummer;
    }
}

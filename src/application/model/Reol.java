package application.model;


import java.util.ArrayList;

public class Reol {
    private int reolNummer;
    private ArrayList<Hylde> hylder;

    public Reol(int reolNummer) {
        this.reolNummer = reolNummer;
        hylder = new ArrayList<Hylde>();
    }

    public void tilføjHylde(int antalHylder, int antalPladser){
        for (int i = 1; i <= antalHylder; i++) {
            Hylde hylde = new Hylde(i);
            hylder.add(hylde);
            hylde.tilføjPlads(antalPladser);
        }
    }

    public int getReolNummer() {
        return reolNummer;
    }

    public ArrayList<Hylde> getHylder() {
        return hylder;
    }

    @Override
    public String toString() {
        return "Reol nummer: " + reolNummer;
    }
}

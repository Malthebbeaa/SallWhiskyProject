package application.model;

import java.util.ArrayList;

public class Lager {
    private String navn;
    private String vejnavn;
    private String postnummer;
    private String by;
    private ArrayList<Reol> reoler;

    public Lager(String navn, String vejnavn, String postnummer, String by) {
        this.navn = navn;
        this.vejnavn = vejnavn;
        this.postnummer = postnummer;
        this.by = by;
        reoler = new ArrayList<Reol>();
    }

    public void tilføjReol(int antalReoler, int antalHylder, int antalPladser) {
        for (int i = 1; i <= antalReoler; i++) {
            Reol reol = new Reol(i);
            reol.tilføjHylde(antalHylder, antalPladser);
            reoler.add(reol);
        }
    }

    public ArrayList<Reol> getReoler() {
        return reoler;
    }

    public int ledigePladser(){
        int count = 0;
        for (Reol reol : reoler) {
            for (Hylde hylde : reol.getHylder()) {
                for (Plads plads : hylde.getPladser()) {
                    if(plads.isLedig()){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return navn + " antal ledige pladser: " + ledigePladser();
    }
}

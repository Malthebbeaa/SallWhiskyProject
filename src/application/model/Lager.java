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

    public ArrayList<Reol> getReoler() {
        return reoler;
    }
}

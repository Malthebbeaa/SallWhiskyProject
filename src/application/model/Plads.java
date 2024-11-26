package application.model;

public class Plads {
    private int pladsNummer;
    private boolean ledig;

    public Plads(int pladsNummer, boolean ledig) {
        this.pladsNummer = pladsNummer;
        this.ledig = ledig;
    }

    public int getPladsNummer() {
        return pladsNummer;
    }

    public boolean isLedig() {
        return ledig;
    }

    public void setLedig(boolean ledig) {
        this.ledig = ledig;
    }

    @Override
    public String toString() {
        return "Plads nummer: " + pladsNummer + (ledig? "tom" : "optaget");
    }
}

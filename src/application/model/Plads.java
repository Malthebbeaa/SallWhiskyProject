package application.model;

public class Plads {
    private int pladsNummer;
    private boolean ledig;
    private Fad fad;
    private Hylde hylde;

    public Plads(int pladsNummer, boolean ledig, Hylde hylde) {
        this.pladsNummer = pladsNummer;
        this.ledig = ledig;
        this.hylde = hylde;
    }

    public Fad getFad() {
        return fad;
    }

    public void setFad(Fad fad) {
        if (this.fad != fad) {
            this.fad = fad;
            if(fad != null) {
                fad.setPlads(this);
            }
        }
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
        return "Plads: " + pladsNummer + (ledig ? " ledig" : " optaget");
    }

    public Hylde getHylde() {
        return hylde;
    }
}

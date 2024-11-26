package application.model;

public class Maltbatch {
    private String batchNummer;
    private double mængde;

    private Rygemateriale rygemateriale;
    //Tvunget association --> 1 Korn
    private Korn korn;

    public Maltbatch(String batchNummer, double mængde, Korn korn) {
        this.batchNummer = batchNummer;
        this.mængde = mængde;
        this.korn = korn;
    }

    public Korn getKorn() {
        return korn;
    }

    public Rygemateriale getRygemateriale() {
        return rygemateriale;
    }

    /**
     * Sætter dette rygemateriale som værende det materiale dette batch er røget med.
     * @param rygemateriale materiale som er brugt til rygning af malten
     */
    public void setRygemateriale(Rygemateriale rygemateriale) {
        if (this.rygemateriale != rygemateriale) {
            this.rygemateriale = rygemateriale;
        }
    }

    @Override
    public String toString() {
        return batchNummer + ", " + mængde + "kg, " + ", " + korn.getSort();
    }

    public String getBatchNummer() {
        return batchNummer;
    }
}

package application.model;

public class Maltbatch {
    private String batchNummer;
    private int mængde;

    private Rygemateriale rygemateriale;
    //Tvunget association --> 1 Korn
    private Korn korn;

    public Maltbatch(String batchNummer, int mængde, Korn korn) {
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
}

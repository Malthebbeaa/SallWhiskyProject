package application.model;

public class Mængde {
    private double mængde;
    private Destillering destillering;
    private Fad fad;
    private Påfyldning påfyldning;
    private Aftapning aftapning;

    public Mængde(double mængde, Destillering destillering) {
        this.mængde = mængde;
        this.destillering = destillering;
    }
    public Mængde(double mængde, Fad fad) {
        this.mængde = mængde;
        this.fad = fad;
    }

    public void setPåfyldning(Påfyldning påfyldning){
        if (påfyldning != null){
            this.påfyldning = påfyldning;
        }
    }

    public Aftapning getAftapning() {
        return aftapning;
    }

    public void setAftapning(Aftapning aftapning){
        if (aftapning != null){
            this.aftapning = aftapning;
        }
    }

    public double getMængde() {
        return mængde;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public Påfyldning getPåfyldning() {
        return påfyldning;
    }
}

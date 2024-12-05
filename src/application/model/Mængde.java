package application.model;

public class Mængde {
    private double mængde;
    private Destillering destillering;
    private Påfyldning påfyldning;

    public Mængde(double mængde, Destillering destillering) {
        this.mængde = mængde;
        this.destillering = destillering;
        this.destillering.tilføjMængdeGivet(this);
    }

    public void setPåfyldning(Påfyldning påfyldning){
        if (påfyldning != null){
            this.påfyldning = påfyldning;
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

package application.model;

public class Væske extends PåfyldningsComponent {
    private double mængde;
    private Destillering destillering;
    private PåfyldningsComponent påfyldningsComponent;

    public Væske(double mængde, Destillering destillering) {
        this.mængde = mængde;
        this.destillering = destillering;
        destillering.givVæske(this);
    }

    public void setVæskeMix(PåfyldningsComponent påfyldningsComponent){
        if (påfyldningsComponent != null){
            this.påfyldningsComponent = påfyldningsComponent;
        }
    }

    public double getVæskeMængde() {
        return mængde;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public PåfyldningsComponent getPåfyldning() {
        return påfyldningsComponent;
    }

    @Override
    public String toString() {
        return "Væske{}" + mængde + " " + destillering;
    }
}

package application.model;

public class Væske implements PåfyldningsComponent {
    private double mængde;
    private Destillering destillering;
    private PåfyldningsComponent påfyldningsComponent;

    public Væske(double mængde, Destillering destillering) {
        this.mængde = mængde;
        this.destillering = destillering;
    }

    public void setPåfyldning(PåfyldningsComponent påfyldningsComponent){
        if (påfyldningsComponent != null){
            this.påfyldningsComponent = påfyldningsComponent;
        }
    }

    public double getMængde() {
        return mængde;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public PåfyldningsComponent getPåfyldning() {
        return påfyldningsComponent;
    }

    @Override
    public void add(PåfyldningsComponent påfyldningsComponent) {
        //TODO
    }

    @Override
    public void remove(PåfyldningsComponent påfyldningsComponent) {
        //TODO
    }

    @Override
    public PåfyldningsComponent getChild(int i) {
        //TODO
        return null;
    }
}

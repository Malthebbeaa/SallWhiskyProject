package application.model;

public abstract class PåfyldningsComponent {
    public void add(PåfyldningsComponent påfyldningsComponent){}
    public void remove(PåfyldningsComponent påfyldningsComponent){}
    public PåfyldningsComponent getChild(int i){
        return null;
    }
    public double getVæskeMængde(){
        return 0.0;
    }
}

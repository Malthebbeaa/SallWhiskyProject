package application.model;

public interface PåfyldningsComponent {
    public void add(PåfyldningsComponent påfyldningsComponent);
    public void remove(PåfyldningsComponent påfyldningsComponent);
    public PåfyldningsComponent getChild(int i);
}

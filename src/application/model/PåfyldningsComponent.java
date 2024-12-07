package application.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public abstract class PåfyldningsComponent {
    public void add(PåfyldningsComponent påfyldningsComponent) {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public void remove(PåfyldningsComponent påfyldningsComponent) {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public PåfyldningsComponent getChild(int i) {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public double getVæskeMængde() {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public void aftapVæske(Aftapning aftapning) {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public double getLiterPåfyldt(){
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public Fad getFad(){
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public List<PåfyldningsComponent> getPåfyldningsComponenter(){
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public LocalDate getPåfyldningsDato(){
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public Destillering getDestillering(){
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }
    public Period antalÅrPåFad(LocalDate date){
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public boolean klarTilAftapning(LocalDate now) {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }

    public String getAntalÅrMånederDage() {
        throw new UnsupportedOperationException("Operation ikke understøttet.");
    }
}

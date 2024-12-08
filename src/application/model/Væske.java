package application.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Væske extends PåfyldningsComponent {
    private double mængde;
    private Destillering destillering;
    private PåfyldningsComponent påfyldningsComponent;

    public Væske(double mængde, Destillering destillering) {
        this.mængde = mængde;
        this.destillering = destillering;
        destillering.givVæske(this);
    }

    public Væske(Destillering destillering, double mængde){
        this.mængde = mængde;
        this.destillering = destillering;
    }

    public double getVæskeMængde() {
        return mængde;
    }

    public void setMængde(double mængde) {
        this.mængde = mængde;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public PåfyldningsComponent getPåfyldning() {
        return påfyldningsComponent;
    }

    @Override
    public String toString() {
        return "Væske: " + mængde + "L";
    }
}

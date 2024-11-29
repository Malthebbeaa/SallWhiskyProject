package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Påfyldning {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private Fad fad;
    private List<Mængde> mængderPåfyldt;

    public Påfyldning(LocalDate påfyldningsDato) {
        this.påfyldningsDato = påfyldningsDato;
        this.literPåfyldt = 0;
        mængderPåfyldt = new ArrayList<>();
    }

    public void tilføjMængde(Mængde mængde){
        double totalMedTilføjelse = literPåfyldt + mængde.getMængde();
        double fremtidigeTotal = totalMedTilføjelse + fad.getMængdeFyldtPåFad();

        if (fremtidigeTotal <= fad.getStørrelse()){
            mængde.setPåfyldning(this);
            mængderPåfyldt.add(mængde);
            literPåfyldt = totalMedTilføjelse;
        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
    }

    public void setFad(Fad fad){
        if (fad != null){
            this.fad = fad;
        }
    }
    public List<Mængde> getMængderPåfyldt() {
        return mængderPåfyldt;
    }
    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public double getLiterPåfyldt() {
        return literPåfyldt;
    }

    public void setLiterPåfyldt(double literPåfyldt) {
        this.literPåfyldt = literPåfyldt;
    }

    public Fad getFad() {
        return fad;
    }
}

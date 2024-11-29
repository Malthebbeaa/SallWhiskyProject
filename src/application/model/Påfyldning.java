package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Påfyldning {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private Fad fad;
    private List<Mængde> mængderPåfyldt;

    public Påfyldning(LocalDate påfyldningsDato, Fad fad) {
        this.påfyldningsDato = påfyldningsDato;
        this.fad = fad;
        this.literPåfyldt = 0;
        mængderPåfyldt = new ArrayList<>();
    }

    public boolean mængdenOverskriderFadKapacitet(Mængde mængde){
        double totalMedTilføjelse = literPåfyldt + mængde.getMængde();
        double fremtidigeTotal = totalMedTilføjelse + fad.getMængdeFyldtPåFad();

        return fremtidigeTotal > fad.getStørrelse();

    }

    public boolean mængdenOverskriderFadKapacitet(double mængde){
        double totalMedTilføjelse = literPåfyldt + mængde;
        double fremtidigeTotal = totalMedTilføjelse + fad.getMængdeFyldtPåFad();

        return fremtidigeTotal > fad.getStørrelse();

    }
    public void tilføjMængde(Mængde mængde){
        if (!mængdenOverskriderFadKapacitet(mængde)){
            mængde.setPåfyldning(this);
            mængderPåfyldt.add(mængde);
            literPåfyldt = literPåfyldt + mængde.getMængde();
        } else {
            throw new RuntimeException("Du overskrider fadets kapacitet");
        }
    }

    public void fjernMængde(Mængde mængde){
        if (mængde != null){
            mængderPåfyldt.remove(mængde);
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

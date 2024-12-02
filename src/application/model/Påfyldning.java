package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Påfyldning {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private Fad fad;
    private List<Mængde> mængderPåfyldt;
    private List<Aftapning> aftapninger;

    public Påfyldning(LocalDate påfyldningsDato, Fad fad) {
        this.påfyldningsDato = påfyldningsDato;
        this.fad = fad;
        this.literPåfyldt = 0;
        mængderPåfyldt = new ArrayList<>();
        aftapninger = new ArrayList<>();
    }

    public void aftapVæske(Aftapning aftapning){
        if (!aftapningGårIMinus(aftapning.getLiterAftappet())){
            literPåfyldt -= aftapning.getLiterAftappet();
            tilføjAftapning(aftapning);
        } else {
            throw new RuntimeException("Du aftapper for meget fra fadet");
        }
    }

    public boolean aftapningGårIMinus(double mængde) {
        double fremtidigeTotal = literPåfyldt - mængde;
        return fremtidigeTotal < 0;
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

    public void tilføjAftapning(Aftapning aftapning){
        if (!aftapninger.contains(aftapning)){
            aftapninger.add(aftapning);
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

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    @Override
    public String toString() {
        return "FadId" +fad.getFadId() + ", " + literPåfyldt + " L, evt år lagret";
    }
}

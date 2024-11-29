package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aftapning {
    public LocalDate aftapningsDato;
    public Double literAftappet;
    public Double alkoholProcent;
    private Fad fad;
    private List<Mængde> mængderAftappet;


    public Aftapning(LocalDate aftapningsDato, Double alkoholProcent, Fad fad) {
        this.aftapningsDato = aftapningsDato;
        this.literAftappet = 0.0;
        this.alkoholProcent = alkoholProcent;
        this.fad = fad;
        mængderAftappet = new ArrayList<>();
    }


    public void aftapMængde(Mængde mængde){
        double totalEfterTap = literAftappet + mængde.getMængde();
        double fremtidigeTotal = totalEfterTap - fad.getMængdeFyldtPåFad();

        if (fremtidigeTotal <= 0){
            mængde.setAftapning(this);
            mængderAftappet.add(mængde);
            literAftappet = totalEfterTap;
        } else {
            throw new RuntimeException("Det er ikke muligt at tappe så meget");
        }
    }

    public LocalDate getAftapningsDato() {
        return aftapningsDato;
    }

    public Double getLiterAftappet() {
        return literAftappet;
    }

    public Double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setFad(Fad fad) {
        if (fad != null){
            this.fad = fad;
        }
    }
}

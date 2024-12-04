package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aftapning {
    private LocalDate aftapningsDato;
    private Double literAftappet;
    private Double alkoholProcent;
    private Påfyldning påfyldning;



    public Aftapning(Double literAftappet, Double alkoholProcent) {
        this.literAftappet = literAftappet;
        this.alkoholProcent = alkoholProcent;
    }

    public void aftapMængde(double mængde) {
        double totalEfterTap = getLiterAftappet() + mængde;
        double fremtidigeTotal = totalEfterTap - påfyldning.getLiterPåfyldt();

        if (fremtidigeTotal <= 0) {
            // LAVES

        } else {
            throw new RuntimeException("Det er ikke muligt at tappe så meget");
        }}
    public void setPåfyldning(Påfyldning påfyldning) {
        if (this.påfyldning != påfyldning){
            this.påfyldning = påfyldning;
        }
    }

    public Påfyldning getPåfyldning() {
        return påfyldning;
    }

    public LocalDate getAftapningsDato() {
        return aftapningsDato;
    }

    public void setLiterAftappet(Double literAftappet) {
        this.literAftappet = literAftappet;
    }

    public Double getLiterAftappet() {
        return literAftappet;
    }

    public Double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAftapningsDato(LocalDate aftapningsDato) {
        this.aftapningsDato = aftapningsDato;
    }



    public void setAlkoholProcent(Double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }


}
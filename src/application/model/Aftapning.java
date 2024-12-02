package application.model;

import java.time.LocalDate;

public class Aftapning {
    public LocalDate aftapningsDato;
    public Double literAftappet;
    public boolean fortyndet;
    public Double alkoholProcent;

    private Påfyldning påfyldning;
    public Aftapning(Double literAftappet, Double alkoholProcent) {
        this.literAftappet = literAftappet;
        this.alkoholProcent = alkoholProcent;
    }

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

    public Double getLiterAftappet() {
        return literAftappet;
    }

    public boolean isFortyndet() {
        return fortyndet;
    }

    public Double getAlkoholProcent() {
        return alkoholProcent;
    }
}

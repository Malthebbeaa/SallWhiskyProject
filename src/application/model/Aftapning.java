package application.model;

import java.time.LocalDate;

public class Aftapning {
    private LocalDate aftapningsDato;
    private double literAftappet;
    private double alkoholProcent;
    private PåfyldningsComponent påfyldningsComponent;
    private WhiskyProdukt whiskyProdukt;

    public Aftapning(double literAftappet, double alkoholProcent) {
        this.literAftappet = literAftappet;
        this.alkoholProcent = alkoholProcent;
    }

    public void setWhiskyProdukt(WhiskyProdukt whiskyProdukt){
        if (this.whiskyProdukt != whiskyProdukt){
            this.whiskyProdukt = whiskyProdukt;
        }
    }
    public void setPåfyldning(PåfyldningsComponent påfyldning) {
        if (this.påfyldningsComponent != påfyldning){
            this.påfyldningsComponent = påfyldning;
        }
    }

    public PåfyldningsComponent getPåfyldning() {
        return påfyldningsComponent;
    }

    public LocalDate getAftapningsDato() {
        return aftapningsDato;
    }

    public double getLiterAftappet() {
        return literAftappet;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }
}

package application.model;

import java.time.LocalDate;

public class Aftapning {
    private LocalDate aftapningsDato;
    private Double literAftappet;
    private Double alkoholProcent;
    private Påfyldning påfyldning;
    private WhiskyProdukt whiskyProdukt;

    public Aftapning(Double literAftappet, Double alkoholProcent) {
        this.literAftappet = literAftappet;
        this.alkoholProcent = alkoholProcent;
    }

    public void setWhiskyProdukt(WhiskyProdukt whiskyProdukt){
        if (this.whiskyProdukt != whiskyProdukt){
            this.whiskyProdukt = whiskyProdukt;
        }
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

    public Double getAlkoholProcent() {
        return alkoholProcent;
    }
}

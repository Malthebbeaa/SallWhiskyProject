package application.model;

import java.time.LocalDate;

public class Aftapning {
    public LocalDate aftapningsDato;
    public Double literAftappet;
    public Double alkoholProcent;
    private Fad fad;

    public Aftapning(LocalDate aftapningsDato, Double literAftappet, Double alkoholProcent, Fad fad) {
        this.aftapningsDato = aftapningsDato;
        this.literAftappet = literAftappet;
        this.alkoholProcent = alkoholProcent;
        this.fad = fad;
    }

    public void AftapningAfFad() {
        fad.AftapWhisky(literAftappet);
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

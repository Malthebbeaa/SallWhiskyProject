package application.model;

import java.time.LocalDate;

public class Aftapning {
    public LocalDate aftapningsDato;
    public Double literAftappet;
    public boolean fortyndet;
    public Double alkoholProcent;
    private Fad fad;

    public Aftapning(LocalDate aftapningsDato, Double literAftappet, boolean fortyndet, Double alkoholProcent, Fad fad) {
        this.aftapningsDato = aftapningsDato;
        this.literAftappet = literAftappet;
        this.fortyndet = fortyndet;
        this.alkoholProcent = alkoholProcent;
        this.fad = fad;
    }

    public void AftapningAfFad() {
        fad.AftapWhisky(literAftappet);
    }
}

package application.model;

import java.util.ArrayList;
import java.util.List;

public class WhiskyProdukt {
    public String AarLagret;
    public String navn;
    public String whiskytype;
    private List<Fad> fade;


    public WhiskyProdukt(String aarLagret, String navn, String whiskytype) {
        AarLagret = aarLagret;
        this.navn = navn;
        this.whiskytype = whiskytype;
    }

    public void tilføjFade(Fad fad) {
        fade.add(fad);
    }
    public List<Fad> getFade() {
        return fade;
    }

//    public Mark getMark() {
//        return Påfyldning.getDestillering().getKorn().getMark();
//    }
//
//    public Korn getKorn() {
//        return Påfyldning.getDestillering().getKorn();
//    }
//
//    public String getMaltbatch() {
//        return Påfyldning.getDestillering().getBatchNummer();
//    }

    public String getAarLagret() {
        return AarLagret;
    }
}

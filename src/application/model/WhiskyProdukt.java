package application.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class WhiskyProdukt {
    private int AarLagret;
    private String navn;
    private String whiskytype;
    private List<Fad> fade;


    public WhiskyProdukt(int aarLagret, String navn, String whiskytype) {
        this.AarLagret = aarLagret;
        this.navn = navn;
        this.whiskytype = whiskytype;
    }

    public int getAarLagret(Aftapning aftapning, Påfyldning påfyldning) {
        LocalDate a = påfyldning.getPåfyldningsDato();
        LocalDate b = aftapning.getAftapningsDato();
        int diff = b.getYear() - a.getYear();
        if (a.getDayOfYear() - b.getDayOfYear() >= 0) {
            diff--;
        }
        return diff;
    }
    public String getWhiskytype(Aftapning aftapning, WhiskyProdukt whiskyProdukt) {
        String whiskytype = null;
        if (!aftapning.fortyndet && whiskyProdukt.getFade().size() < 2) {
            whiskytype = "Single Cask";
        } else if (aftapning.fortyndet && whiskyProdukt.getFade().size() < 2) {
            whiskytype = "Cask Strenght";
        } else if (!aftapning.fortyndet && whiskyProdukt.getFade().size() > 1) {
            whiskytype = "Cask Strenght Single Malt";
        } else if (aftapning.fortyndet && whiskyProdukt.getFade().size() > 1) {
            whiskytype = "Single Malt";
        }
        return whiskytype;
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


}

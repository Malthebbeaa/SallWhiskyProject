package application.model;

import java.util.ArrayList;
import java.util.List;

public class WhiskyProdukt {
    String navn;
    Double alkoholProcent;
    int antalFlasker;
    String whiskytype;
    Double literVand;
    public Historie historie;
    private List<Fad> fade;

    Maltbatch maltbatch;


    public WhiskyProdukt(String navn, Double alkoholProcent, int antalFlasker, String whiskytype,
                         Double literVand, Historie historie, List<Fad> fad) {
        this.navn = navn;
        this.alkoholProcent = alkoholProcent;
        this.antalFlasker = antalFlasker;
        this.whiskytype = whiskytype;
        this.literVand = literVand;
        this.historie = historie;
        this.fade = new ArrayList<>();
    }


    public void tilføjFade(Fad fad) {
        fade.add(fad);
    }

    public Historie getHistorie() {
        return historie;
    }

    public void setHistorie(Historie historie) {
        this.historie = historie;
    }

    public List<Fad> getFade() {
        return fade;
    }

    public Mark getMark() {
        return maltbatch.getKorn().getMark();
    }

    public Korn getKorn() {
        return maltbatch.getKorn();
    }

    public String getMaltbatch(){
        return maltbatch.getBatchNummer();
    }
}

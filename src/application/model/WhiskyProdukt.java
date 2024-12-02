package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WhiskyProdukt {
    private int AarLagret;
    private LocalDate opretDato;
    private String navn;
    private String whiskytype;
    private List<Aftapning> aftapninger;
    private double totalWhiskyMængde;
    private double endeligAlkoholProcent;
    private double vandMængde;

    public WhiskyProdukt(String navn, LocalDate opretDato) {
        this.navn = navn;
        this.opretDato = opretDato;
        aftapninger = new ArrayList<>();
    }

    public void tilføjAftapning(Aftapning aftapning) {
        if (!aftapninger.contains(aftapning)){
            aftapninger.add(aftapning);
            totalWhiskyMængde += aftapning.getLiterAftappet();
        }
    }

    public void tilføjVand(double vandMængde){
        if (vandMængde > 0){
            totalWhiskyMængde += vandMængde;
            this.vandMængde = vandMængde;
        } else {
            throw new RuntimeException("Mængden skal være positiv");
        }
    }

    public double beregnAlkoholProcentUdenVand(){
        double volumeGangeAlkoholprocent = 0;
        double samledeVolume = 0;


        for (Aftapning aftapning : aftapninger) {
            volumeGangeAlkoholprocent += aftapning.getAlkoholProcent() * aftapning.getLiterAftappet();
            samledeVolume += aftapning.getLiterAftappet();
        }


        return volumeGangeAlkoholprocent / samledeVolume;
    }

    public double beregnSamledeAlkoholProcent(){
        if (vandMængde > 0){
            double alkoholMængde = totalWhiskyMængde * (beregnAlkoholProcentUdenVand() / 100);
            double samletVolumen = totalWhiskyMængde + vandMængde;
            return (alkoholMængde / samletVolumen) * 100;
        }

        return beregnSamledeAlkoholProcent();
    }
    public List<Aftapning> getAftapninger() {
        return aftapninger;
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

    public LocalDate getOpretDato() {
        return opretDato;
    }

    public String getNavn() {
        return navn;
    }

    public String getWhiskytype() {
        return whiskytype;
    }

    public double getTotalWhiskyMængde() {
        return totalWhiskyMængde;
    }

    /*
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

     */





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

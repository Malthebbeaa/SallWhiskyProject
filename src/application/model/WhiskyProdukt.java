package application.model;

import java.time.LocalDate;
import java.util.List;

public class WhiskyProdukt {
    private int AarLagret;
    private String navn;
    private String whiskytype;
    private boolean fortyndet;
    private List<Fad> fade;


    public WhiskyProdukt(int aarLagret, String navn, String whiskytype, boolean fortyndet) {
        this.AarLagret = aarLagret;
        this.navn = navn;
        this.whiskytype = whiskytype;
        this.fortyndet = fortyndet;
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
        if (!fortyndet && whiskyProdukt.getFade().size() < 2) {
            whiskytype = "Single Cask";
        } else if (fortyndet && whiskyProdukt.getFade().size() < 2) {
            whiskytype = "Cask Strenght";
        } else if (!fortyndet && whiskyProdukt.getFade().size() > 1) {
            whiskytype = "Cask Strenght Single Malt";
        } else if (fortyndet && whiskyProdukt.getFade().size() > 1) {
            whiskytype = "Single Malt";
        }
        return whiskytype;
    }

    public String skrivHistorie() {
        return
        "Vores whisky " + navn + " er lavet af ";
//                + .getKorn()
//                + " fra vores medejer Lars mark " + produkt.getMark() + ". ");
//        if (produkt.getFade().size() > 1) {
//            System.out.println("Den er lagret på " + produkt.getFade().size() + " fade ");
//        } else {
//            System.out.println("Den er lagret på fad fra " + produkt.getLand());
//        }
//        if (produkt.getFade().indexOf(0).getTidligereIndhold() != produkt.getFade().indexOf(1).getTidligereIndhold()) {
//            System.out.println(", der tidligere har været brugt til at lagre ");
//            for (int i = produkt.getFade().size() + 1; i == produkt.getFade().size() - 1; i++) {
//                System.out.println(produkt.getFade().getFirst().getTidligereIndhold() + " og "
//                        + produkt.getFade().getLast().getTidligereIndhold());
//            }
//        } else {
//            System.out.println(produkt.getFade().indexOf(0).getTidligereIndhold());
//        }
//        System.out.println(". Derefter er det blevet lagret i " + produkt.getAarLagret()
//                + " og hældt på flaske den " + produkt.getDatoTappet());
//        System.out.println("\n. Der er blevet tappet " + produkt.getAntalFlasker());

    }


    public void tilføjFade(Fad fad) {
        fade.add(fad);
    }

    public List<Fad> getFade() {
        return fade;
    }

    public String getNavn() {
        return navn;
    }

    public String getMark() {
        return "Lort";
    }

    public String getKorn() {
        return "Skrald";
    }
//
//    public String getMaltbatch() {
//        return Påfyldning.getDestillering().getBatchNummer();
//    }


}

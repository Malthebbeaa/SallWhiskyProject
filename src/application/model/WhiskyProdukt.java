package application.model;

import java.time.LocalDate;
import java.util.List;

public class WhiskyProdukt {
    private int AarLagret;
    private String navn;
    private String whiskytype;
    private double vand;
    private int antalFlasker;
    private List<Aftapning> aftapninger;


    public WhiskyProdukt(double vand, int antalFlasker) {
        this.vand = vand;
        this.antalFlasker = antalFlasker;
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

    public String getWhiskytype(Aftapning aftapning) {
        String whiskytype = null;
        if (vand != 0 && aftapninger.size() < 2) {
            whiskytype = "Single Cask";
        } else if (vand == 0 && aftapninger.size() < 2) {
            whiskytype = "Cask Strenght";
        } else if (vand != 0 && aftapninger.size() > 1) {
            whiskytype = "Cask Strenght Single Malt";
        } else if (vand == 0 && aftapninger.size() > 1) {
            whiskytype = "Single Malt";
        }
        return whiskytype;
    }


    public String lavHistorie() {
        return
                "Vores whisky " + navn + " er lavet af "
//                + .getKorn()
//                + " fra vores medejer Lars mark " + produkt.getMark() + ". ");
//        if (produkt.getFade().size() > 1) {
                        + "Den er lagret på " + aftapninger.size() + " fade ";
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


    public int getAarLagret() {
        return AarLagret;
    }

    public void setAarLagret(int aarLagret) {
        AarLagret = aarLagret;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getWhiskytype() {
        return whiskytype;
    }

    public void setWhiskytype(String whiskytype) {
        this.whiskytype = whiskytype;
    }

    public double getVand() {
        return vand;
    }

    public void setVand(double vand) {
        this.vand = vand;
    }

    public int getAntalFlasker() {
        return antalFlasker;
    }

    public void setAntalFlasker(int antalFlasker) {
        this.antalFlasker = antalFlasker;
    }

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    public void setAftapninger(List<Aftapning> aftapninger) {
        this.aftapninger = aftapninger;
    }
}

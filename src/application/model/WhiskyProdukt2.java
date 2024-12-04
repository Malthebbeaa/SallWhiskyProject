package application.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WhiskyProdukt2 {
    private int AarLagret;
    private LocalDate opretDato;
    private String navn;
    private String whiskytype;
    private double vand;
    private int antalFlasker;
    private List<Aftapning> aftapninger = new ArrayList<>();
    private double totalWhiskyMængde;
    private double endeligAlkoholProcent;
    private double vandMængde;


    public WhiskyProdukt2(double vand, int antalFlasker) {
        this.vand = vand;
        this.antalFlasker = antalFlasker;
    }

    public int antalFlasker(double flaskeStørrelse) {
        double antalLiter = 0;
        for (Aftapning aftapning : aftapninger) {
            antalLiter += aftapning.getLiterAftappet();
        }
        this.antalFlasker = (int) (antalLiter / flaskeStørrelse);
        return antalFlasker;
    }

    public void tilføjAftapning(Aftapning aftapning) {
        if (!aftapninger.contains(aftapning)) {
            aftapninger.add(aftapning);
            totalWhiskyMængde += aftapning.getLiterAftappet();
        }
    }

    public void tilføjVand(double vandMængde) {
        if (vandMængde > 0) {
            totalWhiskyMængde += vandMængde;
            this.vandMængde = vandMængde;
        } else {
            throw new RuntimeException("Mængden skal være positiv");
        }
    }

    public double beregnAlkoholProcentUdenVand() {
        double volumeGangeAlkoholprocent = 0;
        double samledeVolume = 0;


        for (Aftapning aftapning : aftapninger) {
            volumeGangeAlkoholprocent += aftapning.getAlkoholProcent() * aftapning.getLiterAftappet();
            samledeVolume += aftapning.getLiterAftappet();
        }


        return volumeGangeAlkoholprocent / samledeVolume;
    }

    public double beregnSamledeAlkoholProcent() {
        double alkoholMængde = 0;
        double samletVolumen = 0;

        if (vandMængde > 0) {
            alkoholMængde = totalWhiskyMængde * (beregnAlkoholProcentUdenVand() / 100);
            samletVolumen = totalWhiskyMængde + vandMængde;
        }

        return (alkoholMængde / samletVolumen) * 100;
    }


    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }


    public void setAarLagret(Aftapning aftapning, Påfyldning påfyldning) {
        LocalDate a = påfyldning.getPåfyldningsDato();
        LocalDate b = aftapning.getAftapningsDato();
        int diff = b.getYear() - a.getYear();
        if (a.getDayOfYear() - b.getDayOfYear() >= 0) {
            diff--;
        }
        AarLagret = diff;
    }


    public LocalDate getOpretDato() {
        return opretDato;
    }

    public String getNavn() {
        return navn;
    }


    public double getTotalWhiskyMængde() {
        return totalWhiskyMængde;
    }


    public String setWhiskytype(Aftapning aftapning, WhiskyProdukt whiskyProdukt) {
        String whiskytype = "";
        if (vand != 0.0 && aftapninger.size() == 1) {
            whiskytype = "Single Cask";
        } else if (vand == 0.0 && aftapninger.size() == 1) {
            whiskytype = "Cask Strength";
        } else if (vand == 0.0 && aftapninger.size() >= 2) {
            whiskytype = "Cask Strength Single Malt";
        } else if (vand != 0.0 && aftapninger.size() >= 2) {
            whiskytype = "Single Malt";
        }
        this.whiskytype = whiskytype;  // Ensure the instance variable is updated
        return whiskytype;
    }

    public String getWhiskytype() {
        return whiskytype;
    }


    public String lavHistorie() {
        String aftapDato = null;
        String korn = "";
        String mark = "";
        String maltbatchNummer = "";
        List<String> fadLande = new ArrayList<>();
        String fadLand = "";
        List<String> tidligereIndholde = new ArrayList<>();
        String tidligereIndhold = "";
        double alkoholprocent = 0.0;

        for (Aftapning aftapning : getAftapninger()) {
            aftapning.getPåfyldning().getFad();
            aftapDato = String.valueOf(aftapning.getAftapningsDato());
            String currentFadLand = String.valueOf(aftapning.getPåfyldning().getFad().getLand());
            if (!fadLande.contains(currentFadLand)) {
                fadLande.add(currentFadLand);
            }
            String currentFadTidlIndhold = String.valueOf(aftapning.getPåfyldning().getFad().getTidligereIndhold());
            if (!tidligereIndholde.contains(currentFadTidlIndhold)) {
                tidligereIndholde.add(currentFadTidlIndhold);
            }
            for (Mængde mængde : aftapning.getPåfyldning().getMængderPåfyldt()) {
                if (mængde != null && mængde.getDestillering() != null) {
                    korn = String.valueOf(mængde.getDestillering().getMaltbatch().getKorn().getSort());
                    mark = String.valueOf(mængde.getDestillering().getMaltbatch().getKorn().getMark().getMarkNavn());
                    maltbatchNummer = mængde.getDestillering().getMaltbatch().getBatchNummer();
                }
            }

            // Lande fade er fra
            if (fadLande.size() > 1) {
                fadLand = "Den er lagret på " + getAftapninger().size() + " fade fra "
                        + fadLande.get(0) + " og " + fadLande.get(1) + ", ";
            } else if (!fadLande.isEmpty()) {
                fadLand = "Den er lagret på et fad fra " + fadLande.getFirst() + ", ";
            }

            //Væsker der har været på fadene
            if (tidligereIndholde.size() > 1) {
                tidligereIndhold = "der tidligere har været brugt til "
                        + tidligereIndholde.get(0).toLowerCase() + " og " + tidligereIndholde.get(1).toLowerCase() + ". ";
            } else if (!tidligereIndholde.isEmpty()) {
                tidligereIndhold = "der tidligere har været brugt til " + tidligereIndholde.getFirst() + ". ";
            }

            // Alkoholprocent beregninger
            if (vand > 0) {
                alkoholprocent = beregnSamledeAlkoholProcent();
            } else {
                alkoholprocent = beregnAlkoholProcentUdenVand();
            }
        }

        String historie = "Vores whisky " + getNavn() + " er lavet af " + korn + " korn,"
                + "\nfra vores medejer Lars mark " + mark + ". "
                + "\nDet er blevet maltet i Thy som en del af maltbachet nummer: " + maltbatchNummer + ". "
                + "\n" + fadLand
                + "\n" + tidligereIndhold
                + "\nDerefter er det blevet lagret i " + getAarLagret() + " år"
                + "\nog hældt på flaske den " + aftapDato + ". "
                + "\nWhiskyen er opblandet med " + getVand() + " liter vand. "
                + "\nDet er en " + getWhiskytype() + " whisky, der er endt på " + alkoholprocent + ". "
                + "\nDer er blevet tappet " + getAntalFlasker() + " flasker";

        return historie;
    }


    public int getAarLagret() {
        return AarLagret;
    }


    public void setNavn(String navn) {
        this.navn = navn;
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

    public void setAftapninger(List<Aftapning> aftapninger) {
        this.aftapninger = aftapninger;
    }
}


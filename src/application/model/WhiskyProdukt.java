package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private int antalFlasker;

    public WhiskyProdukt(String navn, LocalDate opretDato) {
        this.navn = navn;
        this.opretDato = opretDato;
        aftapninger = new ArrayList<>();
    }

    public void tilføjAftapning(Aftapning aftapning) {
        if (!aftapninger.contains(aftapning)) {
            aftapninger.add(aftapning);
            totalWhiskyMængde += aftapning.getLiterAftappet();
            aftapning.setWhiskyProdukt(this);
        }
    }

    /***
     * en vandmængde tilføjes til den samlede mængde af whiskyprodukt
     * @param vandMængde
     */
    public void tilføjVand(double vandMængde) {
        totalWhiskyMængde += vandMængde;
        this.vandMængde = vandMængde;
    }

    public double beregnAlkoholProcent(){
        if (this.vandMængde > 0){
            return beregnSamledeAlkoholProcentMedVand();
        } else {
            return beregnAlkoholProcentUdenVand();
        }
    }

    /***
     * beregner den samlede alkoholprocent uden tilføjet vand
     * @return alkoholprocent uden vand
     */
    private double beregnAlkoholProcentUdenVand() {
        double volumeGangeAlkoholprocent = 0;
        double samledeVolume = 0;


        for (Aftapning aftapning : aftapninger) {
            volumeGangeAlkoholprocent += aftapning.getAlkoholProcent() * aftapning.getLiterAftappet();
            samledeVolume += aftapning.getLiterAftappet();
        }


        return volumeGangeAlkoholprocent / samledeVolume;
    }

    /***
     * beregner samlede alkoholprocent med vand tilføjet
     * beregner mængden af alkohol ved at fjerne vandet
     * tager mængden af alkohol divideret med samlet volume og ganger med 100
     * @return alkoholprocent med vand
     */
    private double beregnSamledeAlkoholProcentMedVand() {
        double alkoholMængde = (totalWhiskyMængde - vandMængde) * (beregnAlkoholProcentUdenVand() / 100);
        double samletVolumen = totalWhiskyMængde;
        return (alkoholMængde / samletVolumen) * 100;
    }

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    /***
     *
     * @param flaskeStørrelse
     * @return hvor mange flasker der kan laves uf af den total mængde
     */
    public int antalFlasker(double flaskeStørrelse) {
        int antalFlasker = (int) (totalWhiskyMængde / flaskeStørrelse);
        return antalFlasker;
    }

    public LocalDate getOpretDato() {
        return opretDato;
    }

    public String getNavn() {
        return navn;
    }

    public String getWhiskytype() {
        String whiskytype = "";
        if (vandMængde != 0.0 && aftapninger.size() == 1) {
            whiskytype = "Single Cask";
        } else if (vandMængde == 0.0 && aftapninger.size() == 1) {
            whiskytype = "Cask Strength";
        } else if (vandMængde == 0.0 && aftapninger.size() >= 2) {
            whiskytype = "Cask Strength Single Malt";
        } else if (vandMængde != 0.0 && aftapninger.size() >= 2) {
            whiskytype = "Single Malt";
        }
        return whiskytype;
    }

    public double getTotalWhiskyMængde() {
        return totalWhiskyMængde;
    }

    public String lavHistorie(double flaskeStørrelse) {
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
            for (PåfyldningsComponent væske : aftapning.getPåfyldning().getPåfyldningsComponenter()) {
                if (væske instanceof Væske) {
                    if (væske != null && ((Væske)væske).getDestillering() != null) {
                        korn = String.valueOf(((Væske)væske).getDestillering().getMaltbatch().getKorn().getSort());
                        mark = String.valueOf(((Væske)væske).getDestillering().getMaltbatch().getKorn().getMark().getMarkNavn());
                        maltbatchNummer = ((Væske)væske).getDestillering().getMaltbatch().getBatchNummer();
                    }
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
            alkoholprocent = beregnAlkoholProcent();
        }

        String stringAlkoholProcent = String.format("%.2f", alkoholprocent);
        String historie = "Vores whisky " + getNavn() + " er lavet af " + korn + " korn,"
                + "\nfra vores medejer Lars mark " + mark + ". "
                + "\nDet er blevet maltet i Thy i maltbacht nummer: " + maltbatchNummer + ". "
                + "\n" + fadLand
                + "\n" + tidligereIndhold
                + "\nDerefter er det blevet lagret i " + getÅrLagret() + " år"
                + "\nog hældt på flaske den " + opretDato + ". "
                + "\nWhiskyen er opblandet med " + vandMængde + " liter vand. "
                + "\nDet er en " + getWhiskytype() + " whisky, der er endt på " + stringAlkoholProcent + "% ABV. "
                + "\nDer er blevet tappet " + antalFlasker(flaskeStørrelse) + " flasker"
                + "\n\n\n\n"+ flaskeStørrelse+" L                            " +
                "                                            " + stringAlkoholProcent + "% ABV";

        return historie;
    }

    /***
     *
     * @return gennemsnit af år lagret på aftapninger
     */
    private int getÅrLagret() {
        int samletÅr = 0;
        for (Aftapning aftapning : aftapninger) {
            int påfyldningÅr = aftapning.getPåfyldning().getPåfyldningsDato().getYear();
            int aftapningsÅr = opretDato.getYear();
            int årlagret = aftapningsÅr - påfyldningÅr;

            samletÅr += årlagret;

        }
        return samletÅr / aftapninger.size();
    }

}

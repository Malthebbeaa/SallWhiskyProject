package application.model;

public class Fad {
    private static int IdCount = 1;
    int fadId;
    int størrelse;
    String materiale;
    FadLeverandør fadLeverandør;
    String tidligereIndhold;
    int alder;
    int antalGangeBrugt;
    private Double whiskyIFad;

    public Fad(int størrelse, String materiale, FadLeverandør fadLeverandør, String tidligereIndhold, int alder, int antalGangeBrugt) {
        this.fadId = IdCount++;
        this.størrelse = størrelse;
        this.materiale = materiale;
        this.fadLeverandør = fadLeverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.alder = alder;
        this.antalGangeBrugt = antalGangeBrugt;
        this.whiskyIFad = null;
    }

    public void påfyldWhisky(double literPåfyldt){

    }


    public void AftapWhisky(Double literAftappet) {
        if (whiskyIFad == null) {
            throw new IllegalStateException("Fadet er tomt");
        }

        if (literAftappet <= whiskyIFad) {
            whiskyIFad -= literAftappet;
            if (whiskyIFad == 0) {
                antalGangeBrugt++;
            }
        } else {
            System.out.println();
        }
    }

    public Double getWhiskyIFad() {
        return whiskyIFad;
    }

    public FadLeverandør getFadLeverandør() {
        return fadLeverandør;
    }

    public int getFadId() {
        return fadId;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }

    public String getLand() {
        return getFadLeverandør().land;
    }

    public void AntalGangeBrugt() {
        antalGangeBrugt++;
    }


}

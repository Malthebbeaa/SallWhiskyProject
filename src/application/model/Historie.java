package application.model;

public class Historie {
    public String historie;


    public Historie(String historie) {
        this.historie = historie;
    }

//"Whiskyen er lavet på" + Kornnavn + " fra vores medejer Lars økologiske mark(er)" + Marknavn(e)
// + ". Den er destilleret " + AntalDestilleringer + " af maltbatch " + Maltbatch
// + ". Derefter er den lagret på "  + AntalFade + " lavet af " + Fadmateriale(r)
// + ", der tidligere er blevet brugt til " + TidligereFyld + ". Der har vi lagret den i "
// + ÅrLagret + " hvorefter den er blevet hældt på flaske " + PåfyldningsDato "."
//
//"Der er lavet " + AntalFlasker + " af denne whiskyBatch


    public void skrivHistorie(WhiskyProdukt produkt) {
        System.out.println("Whiskyen er lavet af " + produkt.getKorn()
                + " fra vores medejer Lars mark " + produkt.getMark() + ". ");
        if (produkt.getFade().size() > 1) {
            System.out.println("Den er lagret på " + produkt.getFade().size() + " fade"
                    + "");
        }
    }
}

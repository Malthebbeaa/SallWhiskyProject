package application.model;

public class Historie {
    private String historie;

    public Historie(String historie) {
        this.historie = historie;
    }

    //    public void skrivHistorie(WhiskyProdukt produkt) {
//        System.out.println("Whiskyen er lavet af " + produkt.getKorn()
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
//            System.out.println(". Derefter er det blevet lagret i " + produkt.getAarLagret()
//                    + " og hældt på flaske den " + produkt.getDatoTappet());
//            System.out.println("\n. Der er blevet tappet " + produkt.getAntalFlasker());
//
//        }
//    }
}

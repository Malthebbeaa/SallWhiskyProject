package gui.lager;

import application.controller.Controller;
import application.model.Lager;

public class LagerHandler {
    private Controller controller;

    public LagerHandler(Controller controller) {
        this.controller = controller;
    }

    public void opretLagerActionHandler(LagerForm form){
        String lagerNavn = form.getLagerNavn();
        String vejnavn = form.getVejnavn();
        String by = form.getBy();
        String postnummer = form.getPostNummer();


        Lager lager = controller.opretLager(lagerNavn,vejnavn,postnummer,by);

        int antalReoler = form.getAntalReoler();
        int antalHylder = form.getAntalHylder();
        int antalPladser = form.getAntalPladser();

        lager.tilføjReol(antalReoler,antalHylder,antalPladser);

        form.clearAction();
        form.getLvLagre().getItems().setAll(controller.getStorage().getLager());
    }
}

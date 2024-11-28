package gui.lager;

import application.controller.Controller;
import application.model.Lager;
import application.model.Reol;

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

        int antalHylder = form.getAntalHylder();
        int antalPladser = form.getAntalPladser();

        form.clearAction();
        for (Lager l : controller.getStorage().getLager()) {
            form.getLvLagre().getItems().add(l.toString() + " ledige pladser: " + l.ledigePladser());
        }
    }
    public void opretReolHandler(LagerForm form){
        Lager lager = form.getCbLager();
        int antalReoler = form.getAntalReoler();
        lager.tilføjReol();
        form.clearAction();
    }

    public void opretHyldeHandler(LagerForm form){
        Reol reol = form.getCbReol();
        reol.tilføjHylde(form.getAntalHylder(), form.getAntalPladser());
        form.clearAction();
    }
}

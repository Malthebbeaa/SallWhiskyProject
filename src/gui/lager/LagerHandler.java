package gui.lager;

import application.controller.Controller;
import application.model.Lager;
import application.model.Reol;
import javafx.scene.control.Alert;

public class LagerHandler {
    private Controller controller;

    public LagerHandler(Controller controller) {
        this.controller = controller;
    }

    public void opretLagerActionHandler(LagerForm form) {
        String lagerNavn = form.getLagerNavn();
        String vejnavn = form.getVejnavn();
        String by = form.getBy();
        String postnummer = form.getPostNummer();
        if(lagerNavn.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive et lagernavn inden du fortsætter");
            alert.showAndWait();
        }
        else if(vejnavn.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive et vejnavn inden du fortsætter");
            alert.showAndWait();
        }
        else if(by.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal vælge en by inden du fortsætter");
            alert.showAndWait();
        }
        else if(postnummer.isEmpty() || !postnummer.matches("\\d+") || !(postnummer.length() == 4)){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal vælge et gyldigt postnummer");
            alert.showAndWait();
        }
        else {
            Lager lager = controller.opretLager(lagerNavn, vejnavn, postnummer, by);
            form.clearAction();
        }
    }

    public void opretReolHandler(LagerForm form) {
        Lager lager = form.getCbLager();
        if(lager == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive et lager inden du fortsætter");
            alert.showAndWait();
        }
        else if(form.getAntalHylder().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive et antal hylder inden du fortsætter");
            alert.showAndWait();
        }
        else if(form.getAntalPladser().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive antal pladser inden du fortsætter");
            alert.showAndWait();
        }
        else{
            Reol reol = lager.tilføjReol();
            int hyldeAntal = Integer.parseInt(form.getAntalHylder());
            int pladsAntal = Integer.parseInt(form.getAntalPladser());
            reol.tilføjHylde(hyldeAntal, pladsAntal);
            form.clearAction();
        }
    }
}

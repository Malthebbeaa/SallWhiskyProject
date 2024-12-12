package gui.destillering;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Lager;
import application.model.Maltbatch;
import gui.destillering.DestilleringForm;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class DestilleringHandler {
    private Controller controller;

    public DestilleringHandler(Controller controller) {
        this.controller = controller;
    }

    public void opretDestilleringAction(DestilleringForm form) {
        try {
            int antalDestilleringer = form.getAntalDestilleringer();
            LocalDate startDato = form.getStartDato();
            LocalDate slutDato = form.getSlutDato();
            Maltbatch maltbatch = form.getMaltbatch();
            double alkoholProcent = form.getAlkoholProcent();
            double væskeMængde = form.getVæskeMængde();

            if (form.getMaltbatch() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vælg et maltbatch");
                alert.showAndWait();
            } else if (startDato.isAfter(slutDato)){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Startdato skal være før slutdatoen");
                alert.showAndWait();
            } else {
                Destillering destillering = controller.opretDestillering(antalDestilleringer, startDato, slutDato, væskeMængde, alkoholProcent, maltbatch);

                if (form.getKommentar() != null) {
                    controller.tilføjKommentarTilDestillering(form.getKommentar(), destillering);
                }

                clearAction(form);
            }
        } catch (Exception e){
            if (e.getMessage().contains("For input string"));{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ufyld venligst alle felter");
                alert.showAndWait();
            }
        }
    }

    public void clearAction(DestilleringForm form){
        form.getDatepickerstartDato().setValue(LocalDate.now());
        form.getDatepickerSlutDato().setValue(LocalDate.now());
        form.getTxfVæskeMængde().clear();
        form.getTxfAlkoholProcent().clear();
        form.getTxfAntalDestilleringer().clear();
        form.getTxfKommentar().clear();
        form.getComboBoxMaltbatch().setValue(null);
    }
}

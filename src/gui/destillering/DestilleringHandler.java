package gui.destillering;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Maltbatch;
import gui.destillering.DestilleringForm;

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
            double alkoholProcent = form.getAlkoholProcent();
            double væskeMængde = form.getVæskeMængde();
            Maltbatch maltbatch = form.getMaltbatch();

            Destillering destillering = controller.opretDestillering(antalDestilleringer, startDato, slutDato, væskeMængde, alkoholProcent, maltbatch);

            if (form.getKommentar() != null) {
                controller.tilføjKommentarTilDestillering(form.getKommentar(), destillering);
            }

            form.clearAction();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

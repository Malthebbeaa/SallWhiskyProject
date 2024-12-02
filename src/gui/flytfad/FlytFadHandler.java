package gui.flytfad;

import application.controller.Controller;
import application.model.Fad;
import application.model.Korn;
import application.model.Mark;
import application.model.Plads;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class FlytFadHandler {
    private Controller controller;

    public FlytFadHandler(Controller controller) {
        this.controller = controller;
    }

    public void flytFadAktion(Plads plads, Fad fad, FlytfadForm form){
        controller.flytFad(plads, fad);
        form.selectedHyldeChanged();
    }
}

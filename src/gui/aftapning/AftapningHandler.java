package gui.aftapning;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Fad;

import java.time.LocalDate;

public class AftapningHandler {
    private Controller controller;

    public AftapningHandler(Controller controller) {
        this.controller = controller;
    }

    public void aftapFadAction(AftapningForm form){
        Fad fad = form.getFad();
        LocalDate aftapningsDato = form.getAftapningsDato();
        double mængde = form.getTxfMængdeTilAftapning();

        Aftapning aftapning = new Aftapning(aftapningsDato, mængde,44.2, fad);

        form.clearAction();
    }
}

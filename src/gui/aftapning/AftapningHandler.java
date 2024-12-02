package gui.aftapning;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Påfyldning;

import java.time.LocalDate;

public class AftapningHandler {
    private Controller controller;

    public AftapningHandler(Controller controller) {
        this.controller = controller;
    }

    public void aftapFadAction(AftapningForm form) {
        Påfyldning påfyldning = form.getPåfyldning();
        LocalDate aftapningsDato = form.getAftapningsDato();
        double literAftappet = form.getTxfMængdeTilAftapning();
        double alkoholProcent = form.getTxfAlkoholProcent();


        Aftapning aftapning = new Aftapning(literAftappet, alkoholProcent);
        aftapning.setLiterAftappet(literAftappet);
        aftapning.setAlkoholProcent(literAftappet);

        form.clearAction();
    }
}

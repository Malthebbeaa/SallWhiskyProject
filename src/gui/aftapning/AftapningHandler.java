package gui.aftapning;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Fad;
import application.model.Mængde;

import java.time.LocalDate;

public class AftapningHandler {
    private Controller controller;

    public AftapningHandler(Controller controller) {
        this.controller = controller;
    }

    public void aftapFadAction(AftapningForm form){
        Fad fad = form.getFad();
        LocalDate aftapningsDato = form.getAftapningsDato();
        Mængde mængde = new Mængde(form.getTxfMængdeTilAftapning(), fad);
        double alkoholProcent = form.getTxfAlkoholProcent();

        Aftapning aftapning = new Aftapning(aftapningsDato,44.2, fad);
        aftapning.aftapMængde(mængde);
        fad.aftapWhisky(mængde.getAftapning());

        form.clearAction();
    }
}

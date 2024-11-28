package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Påfyldning;

import java.time.LocalDate;
import java.util.HashMap;

public class PåfyldningHandler {
    private Controller controller;

    public PåfyldningHandler(Controller controller) {
        this.controller = controller;
    }

    public void påfyldFadAction(PåfyldningForm form){
        Destillering destillering = form.getDestillering();
        Fad fad = form.getFad();
        LocalDate påfyldningsDato = form.getPåfyldningsDato();
        double mængde = form.getTxfMængdeTilPåfyldning();

        //Påfyldning påfyldning = controller.påfyldningFad(fad, destillering, påfyldningsDato,mængde);
        form.clearAction();
    }
}

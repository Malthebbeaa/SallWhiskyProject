package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Mængde;
import application.model.Påfyldning;

import javax.swing.text.html.ListView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PåfyldningHandler {
    private Controller controller;

    public PåfyldningHandler(Controller controller) {
        this.controller = controller;
    }

    public void påfyldFadAction(PåfyldningForm form) {
        Fad fad = form.getFad();
        LocalDate påfyldningsDato = form.getPåfyldningsDato();

        List<Mængde> mængder = form.getMængder();


        Påfyldning påfyldning = new Påfyldning(påfyldningsDato);
        for (Mængde mængde : mængder) {
            påfyldning.tilføjMængde(mængde);
        }

        fad.tilføjPåfyldning(påfyldning);
        System.out.println(fad.getMængdeFyldtPåFad());
        form.clearAction();
    }

}

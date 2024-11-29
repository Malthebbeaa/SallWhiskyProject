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
        try {
            Fad fad = form.getFad();
            LocalDate påfyldningsDato = form.getPåfyldningsDato();

            List<Mængde> mængder = form.getMængder();


            Påfyldning påfyldning = new Påfyldning(påfyldningsDato, fad);
            for (Mængde mængde : mængder) {
                påfyldning.tilføjMængde(mængde);
            }

            controller.påfyldFad(påfyldning, fad);
            clearAction(form);
        } catch (RuntimeException e){

        }
    }

    public void vælgAction(PåfyldningForm form) {
        Destillering selected = form.getLvwMuligeDestilleringer().getSelectionModel().getSelectedItem();
        MængdePopUpWindow popUpWindow = new MængdePopUpWindow("Afgiv mængde", selected);
        popUpWindow.showAndWait();
        //hvis mængde er udfyldt
        if (popUpWindow.getMængde() != 0){
            if (selected != null){
                form.getLveValgtDestilleringer().getItems().add(selected);
                form.getLvwMuligeDestilleringer().getItems().remove(selected);
                Mængde mængde = new Mængde(popUpWindow.getMængde(), selected);
                form.getMængder().add(mængde);
            }
        }
    }

    public void removeAllAction(PåfyldningForm form){
        form.getLvwMuligeDestilleringer().getItems().addAll(form.getLveValgtDestilleringer().getItems());
        form.getLveValgtDestilleringer().getItems().clear();
    }

    public void clearAction(PåfyldningForm form){
        form.getCboxFad().setValue(null);
        form.getDatePickerPåfyldningsDato().setValue(LocalDate.now());
        removeAllAction(form);
    }

    public void fravælgAction(PåfyldningForm form){
        Destillering selected = form.getLveValgtDestilleringer().getSelectionModel().getSelectedItem();
        if (selected != null){
            form.getLvwMuligeDestilleringer().getItems().add(selected);
            form.getLveValgtDestilleringer().getItems().remove(selected);
        }
    }

}

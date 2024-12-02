package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Mængde;
import application.model.Påfyldning;
import gui.GuiObserver;
import gui.GuiSubject;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

import javax.swing.text.html.ListView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PåfyldningHandler implements GuiSubject {
    private Controller controller;
    private Påfyldning påfyldning;
    private ArrayList<GuiObserver> observers;

    public PåfyldningHandler(Controller controller) {
        this.controller = controller;
        observers = new ArrayList<>();
    }

    public void påfyldFadAction(PåfyldningForm form, Fad fad) {
        påfyldning = form.getPåfyldning();
        if (påfyldning.getMængderPåfyldt().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal tilføje mængde af destillering(er) inden du fortsætter");
            alert.showAndWait();
        }

        controller.påfyldFad(påfyldning, fad);
        System.out.println("Påfyldning gennemført på fadnr: "+ påfyldning.getFad().getFadId() + " - der er nu " + fad.getMængdeFyldtPåFad() + " på fadet");
        for (Mængde mængde : påfyldning.getMængderPåfyldt()){
            System.out.println("Destillering " + mængde.getDestillering().getBatchNummer() + " har nu " + mængde.getDestillering().getVæskeMængde() + " L væske");
        }
        if (fad.getMængdeFyldtPåFad() == fad.getStørrelse()){
            System.out.println("Fadnr " + fad.getFadId() + " er nu fyldt og klar til flytning");
        }
        notifyObservers();
    }

    public void vælgAction(PåfyldningForm form, Påfyldning påfyldning) {
        Destillering selected = form.getLvwMuligeDestilleringer().getSelectionModel().getSelectedItem();
        if (selected == null) return;

        this.påfyldning = påfyldning;
        MængdePopUpWindow popUpWindow = new MængdePopUpWindow("Afgiv mængde", selected, påfyldning);
        popUpWindow.showAndWait();
        //hvis mængde er udfyldt
        if (popUpWindow.getMængde() != 0) {
            if (selected != null) {
                form.getLveValgtDestilleringer().getItems().add(selected);
                form.getLvwMuligeDestilleringer().getItems().remove(selected);
                Mængde mængde = new Mængde(popUpWindow.getMængde(), selected);
                this.påfyldning.tilføjMængde(mængde);
            }
        }
        System.out.println("Mængde tilføjet til " + påfyldning.getFad().getFadId() + " fra batchnr " + selected.getBatchNummer()+ ": "+ påfyldning.getMængderPåfyldt().getLast().getMængde());
    }

    public void removeAllAction(PåfyldningForm form) {
        påfyldning = form.getPåfyldning();
        form.getLvwMuligeDestilleringer().getItems().addAll(form.getLveValgtDestilleringer().getItems());
        form.getLveValgtDestilleringer().getItems().clear();
        påfyldning.getMængderPåfyldt().clear();
    }

    public void clearAction(PåfyldningForm form) {
        påfyldning = form.getPåfyldning();
        form.getCboxFad().setValue(null);
        form.getDatePickerPåfyldningsDato().setValue(LocalDate.now());
        removeAllAction(form);
    }

    public void fravælgAction(PåfyldningForm form) {
        Destillering selected = form.getLveValgtDestilleringer().getSelectionModel().getSelectedItem();
        påfyldning = form.getPåfyldning();
        if (selected != null) {
            form.getLvwMuligeDestilleringer().getItems().add(selected);
            form.getLveValgtDestilleringer().getItems().remove(selected);

            påfyldning.getMængderPåfyldt().removeIf(mængde -> mængde.getDestillering().equals(selected));
        }
    }


    public void getStartVindue(GridPane pane, PåfyldningForm form) {
        pane.getChildren().setAll(form.getPåfyldningsPane());

    }

    @Override
    public void addObserver(GuiObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(GuiObserver o) {
        observers.remove(o);
    }

    public void notifyObservers(){
        for (GuiObserver o : observers) {
            o.update(this);
        }
    }
}

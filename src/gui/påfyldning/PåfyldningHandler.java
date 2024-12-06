package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Væske;
import application.model.VæskeMix;
import gui.GuiObserver;
import gui.GuiSubject;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class PåfyldningHandler implements GuiSubject {
    private Controller controller;
    private VæskeMix væskeMix;
    private ArrayList<GuiObserver> observers;

    public PåfyldningHandler(Controller controller) {
        this.controller = controller;
        observers = new ArrayList<>();
    }

    public void påfyldFadAction(PåfyldningForm form, Fad fad) {
        væskeMix = form.getPåfyldning();
        if (væskeMix.getVæskerFyldtPå().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal tilføje mængde af destillering(er) inden du fortsætter");
            alert.showAndWait();
        } else {
            controller.påfyldFad(væskeMix, fad);

            notifyObservers();
            form.tommefade();
        }
    }

    public void vælgAction(PåfyldningForm form, VæskeMix væskeMix) {
        try {
            Destillering selected = form.getLvwMuligeDestilleringer().getSelectionModel().getSelectedItem();
            if (selected == null) return;

            this.væskeMix = væskeMix;
            MængdePopUpWindow popUpWindow = new MængdePopUpWindow("Afgiv mængde", selected, væskeMix);
            popUpWindow.showAndWait();
            //hvis mængde er udfyldt
            if (popUpWindow.getMængde() != 0) {
                if (selected != null) {
                    form.getLveValgtDestilleringer().getItems().add(selected);
                    form.getLvwMuligeDestilleringer().getItems().remove(selected);
                    Væske væske = new Væske(popUpWindow.getMængde(), selected);
                    this.væskeMix.tilføjVæske(væske);
                }
                System.out.println("Mængde tilføjet til " + væskeMix.getFad().getFadId() + " fra batchnr " + selected.getBatchNummer() + ": " + væskeMix.getVæskerFyldtPå().getLast().getMængde());
            }
        } catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }

    public void removeAllAction(PåfyldningForm form) {
        væskeMix = form.getPåfyldning();
        form.getLvwMuligeDestilleringer().getItems().addAll(form.getLveValgtDestilleringer().getItems());
        form.getLveValgtDestilleringer().getItems().clear();
        væskeMix.getVæskerFyldtPå().clear();
    }

    public void clearAction(PåfyldningForm form) {
        væskeMix = form.getPåfyldning();
        form.getCboxFad().setValue(null);
        form.getDatePickerPåfyldningsDato().setValue(LocalDate.now());
        removeAllAction(form);
    }

    public void fravælgAction(PåfyldningForm form) {
        Destillering selected = form.getLveValgtDestilleringer().getSelectionModel().getSelectedItem();
        væskeMix = form.getPåfyldning();
        if (selected != null) {
            form.getLvwMuligeDestilleringer().getItems().add(selected);
            form.getLveValgtDestilleringer().getItems().remove(selected);

            væskeMix.getVæskerFyldtPå().removeIf(mængde -> mængde.getDestillering().equals(selected));
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

    public void notifyObservers() {
        for (GuiObserver o : observers) {
            o.update(this);
        }
    }
}

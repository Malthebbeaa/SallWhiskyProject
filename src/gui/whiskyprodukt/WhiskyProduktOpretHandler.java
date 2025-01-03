package gui.whiskyprodukt;

import application.controller.Controller;
import application.model.*;
import gui.GuiObserver;
import gui.GuiSubject;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WhiskyProduktOpretHandler implements GuiSubject {

    private Controller controller;
    private WhiskyProdukt whiskyProdukt;
    private ArrayList<GuiObserver> observers;

    public WhiskyProduktOpretHandler(Controller controller) {
        this.controller = controller;
        this.observers = new ArrayList<>();
    }


    public void aftapFadAction(WhiskyProduktOpretForm form) {
        whiskyProdukt = form.getWhiskyProdukt();
        List<Aftapning> aftapninger = form.getAftapninger();
        if (aftapninger.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal tilføje aftapninger fra fade inden du fortsætter");
            alert.showAndWait();
        }

        controller.lavAftapninger(aftapninger, whiskyProdukt);

        if (form.getVandMængde() > 0){
            whiskyProdukt.tilføjVand(form.getVandMængde());
        }

        notifyObservers();
    }

    public void vælgAction(WhiskyProduktOpretForm form, WhiskyProdukt whiskyProdukt) {
        Påfyldning selected = form.getLvwMuligePåfyldninger().getSelectionModel().getSelectedItem();
        if (selected == null) return;

        this.whiskyProdukt = whiskyProdukt;
        PopupWindowAftap popupWindowAftap = new PopupWindowAftap("Aftap", selected, whiskyProdukt);
        popupWindowAftap.showAndWait();
        //hvis mængde er udfyldt
        if (popupWindowAftap.getMængde() != 0) {
            if (selected != null) {
                form.getLvwValgtePåfyldninger().getItems().add(selected);
                form.getLvwMuligePåfyldninger().getItems().remove(selected);
                Aftapning aftapning = new Aftapning(popupWindowAftap.getMængde(), popupWindowAftap.getAlkoholProcent());
                aftapning.setPåfyldning(selected);
                form.getAftapninger().add(aftapning);
            }
        }
    }

    public void removeAllAction(WhiskyProduktOpretForm form) {
        whiskyProdukt = form.getWhiskyProdukt();
        form.getLvwMuligePåfyldninger().getItems().addAll(form.getLvwValgtePåfyldninger().getItems());
        form.getLvwValgtePåfyldninger().getItems().clear();
        whiskyProdukt.getAftapninger().clear();
    }

    public void clearAction(WhiskyProduktOpretForm form) {
        whiskyProdukt = form.getWhiskyProdukt();
        form.getTxfNavn().clear();
        form.getDatePickerOprettelsesdato().setValue(LocalDate.now());
        removeAllAction(form);
    }

    public void fravælgAction(WhiskyProduktOpretForm form) {
        Påfyldning selected = form.getLvwValgtePåfyldninger().getSelectionModel().getSelectedItem();
        whiskyProdukt = form.getWhiskyProdukt();
        if (selected != null) {
            form.getLvwMuligePåfyldninger().getItems().add(selected);
            form.getLvwValgtePåfyldninger().getItems().remove(selected);

            whiskyProdukt.getAftapninger().removeIf(aftapning -> aftapning.getPåfyldning().equals(selected));
        }
    }

    public double beregnSamledeAlkoholProcent(WhiskyProduktOpretForm form) {
        double volumeGangeAlkoholprocent = 0;
        double samledeVolume = 0;


        for (Aftapning aftapning : form.getAftapninger()) {
            volumeGangeAlkoholprocent += aftapning.getAlkoholProcent() * aftapning.getLiterAftappet();
            samledeVolume += aftapning.getLiterAftappet();
        }

        return volumeGangeAlkoholprocent / samledeVolume;
    }

    @Override
    public void addObserver(GuiObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(GuiObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (GuiObserver observer : observers) {
            observer.update(this);
        }
    }
}

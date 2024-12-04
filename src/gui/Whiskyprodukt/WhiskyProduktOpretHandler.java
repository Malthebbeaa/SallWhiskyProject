package gui.Whiskyprodukt;

import application.controller.Controller;
import application.model.*;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.List;

public class WhiskyProduktOpretHandler {

    private Controller controller;
    private WhiskyProdukt whiskyProdukt;

    public WhiskyProduktOpretHandler(Controller controller) {
        this.controller = controller;
    }


    public void påfyldFadAction(WhiskyProduktOpretForm form) {
        whiskyProdukt = form.getWhiskyProdukt();
        List<Aftapning> aftapninger = form.getAftapninger();
        if (aftapninger.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal tilføje aftapninger fra fade inden du fortsætter");
            alert.showAndWait();
        }

        controller.lavAftapninger(aftapninger, whiskyProdukt);

        if (form.getVandMængde() != 0){
            whiskyProdukt.tilføjVand(form.getVandMængde());
        }

        System.out.println("Whiskyprouktet er oprettet " + whiskyProdukt.getNavn() + ", " + whiskyProdukt.getTotalWhiskyMængde()+ " med en alkoholprocent på " + whiskyProdukt.beregnSamledeAlkoholProcent());
        for (Aftapning aftapning : whiskyProdukt.getAftapninger()) {
            System.out.println("Påfyldning " + aftapning.getPåfyldning() + " har nu " + aftapning.getPåfyldning().getLiterPåfyldt() + " L væske");
            if (aftapning.getPåfyldning().getLiterPåfyldt() == 0) {
                System.out.println("Fadnr" + aftapning.getPåfyldning().getFad().getFadId() + " er nu tomt");
            }
        }


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
        System.out.println("Mængde tilføjet til " + whiskyProdukt.getNavn() + " fra fad " + selected.getFad().getFadId() + ": " + form.getAftapninger().getLast().getLiterAftappet());
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
}

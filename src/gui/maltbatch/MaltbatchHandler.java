package gui.maltbatch;

import application.controller.Controller;
import application.model.Korn;
import application.model.Mark;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class MaltbatchHandler {
    private Controller controller;

    public MaltbatchHandler(Controller controller) {
        this.controller = controller;
    }

    public void opretMaltbatchHandler(MaltbatchForm form){
        String batchnummer = form.getBatchnummer();
        String mængde = form.getMængde();
        Korn korn = form.getKorn();
        if(batchnummer.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive et batchnummer inden du fortsætter");
            alert.showAndWait();
        }
        else if(mængde.isEmpty() || !mængde.matches("\\d+")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive en gyldig mængde inden du fortsætter");
            alert.showAndWait();
        }
        else if(korn == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal vælge en kornsort inden du fortsætter");
            alert.showAndWait();
        }
        else {
            double mængdeDouble = Double.parseDouble(mængde);
            controller.opretMaltbatch(batchnummer, mængdeDouble, korn);
            form.clearAktion();
        }
    }

    public void opretKornHandler(MaltbatchForm form){
        String sort = form.getSort();
        LocalDate høstDag = form.getHøstDag();
        Mark mark = form.getMark();
        if(sort.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal angive en sort inden du fortsætter");
            alert.showAndWait();
        }
        else if(mark == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal vælge en mark inden du fortsætter");
            alert.showAndWait();
        }
        else {
            controller.opretKorn(høstDag, sort, mark);
            form.clearAktion();
        }
    }

    public void opretMarkHandler(MaltbatchForm form){
        String markNavn = form.getMarkNavn();
        boolean økologisk = form.getIsØkologisk();
        if (markNavn.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Du skal vælge et navn til marken inden du fortsætter");
            alert.showAndWait();
        }
        else {
            controller.opretMark(markNavn, økologisk);
        }
        form.clearAktion();
    }
}

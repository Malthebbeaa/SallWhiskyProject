package gui.maltbatch;

import application.controller.Controller;
import application.model.Korn;
import application.model.Mark;

import java.time.LocalDate;

public class MaltbatchHandler {
    private Controller controller;

    public MaltbatchHandler(Controller controller) {
        this.controller = controller;
    }

    public void opretMaltbatchHandler(MaltbatchForm form){
        String batchnummer = form.getBatchnummer();
        double mængde = form.getMængde();
        Korn korn = form.getKorn();

        controller.opretMaltbatch(batchnummer, mængde, korn);
    }

    public void opretKornHandler(MaltbatchForm form){
        String sort = form.getSort();
        LocalDate høstDag = form.getHøstDag();
        Mark mark = form.getMark();

        controller.opretKorn(høstDag,sort,mark);
        form.clearAktion();
        form.getCbKorn().getItems().addAll(controller.getStorage().getKorn());
    }

    public void opretMarkHandler(MaltbatchForm form){
        String markNavn = form.getMarkNavn();
        boolean økologisk = form.getIsØkologisk();

        controller.opretMark(markNavn, økologisk);
        form.clearAktion();
        form.getCbKornMark().getItems().addAll(controller.getStorage().getMarker());
    }
}

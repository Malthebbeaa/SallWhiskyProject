package gui.OmhældFad;

import application.controller.Controller;
import application.model.Fad;
import application.model.Plads;
import application.model.PåfyldningsComponent;
import application.model.Væske;
import gui.GuiObserver;
import gui.GuiSubject;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class OmhældFadHandler implements GuiSubject {
    private Controller controller;
    private ArrayList<GuiObserver> observers = new ArrayList<>();

    public OmhældFadHandler(Controller controller) {
        this.controller = controller;
    }

    public void omhældFadAktion(Fad fraFad, Fad destinationsFad, PåfyldningsComponent væske, String mængde, OmhældFadForm form) {
        LocalDate omhældningsDato = form.getOmhældningsDato();
        try {
            controller.flytVæskeTilFad(form.getfraFad(), form.getDestinationsFad(), form.getVæske(), Double.parseDouble(form.getTxfMængde().getText()), omhældningsDato);
            form.clearAktion();
            notifyObservers();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fad " + destinationsFad.getFadId() + " har fået " + mængde + " L " + " omhældt");
            alert.setHeaderText(null);
            alert.showAndWait();
        } catch (RuntimeException e) {
            String message = "";
            if (e.getMessage().equals("empty String")) {
                message = "Du skal angive en mængde";
                Alert alert = new Alert(Alert.AlertType.WARNING, message);
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    @Override
    public void addObserver(GuiObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(GuiObserver o) {
        removeObserver(o);
    }

    @Override
    public void notifyObservers() {
        for (GuiObserver observer : observers) {
            observer.update(this);
        }
    }
}

package gui.fad;

import application.controller.Controller;
import application.model.Fad;
import application.model.FadLeverandør;
import gui.GuiObserver;
import gui.GuiSubject;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class FadHandler implements GuiSubject {
    private Controller controller;
    private ArrayList<GuiObserver> observers = new ArrayList<>();

    public FadHandler(Controller controller) {
        this.controller = controller;
    }

    public void opretFadAction(FadForm form) {
        try {
            int størrelse = form.getTøndensStørrelse();
            String materiale = form.getMateriale();
            FadLeverandør fadleverandør = form.getFadLeverandør();
            String tidligereIndhold = form.getTidligerIndhold();
            int alder = form.getTøndensAlder();
            int antalGangeBrugt = form.getAntalGangeBrugt();

            Fad fad = controller.opretFad(størrelse, materiale, fadleverandør, tidligereIndhold, alder, antalGangeBrugt);
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        notifyObservers();
        clearAction(form);
    }

    public void clearAction(FadForm form) {
        form.getTxfAlder().clear();
        form.getTxfAntalGangeBrugt().clear();
        form.getComboboxLeverandører().setValue(null);
        form.getComboboxMateriale().setValue(null);
        form.getComboboxStørrelse().setValue(null);
        form.getComboboxTidligereIndhold().setValue(null);
    }

    @Override
    public void addObserver(GuiObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(GuiObserver o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (GuiObserver observer : observers) {
            observer.update(this);
        }
    }
}

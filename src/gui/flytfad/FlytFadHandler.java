package gui.flytfad;

import application.controller.Controller;
import application.model.Fad;
import application.model.Korn;
import application.model.Mark;
import application.model.Plads;
import gui.GuiObserver;
import gui.GuiSubject;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlytFadHandler implements GuiSubject {
    private Controller controller;
    private ArrayList<GuiObserver> observers = new ArrayList<>();

    public FlytFadHandler(Controller controller) {
        this.controller = controller;
    }

    public void flytFadAktion(Plads plads, Fad fad, FlytfadForm form){
        controller.flytFad(plads, fad);
        form.selectedHyldeChanged();
        notifyObservers();
    }

    @Override
    public void addObserver(GuiObserver o) {
        if (!observers.contains(o)){
            observers.add(o);
        }
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

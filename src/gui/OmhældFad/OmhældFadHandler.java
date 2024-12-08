package gui.OmhældFad;

import application.controller.Controller;
import application.model.Fad;
import application.model.Plads;
import application.model.PåfyldningsComponent;
import application.model.Væske;
import gui.GuiObserver;
import gui.GuiSubject;

import java.util.ArrayList;

public class OmhældFadHandler implements GuiSubject{
    private Controller controller;
    private ArrayList<GuiObserver> observers = new ArrayList<>();

    public OmhældFadHandler(Controller controller) {
        this.controller = controller;
    }

    public void flytFadAktion(Fad fraFad, Fad destinationsFad, PåfyldningsComponent væske, double mængde, OmhældFadForm form){
        controller.flytVæskeTilFad(form.getfraFad(), form.getDestinationsFad(), form.getVæske(), Double.parseDouble(form.getTxfMængde().getText()));
        form.clearAktion();
        notifyObservers();
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

package gui;

public interface GuiSubject {

    void addObserver(GuiObserver o);
    void removeObserver(GuiObserver o);
    void notifyObservers();
}

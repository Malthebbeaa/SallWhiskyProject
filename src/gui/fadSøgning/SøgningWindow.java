package gui.fadSøgning;

import application.controller.Controller;
import gui.BaseWindow;
import javafx.scene.layout.GridPane;

public class SøgningWindow extends BaseWindow {
    private Controller controller;
    private SøgningHandler handler;
    private SøgningForm form;
    private GridPane søgningsPane;

    public SøgningWindow(Controller controller) {
        this.controller = controller;
        handler = new SøgningHandler(controller);
        form = new SøgningForm(controller,handler);

        initContent();

    }
    @Override
    public void initContent() {
        getPane().add(form.getSøgningsPane(), 0,1);

    }

    public SøgningForm getForm() {
        return form;
    }
}

package gui.destillering;

import application.controller.Controller;
import gui.BaseWindow;
import gui.destillering.DestilleringForm;
import gui.destillering.DestilleringHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class DestilleringWindow extends BaseWindow {
    private DestilleringForm form;
    private DestilleringHandler handler;
    private GridPane destilleringPane;



    public DestilleringWindow(Scene scene, Controller controller) {
        this.form = new DestilleringForm(controller);
        this.handler = new DestilleringHandler(controller);

        initContent();
    }
    @Override
    public void initContent() {
        getPane().add(form.getDestilleringPane(), 0,0);

        GridPane gridPaneButtons = new GridPane();
        gridPaneButtons.setHgap(10);
        gridPaneButtons.setPadding(new Insets(20));
        Button btnOpretDestillering = new Button("Opret Destillering");
        btnOpretDestillering.setOnAction(e -> handler.opretDestilleringAction(form));
        gridPaneButtons.add(btnOpretDestillering, 0,0);
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> form.clearAction());
        gridPaneButtons.add(btnAfbryd, 1,0);

        getPane().add(gridPaneButtons, 0,1);
    }
}

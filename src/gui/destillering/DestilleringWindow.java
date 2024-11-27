package gui.destillering;

import application.controller.Controller;
import gui.BaseWindow;
import gui.destillering.DestilleringForm;
import gui.destillering.DestilleringHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DestilleringWindow extends BaseWindow {
    private DestilleringForm form;
    private DestilleringHandler handler;
    private GridPane destilleringPane;



    public DestilleringWindow(Controller controller) {
        this.form = new DestilleringForm(controller);
        this.handler = new DestilleringHandler(controller);

        initContent();
    }
    @Override
    public void initContent() {
        getPane().add(form.getDestilleringPane(), 0,1);

        Button btnOpretDestillering = new Button("Opret Destillering");
        btnOpretDestillering.setOnAction(e -> handler.opretDestilleringAction(form));
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> form.clearAction());


        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpretDestillering, btnAfbryd);
        hBoxButtons.setAlignment(Pos.CENTER);

        getPane().add(hBoxButtons, 0, 2);

    }
}

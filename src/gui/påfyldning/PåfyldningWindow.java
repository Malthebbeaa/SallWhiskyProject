package gui.påfyldning;

import application.controller.Controller;
import gui.BaseWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PåfyldningWindow extends BaseWindow {
    private PåfyldningForm form;
    private PåfyldningHandler handler;
    private GridPane påfyldningsPane;

    public PåfyldningWindow(Controller controller) {
        form = new PåfyldningForm(controller);
        handler = new PåfyldningHandler(controller);

        initContent();
    }

    @Override
    public void initContent() {
        getPane().add(form.getPåfyldningsPane(), 0,1);

        Button btnPåfyld = new Button("Påfyld");
        btnPåfyld.setOnAction(e -> handler.påfyldFadAction(form));
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> form.clearAction());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnPåfyld, btnAfbryd);
        hBoxButtons.setAlignment(Pos.CENTER);

        getPane().add(hBoxButtons, 0, 2);
    }
}

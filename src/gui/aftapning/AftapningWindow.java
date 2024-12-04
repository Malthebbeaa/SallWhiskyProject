package gui.aftapning;

import application.controller.Controller;
import gui.BaseWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AftapningWindow extends BaseWindow {
    private AftapningForm form;
    private AftapningHandler handler;
    private GridPane aftapningsPane;

    public AftapningWindow(Controller controller) {
        form = new AftapningForm(controller);
        handler = new AftapningHandler(controller);

        initContent();
    }

    @Override
    public void initContent() {
        getPane().add(form.getAftapningsPane(), 0,1);

        Button btnPåfyld = new Button("Aftap");
        btnPåfyld.setOnAction(e -> handler.aftapFadAction(form));
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> form.clearAction());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnPåfyld, btnAfbryd);
        hBoxButtons.setAlignment(Pos.CENTER);

        getPane().add(hBoxButtons, 0, 2);
    }
}

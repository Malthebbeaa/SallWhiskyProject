package gui.flytfad;

import application.controller.Controller;
import gui.BaseWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FlytFadWindow extends BaseWindow {
    private FlytfadForm form;
    private FlytFadHandler handler;
    private GridPane flytFadPane;

    public FlytFadWindow(Controller controller) {
        handler = new FlytFadHandler(controller);
        form = new FlytfadForm(controller, handler);
        flytFadPane = getPane();
        this.initContent();
    }

    public void initContent() {
        getPane().add(form.getFlytFadPane(), 0,1);

        Button btnFlytFad = new Button("Flyt Fad");
        btnFlytFad.setOnAction(e -> handler.flytFadAktion(form.getPlads(), form.getFad(), form));

        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e -> form.clearAktion());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnFlytFad, btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);

        flytFadPane.add(hBoxButtons, 0, 2);
    }

    public GridPane getFlytFadPane() {
        return flytFadPane;
    }
}

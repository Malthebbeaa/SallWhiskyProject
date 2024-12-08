package gui.OmhældFad;

import application.controller.Controller;
import gui.BaseWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class OmhældFadWindow extends BaseWindow {
    private OmhældFadForm form;
    private OmhældFadHandler handler;
    private GridPane omhældFadPane;

    public OmhældFadWindow(Controller controller) {
        handler = new OmhældFadHandler(controller);
        form = new OmhældFadForm(controller, handler);
        omhældFadPane = getPane();
        this.initContent();
    }

    public void initContent() {
        getPane().add(form.getOmhældFadPane(), 0,1);

        Button btnFlytFad = new Button("Omhæld Fad");
        btnFlytFad.setOnAction(e -> handler.omhældFadAktion(form.getfraFad(),form.getDestinationsFad(), form.getVæske(), Double.parseDouble(form.getTxfMængde().getText()),form));

        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e -> form.clearAktion());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnFlytFad, btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);

        omhældFadPane.add(hBoxButtons, 0, 2);
    }

    public GridPane getFlytFadPane() {
        return omhældFadPane;
    }

    public OmhældFadForm getForm() {
        return form;
    }

    public OmhældFadHandler getHandler() {
        return handler;
    }
}

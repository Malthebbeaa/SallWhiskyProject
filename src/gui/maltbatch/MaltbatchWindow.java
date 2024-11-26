package gui.maltbatch;

import application.controller.Controller;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import application.model.Rygemateriale;
import gui.BaseWindow;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class MaltbatchWindow extends BaseWindow {
    private MaltbatchForm form;
    private MaltbatchHandler handler;
    private GridPane maltPane;

    public MaltbatchWindow(Controller controller) {
        handler = new MaltbatchHandler(controller);
        form = new MaltbatchForm(controller, handler);
        maltPane = getPane();
        this.initContent();
    }

    public void initContent() {
        getPane().add(form.getMaltPane(), 0,0);

        Button btnOpretMaltbatch = new Button("Opret Maltbatch");
        btnOpretMaltbatch.setOnAction(e -> handler.opretMaltbatchHandler(form));

        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e -> form.clearAktion());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpretMaltbatch, btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);

        maltPane.add(hBoxButtons, 0, 1);
    }

    public GridPane getMaltPane() {
        return maltPane;
    }
}

package gui.lager;

import application.controller.Controller;
import application.model.Lager;
import gui.BaseWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class opretLagerWindow extends BaseWindow {
    private Controller controller;
    private GridPane lagerPane;
    private LagerHandler handler;
    private LagerForm form;

    public opretLagerWindow(Controller controller){
        this.controller = controller;
        this.lagerPane = getPane();
        form = new LagerForm(controller);
        handler = new LagerHandler(controller);

        initContent();
    }

    public void initContent(){
        getPane().add(form.getLagerPane(), 0,1);

        Button btnOpretLager = new Button("Opret Lager");
        btnOpretLager.setOnAction(e-> handler.opretLagerActionHandler(form));
        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e-> form.clearAction());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpretLager,btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);

        lagerPane.add(hBoxButtons,0,2);
    }

    public GridPane getLagerPane() {
        return lagerPane;
    }
}

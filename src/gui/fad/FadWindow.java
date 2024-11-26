package gui.fad;

import application.controller.Controller;
import application.model.Fad;
import application.model.FadLeverandør;
import gui.BaseWindow;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import storage.StorageInterface;

import java.util.List;

public class FadWindow extends BaseWindow {
    private FadForm form;
    private FadHandler handler;
    private Controller controller;
    private GridPane fadPane;
    public FadWindow(Controller controller){
        this.controller = controller;
        form = new FadForm(controller);
        handler = new FadHandler(controller);
        fadPane = getPane();

        initContent();
    }
    @Override
    public void initContent() {
        getPane().add(form.getFadPane(), 0,0);
        Button btnOpret = new Button("Opret Fad");
        btnOpret.setOnAction(e -> handler.opretFadAction(form));
        fadPane.add(btnOpret, 0, 5);

        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> form.clearAction());
        fadPane.add(btnAfbryd, 1, 5);
    }
}

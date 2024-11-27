package gui.Whiskyprodukt;

import application.controller.Controller;
import gui.BaseWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class WhiskyProduktWindow extends BaseWindow {
    private WhiskyProduktForm form;
    private WhiskyProduktHandler handler;
    private Controller controller;
    private GridPane fadPane;
    public WhiskyProduktWindow(Controller controller){
        this.controller = controller;
        form = new WhiskyProduktForm(controller);
        handler = new WhiskyProduktHandler(controller);
        fadPane = getPane();

        initContent();
    }
    @Override
    public void initContent() {
        getPane().add(form.getFadPane(), 0,1);


        Button btnOpret = new Button("Opret whisky produkt");
        btnOpret.setOnAction(e -> handler.opretWhiskyProduktAction(form));

        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> form.clearAction());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpret, btnAfbryd);
        hBoxButtons.setAlignment(Pos.CENTER);

        getPane().add(hBoxButtons, 0, 2);
    }
}

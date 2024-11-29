package gui.påfyldning;

import application.controller.Controller;
import application.model.Fad;
import application.model.Påfyldning;
import gui.BaseWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class PåfyldningWindow extends BaseWindow {
    private PåfyldningForm form;
    private PåfyldningHandler handler;
    private GridPane påfyldningsPane;

    public PåfyldningWindow(Controller controller) {
        handler = new PåfyldningHandler(controller);
        form = new PåfyldningForm(controller, handler);

        initContent();
    }

    @Override
    public void initContent() {
        getPane().add(form.getPåfyldningsPane(), 0, 1);

        Button btnPåfyld = new Button("Begynd Påfyldning");
        btnPåfyld.setOnAction(e -> {
            begyndPåfyldningAction();
        });
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> {
            resetAction();
        });

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnPåfyld, btnAfbryd);
        hBoxButtons.setAlignment(Pos.CENTER);

        getPane().add(hBoxButtons, 0, 2);
    }

    public void begyndPåfyldningAction(){
        Fad fad = form.getFad();
        LocalDate påfyldningsDato = form.getPåfyldningsDato();

        if (fad == null || påfyldningsDato == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vælg både fad og påfyldningsdato før du fortsætter");
            alert.showAndWait();
            return;
        }
        Påfyldning påfyldning = new Påfyldning(form.getPåfyldningsDato(), form.getFad());

        form.initNextForm(handler, påfyldning);
        getPane().getChildren().setAll(form.getNextPane());
        setØverste();

        Button btnPåfyld = new Button("Lav Påfyldning");
        btnPåfyld.setOnAction(e -> {
            handler.påfyldFadAction(form, fad);
            resetAction();
        });
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> {
            resetAction();
        });
        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnPåfyld, btnAfbryd);
        hBoxButtons.setAlignment(Pos.CENTER);

        getPane().add(hBoxButtons, 0, 2);

    }

    public void resetAction(){
        getPane().getChildren().clear();
        initContent();
        form.getLblOverskrift().setText("Påfyld fad");
        form.getDatePickerPåfyldningsDato().setValue(LocalDate.now());
        form.getCboxFad().setValue(null);
        setØverste();
    }
    public void setØverste() {
        Image logo = new Image(getClass().getResource("/ressources/sall-whisky-transparent-logo-e1609503360305.png").toExternalForm());

        getPane().setStyle("-fx-background-color: lightblue");

        ImageView logoViewer = new ImageView(logo);
        logoViewer.setFitWidth(200);
        logoViewer.setPreserveRatio(true);

        getPane().add(logoViewer, 0, 0);
    }
}

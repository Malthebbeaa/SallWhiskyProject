package gui.Whiskyprodukt;

import application.controller.Controller;
import application.model.Fad;
import application.model.Påfyldning;
import application.model.WhiskyProdukt;
import gui.BaseWindow;
import gui.påfyldning.PåfyldningForm;
import gui.påfyldning.PåfyldningHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class WhiskyProduktOpretWinow extends BaseWindow {
    private WhiskyProduktOpretForm form;
    private WhiskyProduktOpretHandler handler;
    private GridPane whiskyOpretPane;
    private Controller controller;

    public WhiskyProduktOpretWinow(Controller controller) {
        handler = new WhiskyProduktOpretHandler(controller);
        form = new WhiskyProduktOpretForm(controller, handler);
        this.controller = controller;

        initContent();
    }
    @Override
    public void initContent() {
        getPane().add(form.getOpretWhiskyProduktPane(), 0, 1);

        Button btnPåfyld = new Button("Begynd Oprettelse");
        btnPåfyld.setOnAction(e -> {
            begyndtOprettelseAction();
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

    public void begyndtOprettelseAction(){
        String navn = form.getTxfNavn().getText();
        LocalDate oprettelsesDato = form.getDatePickerOprettelsesdato().getValue();

        if (navn == null || oprettelsesDato == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vælg både navn og oprettelsesdato før du fortsætter");
            alert.showAndWait();
            return;
        }
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(navn, oprettelsesDato);

        form.initNextForm(handler, whiskyProdukt);
        getPane().getChildren().setAll(form.getNextPane());
        setØverste();

        Button btnPåfyld = new Button("Opret Whiskyprodukt");
        btnPåfyld.setOnAction(e -> {
            handler.påfyldFadAction(form);
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
        form.getDatePickerOprettelsesdato().setValue(LocalDate.now());
        form.getTxfNavn().clear();
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

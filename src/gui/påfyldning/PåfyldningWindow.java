package gui.påfyldning;

import application.controller.Controller;
import application.model.Fad;
import application.model.VæskeMix;
import gui.BaseWindow;
import gui.flytfad.FlytFadWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class PåfyldningWindow extends BaseWindow {
    private PåfyldningForm form;
    private PåfyldningHandler handler;
    private GridPane påfyldningsPane;
    private Controller controller;
    private TabPane tabPane;
    private FlytFadWindow flytFadWindow;

    public PåfyldningWindow(Controller controller, FlytFadWindow flytFadWindow) {
        handler = new PåfyldningHandler(controller);
        form = new PåfyldningForm(controller, handler);
        this.controller = controller;
        this.flytFadWindow = flytFadWindow;

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
        VæskeMix væskeMix = controller.opretPåfyldning(form.getFad(),form.getPåfyldningsDato());

        form.initNextForm(handler, væskeMix);
        getPane().getChildren().setAll(form.getNextPane());
        setØverste();

        Button btnPåfyld = new Button("Lav Påfyldning");
        btnPåfyld.setOnAction(e -> {
            try {
                handler.påfyldFadAction(form, fad);
                resetAction();
                if (væskeMix.getLiterPåfyldt() > 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Fad nummer " +fad.getFadId() +" er påfyldt, gå til flytning");
                    alert.showAndWait();
                    if (alert.getResult().getButtonData().isDefaultButton()){
                        tabPane.getSelectionModel().select(6);
                        flytFadWindow.getForm().getCbFade().setValue(fad);
                    }
                }
            } catch (RuntimeException exception){
                Alert alert = new Alert(Alert.AlertType.WARNING, exception.getMessage());
                alert.showAndWait();
            }
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

    public PåfyldningForm getForm() {
        return form;
    }

    public PåfyldningHandler getHandler() {
        return handler;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }
}

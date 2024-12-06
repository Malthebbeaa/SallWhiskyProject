package gui.whiskyprodukt;

import application.model.VæskeMix;
import application.model.WhiskyProdukt;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupWindowAftap extends Stage {
    private VæskeMix væskeMix;
    private WhiskyProdukt whiskyProdukt;
    private TextField txfAlkoholProcent, txfMængde;
    private double mængde;
    private double alkoholProcent;
    public PopupWindowAftap(String title, VæskeMix selected, WhiskyProdukt whiskyProdukt) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        væskeMix = selected;
        this.whiskyProdukt = whiskyProdukt;

        setTitle(title);
        GridPane pane = new GridPane();
        pane.setPrefSize(400, 300);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    private void initContent(GridPane pane) {
        Label lblAlkoholProcent = new Label("Registre ny alkoholprocent: ");
        pane.add(lblAlkoholProcent, 0,0);
        txfAlkoholProcent = new TextField();
        pane.add(txfAlkoholProcent, 1,0);
        Label lblMængde = new Label("Mængde der skal aftappes");
        pane.add(lblMængde, 0,1);
        txfMængde = new TextField();
        pane.add(txfMængde, 1,1);

        Button btnTilføj = new Button("Aftap mængde");
        btnTilføj.setOnAction(e -> {
            mængde = Double.parseDouble(txfMængde.getText());
            alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
            if (væskeMix.aftapningGårIMinus(mængde)){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Der er ikke nok væske i fadet");
                alert.showAndWait();
                return;
            }
            hide();
        });
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> {
            hide();
        });

        HBox hBoxButtons = new HBox(5, btnTilføj, btnAfbryd);
        pane.add(hBoxButtons, 0, 2);
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public double getMængde() {
        return mængde;
    }
}

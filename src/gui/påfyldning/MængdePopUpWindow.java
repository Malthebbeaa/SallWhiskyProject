package gui.påfyldning;

import application.model.Destillering;
import application.model.VæskeMix;
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

public class MængdePopUpWindow extends Stage {
    private Destillering destillering;
    private double mængde;
    private TextField txfMængde;
    private VæskeMix væskeMix;

    public MængdePopUpWindow(String title, Destillering selected, VæskeMix væskeMix) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        destillering = selected;
        this.væskeMix = væskeMix;

        setTitle(title);
        GridPane pane = new GridPane();
        pane.setPrefSize(200, 150);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public void initContent(GridPane pane) {
        Label lblMængde = new Label("Indtast mængde (L)");
        pane.add(lblMængde, 0, 0);
        txfMængde = new TextField();
        txfMængde.setPromptText("Eks. 50");
        pane.add(txfMængde, 0, 1);

        Button btnTilføj = new Button("Tilføj mængde");
        btnTilføj.setOnAction(e -> {
                tilføjAction();
        });
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> {
            mængde = 0;
            hide();
        });

        HBox hBoxButtons = new HBox(5, btnTilføj, btnAfbryd);
        pane.add(hBoxButtons, 0, 2);

    }

    public void tilføjAction(){
        mængde = Double.parseDouble(txfMængde.getText());

        if (destillering.getVæskeMængde() - mængde < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ikke tilstrækkeligt væske i destilleringen");
            alert.showAndWait();
            mængde = 0;
        }
        if (væskeMix.mængdenOverskriderFadKapacitet(mængde)){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Mængden overskrider fadets kapacitet");
            alert.showAndWait();
            mængde = 0;
        }
        hide();
    }
    public Destillering getDestillering() {
        return destillering;
    }

    public double getMængde() {
        return mængde;
    }
}

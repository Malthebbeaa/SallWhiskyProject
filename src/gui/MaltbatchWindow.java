package gui;

import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class MaltbatchWindow {
    private GridPane startPane;
    private Scene scene;
    private ComboBox cbKorn;
    private GridPane maltPane;

    public MaltbatchWindow(GridPane startPane, Scene scene){
          maltPane = new GridPane();
          this.initContent(maltPane);
          this.startPane = startPane;
          this.scene = scene;
    }

    public void initContent(GridPane maltPane) {
        maltPane.setHgap(10);
        maltPane.setVgap(10);
        maltPane.setPadding(new Insets(20));

        //Disse ting er kun lavet for at kunne teste.
        Mark nybogård = new Mark("Nybogård", true);
        cbKorn = new ComboBox<>();
        cbKorn.getItems().add(new Korn(LocalDate.of(2024, 11, 22), "Evergreen", nybogård));
        maltPane.add(cbKorn, 0, 0,2,1);

        Label lblBatchNummer = new Label("Batchnummer: ");
        maltPane.add(lblBatchNummer,0,1);

        TextField txfBatchnummer = new TextField();
        maltPane.add(txfBatchnummer,1,1);

        Label lblMængde = new Label("Mængde: ");
        maltPane.add(lblMængde,0,2);

        TextField txfMængde = new TextField();
        maltPane.add(txfMængde,1,2);

        Button btnOpretMaltbatch = new Button("Opret Maltbatch");
        btnOpretMaltbatch.setOnAction(e-> opretMaltbatchAktion());

        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e-> afbrydAktion());

        maltPane.add(btnOpretMaltbatch,0,3);
        maltPane.add(btnAfbrydAktion,1,3);
    }

    public void opretMaltbatchAktion(){

    }

    public void afbrydAktion(){
        scene.setRoot(startPane);
    }

    public GridPane getMaltPane() {
        return maltPane;
    }
}

package gui;

import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
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

        HBox HbMarkNavn = new HBox(10);
        Label lblMarkNavn = new Label("Marknavn: ");
        TextField txfMarkNavn = new TextField();
        HbMarkNavn.getChildren().addAll(lblMarkNavn,txfMarkNavn);

        HBox HbØkologisk = new HBox(10);
        Label lblØkologisk = new Label("Økologisk");
        CheckBox cbØkologisk = new CheckBox();
        HbØkologisk.getChildren().addAll(lblØkologisk,cbØkologisk);

        VBox vBoxMark = new VBox(20);
        vBoxMark.setPadding(new Insets(20,50,50,10));
        vBoxMark.getChildren().addAll(HbMarkNavn,HbØkologisk);
        vBoxMark.setBorder(Border.stroke(Paint.valueOf("Black")));

        TitledPane tPaneMark = new TitledPane("Mark",vBoxMark);

        maltPane.add(tPaneMark,0,0,2,1);

        HBox HbSort = new HBox(10);
        Label lblSort = new Label("Sort: ");
        TextField txfSort = new TextField();
        HbSort.getChildren().addAll(lblSort,txfSort);

        HBox HbHøstdag = new HBox(10);
        Label lblHøstdag = new Label("Høstdag");
        DatePicker dpHøstdag = new DatePicker();
        dpHøstdag.setValue(LocalDate.now());
        HbHøstdag.getChildren().addAll(lblHøstdag,dpHøstdag);

        VBox vBoxKorn = new VBox(20);
        vBoxKorn.setPadding(new Insets(20,50,50,10));
        vBoxKorn.getChildren().addAll(HbSort,HbHøstdag);
        vBoxKorn.setBorder(Border.stroke(Paint.valueOf("Black")));

        TitledPane tPaneKorn = new TitledPane("Korn",vBoxKorn);

        maltPane.add(tPaneKorn,2,0,2,1);

        //Disse ting er kun lavet for at kunne teste.

        VBox vBoxKornVælger = new VBox(10);
        Label lblKornSortVælger = new Label("Vælg Korn:");
        cbKorn = new ComboBox<>();
        vBoxKornVælger.getChildren().addAll(lblKornSortVælger,cbKorn);

        Label lblBatchNummer = new Label("Batchnummer: ");
        TextField txfBatchnummer = new TextField();
        HBox hBoxBatchNummer = new HBox(10);
        hBoxBatchNummer.getChildren().addAll(lblBatchNummer,txfBatchnummer);

        Label lblMængde = new Label("Mængde: ");
        TextField txfMængde = new TextField();

        HBox hBoxMængde = new HBox(10);
        hBoxMængde.getChildren().addAll(lblMængde,txfMængde);

        VBox vBoxMaltbatch = new VBox(20);
        vBoxMaltbatch.setPadding(new Insets(20,50,50,10));
        vBoxMaltbatch.setBorder(Border.stroke(Paint.valueOf("Black")));
        vBoxMaltbatch.getChildren().addAll(vBoxKornVælger,hBoxBatchNummer,hBoxMængde);

        maltPane.add(vBoxMaltbatch,0,1,2,1);

        Button btnOpretMaltbatch = new Button("Opret Maltbatch");
        btnOpretMaltbatch.setOnAction(e-> opretMaltbatchAktion());

        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e-> afbrydAktion());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpretMaltbatch,btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);

        maltPane.add(hBoxButtons,1,4);
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

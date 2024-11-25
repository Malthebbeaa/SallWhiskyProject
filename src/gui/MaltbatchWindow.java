package gui;

import application.controller.Controller;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import javafx.beans.value.ChangeListener;
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
    private Scene scene;
    private ComboBox<Mark> cbMark;
    private ComboBox<Korn> cbKorn;
    private TextField txfMarkNavn, txfSort;
    private CheckBox chbØkologisk;
    private DatePicker dpHøstdag;
    private GridPane maltPane;
    private Controller controller;

    public MaltbatchWindow(Scene scene, Controller controller){
          maltPane = new GridPane();
          this.controller = controller;
          this.initContent(maltPane);
          this.scene = scene;

    }

    public void initContent(GridPane maltPane) {
        maltPane.setHgap(10);
        maltPane.setVgap(10);
        maltPane.setPadding(new Insets(20));

        Label lblMark = new Label("Tilføj Mark:");
        Label lblKorn = new Label("Tilføj Korn:");
        maltPane.add(lblMark,0,0);
        maltPane.add(lblKorn,2,0);

        GridPane markPane = new GridPane();
        Label lblVælgMark = new Label("Vælg Mark:");
        markPane.add(lblVælgMark,0,0);
        cbMark = new ComboBox<>();
        cbMark.getItems().addAll(controller.getStorage().getMarker());
        markPane.add(cbMark,1,0);
        Label lblMarkNavn = new Label("Marknavn:");
        markPane.add(lblMarkNavn,0,1);
        txfMarkNavn = new TextField();
        markPane.add(txfMarkNavn,1,1);
        Label lblØkologisk = new Label("Økologisk:");
        markPane.add(lblØkologisk,0,2);
        chbØkologisk = new CheckBox();
        markPane.add(chbØkologisk,1,2);
        markPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        markPane.setPadding(new Insets(20,50,50,10));
        markPane.setVgap(10);
        markPane.setHgap(10);
        maltPane.add(markPane,0,1,2,1);

        GridPane kornPane = new GridPane();
        Label lblVælgKorn = new Label("Vælg Korn:");
        kornPane.add(lblVælgKorn,0,0);
        cbKorn = new ComboBox<>();
        cbKorn.getItems().addAll(controller.getStorage().getKorn());
        kornPane.add(cbKorn,1,0);
        Label lblSort = new Label("Sort:");
        kornPane.add(lblSort,0,1);
        TextField txfSort = new TextField();
        kornPane.add(txfSort,1,1);
        Label lblHøstdag = new Label("Høstdag:");
        kornPane.add(lblHøstdag,0,2);
        dpHøstdag = new DatePicker();
        dpHøstdag.setValue(LocalDate.now());
        kornPane.add(dpHøstdag,1,2);
        kornPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        kornPane.setPadding(new Insets(20,50,50,10));
        kornPane.setVgap(10);
        kornPane.setHgap(10);
        maltPane.add(kornPane,2,1,2,1);

        Label opretMaltbatch = new Label("Opret maltbatch:");
        maltPane.add(opretMaltbatch,0,2);

        GridPane maltbatchPane = new GridPane();
        Label lblKornSortVælger = new Label("Vælg Korn:");
        maltbatchPane.add(lblKornSortVælger,0,0);
        ComboBox cbMaltetKorn = new ComboBox<Korn>();
        maltbatchPane.add(cbMaltetKorn,1,0);
        Label lblBatchNummer = new Label("Batchnummer: ");
        maltbatchPane.add(lblBatchNummer,0,1);
        TextField txfBatchnummer = new TextField();
        maltbatchPane.add(txfBatchnummer,1,1);
        Label lblMængde = new Label("Mængde: ");
        maltbatchPane.add(lblMængde,0,2);
        TextField txfMængde = new TextField();
        maltbatchPane.add(txfMængde,1,2);
        maltbatchPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        maltbatchPane.setPadding(new Insets(20,50,50,10));
        maltbatchPane.setVgap(10);
        maltbatchPane.setHgap(10);
        maltPane.add(maltbatchPane,0,3,2,1);

        Button btnOpretMaltbatch = new Button("Opret Maltbatch");
        btnOpretMaltbatch.setOnAction(e-> opretMaltbatchAktion());

        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e-> afbrydAktion());

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpretMaltbatch,btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);

        maltPane.add(hBoxButtons,1,4);

        ChangeListener<Mark> markListener = (om, olMark, newMark) -> this.valgtMarkChanged();
        cbMark.getSelectionModel().selectedItemProperty().addListener(markListener);

        ChangeListener<Korn> kornListener = (ok, olKorn, newKorn) -> this.valgtKornChanged();
        cbKorn.getSelectionModel().selectedItemProperty().addListener(kornListener);
    }

    public void opretMaltbatchAktion(){

    }

    public void afbrydAktion(){

    }

    public void valgtMarkChanged(){
        Mark mark = cbMark.getSelectionModel().getSelectedItem();
        txfMarkNavn.setText(mark.getMarkNavn());
        chbØkologisk.setSelected(mark.isØkologisk());
    }

    public void valgtKornChanged(){
        Korn korn = cbKorn.getSelectionModel().getSelectedItem();
        txfSort.setText(korn.getSort());
        dpHøstdag.setValue(korn.getHøstdag());
    }

    public GridPane getMaltPane() {
        return maltPane;
    }
}

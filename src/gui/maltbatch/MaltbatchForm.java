package gui.maltbatch;

import application.controller.Controller;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.time.LocalDate;

public class MaltbatchForm {
    private ComboBox<Mark> cbKornMark;
    private ComboBox<Korn> cbKorn;
    private TextField txfMarkNavn, txfSort, txfBatchnummer, txfMængde, txfRygemateriale;
    private CheckBox chbØkologisk;
    private DatePicker dpHøstdag;
    private GridPane maltPane;
    private Controller controller;
    private MaltbatchHandler handler;

    public MaltbatchForm(Controller controller, MaltbatchHandler handler) {
        this.controller = controller;
        handler = new MaltbatchHandler(controller);
        maltPane = new GridPane();

        initForm();
    }

    private void initForm() {
        maltPane.setHgap(10);
        maltPane.setVgap(10);

        Label lblMark = new Label("Tilføj Mark:");
        Label lblKorn = new Label("Tilføj Korn:");
        maltPane.add(lblMark,0,0);
        maltPane.add(lblKorn,2,0);

        GridPane markPane = new GridPane();
        Label lblMarkNavn = new Label("Marknavn:");
        markPane.add(lblMarkNavn,0,1);
        txfMarkNavn = new TextField();
        markPane.add(txfMarkNavn,1,1);
        Label lblØkologisk = new Label("Økologisk:");
        markPane.add(lblØkologisk,0,2);
        chbØkologisk = new CheckBox();
        markPane.add(chbØkologisk,1,2);
        Button btnTilføjMark = new Button("Tilføj Mark");
        btnTilføjMark.setOnAction(e-> handler.opretMarkHandler(this));
        markPane.add(btnTilføjMark,1,3);
        markPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        markPane.setPadding(new Insets(20,50,50,10));
        markPane.setVgap(10);
        markPane.setHgap(10);
        maltPane.add(markPane,0,1,2,1);

        GridPane kornPane = new GridPane();
        Label lblSort = new Label("Sort:");
        kornPane.add(lblSort,0,1);
        txfSort = new TextField();
        kornPane.add(txfSort,1,1);
        Label lblHøstdag = new Label("Høstdag:");
        kornPane.add(lblHøstdag,0,2);
        dpHøstdag = new DatePicker();
        dpHøstdag.setValue(LocalDate.now());
        kornPane.add(dpHøstdag,1,2);
        Label lblMarkTilKorn = new Label("Vælg mark:");
        kornPane.add(lblMarkTilKorn,0,3);
        cbKornMark = new ComboBox<>();
        cbKornMark.getItems().addAll(controller.getStorage().getMarker());
        kornPane.add(cbKornMark,1,3);
        Button btnTilføjKorn = new Button("Tilføj Korn");
        btnTilføjKorn.setOnAction(e-> handler.opretKornHandler(this));
        kornPane.add(btnTilføjKorn,1,4);
        kornPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        kornPane.setPadding(new Insets(20,50,50,10));
        kornPane.setVgap(10);
        kornPane.setHgap(10);
        maltPane.add(kornPane,2,1,2,1);

        Label opretMaltbatch = new Label("Opret maltbatch:");
        maltPane.add(opretMaltbatch,0,2);

        GridPane maltbatchPane = new GridPane();
        Label lblVælgKorn = new Label("Vælg Korn:");
        maltbatchPane.add(lblVælgKorn,0,0);
        cbKorn = new ComboBox<>();
        cbKorn.getItems().addAll(controller.getStorage().getKorn());
        maltbatchPane.add(cbKorn,1,0);
        Label lblBatchNummer = new Label("Batchnummer: ");
        maltbatchPane.add(lblBatchNummer,0,1);
        txfBatchnummer = new TextField();
        maltbatchPane.add(txfBatchnummer,1,1);
        Label lblMængde = new Label("Mængde: ");
        maltbatchPane.add(lblMængde,0,2);
        txfMængde = new TextField();
        maltbatchPane.add(txfMængde,1,2);
        Label lblEvtRygemateriale = new Label("Evt Rygemateriale:");
        maltbatchPane.add(lblEvtRygemateriale,0,3);
        txfRygemateriale = new TextField();
        maltbatchPane.add(txfRygemateriale,1,3);
        maltbatchPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        maltbatchPane.setPadding(new Insets(20,50,50,10));
        maltbatchPane.setVgap(10);
        maltbatchPane.setHgap(10);
        maltPane.add(maltbatchPane,0,3,2,1);
    }


    public Mark getMark(){return cbKornMark.getValue();}
    public Korn getKorn(){return cbKorn.getValue();}
    public String getMarkNavn(){return txfMarkNavn.getText();}
    public String getSort(){return txfSort.getText();}
    public String getBatchnummer(){return txfBatchnummer.getText();}
    public double getMængde(){return Double.parseDouble(txfMængde.getText());}
    public boolean getIsØkologisk(){return chbØkologisk.isSelected();}
    public LocalDate getHøstDag(){return dpHøstdag.getValue();}
    public String getRygeMateriale(){return txfRygemateriale.getText();}
    public ComboBox<Mark> getCbKornMark(){return cbKornMark;}
    public ComboBox<Korn> getCbKorn(){return cbKorn;}
    public GridPane getMaltPane(){return maltPane;}

    public void clearAktion(){
        txfMarkNavn.clear();
        txfSort.clear();
        txfSort.setEditable(true);
        dpHøstdag.setDisable(false);
        chbØkologisk.setSelected(false);
        txfBatchnummer.clear();
        txfMængde.clear();
        txfRygemateriale.clear();
    }

}
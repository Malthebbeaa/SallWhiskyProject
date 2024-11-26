package gui.maltbatch;

import application.controller.Controller;
import application.model.Korn;
import application.model.Mark;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.time.LocalDate;

public class MaltbatchForm {
    private ComboBox<Mark> cbMark;
    private ComboBox<Korn> cbKorn;
    private TextField txfMarkNavn, txfSort, txfBatchnummer, txfMængde;
    private CheckBox chbØkologisk;
    private DatePicker dpHøstdag;
    private GridPane maltPane;
    private Controller controller;

    public MaltbatchForm(Controller controller) {
        this.controller = controller;
        maltPane = new GridPane();

        initForm();
    }

    private void initForm() {
        maltPane.setHgap(10);
        maltPane.setVgap(10);
        maltPane.setPadding(new Insets(20));

        Label lblMark = new Label("Tilføj Mark:");
        Label lblKorn = new Label("Tilføj Korn:");
        maltPane.add(lblMark, 0, 0);
        maltPane.add(lblKorn, 2, 0);

        GridPane markPane = new GridPane();
        Label lblVælgMark = new Label("Vælg Mark:");
        markPane.add(lblVælgMark, 0, 0);
        cbMark = new ComboBox<>();
        cbMark.getItems().addAll(controller.getStorage().getMarker());
        markPane.add(cbMark, 1, 0);
        Label lblMarkNavn = new Label("Marknavn:");
        markPane.add(lblMarkNavn, 0, 1);
        txfMarkNavn = new TextField();
        markPane.add(txfMarkNavn, 1, 1);
        Label lblØkologisk = new Label("Økologisk:");
        markPane.add(lblØkologisk, 0, 2);
        chbØkologisk = new CheckBox();
        markPane.add(chbØkologisk, 1, 2);
        markPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        markPane.setPadding(new Insets(20, 50, 50, 10));
        markPane.setVgap(10);
        markPane.setHgap(10);
        maltPane.add(markPane, 0, 1, 2, 1);

        GridPane kornPane = new GridPane();
        Label lblVælgKorn = new Label("Vælg Korn:");
        kornPane.add(lblVælgKorn, 0, 0);
        cbKorn = new ComboBox<>();
        cbKorn.getItems().addAll(controller.getStorage().getKorn());
        kornPane.add(cbKorn, 1, 0);
        Label lblSort = new Label("Sort:");
        kornPane.add(lblSort, 0, 1);
        txfSort = new TextField();
        kornPane.add(txfSort, 1, 1);
        Label lblHøstdag = new Label("Høstdag:");
        kornPane.add(lblHøstdag, 0, 2);
        dpHøstdag = new DatePicker();
        dpHøstdag.setValue(LocalDate.now());
        kornPane.add(dpHøstdag, 1, 2);
        kornPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        kornPane.setPadding(new Insets(20, 50, 50, 10));
        kornPane.setVgap(10);
        kornPane.setHgap(10);
        maltPane.add(kornPane, 2, 1, 2, 1);

        Label opretMaltbatch = new Label("Opret maltbatch:");
        maltPane.add(opretMaltbatch, 0, 2);

        GridPane maltbatchPane = new GridPane();
        Label lblKornSortVælger = new Label("Valgt Korn:");
        maltbatchPane.add(lblKornSortVælger, 0, 0);
        Label lblBatchNummer = new Label("Batchnummer: ");
        maltbatchPane.add(lblBatchNummer, 0, 1);
        txfBatchnummer = new TextField();
        maltbatchPane.add(txfBatchnummer, 1, 1);
        Label lblMængde = new Label("Mængde: ");
        maltbatchPane.add(lblMængde, 0, 2);
        txfMængde = new TextField();
        maltbatchPane.add(txfMængde, 1, 2);
        maltbatchPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        maltbatchPane.setPadding(new Insets(20, 50, 50, 10));
        maltbatchPane.setVgap(10);
        maltbatchPane.setHgap(10);
        maltPane.add(maltbatchPane, 0, 3, 2, 1);
    }


    public Mark getMark(){return cbMark.getValue();}
    public Korn getKorn(){return cbKorn.getValue();}
    public String getMarkNavn(){return txfMarkNavn.getText();}
    public String getSort(){return txfSort.getText();}
    public String getBatchnummer(){return txfBatchnummer.getText();}
    public double getMængde(){return Double.parseDouble(txfMængde.getText());}
    public boolean getIsØkologisk(){return chbØkologisk.isSelected();}
    public LocalDate getHøstDag(){return dpHøstdag.getValue();}
    public GridPane getMaltPane(){return maltPane;}

    public void clearAktion(){
        txfMarkNavn.clear();
        txfSort.clear();
        txfSort.setEditable(true);
        dpHøstdag.setDisable(false);
        chbØkologisk.setSelected(false);
        txfBatchnummer.clear();
        txfMængde.clear();
    }

}

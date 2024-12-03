package gui.maltbatch;

import application.controller.Controller;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import gui.PaneCreator;
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
        this.handler = handler;
        maltPane = new GridPane();
        initForm();
    }

    private void initForm() {
        maltPane.setHgap(10);
        maltPane.setVgap(10);

        Label lblMark = new Label("Tilføj Mark:");
        Label lblKorn = new Label("Tilføj Korn:");
        Label lblOpretMaltbatch = new Label("Opret maltbatch:");
        maltPane.add(lblMark,0,0);
        maltPane.add(lblKorn,1,0);
        maltPane.add(lblOpretMaltbatch,0,2);

        PaneCreator markPane = new PaneCreator();

        Label lblMarkNavn = new Label("Marknavn:");
        txfMarkNavn = new TextField();
        txfMarkNavn.setPromptText("Stadsgaard");
        markPane.add(lblMarkNavn,0,1);
        markPane.add(txfMarkNavn,1,1);

        Label lblØkologisk = new Label("Økologisk:");
        chbØkologisk = new CheckBox();
        markPane.add(lblØkologisk,0,2);
        markPane.add(chbØkologisk,1,2);

        Button btnTilføjMark = new Button("Tilføj Mark");
        btnTilføjMark.setOnAction(e-> handler.opretMarkHandler(this));
        markPane.setGraphics(btnTilføjMark, "grass-field.png");
        markPane.add(btnTilføjMark,1,7);

        maltPane.add(markPane,0,1);

        PaneCreator kornPane = new PaneCreator();

        Label lblSort = new Label("Sort:");
        txfSort = new TextField();
        txfSort.setPromptText("Evergreen");
        kornPane.add(lblSort,0,1);
        kornPane.add(txfSort,1,1);

        Label lblHøstdag = new Label("Høstdag:");
        dpHøstdag = new DatePicker();
        dpHøstdag.setValue(LocalDate.now());
        kornPane.add(lblHøstdag,0,2);
        kornPane.add(dpHøstdag,1,2);

        Label lblMarkTilKorn = new Label("Vælg mark:");
        cbKornMark = new ComboBox<>();
        cbKornMark.getItems().addAll(controller.getStorage().getMarker());
        kornPane.add(lblMarkTilKorn,0,3);
        kornPane.add(cbKornMark,1,3);

        Button btnTilføjKorn = new Button("Tilføj Korn");
        btnTilføjKorn.setOnAction(e-> handler.opretKornHandler(this));
        kornPane.setGraphics(btnTilføjKorn, "corn.png");
        kornPane.add(btnTilføjKorn,1,4);

        maltPane.add(kornPane,1,1);



        GridPane maltbatchPane = new PaneCreator();
        Label lblVælgKorn = new Label("Vælg Korn:");
        cbKorn = new ComboBox<>();
        cbKorn.getItems().addAll(controller.getStorage().getKorn());
        maltbatchPane.add(lblVælgKorn,0,0);
        maltbatchPane.add(cbKorn,1,0);

        Label lblBatchNummer = new Label("Batchnummer: ");
        txfBatchnummer = new TextField();
        txfBatchnummer.setPromptText("NM90P");
        maltbatchPane.add(lblBatchNummer,0,1);
        maltbatchPane.add(txfBatchnummer,1,1);

        Label lblMængde = new Label("Mængde: ");
        txfMængde = new TextField();
        txfMængde.setPromptText("200");
        maltbatchPane.add(lblMængde,0,2);
        maltbatchPane.add(txfMængde,1,2);

        Label lblEvtRygemateriale = new Label("Evt Rygemateriale:");
        txfRygemateriale = new TextField();
        txfRygemateriale.setPromptText("Tørv");
        maltbatchPane.add(lblEvtRygemateriale,0,3);
        maltbatchPane.add(txfRygemateriale,1,3);

        maltPane.add(maltbatchPane,0,3,2,1);
    }


    public Mark getMark(){return cbKornMark.getValue();}
    public Korn getKorn(){return cbKorn.getValue();}
    public String getMarkNavn(){return txfMarkNavn.getText();}
    public String getSort(){return txfSort.getText();}
    public String getBatchnummer(){return txfBatchnummer.getText();}
    public String getMængde(){return txfMængde.getText();}
    public boolean getIsØkologisk(){return chbØkologisk.isSelected();}
    public LocalDate getHøstDag(){return dpHøstdag.getValue();}
    public String getRygeMateriale(){return txfRygemateriale.getText();}
    public ComboBox<Mark> getCbKornMark(){return cbKornMark;}
    public ComboBox<Korn> getCbKorn(){return cbKorn;}
    public GridPane getMaltPane(){return maltPane;}

    public void clearAktion(){
        txfBatchnummer.clear();
        txfMarkNavn.clear();
        txfMængde.clear();
        txfRygemateriale.clear();
        txfSort.clear();
        chbØkologisk.setSelected(false);
        cbKorn.getItems().clear();
        cbKornMark.getItems().clear();
        cbKorn.getItems().addAll(controller.getStorage().getKorn());
        cbKornMark.getItems().addAll(controller.getStorage().getMarker());
    }

}

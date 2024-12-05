package gui.whiskyprodukt;

import application.controller.Controller;
import application.model.*;
import gui.PaneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class WhiskyProduktOpretForm {
    private Controller controller;
    private DatePicker datePickerOprettelsesdato;
    private GridPane opretWhiskyProduktPane, opretWhiskyProduktInfoPane, nextPane;
    private ListView<Påfyldning> lvwMuligePåfyldninger;
    private ListView<Påfyldning> lvwValgtePåfyldninger;
    private ObservableList<Aftapning> aftapninger;
    private Label lblOverskrift;
    private Påfyldning påfyldning;
    private WhiskyProdukt whiskyProdukt;
    private TextField txfNavn, txfVand;


    public WhiskyProduktOpretForm(Controller controller, WhiskyProduktOpretHandler handler) {
        this.controller = controller;
        opretWhiskyProduktPane = new GridPane();
        aftapninger = FXCollections.observableArrayList();
        initForm(handler);
    }

    public void initForm(WhiskyProduktOpretHandler handler) {
        opretWhiskyProduktInfoPane = new PaneCreator();
        lblOverskrift = new Label("Opret whiskyprodukt:");
        opretWhiskyProduktPane.add(lblOverskrift, 0,0);
        opretWhiskyProduktPane.add(opretWhiskyProduktInfoPane, 0,1);
        opretWhiskyProduktPane.setHgap(10);
        opretWhiskyProduktPane.setVgap(10);

        //Vælg fad og påfyldningsdato pane
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        Label lblNavn = new Label("Indtast produktets navn:");
        txfNavn = new TextField();
        pane.add(lblNavn,0,0);
        pane.add(txfNavn, 0,1);
        Label lblPåfyldningsDato = new Label("Oprettelsdato: ");
        datePickerOprettelsesdato = new DatePicker(LocalDate.now());
        pane.add(lblPåfyldningsDato, 0,2);
        pane.add(datePickerOprettelsesdato, 0,3);
        opretWhiskyProduktInfoPane.add(pane,0,0,1,3);
    }

    public void initNextForm(WhiskyProduktOpretHandler handler, WhiskyProdukt whiskyProdukt){
        //Valg af påfyldninger og aftapning popup

        this.whiskyProdukt = whiskyProdukt;
        nextPane = new PaneCreator();

        opretWhiskyProduktPane.layout();
        opretWhiskyProduktPane.add(nextPane, 0,1);

        Label lblDestillering = new Label("Vælg Fad(e):");
        nextPane.add(lblDestillering, 1,0);
        lvwMuligePåfyldninger = new ListView<>();
        lvwValgtePåfyldninger = new ListView<>();

        lvwMuligePåfyldninger.getItems().addAll(getMuligePåfyldninger());
        lvwValgtePåfyldninger.setPrefSize(250,100);
        lvwMuligePåfyldninger.setPrefSize(250,100);


        Button btnAddSelected = new Button("Vælg");
        btnAddSelected.setOnAction(e -> {
            handler.vælgAction(this, whiskyProdukt);
        });
        Button btnRemoveSelected = new Button("Fravælg");
        btnRemoveSelected.setOnAction(e -> {
            handler.fravælgAction(this);
        });
        Button btnRemoveAll = new Button("Fravælg alle");
        btnRemoveAll.setOnAction(e -> {
            handler.removeAllAction(this);
        });
        VBox buttonBox = new VBox(5, btnAddSelected, btnRemoveSelected, btnRemoveAll);


        nextPane.add(lvwMuligePåfyldninger, 1,1);
        nextPane.add(lvwValgtePåfyldninger, 3,1);
        nextPane.add(buttonBox, 2, 1);

        String alkoholProcent = String.format("%.2f",handler.beregnSamledeAlkoholProcent(this));
        Label lblAlkoholProcent = new Label("Nuværende alkoholprocent: " + alkoholProcent + "%");

        aftapninger.addListener((ListChangeListener<Aftapning>) change -> {
            String nyAlkoholProcent = String.format("%.2f",handler.beregnSamledeAlkoholProcent(this));
            lblAlkoholProcent.setText("Nuværende alkoholprocent: " + nyAlkoholProcent + "%");
        });
        nextPane.add(lblAlkoholProcent, 1,2);
        Label lblVandMængde = new Label("Hvor mange liter vand skal der fortyndes med (L): ");
        //todo listener på vand tilføjet
        txfVand = new TextField();
        txfVand.setMaxWidth(75);
        HBox hBoxVand = new HBox(10);
        hBoxVand.getChildren().addAll(lblVandMængde, txfVand);
        nextPane.add(hBoxVand, 1,3);
    }


    public ObservableList<Påfyldning> getMuligePåfyldninger(){
        ObservableList<Påfyldning> påfyldningMed3År = FXCollections.observableArrayList();
        for (Påfyldning pf : controller.getStorage().getPåfyldninger()) {
            if (pf.klarTilAftapning(LocalDate.now()) && pf.getLiterPåfyldt() != 0){
                påfyldningMed3År.add(pf);
            }
        }
        return påfyldningMed3År;
    }
    public GridPane getNextPane() {return nextPane;}

    public Påfyldning getPåfyldning() {return påfyldning;}

    public Label getLblOverskrift() {
        return lblOverskrift;
    }

    public DatePicker getDatePickerOprettelsesdato() {
        return datePickerOprettelsesdato;
    }

    public GridPane getOpretWhiskyProduktPane() {
        return opretWhiskyProduktPane;
    }

    public GridPane getOpretWhiskyProduktInfoPane() {
        return opretWhiskyProduktInfoPane;
    }

    public ListView<Påfyldning> getLvwMuligePåfyldninger() {
        return lvwMuligePåfyldninger;
    }

    public ListView<Påfyldning> getLvwValgtePåfyldninger() {
        return lvwValgtePåfyldninger;
    }

    public TextField getTxfNavn() {
        return txfNavn;
    }

    public WhiskyProdukt getWhiskyProdukt() {
        return whiskyProdukt;
    }

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    public TextField getTxfVand() {
        return txfVand;
    }
    public double getVandMængde(){
        if (!txfVand.getText().isEmpty()){
            return Double.parseDouble(txfVand.getText());
        }
        return 0;
    }
}

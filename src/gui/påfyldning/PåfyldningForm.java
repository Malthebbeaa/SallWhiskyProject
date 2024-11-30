package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Mængde;
import application.model.Påfyldning;
import gui.PaneCreator;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PåfyldningForm {
    private Controller controller;
    private ComboBox<Destillering> cboxDestillering;
    private ComboBox<Fad> cboxFad;
    //private ListView<Destillering> lvwMuligeDestilleringer, lveValgtDestilleringer;
    private DatePicker datePickerPåfyldningsDato;
    private TextField txfMængdeTilPåfyldning;
    private GridPane påfyldningsPane, påfyldningsInfoPane, nextPane;
    private ListView<Destillering> lvwMuligeDestilleringer;
    private ListView<Destillering> lveValgtDestilleringer;
    private List<Mængde> mængder;
    private Label lblOverskrift;
    private Påfyldning påfyldning;


    public PåfyldningForm(Controller controller, PåfyldningHandler handler) {
        this.controller = controller;
        påfyldningsPane = new GridPane();
        mængder = new ArrayList<>();
        initForm(handler);
    }

    public void initForm(PåfyldningHandler handler) {
        påfyldningsInfoPane = new PaneCreator();
        lblOverskrift = new Label("Påfyld fad:");
        påfyldningsPane.add(lblOverskrift, 0,0);
        påfyldningsPane.add(påfyldningsInfoPane, 0,1);
        påfyldningsPane.setHgap(10);
        påfyldningsPane.setVgap(10);

        //Vælg fad og påfyldningsdato pane
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        Label lblFad = new Label("Vælg Fad:");
        cboxFad = new ComboBox<>();
        cboxFad.setItems(controller.getStorage().getFade());
        pane.add(lblFad,0,0);
        pane.add(cboxFad, 0,1);
        Label lblPåfyldningsDato = new Label("Påfyldningsdato: ");
        datePickerPåfyldningsDato = new DatePicker(LocalDate.now());
        pane.add(lblPåfyldningsDato, 0,2);
        pane.add(datePickerPåfyldningsDato, 0,3);
        påfyldningsInfoPane.add(pane,0,0,1,3);
    }

    public void initNextForm(PåfyldningHandler handler, Påfyldning påfyldning){
        //Valg af destilleringer og mængde popup
        this.påfyldning = påfyldning;
        nextPane = new PaneCreator();
        lblOverskrift.setText("Vælg destilleringer og Mængder");
        påfyldningsPane.add(nextPane, 0,1);

        Label lblDestillering = new Label("Vælg destillering(er):");
        nextPane.add(lblDestillering, 1,0);
        lvwMuligeDestilleringer = new ListView<>();
        lveValgtDestilleringer = new ListView<>();
        lvwMuligeDestilleringer.getItems().addAll(controller.getStorage().getDestilleringer());
        lveValgtDestilleringer.setPrefSize(250,100);
        lvwMuligeDestilleringer.setPrefSize(250,100);


        Button btnAddSelected = new Button("Vælg");
        btnAddSelected.setOnAction(e -> {
            handler.vælgAction(this, påfyldning);
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


        nextPane.add(lvwMuligeDestilleringer, 1,1);
        nextPane.add(lveValgtDestilleringer, 3,1);
        nextPane.add(buttonBox, 2, 1);
    }

    public Fad getFad() {return cboxFad.getValue();}

    public LocalDate getPåfyldningsDato() {return datePickerPåfyldningsDato.getValue();}

    public double getTxfMængdeTilPåfyldning() {return Double.parseDouble(txfMængdeTilPåfyldning.getText());}

    public ComboBox<Fad> getCboxFad(){return cboxFad;}
    public DatePicker getDatePickerPåfyldningsDato(){return datePickerPåfyldningsDato;}
    public GridPane getPåfyldningsPane() {return påfyldningsPane;}
    public ListView<Destillering> getLvwMuligeDestilleringer(){return lvwMuligeDestilleringer;}
    public ListView<Destillering> getLveValgtDestilleringer(){return lveValgtDestilleringer;}
    public List<Mængde> getMængder(){return mængder;}

    public GridPane getNextPane() {return nextPane;}

    public Påfyldning getPåfyldning() {return påfyldning;}

    public Label getLblOverskrift() {
        return lblOverskrift;
    }
}


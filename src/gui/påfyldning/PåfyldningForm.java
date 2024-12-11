package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Væske;
import application.model.VæskeMix;
import gui.GuiObserver;
import gui.GuiSubject;
import gui.PaneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PåfyldningForm implements GuiObserver {
    private Controller controller;
    private ComboBox<Destillering> cboxDestillering;
    private ComboBox<Fad> cboxFad;
    private DatePicker datePickerPåfyldningsDato;
    private TextField txfMængdeTilPåfyldning;
    private GridPane påfyldningsPane, påfyldningsInfoPane, nextPane;
    private ListView<Destillering> lvwMuligeDestilleringer;
    private ListView<Destillering> lveValgtDestilleringer;
    private List<Væske> mængder;
    private Label lblOverskrift, lblFadInfo;
    private VæskeMix væskeMix;


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
        cboxFad.setVisibleRowCount(3);
        //fade der kan fyldes
        ObservableList<Fad> tommeFade = FXCollections.observableArrayList();
        for (Fad fad : controller.getStorage().getFade()) {
            if (fad.getMængdeFyldtPåFad() != fad.getStørrelse()){
                tommeFade.add(fad);
            }
        }

        cboxFad.setItems(tommeFade);
        pane.add(lblFad,0,0);
        pane.add(cboxFad, 0,1);
        Label lblPåfyldningsDato = new Label("Påfyldningsdato: ");
        datePickerPåfyldningsDato = new DatePicker(LocalDate.now());
        pane.add(lblPåfyldningsDato, 0,2);
        pane.add(datePickerPåfyldningsDato, 0,3);
        påfyldningsInfoPane.add(pane,0,0,1,3);
    }

    public void initNextForm(PåfyldningHandler handler, VæskeMix væskeMix){
        //Valg af destilleringer og mængde popup
        this.væskeMix = væskeMix;
        nextPane = new PaneCreator();
        lblOverskrift.setText("Påfyldning af fadnr " + væskeMix.getFad().getFadId() + " som har plads til " +
                væskeMix.getLiterPåfyldt() + " L");
        påfyldningsPane.layout();
        påfyldningsPane.add(nextPane, 0,1);

        Label lblDestillering = new Label("Vælg destillering(er):");
        //nextPane.add(lblDestillering, 1,0);
        lblFadInfo = new Label("Antal liter på fad: " + cboxFad.getSelectionModel().getSelectedItem().getMængdeFyldtPåFad() +
                 " ud af " + cboxFad.getSelectionModel().getSelectedItem().getStørrelse() + " liter");
        lblFadInfo.setFont(new Font(16));
        HBox hBoxØvreInfo = new HBox(25);
        hBoxØvreInfo.getChildren().addAll(lblDestillering, lblFadInfo);
        nextPane.add(hBoxØvreInfo, 1,0);

        lvwMuligeDestilleringer = new ListView<>();
        lveValgtDestilleringer = new ListView<>();

        ObservableList<Destillering> fyldteDestilleringer = FXCollections.observableArrayList();
        for (Destillering destillering : controller.getStorage().getDestilleringer()){
            if (destillering.getVæskeMængde() != 0){
                fyldteDestilleringer.add(destillering);
            }
        }
        lvwMuligeDestilleringer.getItems().addAll(fyldteDestilleringer);
        lveValgtDestilleringer.setPrefSize(250,100);
        lvwMuligeDestilleringer.setPrefSize(250,100);


        Button btnAddSelected = new Button("Vælg");
        btnAddSelected.setOnAction(e -> {
            handler.vælgAction(this, væskeMix);
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
    public List<Væske> getMængder(){return mængder;}

    public GridPane getNextPane() {return nextPane;}

    public VæskeMix getPåfyldning() {return væskeMix;}

    public Label getLblOverskrift() {
        return lblOverskrift;
    }
    public Label getLblFadInfo(){return lblFadInfo;}
    public void tommefade(){
        cboxFad.getItems().clear();

        ObservableList<Fad> tommeFade = FXCollections.observableArrayList();
        for (Fad fad : controller.getStorage().getFade()) {
            if (fad.getMængdeFyldtPåFad() != fad.getStørrelse()){
                tommeFade.add(fad);
            }
        }

        cboxFad.setItems(tommeFade);

    }

    @Override
    public void update(GuiSubject s) {
        tommefade();
    }
}


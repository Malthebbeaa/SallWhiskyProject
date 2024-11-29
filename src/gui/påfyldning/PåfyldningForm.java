package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Mængde;
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
    private GridPane påfyldningsPane, påfyldningsInfoPane;
    private ListView<Destillering> lvwMuligeDestilleringer;
    private ListView<Destillering> lveValgtDestilleringer;
    private List<Mængde> mængder;


    public PåfyldningForm(Controller controller) {
        this.controller = controller;
        påfyldningsPane = new GridPane();
        mængder = new ArrayList<>();
        initForm();
    }

    private void initForm() {
        påfyldningsInfoPane = new PaneCreator();
        Label lblPåfyldning = new Label("Påfyld fad:");
        påfyldningsPane.add(lblPåfyldning, 0,0);
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

        //valg af destilleringer og mængde
        Label lblDestillering = new Label("Vælg destillering(er):");
        påfyldningsInfoPane.add(lblDestillering, 1,0);
        lvwMuligeDestilleringer = new ListView<>();
        lveValgtDestilleringer = new ListView<>();
        lvwMuligeDestilleringer.getItems().addAll(controller.getStorage().getDestilleringer());
        lveValgtDestilleringer.setPrefSize(250,100);
        lvwMuligeDestilleringer.setPrefSize(250,100);


        Button btnAddSelected = new Button("Vælg");
        btnAddSelected.setOnAction(e -> {
            vælgAction();
        });
        Button btnRemoveSelected = new Button("Fravælg");
        btnRemoveSelected.setOnAction(e -> {
            fravælgAction();
        });
        Button btnRemoveAll = new Button("Fravælg alle");
        btnRemoveAll.setOnAction(e -> {
            removeAllAction();
        });
        VBox buttonBox = new VBox(5, btnAddSelected, btnRemoveSelected, btnRemoveAll);


        påfyldningsInfoPane.add(lvwMuligeDestilleringer, 1,1);
        påfyldningsInfoPane.add(lveValgtDestilleringer, 3,1);
        påfyldningsInfoPane.add(buttonBox, 2, 1);
    }



    public Fad getFad() {return cboxFad.getValue();}

    public LocalDate getPåfyldningsDato() {return datePickerPåfyldningsDato.getValue();}

    public double getTxfMængdeTilPåfyldning() {return Double.parseDouble(txfMængdeTilPåfyldning.getText());}

    public GridPane getPåfyldningsPane() {return påfyldningsPane;}
    public ListView<Destillering> getLvwMuligeDestilleringer(){return lvwMuligeDestilleringer;}
    public ListView<Destillering> getLveValgtDestilleringer(){return lveValgtDestilleringer;}
    public List<Mængde> getMængder(){return mængder;}
    public void removeAllAction(){
        lvwMuligeDestilleringer.getItems().addAll(lveValgtDestilleringer.getItems());
        lveValgtDestilleringer.getItems().clear();
    }

    private void vælgAction() {
        Destillering selected = lvwMuligeDestilleringer.getSelectionModel().getSelectedItem();
        MængdePopUpWindow popUpWindow = new MængdePopUpWindow("Afgiv mængde", selected);
        popUpWindow.showAndWait();
        //hvis det ikke er udfyldt
        if (popUpWindow.getMængde() != 0){
            if (selected != null){
                lveValgtDestilleringer.getItems().add(selected);
                lvwMuligeDestilleringer.getItems().remove(selected);
                Mængde mængde = new Mængde(popUpWindow.getMængde(), selected);
                mængder.add(mængde);
            }
        }
    }
    public void fravælgAction(){
        Destillering selected = lveValgtDestilleringer.getSelectionModel().getSelectedItem();
        if (selected != null){
            lvwMuligeDestilleringer.getItems().add(selected);
            lveValgtDestilleringer.getItems().remove(selected);
        }
    }
    public void clearAction(){
        cboxFad.setValue(null);
        datePickerPåfyldningsDato.setValue(LocalDate.now());
        removeAllAction();
    }
}


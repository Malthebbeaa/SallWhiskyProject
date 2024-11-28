package gui.påfyldning;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import gui.PaneCreator;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class PåfyldningForm {
    private Controller controller;
    private ComboBox<Destillering> cboxDestillering;
    private ComboBox<Fad> cboxFad;
    private DatePicker datePickerPåfyldningsDato;
    private TextField txfMængdeTilPåfyldning;
    private GridPane påfyldningsPane, påfyldningsInfoPane;

    public PåfyldningForm(Controller controller) {
        this.controller = controller;
        påfyldningsPane = new GridPane();
        initForm();
    }

    private void initForm() {
        påfyldningsInfoPane = new PaneCreator();
        Label lblPåfyldning = new Label("Påfyld fad:");
        påfyldningsPane.add(lblPåfyldning, 0,0);
        påfyldningsPane.add(påfyldningsInfoPane, 0,1);
        påfyldningsPane.setHgap(10);
        påfyldningsPane.setVgap(10);

        Label lblDestillering = new Label("Vælg destillering:");
        påfyldningsInfoPane.add(lblDestillering, 0,0);
        cboxDestillering = new ComboBox<>();
        cboxDestillering.setItems(controller.getStorage().getDestilleringer());
        påfyldningsInfoPane.add(cboxDestillering, 1,0);

        Label lblFad = new Label("Vælg Fad:");
        påfyldningsInfoPane.add(lblFad, 2,0);
        cboxFad = new ComboBox<>();
        cboxFad.setItems(controller.getStorage().getFade());
        påfyldningsInfoPane.add(cboxFad,3,0);


        Label lblPåfyldningsDato = new Label("Påfyldningsdato: ");
        påfyldningsInfoPane.add(lblPåfyldningsDato, 0,1);
        datePickerPåfyldningsDato = new DatePicker(LocalDate.now());
        påfyldningsInfoPane.add(datePickerPåfyldningsDato, 1,1);

        Label lblPåfyldningsMængde = new Label("Mængde til påfyldning (L): ");
        påfyldningsInfoPane.add(lblPåfyldningsMængde, 2,1);
        txfMængdeTilPåfyldning = new TextField();
        txfMængdeTilPåfyldning.setPromptText("Eks. 90");
        txfMængdeTilPåfyldning.setMaxWidth(75);
        påfyldningsInfoPane.add(txfMængdeTilPåfyldning, 3,1);


    }

    public Destillering getDestillering() {return cboxDestillering.getValue();}
    public Fad getFad() {return cboxFad.getValue();}

    public LocalDate getPåfyldningsDato() {return datePickerPåfyldningsDato.getValue();}

    public double getTxfMængdeTilPåfyldning() {return Double.parseDouble(txfMængdeTilPåfyldning.getText());}

    public GridPane getPåfyldningsPane() {return påfyldningsPane;}

    public void clearAction(){
        cboxDestillering.setValue(null);
        cboxFad.setValue(null);
        datePickerPåfyldningsDato.setValue(LocalDate.now());
        txfMængdeTilPåfyldning.clear();
    }
}


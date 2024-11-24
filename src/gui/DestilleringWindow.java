package gui;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import storage.StorageInterface;

import java.time.LocalDate;
import java.util.List;

public class DestilleringWindow {
    private GridPane startPane, destilleringPane;
    private Scene scene;
    private StorageInterface storage;
    private Controller controller;
    private DatePicker datepickerstartDato, datepickerSlutDato;
    private TextField txfAlkoholProcent, txfVæskeMængde, txfAntalDestilleringer;
    private ListView listviewMaltbatches;


    public DestilleringWindow(GridPane startPane, Scene scene, StorageInterface storage) {
        controller = new Controller(storage);
        this.storage = storage;
        this.startPane = startPane;
        this.scene = scene;
        destilleringPane = new GridPane();
        this.initContent(destilleringPane);
    }

    private void initContent(GridPane destilleringPane) {
        destilleringPane.setHgap(10);
        destilleringPane.setVgap(10);
        destilleringPane.setPadding(new Insets(20));

        Label lblMaltbatch = new Label("Maltbatch(es): ");
        listviewMaltbatches = new ListView<>();
        Mark nybogård = new Mark("Nybogård", true);
        Korn evergreen = new Korn(LocalDate.now(), "Evergreen", nybogård);
        listviewMaltbatches.getItems().addAll(new Maltbatch("FM2232", 300,evergreen), new Maltbatch("FM2333", 1000,evergreen), new Maltbatch("FM2032", 2300,evergreen));
        listviewMaltbatches.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listviewMaltbatches.setPrefHeight(50);
        listviewMaltbatches.setPrefWidth(200);
        destilleringPane.add(lblMaltbatch, 0,0);
        destilleringPane.add(listviewMaltbatches, 1,0);

        Label lblAntalDestilleringer = new Label("Antal destilleringer: ");
        txfAntalDestilleringer = new TextField();
        txfAntalDestilleringer.setPrefWidth(30);
        destilleringPane.add(lblAntalDestilleringer, 2,0);
        destilleringPane.add(txfAntalDestilleringer, 3,0);

        Label lblStartDato = new Label("Startdato: ");
        datepickerstartDato = new DatePicker(LocalDate.now());
        destilleringPane.add(lblStartDato, 0,1);
        destilleringPane.add(datepickerstartDato, 1,1);
        Label lblSlutDato = new Label("Slutdato: ");
        datepickerSlutDato = new DatePicker(LocalDate.now());
        destilleringPane.add(lblSlutDato, 2,1);
        destilleringPane.add(datepickerSlutDato, 3,1);

        Label lblVæskeMængde = new Label("Væskemængde (L): ");
        destilleringPane.add(lblVæskeMængde, 0,2);
        txfVæskeMængde = new TextField();
        destilleringPane.add(txfVæskeMængde, 1,2);

        Label lblAlkoholProcent = new Label("Alkoholprocent: ");
        destilleringPane.add(lblAlkoholProcent, 2,2);
        txfAlkoholProcent = new TextField();
        destilleringPane.add(txfAlkoholProcent, 3,2);


        Button btnOpret = new Button("Opret Destillering");
        btnOpret.setOnAction(e -> opretAction());
        destilleringPane.add(btnOpret, 0,3);

        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> afbrydAction());
        destilleringPane.add(btnAfbryd,1,3);
    }

    private void opretAction() {
        int antalDestilleringer = Integer.parseInt(txfAntalDestilleringer.getText());
        LocalDate startDato = datepickerstartDato.getValue();
        LocalDate slutDato = datepickerSlutDato.getValue();
        double alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
        double væskeMængde = Double.parseDouble(txfVæskeMængde.getText());
        List<Maltbatch> maltbatches = listviewMaltbatches.getItems();


        Destillering destillering = controller.opretDestillering(antalDestilleringer,startDato,slutDato,væskeMængde,alkoholProcent,maltbatches);
    }

    public GridPane getDestilleringPane() {
        return destilleringPane;
    }

    public void afbrydAction(){
        scene.setRoot(startPane);
    }
}

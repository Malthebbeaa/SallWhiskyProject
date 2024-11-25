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
    private TextField txfAlkoholProcent, txfVæskeMængde, txfAntalDestilleringer, txfKommentar;
    private ComboBox<Maltbatch> comboBoxMaltbatch;


    public DestilleringWindow(GridPane startPane, Scene scene, Controller controller) {
        this.controller = controller;
        this.storage = controller.getStorage();
        this.startPane = startPane;
        this.scene = scene;
        destilleringPane = new GridPane();
        this.initContent(destilleringPane);
    }

    private void initContent(GridPane destilleringPane) {
        destilleringPane.setHgap(10);
        destilleringPane.setVgap(10);
        destilleringPane.setPadding(new Insets(20));

        Label lblMaltbatch = new Label("Maltbatch: ");
        comboBoxMaltbatch = new ComboBox();
        comboBoxMaltbatch.getItems().addAll(controller.getStorage().getMaltbatches());
        destilleringPane.add(lblMaltbatch, 0,0);
        destilleringPane.add(comboBoxMaltbatch, 1,0);

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

        Label lblKommentar = new Label("Evt. kommentar: ");
        destilleringPane.add(lblKommentar, 0,3);
        txfKommentar = new TextField();
        destilleringPane.add(txfKommentar, 1,3, 3,1);

        Button btnOpret = new Button("Opret Destillering");
        btnOpret.setOnAction(e -> opretAction());
        destilleringPane.add(btnOpret, 0,4);

        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(e -> afbrydAction());
        destilleringPane.add(btnAfbryd,1,4);
    }

    private void opretAction() {
        int antalDestilleringer = Integer.parseInt(txfAntalDestilleringer.getText());
        LocalDate startDato = datepickerstartDato.getValue();
        LocalDate slutDato = datepickerSlutDato.getValue();
        double alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
        double væskeMængde = Double.parseDouble(txfVæskeMængde.getText());
        Maltbatch maltbatch = comboBoxMaltbatch.getValue();

        Destillering destillering = controller.opretDestillering(antalDestilleringer,startDato,slutDato,væskeMængde,alkoholProcent,maltbatch);

        if (txfKommentar.getText() != null){
            controller.tilføjKommentarTilDestillering(txfKommentar.getText(), destillering);
        }

        afbrydAction();
    }

    public GridPane getDestilleringPane() {
        return destilleringPane;
    }

    /***
     * clearer textfields og sætter datepickers til dagens dato
     */
    public void afbrydAction(){
        datepickerstartDato.setValue(LocalDate.now());
        datepickerSlutDato.setValue(LocalDate.now());
        txfAlkoholProcent.clear();
        txfVæskeMængde.clear();
        txfAntalDestilleringer.clear();
        txfKommentar.clear();
        comboBoxMaltbatch.setValue(null);
    }
}

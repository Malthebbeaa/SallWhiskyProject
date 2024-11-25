package gui;

import application.controller.Controller;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class opretLagerWindow {
    private Controller controller;
    private GridPane lagerPane;
    private TextField txfLagerNavn, txfVejnavn, txfBy, txfPostnummer, txfAntalReoler, txfAntalHylder, txfAntalPladser;
    private ListView<Lager> lvLagre;

    public opretLagerWindow(Controller controller){
        this.controller = controller;
        this.lagerPane = new GridPane();
        initContent();
    }

    public void initContent(){
        lagerPane.setHgap(10);
        lagerPane.setVgap(10);
        lagerPane.setPadding(new Insets(20));

        Label lblLager = new Label("Tilføj Lager:");
        lagerPane.add(lblLager,0,0);
        Label lblOprettedeLagre = new Label("Oprettede Lagre:");
        lagerPane.add(lblOprettedeLagre,1,0);

        GridPane opretLagerPane = new GridPane();
        opretLagerPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        opretLagerPane.setVgap(10);
        opretLagerPane.setHgap(10);
        opretLagerPane.setPadding(new Insets(20,50,50,10));

        Label lblLagerNavn= new Label("Lagernavn:");
        txfLagerNavn = new TextField();
        opretLagerPane.add(lblLagerNavn,0,0);
        opretLagerPane.add(txfLagerNavn,1,0);

        Label lblvejnavn = new Label("Vejnavn:");
        txfVejnavn = new TextField();
        opretLagerPane.add(lblvejnavn,0,1);
        opretLagerPane.add(txfVejnavn,1,1);

        Label lblBynavn = new Label("By:");
        txfBy = new TextField();
        Label lblPostnummer = new Label("Postnummer:");
        txfPostnummer = new TextField();
        opretLagerPane.add(lblBynavn,0,2);
        opretLagerPane.add(txfBy,1,2);
        opretLagerPane.add(lblPostnummer,2,2);
        opretLagerPane.add(txfPostnummer,3,2);

        Label lblAntalReoler = new Label("Antal reoler:");
        txfAntalReoler = new TextField();
        opretLagerPane.add(lblAntalReoler,0,3);
        opretLagerPane.add(txfAntalReoler,1,3);

        Label lblAntalHylder = new Label("Antal hylder:");
        txfAntalHylder = new TextField();
        opretLagerPane.add(lblAntalHylder,0,4);
        opretLagerPane.add(txfAntalHylder,1,4);

        Label lblAntalPladser = new Label("Antal pladser:");
        txfAntalPladser = new TextField();
        opretLagerPane.add(lblAntalPladser,0,5);
        opretLagerPane.add(txfAntalPladser,1,5);

        lagerPane.add(opretLagerPane,0,1);

        lvLagre = new ListView<>();
        lvLagre.getItems().addAll(controller.getStorage().getLager());
        lvLagre.setBorder(Border.stroke(Paint.valueOf("Black")));
        lagerPane.add(lvLagre,1,1);

        Button btnOpretLager = new Button("Opret Lager");
        btnOpretLager.setOnAction(e-> opretLagerAktion());
        Button btnAfbrydAktion = new Button("Afbryd");
        btnAfbrydAktion.setOnAction(e-> afbrydAktion());
        HBox hBoxButtons = new HBox(10);
        hBoxButtons.getChildren().addAll(btnOpretLager,btnAfbrydAktion);
        hBoxButtons.setAlignment(Pos.CENTER);
        lagerPane.add(hBoxButtons,0,2);
    }

    public void opretLagerAktion(){
        String lagerNavn = txfLagerNavn.getText();
        String vejnavn = txfVejnavn.getText();
        String by = txfBy.getText();
        String postnummer = txfPostnummer.getText();
        Lager lager = controller.opretLager(lagerNavn,vejnavn,postnummer,by);

        int antalReoler = Integer.parseInt(txfAntalReoler.getText());
        int antalHylder = Integer.parseInt(txfAntalHylder.getText());
        int antalPladser = Integer.parseInt(txfAntalPladser.getText());

        lager.tilføjReol(antalReoler,antalHylder,antalPladser);
        lvLagre.getItems().clear();
        lvLagre.getItems().addAll(controller.getStorage().getLager());
    }

    public void afbrydAktion(){
        txfLagerNavn.clear();
        txfVejnavn.clear();
        txfBy.clear();
        txfPostnummer.clear();
        txfAntalReoler.clear();
        txfAntalHylder.clear();
        txfAntalPladser.clear();
    }

    public GridPane getLagerPane() {
        return lagerPane;
    }
}

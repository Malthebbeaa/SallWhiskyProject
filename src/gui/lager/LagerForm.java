package gui.lager;

import application.controller.Controller;
import application.model.Hylde;
import application.model.Lager;
import application.model.Plads;
import application.model.Reol;
import gui.PaneCreator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.logging.Handler;

public class LagerForm {
    private TextField txfLagerNavn, txfVejnavn, txfBy, txfPostnummer, txfAntalHylder, txfAntalPladser;
    private ListView<Reol> lvReolView;
    private ListView<Hylde> lvHyldeView;
    private ListView<Plads> lvPladsView;
    private ComboBox<Lager> cbLager, cbLagerReol;
    private ComboBox<Reol> cbReol;
    private GridPane lagerPane;
    private Controller controller;
    private LagerHandler handler;

    public LagerForm(Controller controller, LagerHandler handler) {
        this.controller = controller;
        this.lagerPane = new GridPane();
        this.handler = handler;
        initForm();
    }

    private void initForm() {
        lagerPane.setHgap(10);
        lagerPane.setVgap(10);

        Label lblLager = new Label("Tilføj Lager:");
        lagerPane.add(lblLager, 0, 0);
        Label lblTilføjReol = new Label("Tilføj Reol:");
        lagerPane.add(lblTilføjReol,1,0);
        Label lblTilføjHylde = new Label("Overvåg reoler:");
        lagerPane.add(lblTilføjHylde,2,0);

        PaneCreator opretLagerPane = new PaneCreator();

        Label lblLagerNavn = new Label("Lagernavn:");
        txfLagerNavn = new TextField();
        opretLagerPane.add(lblLagerNavn, 0, 0);
        opretLagerPane.add(txfLagerNavn, 1, 0);

        Label lblvejnavn = new Label("Vejnavn:");
        txfVejnavn = new TextField();
        opretLagerPane.add(lblvejnavn, 0, 1);
        opretLagerPane.add(txfVejnavn, 1, 1);

        Label lblBynavn = new Label("By:");
        txfBy = new TextField();
        Label lblPostnummer = new Label("Postnummer:");
        txfPostnummer = new TextField();
        opretLagerPane.add(lblBynavn, 0, 2);
        opretLagerPane.add(txfBy, 1, 2);
        opretLagerPane.add(lblPostnummer, 0, 3);
        opretLagerPane.add(txfPostnummer, 1, 3);

        PaneCreator reolPane = new PaneCreator();
        Label lblVælgLager = new Label("Vælg lager:");
        reolPane.add(lblVælgLager,0,0);
        cbLager = new ComboBox<>();
        cbLager.setItems(controller.getStorage().getLager());
        cbLager.setValue(controller.getStorage().getLager().getFirst());
        reolPane.add(cbLager,1,0);
        Label lblAntalHylder = new Label("Antal hylder:");
        txfAntalHylder = new TextField();
        reolPane.add(lblAntalHylder, 0, 1);
        reolPane.add(txfAntalHylder, 1, 1);
        Label lblAntalPladser = new Label("Antal pladser:");
        txfAntalPladser = new TextField();
        reolPane.add(lblAntalPladser, 0, 2);
        reolPane.add(txfAntalPladser, 1, 2);
        Button btnOpretReol = new Button("Opret Reol");
        btnOpretReol.setOnAction(e -> handler.opretReolHandler(this));
        lvReolView = new ListView<>();
        lvReolView.setItems(cbLager.getValue().getReoler());
        reolPane.setGraphics(btnOpretReol,"bookcase.gif");
        reolPane.add(btnOpretReol,1,3);
        lagerPane.add(reolPane,1,1);

        PaneCreator hyldeOgPladsPane = new PaneCreator();
        Label lblvælgLagerTilHylde = new Label("Vælg Lager:");
        cbLagerReol = new ComboBox<>();
        cbLagerReol.setItems(controller.getStorage().getLager());
        cbLagerReol.setValue(controller.getStorage().getLager().getFirst());
        hyldeOgPladsPane.add(lblvælgLagerTilHylde, 0, 0);
        hyldeOgPladsPane.add(cbLagerReol, 1, 0);
        Label lblvælgReol = new Label("Vælg Reol:");
        cbReol = new ComboBox<>();
        Lager lager = cbLagerReol.getValue();
        cbReol.setItems(lager.getReoler());
        cbReol.setValue(cbLagerReol.getValue().getReoler().getFirst());
        lvHyldeView = new ListView<>();
        lvHyldeView.setItems(cbReol.getValue().getHylder());
        lvPladsView = new ListView<>();
        hyldeOgPladsPane.add(lblvælgReol, 0, 1);
        hyldeOgPladsPane.add(cbReol, 1, 1);
        hyldeOgPladsPane.add(lvHyldeView,2,0,1,6);
        hyldeOgPladsPane.add(lvPladsView,3,0,1,6);
        lagerPane.add(hyldeOgPladsPane,2,1);

        lagerPane.add(opretLagerPane, 0, 1);

        ChangeListener<Lager> lagerListener = (old, oldLager, newLager) -> selectedLagerChanged();
        cbLagerReol.getSelectionModel().selectedItemProperty().addListener(lagerListener);

        ChangeListener<Reol> reolListener = (old, oldReol, newReol) -> selectedReolChanged();
        cbReol.getSelectionModel().selectedItemProperty().addListener(reolListener);

        ChangeListener<Hylde> pladsViewListener = (old, oldHylde, newHylde) -> selectedHyldeChanged();
        lvHyldeView.getSelectionModel().selectedItemProperty().addListener(pladsViewListener);
    }


    public String getLagerNavn(){return txfLagerNavn.getText();}
    public String getVejnavn(){return txfVejnavn.getText();}
    public String getBy(){return txfBy.getText();}
    public String getPostNummer(){return txfPostnummer.getText();}
    public String getAntalHylder(){return txfAntalHylder.getText();}
    public String getAntalPladser(){return txfAntalPladser.getText();}
    public GridPane getLagerPane(){return lagerPane;}
    public Lager getCbLager(){return cbLager.getValue();}
    public Lager getCbLagerReol(){return cbLagerReol.getValue();}
    public Reol getCbReol(){return cbReol.getValue();}

    public void clearAction() {
        txfLagerNavn.clear();
        txfVejnavn.clear();
        txfBy.clear();
        txfPostnummer.clear();
        txfAntalHylder.clear();
        txfAntalPladser.clear();
    }

    public void selectedLagerChanged(){
        Lager lager = cbLagerReol.getValue();
        cbReol.setItems(lager.getReoler());
    }

    public void selectedReolChanged(){
        Reol reol = cbReol.getValue();
        if(reol == null){
        }
        else {
            lvHyldeView.setItems(reol.getHylder());
        }
    }

    public void selectedHyldeChanged(){
        Hylde hylde = lvHyldeView.getSelectionModel().getSelectedItem();
        lvPladsView.getItems().clear();
        if(hylde == null){
        }
        else {
            lvPladsView.getItems().addAll(hylde.getPladser());
        }
    }
}

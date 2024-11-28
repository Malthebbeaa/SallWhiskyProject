package gui.lager;

import application.controller.Controller;
import application.model.Lager;
import application.model.Reol;
import gui.PaneCreator;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.logging.Handler;

public class LagerForm {
    private TextField txfLagerNavn, txfVejnavn, txfBy, txfPostnummer, txfAntalReoler, txfAntalHylder, txfAntalPladser;
    private ListView<String> lvLagre;
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
        Label lblOprettedeLagre = new Label("Oprettede Lagre:");
        lagerPane.add(lblOprettedeLagre, 2, 0);

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
        cbLager = new ComboBox<>();
        cbLager.getItems().addAll(controller.getStorage().getLager());
        Label lblAntalReoler = new Label("Antal reoler:");
        txfAntalReoler = new TextField();
        Button btnOpretReol = new Button("Opret Reol");
        btnOpretReol.setOnAction(e -> handler.opretReolHandler(this));
        reolPane.add(lblVælgLager,0,0);
        reolPane.add(cbLager,1,0);
        reolPane.add(lblAntalReoler, 0,1);
        reolPane.add(txfAntalReoler, 1, 1);
        reolPane.add(btnOpretReol,1,2);
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
        cbReol.setItems(cbLagerReol.getValue().getReoler());
        hyldeOgPladsPane.add(lblvælgReol, 0, 1);
        hyldeOgPladsPane.add(cbReol, 1, 1);
        Label lblAntalHylder = new Label("Antal hylder:");
        txfAntalHylder = new TextField();
        hyldeOgPladsPane.add(lblAntalHylder, 0, 2);
        hyldeOgPladsPane.add(txfAntalHylder, 1, 2);
        Label lblAntalPladser = new Label("Antal pladser:");
        txfAntalPladser = new TextField();
        hyldeOgPladsPane.add(lblAntalPladser, 0, 3);
        hyldeOgPladsPane.add(txfAntalPladser, 1, 3);
        Button btnOpretHylde = new Button("Opret Reol");
        btnOpretHylde.setOnAction(e -> handler.opretHyldeHandler(this));
        hyldeOgPladsPane.add(btnOpretHylde,0,4);
        lagerPane.add(hyldeOgPladsPane,2,1);

        lagerPane.add(opretLagerPane, 0, 1);

        lvLagre = new ListView<>();
        for (Lager l : controller.getStorage().getLager()) {
            lvLagre.getItems().add(l.toString() + " ledige pladser: " + l.ledigePladser());
        }
        lvLagre.setBorder(Border.stroke(Paint.valueOf("Black")));
        lagerPane.add(lvLagre, 3, 1);
    }


    public String getLagerNavn(){return txfLagerNavn.getText();}
    public String getVejnavn(){return txfVejnavn.getText();}
    public String getBy(){return txfBy.getText();}
    public String getPostNummer(){return txfPostnummer.getText();}
    public int getAntalReoler(){return Integer.parseInt(txfAntalReoler.getText());}
    public int getAntalHylder(){return Integer.parseInt(txfAntalHylder.getText());}
    public int getAntalPladser(){return Integer.parseInt(txfAntalPladser.getText());}
    public GridPane getLagerPane(){return lagerPane;}
    public ListView<String> getLvLagre(){return lvLagre;}
    public Lager getCbLager(){return cbLager.getValue();}
    public Lager getCbLagerReol(){return cbLagerReol.getValue();}
    public Reol getCbReol(){return cbReol.getValue();}

    public void clearAction() {
        txfLagerNavn.clear();
        txfVejnavn.clear();
        txfBy.clear();
        txfPostnummer.clear();
        txfAntalReoler.clear();
        txfAntalHylder.clear();
        txfAntalPladser.clear();
    }

}

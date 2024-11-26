package gui.lager;

import application.controller.Controller;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class LagerForm {
    private TextField txfLagerNavn, txfVejnavn, txfBy, txfPostnummer, txfAntalReoler, txfAntalHylder, txfAntalPladser;
    private ListView<Lager> lvLagre;
    private GridPane lagerPane;
    private Controller controller;

    public LagerForm(Controller controller) {
        this.controller = controller;
        this.lagerPane = new GridPane();
        initForm();
    }

    private void initForm() {
        lagerPane.setHgap(10);
        lagerPane.setVgap(10);
        lagerPane.setPadding(new Insets(20));

        Label lblLager = new Label("Tilføj Lager:");
        lagerPane.add(lblLager, 0, 0);
        Label lblOprettedeLagre = new Label("Oprettede Lagre:");
        lagerPane.add(lblOprettedeLagre, 1, 0);

        GridPane opretLagerPane = new GridPane();
        opretLagerPane.setBorder(Border.stroke(Paint.valueOf("Black")));
        opretLagerPane.setVgap(10);
        opretLagerPane.setHgap(10);
        opretLagerPane.setPadding(new Insets(20, 50, 50, 10));

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
        opretLagerPane.add(lblPostnummer, 2, 2);
        opretLagerPane.add(txfPostnummer, 3, 2);

        Label lblAntalReoler = new Label("Antal reoler:");
        txfAntalReoler = new TextField();
        opretLagerPane.add(lblAntalReoler, 0, 3);
        opretLagerPane.add(txfAntalReoler, 1, 3);

        Label lblAntalHylder = new Label("Antal hylder:");
        txfAntalHylder = new TextField();
        opretLagerPane.add(lblAntalHylder, 0, 4);
        opretLagerPane.add(txfAntalHylder, 1, 4);

        Label lblAntalPladser = new Label("Antal pladser:");
        txfAntalPladser = new TextField();
        opretLagerPane.add(lblAntalPladser, 0, 5);
        opretLagerPane.add(txfAntalPladser, 1, 5);

        lagerPane.add(opretLagerPane, 0, 1);

        lvLagre = new ListView<>();
        lvLagre.getItems().addAll(controller.getStorage().getLager());
        lvLagre.setBorder(Border.stroke(Paint.valueOf("Black")));
        lagerPane.add(lvLagre, 1, 1);
    }


    public String getLagerNavn(){return txfLagerNavn.getText();}
    public String getVejnavn(){return txfVejnavn.getText();}
    public String getBy(){return txfBy.getText();}
    public String getPostNummer(){return txfPostnummer.getText();}
    public int getAntalReoler(){return Integer.parseInt(txfAntalReoler.getText());}
    public int getAntalHylder(){return Integer.parseInt(txfAntalHylder.getText());}
    public int getAntalPladser(){return Integer.parseInt(txfAntalPladser.getText());}
    public GridPane getLagerPane(){return lagerPane;}
    public ListView<Lager> getLvLagre(){return lvLagre;}
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

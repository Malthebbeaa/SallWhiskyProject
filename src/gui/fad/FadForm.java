package gui.fad;

import application.controller.Controller;
import application.model.FadLeverandør;
import gui.PaneCreator;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class FadForm {
    private GridPane fadInfoPane, fadPane;
    private Controller controller;
    private TextField txfAlder, txfAntalGangeBrugt;
    private ComboBox comboboxLeverandører, comboboxStørrelse, comboboxMateriale, comboboxTidligereIndhold;


    public FadForm(Controller controller) {
        this.controller = controller;
        this.fadPane = new GridPane();

        initForm();
    }

    private void initForm() {
        fadInfoPane = new PaneCreator();

        Label lblOpret = new Label("Opret Fad:");
        fadPane.add(lblOpret, 0, 0);
        fadPane.add(fadInfoPane, 0, 1);
        fadPane.setHgap(10);
        fadPane.setVgap(10);


        Label lblLeverandør = new Label("Leverandør: ");
        comboboxLeverandører = new ComboBox();
        comboboxLeverandører.getItems().addAll(new FadLeverandør("La Barril", "Spanien"),
                new FadLeverandør("El Gordo y Pobre", "Spanien"),
                new FadLeverandør("Barrel Land", "USA"),
                new FadLeverandør("Le Ivre et Belle", "Frankrig"));
        comboboxLeverandører.setPrefHeight(20);
        comboboxLeverandører.setPrefWidth(200);
        fadInfoPane.add(lblLeverandør, 0, 0);
        fadInfoPane.add(comboboxLeverandører, 0, 1);

        Label lblStørrelse = new Label("Størrelse: ");
        comboboxStørrelse = new ComboBox<Double>();
        comboboxStørrelse.getItems().addAll(32, 94, 130);
        comboboxStørrelse.setPrefHeight(20);
        comboboxStørrelse.setPrefWidth(200);
        fadInfoPane.add(lblStørrelse, 1, 0);
        fadInfoPane.add(comboboxStørrelse, 1, 1);

        Label lblMateriale = new Label("Materiale: ");
        comboboxMateriale = new ComboBox();
        comboboxMateriale.getItems().addAll("Eg", "Kirsebærtræ", "Bøgetræ");
        comboboxMateriale.setPrefHeight(20);
        comboboxMateriale.setPrefWidth(200);
        fadInfoPane.add(lblMateriale, 2, 0);
        fadInfoPane.add(comboboxMateriale, 2, 1);

        Label lblTidligereIndhold = new Label("Tidligere indhold: ");
        comboboxTidligereIndhold = new ComboBox();
        comboboxTidligereIndhold.getItems().addAll("Sherry", "Bourbon", "Rødvin");
        comboboxTidligereIndhold.setPrefHeight(20);
        comboboxTidligereIndhold.setPrefWidth(200);
        fadInfoPane.add(lblTidligereIndhold, 0, 2);
        fadInfoPane.add(comboboxTidligereIndhold, 0, 3);

        Label lblAlder = new Label("Tøndens alder: ");
        txfAlder = new TextField();
        txfAlder.setPrefWidth(30);
        fadInfoPane.add(lblAlder, 1, 2);
        fadInfoPane.add(txfAlder, 1, 3);

        Label lblAntalGangeBrugt = new Label("Antal gange den har været brugt: ");
        txfAntalGangeBrugt = new TextField();
        txfAntalGangeBrugt.setPrefWidth(30);
        fadInfoPane.add(lblAntalGangeBrugt, 2, 2);
        fadInfoPane.add(txfAntalGangeBrugt, 2, 3);
    }

    public int getTøndensAlder() {
        if (txfAlder.getText().isEmpty()) {
            throw new RuntimeException("Du skal angive tøndens alder");
        }
        return Integer.parseInt(txfAlder.getText());
    }

    public int getTøndensStørrelse() {
        if (comboboxStørrelse.getValue() == null) {
            throw new RuntimeException("Du skal vælge en størrelse");
        }
        return (int) comboboxStørrelse.getValue();
    }

    public int getAntalGangeBrugt() {
        if (txfAntalGangeBrugt.getText().isEmpty()) {
            throw new RuntimeException("Du skal angive antal gange fadet er brugt");
        }
        return Integer.parseInt(txfAntalGangeBrugt.getText());
    }

    public FadLeverandør getFadLeverandør() {
        if (comboboxLeverandører.getValue() == null) {
            throw new RuntimeException("Du skal vælge en fad leverandør");
        }
        return (FadLeverandør) comboboxLeverandører.getValue();
    }

    public String getTidligerIndhold() {
        if (comboboxTidligereIndhold.getValue() == null) {
            throw new RuntimeException("Du skal det tidligere indhold");
        }
        return (String) comboboxTidligereIndhold.getValue();
    }

    public String getMateriale() {
        if (comboboxMateriale.getValue() == null) {
            throw new RuntimeException("Du skal vælge et materiale");
        }
        return (String) comboboxMateriale.getValue();
    }

    public GridPane getFadPane() {
        return fadPane;
    }

    public TextField getTxfAlder() {
        return txfAlder;
    }

    public TextField getTxfAntalGangeBrugt() {
        return txfAntalGangeBrugt;
    }

    public ComboBox getComboboxLeverandører() {
        return comboboxLeverandører;
    }

    public ComboBox getComboboxStørrelse() {
        return comboboxStørrelse;
    }

    public ComboBox getComboboxMateriale() {
        return comboboxMateriale;
    }

    public ComboBox getComboboxTidligereIndhold() {
        return comboboxTidligereIndhold;
    }
}

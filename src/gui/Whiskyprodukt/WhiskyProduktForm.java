package gui.Whiskyprodukt;

import application.controller.Controller;
import application.model.Fad;
import application.model.FadLeverandør;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class WhiskyProduktForm {
    private GridPane fadInfoPane, fadPane;
    private Controller controller;
    private TextField txfAlder, txfAntalGangeBrugt;
    private ComboBox comboboxFad, comboboxStørrelse, comboboxMateriale, comboboxTidligereIndhold;


    public WhiskyProduktForm(Controller controller){
        this.controller = controller;
        this.fadPane = new GridPane();

        initForm();
    }

    private void initForm() {
        fadInfoPane = new GridPane();
        fadInfoPane.setHgap(10);
        fadInfoPane.setVgap(10);
        fadInfoPane.setPadding(new Insets(20,50,50,10));
        fadInfoPane.setBorder(Border.stroke(Paint.valueOf("Black")));

        Label lblOpret = new Label("Opret Fad:");
        fadPane.add(lblOpret,0,0);
        fadPane.add(fadInfoPane, 0,1);
        fadPane.setHgap(10);
        fadPane.setVgap(10);


        Label lblLeverandør = new Label("Leverandør: ");
        comboboxFad = new ComboBox();
        comboboxFad.getItems().addAll(new Fad(94, "Eg", new FadLeverandør("La Barril", "Spanien")
                , "Rødvin", 36, 0));
        comboboxFad.setPrefHeight(20);
        comboboxFad.setPrefWidth(200);
        fadInfoPane.add(lblLeverandør, 0,0);
        fadInfoPane.add(comboboxFad, 0,1);

        Label lblStørrelse = new Label("Størrelse: ");
        comboboxStørrelse = new ComboBox<Double>();
        comboboxStørrelse.getItems().addAll(32, 94, 130);
        comboboxStørrelse.setPrefHeight(20);
        comboboxStørrelse.setPrefWidth(200);
        fadInfoPane.add(lblStørrelse, 1,0);
        fadInfoPane.add(comboboxStørrelse, 1,1);

        Label lblMateriale = new Label("Materiale: ");
        comboboxMateriale = new ComboBox();
        comboboxMateriale.getItems().addAll("Eg", "Kirsebærtræ", "Bøgetræ");
        comboboxMateriale.setPrefHeight(20);
        comboboxMateriale.setPrefWidth(200);
        fadInfoPane.add(lblMateriale, 2,0);
        fadInfoPane.add(comboboxMateriale, 2,1);

        Label lblTidligereIndhold = new Label("Tidligere indhold: ");
        comboboxTidligereIndhold = new ComboBox();
        comboboxTidligereIndhold.getItems().addAll("Sherry", "Bourbon", "Rødvin");
        comboboxTidligereIndhold.setPrefHeight(20);
        comboboxTidligereIndhold.setPrefWidth(200);
        fadInfoPane.add(lblTidligereIndhold, 0,2);
        fadInfoPane.add(comboboxTidligereIndhold, 0,3);

        Label lblAlder = new Label("Tøndens alder: ");
        txfAlder = new TextField();
        txfAlder.setPrefWidth(30);
        fadInfoPane.add(lblAlder, 1,2);
        fadInfoPane.add(txfAlder, 1,3);

        Label lblAntalGangeBrugt = new Label("Antal gange den har været brugt: ");
        txfAntalGangeBrugt = new TextField();
        txfAntalGangeBrugt.setPrefWidth(30);
        fadInfoPane.add(lblAntalGangeBrugt, 2,2);
        fadInfoPane.add(txfAntalGangeBrugt, 2,3);
    }

    public int getTøndensAlder(){return Integer.parseInt(txfAlder.getText());}
    public int getTøndensStørrelse(){return (int) comboboxStørrelse.getValue();}
    public int getAntalGangeBrugt(){return Integer.parseInt(txfAntalGangeBrugt.getText());}
    public FadLeverandør getFadLeverandør(){return (FadLeverandør) comboboxFad.getValue();}
    public String getTidligerIndhold(){return (String) comboboxTidligereIndhold.getValue();}
    public String getMateriale(){return (String) comboboxMateriale.getValue();}
    public GridPane getFadPane(){return fadPane;}

    public void clearAction(){
        txfAlder.clear();
        txfAntalGangeBrugt.clear();

        comboboxFad.setValue(null);
        comboboxMateriale.setValue(null);
        comboboxStørrelse.setValue(null);
        comboboxTidligereIndhold.setValue(null);
    }
}

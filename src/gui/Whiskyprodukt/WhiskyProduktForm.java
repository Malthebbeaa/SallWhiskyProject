package gui.Whiskyprodukt;

import application.controller.Controller;
import application.model.Fad;
import gui.PaneCreator;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import storage.Storage;

import java.awt.event.ActionListener;

public class WhiskyProduktForm {
    private GridPane fadInfoPane, whiskyproduktPane;
    private Controller controller;
    private TextArea txaFadInfo, txaHistorie;
    private TextField txfNavn;
    private ComboBox comboboxFad, comboboxStørrelse, comboboxMateriale, comboboxTidligereIndhold;


    public WhiskyProduktForm(Controller controller) {
        this.controller = controller;
        this.whiskyproduktPane = new GridPane();

        initForm();
    }

    private void initForm() {
        fadInfoPane = new GridPane();
        fadInfoPane.setHgap(10);
        fadInfoPane.setVgap(10);
        fadInfoPane.setPadding(new Insets(10, 50, 50, 10));
        fadInfoPane.setBorder(Border.stroke(Paint.valueOf("Black")));

        Label lblOpret = new Label("Opret Fad:");
        whiskyproduktPane.add(lblOpret, 0, 0);
        whiskyproduktPane.add(fadInfoPane, 0, 1);
        whiskyproduktPane.setHgap(10);
        whiskyproduktPane.setVgap(10);


        Label lblAvailable = new Label("Tilgængelige fad:");
        Label lblChosen = new Label("Valgte fad:");

        ListView<Fad> availableListView = new ListView<>();
        ListView<Fad> chosenListView = new ListView<>();

        availableListView.getItems().addAll(controller.getStorage().getFade());
        availableListView.setPrefSize(200, 150);
        chosenListView.setPrefSize(200, 150);

        availableListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            displayFadInfo(newValue);
        });

        chosenListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            displayFadInfo(newValue);
        });


// Define buttons for moving items between lists
        Button btnAddSelected = new Button(">");
        btnAddSelected.setOnAction(e -> {
            Fad selected = (availableListView.getSelectionModel().getSelectedItem());
            if (selected != null) {
                availableListView.getItems().remove(selected);
                chosenListView.getItems().add(selected);
            }
        });

        Button btnRemoveSelected = new Button("<");
        btnRemoveSelected.setOnAction(e -> {
            Fad selected = chosenListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                chosenListView.getItems().remove(selected);
                availableListView.getItems().add(selected);
            }
        });

        Button btnAddAll = new Button(">>");
        btnAddAll.setOnAction(e -> {
            chosenListView.getItems().addAll(availableListView.getItems());
            availableListView.getItems().clear();
        });

        Button btnRemoveAll = new Button("<<");
        btnRemoveAll.setOnAction(e -> {
            availableListView.getItems().addAll(chosenListView.getItems());
            chosenListView.getItems().clear();
        });

// Add components to the pane
        fadInfoPane.add(lblAvailable, 0, 1);
        fadInfoPane.add(availableListView, 0, 2);

        VBox buttonBox = new VBox(1, btnAddSelected, btnAddAll, btnRemoveAll, btnRemoveSelected);
        fadInfoPane.add(buttonBox, 1, 2);

        fadInfoPane.add(lblChosen, 2, 1);
        fadInfoPane.add(chosenListView, 2, 2);

        txaFadInfo = new TextArea();
        txaFadInfo.isDisabled();
        txaFadInfo.setPrefWidth(300);
        txaFadInfo.setPrefHeight(200);
        fadInfoPane.add(txaFadInfo, 4, 2);


        PaneCreator historiePane = new PaneCreator();
        Label lblNavn = new Label("Whiskyens navn");
        txfNavn = new TextField();
        txfNavn.setPrefWidth(150);




        historiePane.add(lblNavn, 0, 1);
        historiePane.add(txfNavn, 1, 1);
        whiskyproduktPane.add(historiePane, 0, 3);
        txaHistorie = new TextArea();
        historiePane.add(txaHistorie, 1, 2);
        txaHistorie.setEditable(false);
        txaHistorie.setText("Historie");
    }



    public GridPane getWhiskyproduktPane() {
        return whiskyproduktPane;
    }

    public void clearAction() {
        txaFadInfo.clear();
    }

//    public int getStørrelse() {
//        return selectedFad.getStørrelse();
//    }
//
//    public String getMateriale() {
//        return selectedFad.getMateriale();
//    }
//
//    public FadLeverandør getFadleverandør() {
//        return selectedFad.getFadLeverandør();
//    }
//
//    public String getTidligereIndhold() {
//        return selectedFad.getTidligereIndhold();
//    }

    private void displayFadInfo(Fad selectedFad) {
        if (selectedFad != null) {
            txaFadInfo.setText(
                    "FadID: " + selectedFad.getFadId() +
                            "\nStørrelse: " + selectedFad.getStørrelse() +
                            "\nMateriale: " + selectedFad.getMateriale() +
                            "\nLeverandør: " + selectedFad.getFadLeverandør() +
                            "\nTidligere indhold: " + selectedFad.getTidligereIndhold() + '\'' +
                            "\nAlder: " + selectedFad.getAlder() +
                            "\nBrugt " + selectedFad.getAntalGangeBrugt() + " gang(e)" +
                            "\nLiter i fad: " + selectedFad.getMængdeFyldtPåFad()
            );
        } else {
            txaFadInfo.clear();
        }
    }
}

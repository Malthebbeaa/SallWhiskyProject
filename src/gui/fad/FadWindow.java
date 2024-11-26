package gui.fad;

import application.controller.Controller;
import application.model.Fad;
import application.model.FadLeverandør;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import storage.StorageInterface;

import java.util.List;

public class FadWindow {

        private GridPane startPane, fadPane;
        private Scene scene;
        private StorageInterface storage;
        private Controller controller;
        private TextField txfAlder, txfAntalGangeBrugt;
        private ComboBox comboboxLeverandører, comboboxStørrelse, comboboxMateriale, comboboxTidligereIndhold;


        public FadWindow(GridPane startPane, Scene scene, Controller controller) {
            this.controller = controller;
            this.storage = controller.getStorage();
            this.startPane = startPane;
            this.scene = scene;
            fadPane = new GridPane();
            this.initContent(fadPane);
        }

        private void initContent(GridPane fadPane) {
            fadPane.setHgap(10);
            fadPane.setVgap(10);
            fadPane.setPadding(new Insets(20));

            Label lblLeverandør = new Label("Leverandør: ");
            comboboxLeverandører = new ComboBox();
            comboboxLeverandører.getItems().addAll(new FadLeverandør("La Barril", "Spanien"),
                    new FadLeverandør("El Gordo y Pobre", "Spanien"),
                    new FadLeverandør("Barrel Land", "USA"),
                    new FadLeverandør("Le Ivre et Belle", "Frankrig"));
            comboboxLeverandører.setPrefHeight(20);
            comboboxLeverandører.setPrefWidth(200);
            fadPane.add(lblLeverandør, 0,0);
            fadPane.add(comboboxLeverandører, 0,1);

            Label lblStørrelse = new Label("Størrelse: ");
            comboboxStørrelse = new ComboBox();
            comboboxStørrelse.getItems().addAll(32, 94, 130);
            comboboxStørrelse.setPrefHeight(20);
            comboboxStørrelse.setPrefWidth(200);
            fadPane.add(lblStørrelse, 1,0);
            fadPane.add(comboboxStørrelse, 1,1);

            Label lblMateriale = new Label("Materiale: ");
            comboboxMateriale = new ComboBox();
            comboboxMateriale.getItems().addAll("Eg", "Kirsebærtræ", "Bøgetræ");
            comboboxMateriale.setPrefHeight(20);
            comboboxMateriale.setPrefWidth(200);
            fadPane.add(lblMateriale, 2,0);
            fadPane.add(comboboxMateriale, 2,1);

            Label lblTidligereIndhold = new Label("Tidligere indhold: ");
            comboboxTidligereIndhold = new ComboBox();
            comboboxTidligereIndhold.getItems().addAll("Sherry", "Bourbon", "Rødvin");
            comboboxTidligereIndhold.setPrefHeight(20);
            comboboxTidligereIndhold.setPrefWidth(200);
            fadPane.add(lblTidligereIndhold, 0,2);
            fadPane.add(comboboxTidligereIndhold, 0,3);

            Label lblAlder = new Label("Tøndens alder: ");
            txfAlder = new TextField();
            txfAlder.setPrefWidth(30);
            fadPane.add(lblAlder, 1,2);
            fadPane.add(txfAlder, 1,3);

            Label lblAntalGangeBrugt = new Label("Antal gange den har været brugt: ");
            txfAntalGangeBrugt = new TextField();
            txfAntalGangeBrugt.setPrefWidth(30);
            fadPane.add(lblAntalGangeBrugt, 2,2);
            fadPane.add(txfAntalGangeBrugt, 2,3);


            Button btnOpret = new Button("Opret Fad");
            btnOpret.setOnAction(e -> opretAction());
            fadPane.add(btnOpret, 0,5);

            Button btnAfbryd = new Button("Afbryd");
            btnAfbryd.setOnAction(e -> afbrydAction());
            fadPane.add(btnAfbryd,1,5);
        }

        private void opretAction() {
            int størrelse = Integer.parseInt(String.valueOf(comboboxStørrelse.getItems()));
            String materiale = String.valueOf(comboboxMateriale.getItems());
            FadLeverandør fadleverandør = (FadLeverandør) comboboxLeverandører.getValue();
            String tidligereIndhold = String.valueOf(comboboxMateriale.getItems());
            int alder = Integer.parseInt(txfAlder.getText());
            int antalGangeBrugt = Integer.parseInt(txfAlder.getText());


            Fad fad = controller.opretFad(størrelse, materiale, fadleverandør, tidligereIndhold, alder, antalGangeBrugt);
        }

        public GridPane getFadPane() {
            return fadPane;
        }

        public void afbrydAction(){
            txfAlder.clear();
            txfAntalGangeBrugt.clear();
            comboboxLeverandører.setValue(null);
            comboboxMateriale.setValue(null);
            comboboxStørrelse.setValue(null);
            comboboxTidligereIndhold.setValue(null);
        }
}

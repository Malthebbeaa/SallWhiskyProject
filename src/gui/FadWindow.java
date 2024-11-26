package gui;

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

        private void initContent(GridPane destilleringPane) {
            destilleringPane.setHgap(10);
            destilleringPane.setVgap(10);
            destilleringPane.setPadding(new Insets(20));

            Label lblLeverandør = new Label("Leverandør: ");
            comboboxLeverandører = new ComboBox();
            comboboxLeverandører.getItems().addAll(new FadLeverandør("La Barril", "Spanien"),
                    new FadLeverandør("El Gordo y Pobre", "Spanien"),
                    new FadLeverandør("Barrel Land", "USA"),
                    new FadLeverandør("Le Ivre et Belle", "Frankrig"));
            comboboxLeverandører.setPrefHeight(20);
            comboboxLeverandører.setPrefWidth(200);
            destilleringPane.add(lblLeverandør, 0,0);
            destilleringPane.add(comboboxLeverandører, 0,1);

            Label lblStørrelse = new Label("Størrelse: ");
            comboboxStørrelse = new ComboBox();
            comboboxStørrelse.getItems().addAll(32, 94, 130);
            comboboxStørrelse.setPrefHeight(20);
            comboboxStørrelse.setPrefWidth(200);
            destilleringPane.add(lblStørrelse, 1,0);
            destilleringPane.add(comboboxStørrelse, 1,1);

            Label lblMateriale = new Label("Materiale: ");
            comboboxMateriale = new ComboBox();
            comboboxMateriale.getItems().addAll("Eg", "Kirsebærtræ", "Bøgetræ");
            comboboxMateriale.setPrefHeight(20);
            comboboxMateriale.setPrefWidth(200);
            destilleringPane.add(lblMateriale, 2,0);
            destilleringPane.add(comboboxMateriale, 2,1);

            Label lblTidligereIndhold = new Label("Tidligere indhold: ");
            comboboxTidligereIndhold = new ComboBox();
            comboboxTidligereIndhold.getItems().addAll("Sherry", "Bourbon", "Rødvin");
            comboboxTidligereIndhold.setPrefHeight(20);
            comboboxTidligereIndhold.setPrefWidth(200);
            destilleringPane.add(lblTidligereIndhold, 0,3);
            destilleringPane.add(comboboxTidligereIndhold, 0,4);

            Label lblAlder = new Label("Tøndens alder: ");
            txfAlder = new TextField();
            txfAlder.setPrefWidth(30);
            destilleringPane.add(lblAlder, 1,3);
            destilleringPane.add(txfAlder, 1,4);

            Label lblAntalGangeBrugt = new Label("Antal gange den har været brugt: ");
            txfAntalGangeBrugt = new TextField();
            txfAntalGangeBrugt.setPrefWidth(30);
            destilleringPane.add(lblAntalGangeBrugt, 2,3);
            destilleringPane.add(txfAntalGangeBrugt, 2,4);


            Button btnOpret = new Button("Opret Fad");
            btnOpret.setOnAction(e -> opretAction());
            destilleringPane.add(btnOpret, 0,6);

            Button btnAfbryd = new Button("Afbryd");
            btnAfbryd.setOnAction(e -> afbrydAction());
            destilleringPane.add(btnAfbryd,1,6);
        }

        private void opretAction() {
            int størrelse = Integer.parseInt(String.valueOf(comboboxStørrelse.getItems()));
            String materiale = String.valueOf(comboboxMateriale.getItems());
            List<FadLeverandør> fadleverandør = comboboxLeverandører.getItems();
            String tidligereIndhold = String.valueOf(comboboxMateriale.getItems());
            int alder = Integer.parseInt(txfAlder.getText());
            int antalGangeBrugt = Integer.parseInt(txfAlder.getText());


            Fad fad = controller.opretFad(størrelse, materiale, (FadLeverandør) fadleverandør, tidligereIndhold, alder, antalGangeBrugt);
        }

        public GridPane getFadPane() {
            return fadPane;
        }

        public void afbrydAction(){
            scene.setRoot(startPane);
        }
}

package gui;

import application.controller.Controller;
import application.model.Korn;
import application.model.Mark;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class gui extends Application {
    private Scene scene;
    private GridPane startPane;
    private StorageInterface storage;
    private Controller controller;
    private TabPaneGenerator tabPaneGenerator = new TabPaneGenerator();
    private DestilleringWindow destilleringWindow;
    private MaltbatchWindow maltbatchWindow;

    @Override
    public void start(Stage stage){
        stage.setTitle("Destilleri Pro");
        startPane = new GridPane();

        initContent(startPane);

        storage = new Storage();
        controller = new Controller(storage);

        startPane.setPrefWidth(800);
        startPane.setPrefHeight(600);
        scene = new Scene(startPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initContent(GridPane pane){
        Label lblDestilleriPro = new Label("DESTILLERI PRO BETA");
        lblDestilleriPro.setFont(new Font(55));
        pane.add(lblDestilleriPro,0,0);
        pane.setAlignment(Pos.CENTER);

        VBox vboxButtons = new VBox(60);
        pane.add(vboxButtons,0,1);
        vboxButtons.setAlignment(Pos.BOTTOM_LEFT);

        Button btnOpretMaltbatch = new Button("Opret Maltbatch");
        btnOpretMaltbatch.setOnAction(e -> opretMaltbatchAction());
        vboxButtons.getChildren().add(btnOpretMaltbatch);

        Button btnOpretDestillering = new Button("Opret Destillering");
        btnOpretDestillering.setOnAction(e -> opretDestilleringAction());
        vboxButtons.getChildren().add(btnOpretDestillering);


        Button buttonGenerator = new Button("Opret tabpanes");
        buttonGenerator.setOnAction(e -> opretAction());
        vboxButtons.getChildren().add(buttonGenerator);
    }

    private void opretAction() {
        maltbatchWindow = new MaltbatchWindow(startPane, scene);
        destilleringWindow = new DestilleringWindow(startPane, scene, controller);
        List<String> tabs = new ArrayList<>(List.of("Opret Destillering", "Opret Maltbatch"));
        List<GridPane> gridPanes = new ArrayList<>(List.of(destilleringWindow.getDestilleringPane(), maltbatchWindow.getMaltPane()));
        tabPaneGenerator.generateTabPane(tabs, gridPanes);
        scene.setRoot(tabPaneGenerator.getTabPane());
    }

    public void opretMaltbatchAction(){
        maltbatchWindow = new MaltbatchWindow(startPane, scene);
       scene.setRoot(maltbatchWindow.getMaltPane());
    }
    public void opretDestilleringAction(){
        destilleringWindow = new DestilleringWindow(startPane, scene, controller);
        scene.setRoot(destilleringWindow.getDestilleringPane());
    }
}

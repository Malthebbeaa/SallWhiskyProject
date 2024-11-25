package gui;

import application.controller.Controller;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TabPane startTabPane;
    private StorageInterface storage;
    private Controller controller;
    private TabPaneGenerator tabPaneGenerator = new TabPaneGenerator();
    private DestilleringWindow destilleringWindow;
    private MaltbatchWindow maltbatchWindow;

    @Override
    public void start(Stage stage){
        stage.setTitle("Destilleri Pro");
        startPane = new GridPane();

        storage = new Storage();
        controller = new Controller(storage);
        initStorage();

        initContent();
        startTabPane = tabPaneGenerator.getTabPane();


        startTabPane.setPrefWidth(1920);
        startTabPane.setPrefHeight(1080);
        scene = new Scene(startTabPane);
        stage.setScene(scene);
        stage.show();
    }


    public void initContent(){
        maltbatchWindow = new MaltbatchWindow(startPane, scene);
        destilleringWindow = new DestilleringWindow(startPane, scene, controller);
        List<String> tabs = new ArrayList<>(List.of("Opret Destillering", "Opret Maltbatch"));
        List<GridPane> gridPanes = new ArrayList<>(List.of(destilleringWindow.getDestilleringPane(), maltbatchWindow.getMaltPane()));
        tabPaneGenerator.generateTabPane(tabs, gridPanes);
    }

    public void initStorage(){
        Mark kvolbæk = controller.opretMark("Kvolbæk", true);
        Mark stadsgaard = controller.opretMark("Stadsgaard", true);

        Korn evergreen = controller.opretKorn(LocalDate.now(), "Vårbyg", kvolbæk);
        Korn irina = controller.opretKorn(LocalDate.of(2024,11, 22),"Irina",stadsgaard);

        Maltbatch maltbatch1 = controller.opretMaltbatch("NM80P", 500, evergreen);
        Maltbatch maltbatch2 = controller.opretMaltbatch("NM81P", 400, irina);
    }


}

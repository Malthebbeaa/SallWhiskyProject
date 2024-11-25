package gui;

import application.controller.Controller;
import application.model.Korn;
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
        initContent();
        startTabPane = tabPaneGenerator.getTabPane();

        scene = new Scene(startTabPane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    public void initContent(){
        maltbatchWindow = new MaltbatchWindow(startPane, scene);
        destilleringWindow = new DestilleringWindow(startPane, scene, controller);
        List<String> tabs = new ArrayList<>(List.of("Opret Destillering", "Opret Maltbatch"));
        List<GridPane> gridPanes = new ArrayList<>(List.of(destilleringWindow.getDestilleringPane(), maltbatchWindow.getMaltPane()));
        tabPaneGenerator.generateTabPane(tabs, gridPanes);
    }
}

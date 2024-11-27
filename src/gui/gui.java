package gui;

import application.controller.Controller;
import application.model.Korn;
import application.model.Lager;
import application.model.Maltbatch;
import application.model.Mark;
import gui.destillering.DestilleringWindow;
import gui.fad.FadWindow;
import gui.lager.opretLagerWindow;
import gui.maltbatch.MaltbatchWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    private FadWindow fadWindow;
    private opretLagerWindow opretLagerWindow;

    @Override
    public void start(Stage stage){
        stage.setTitle("Destilleri Pro");
        startPane = new GridPane();

        storage = new Storage();
        controller = new Controller(storage);
        initStorage();

        initContent();
        startTabPane = tabPaneGenerator.getTabPane();

        scene = new Scene(startTabPane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    public void initContent(){
        maltbatchWindow = new MaltbatchWindow(controller);
        destilleringWindow = new DestilleringWindow(controller);
        fadWindow = new FadWindow(controller);
        opretLagerWindow = new opretLagerWindow(controller);
        List<String> tabs = new ArrayList<>(List.of("Opret Destillering", "Opret Maltbatch", "Opret Fad", "Opret Lager"));
        List<GridPane> gridPanes = new ArrayList<>(List.of(destilleringWindow.getPane(), maltbatchWindow.getPane(), fadWindow.getPane(), opretLagerWindow.getPane()));

        Image logo = new Image(getClass().getResource("/sall-whisky-transparent-logo-e1609503360305.png").toExternalForm());

        for (GridPane gridPane : gridPanes) {
            //gridPane.setStyle("-fx-background-color: lightblue");

            ImageView logoViewer = new ImageView(logo);
            logoViewer.setFitWidth(200);
            logoViewer.setPreserveRatio(true);

            gridPane.add(logoViewer, 0,0);
        }
        tabPaneGenerator.generateTabPane(tabs, gridPanes);
    }

    public void initStorage(){
        Mark kvolbæk = controller.opretMark("Kvolbæk", true);
        Mark stadsgaard = controller.opretMark("Stadsgaard", true);

        Korn evergreen = controller.opretKorn(LocalDate.now(), "Evergreen", kvolbæk);
        Korn irina = controller.opretKorn(LocalDate.of(2024,11, 22),"Irina",stadsgaard);

        Maltbatch maltbatch1 = controller.opretMaltbatch("NM80P", 500, evergreen);
        Maltbatch maltbatch2 = controller.opretMaltbatch("NM81P", 400, irina);

        Lager lager = controller.opretLager("Lars Gård", "Sall hovedgade","8450","Hammel");
        lager.tilføjReol(5,4,5);
    }
}
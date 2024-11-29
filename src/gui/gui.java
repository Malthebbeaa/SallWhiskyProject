package gui;

import application.controller.Controller;
import application.model.*;
import gui.destillering.DestilleringWindow;
import gui.fad.FadWindow;
import gui.lager.opretLagerWindow;
import gui.maltbatch.MaltbatchWindow;
import gui.Whiskyprodukt.WhiskyProduktWindow;
import gui.påfyldning.PåfyldningWindow;
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
    private WhiskyProduktWindow WhiskyProduktWindow;
    private PåfyldningWindow påfyldningWindow;

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


    public void initContent() {
        maltbatchWindow = new MaltbatchWindow(controller);
        destilleringWindow = new DestilleringWindow(controller);
        fadWindow = new FadWindow(controller);
        opretLagerWindow = new opretLagerWindow(controller);
        WhiskyProduktWindow = new WhiskyProduktWindow(controller);
        påfyldningWindow = new PåfyldningWindow(controller);
        List<String> tabs = new ArrayList<>(List.of("Opret Destillering", "Opret Maltbatch", "Opret Fad", "Opret Lager", "Opret Whiskyprodukt", "Påfyld Fad"));
        List<GridPane> gridPanes = new ArrayList<>(List.of(destilleringWindow.getPane(), maltbatchWindow.getPane(), fadWindow.getPane(), opretLagerWindow.getPane(), WhiskyProduktWindow.getPane(), påfyldningWindow.getPane()));

        Image logo = new Image(getClass().getResource("/ressources/sall-whisky-transparent-logo-e1609503360305.png").toExternalForm());

        for (GridPane gridPane : gridPanes) {
            gridPane.setStyle("-fx-background-color: lightblue");

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


        Destillering destillering1 = controller.opretDestillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 900,68, maltbatch1);

        Lager lager = controller.opretLager("Lars Gård", "Sall hovedgade","8450","Hammel");
        Reol reol = lager.tilføjReol();
        reol.tilføjHylde(5,5);

//        FadLeverandør fadLeverandør1 = controller.opretFadlevandør("La Barril", "Spanien");
//        FadLeverandør fadLeverandør2 = controller.opretFadlevandør("El Gordo y Pobre", "Spanien");
//        FadLeverandør fadLeverandør3 = controller.opretFadlevandør("Barrel Land", "USA");
//        FadLeverandør fadLeverandør4 = controller.opretFadlevandør("Le Ivre et Belle", "Frankrig");

//        Fad fad1 = controller.opretFad(94, "Kirsebær træ", fadLeverandør2,
//                "Sherry", 56, 1);
//        Fad fad2 = controller.opretFad(32, "Eg", fadLeverandør4,
//                "Rødvin", 23, 0);
//        Fad fad3 = controller.opretFad(94, "Eg", fadLeverandør3,
//                "Bourbon", 29, 0);
//
//        Aftapning aftapning1 = controller.opretAftapning(LocalDate.now(), 20.0, 48.0, fad2);
//
//        Påfyldning påfyldning1 = controller.opretPåfyldning(LocalDate.EPOCH, 30.0);


//        WhiskyProdukt whiskyProdukt1 = new WhiskyProdukt(whiskyProdukt1.getAarLagret(aftapning1,påfyldning1), "NAVN",
//                whiskyProdukt1.getWhiskytype(aftapning1,whiskyProdukt1), true);
    }
}
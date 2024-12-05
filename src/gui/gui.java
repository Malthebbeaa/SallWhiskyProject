package gui;

import application.controller.Controller;
import application.model.*;
import gui.destillering.DestilleringWindow;
import gui.fad.FadWindow;
import gui.fadSøgning.SøgningWindow;
import gui.flytfad.FlytFadWindow;
import gui.lager.opretLagerWindow;
import gui.maltbatch.MaltbatchWindow;
import gui.påfyldning.PåfyldningWindow;
import gui.Whiskyprodukt.*;
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
    private WhiskyProduktOpretWindow whiskyProduktOpretWindow;
    private PåfyldningWindow påfyldningWindow;
    private FlytFadWindow flytFadWindow;
    private SøgningWindow søgningWindow;

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
        whiskyProduktOpretWindow = new WhiskyProduktOpretWindow(controller);
        påfyldningWindow = new PåfyldningWindow(controller);
        flytFadWindow = new FlytFadWindow(controller);
        søgningWindow = new SøgningWindow(controller);
        List<String> tabs = new ArrayList<>(List.of("Opret Destillering", "Opret Maltbatch", "Opret Fad", "Opret Lager", "Opret Whiskyprodukt", "Påfyld Fad", "Flyt Fad", "Fad Oversigt"));
        List<GridPane> gridPanes = new ArrayList<>(List.of(destilleringWindow.getPane(), maltbatchWindow.getPane(), fadWindow.getPane(), opretLagerWindow.getPane(), whiskyProduktOpretWindow.getPane(), påfyldningWindow.getPane(), flytFadWindow.getFlytFadPane(), søgningWindow.getPane()));

        Image logo = new Image(getClass().getResource("/ressources/sall-whisky-transparent-logo-e1609503360305.png").toExternalForm());

        for (GridPane gridPane : gridPanes) {
            gridPane.setStyle("-fx-background-color: lightblue");

            ImageView logoViewer = new ImageView(logo);
            logoViewer.setFitWidth(200);
            logoViewer.setPreserveRatio(true);

            gridPane.add(logoViewer, 0,0);
        }
        tabPaneGenerator.generateTabPane(tabs, gridPanes);
        påfyldningWindow.getHandler().addObserver(flytFadWindow.getForm());
        flytFadWindow.getHandler().addObserver(søgningWindow.getForm());
        whiskyProduktOpretWindow.getHandler().addObserver(påfyldningWindow.getForm());
        whiskyProduktOpretWindow.getHandler().addObserver(søgningWindow.getForm());
    }

    public void initStorage(){
        Mark kvolbæk = controller.opretMark("Kvolbæk", true);
        Mark stadsgaard = controller.opretMark("Stadsgaard", true);

        Korn evergreen = controller.opretKorn(LocalDate.now(), "Evergreen", kvolbæk);
        Korn irina = controller.opretKorn(LocalDate.of(2024,11, 22),"Irina",stadsgaard);

        Maltbatch maltbatch1 = controller.opretMaltbatch("NM80P", 500, evergreen);
        Maltbatch maltbatch2 = controller.opretMaltbatch("NM81P", 400, irina);


        Destillering destillering1 = controller.opretDestillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 900,75, maltbatch1);
        Destillering destillering2 = controller.opretDestillering(2, LocalDate.of(2024,11,26), LocalDate.of(2024, 11,29), 100,78, maltbatch2);

        Lager lager = controller.opretLager("Lars Gård", "Sall hovedgade","8450","Hammel");
        Reol reol = lager.tilføjReol();
        reol.tilføjHylde(5,5);

        FadLeverandør fadLeverandør1 = controller.opretFadlevandør("La Barril", "Spanien");
        FadLeverandør fadLeverandør2 = controller.opretFadlevandør("El Gordo y Pobre", "Spanien");
        FadLeverandør fadLeverandør3 = controller.opretFadlevandør("Barrel Land", "USA");
        FadLeverandør fadLeverandør4 = controller.opretFadlevandør("Le Ivre et Belle", "Frankrig");

        Fad fad1 = controller.opretFad(94, "Kirsebær træ", fadLeverandør2,
                "Sherry", 56, 1);
        Fad fad2 = controller.opretFad(32, "Eg", fadLeverandør4,
                "Rødvin", 23, 0);
        Fad fad3 = controller.opretFad(130, "Eg", fadLeverandør3,
                "Bourbon", 25, 0);
        Fad fad4 = controller.opretFad(94, "Eg", fadLeverandør1,
                "Sherry", 45, 0);
        Fad fad5 = controller.opretFad(130, "Eg", fadLeverandør3,
                "Sherry", 39, 0);
        Fad fad6 = controller.opretFad(32, "Eg", fadLeverandør4,
                "Bourbon", 19, 0);
        Fad fad7 = controller.opretFad(94, "Eg", fadLeverandør2,
                "Bourbon", 27, 0);
        Fad fad8 = controller.opretFad(94, "Eg", fadLeverandør3,
                "Rødvin", 51, 0);
        Fad fad9 = controller.opretFad(32, "Eg", fadLeverandør3,
                "Bourbon", 29, 0);
        Fad fad10 = controller.opretFad(130, "Eg", fadLeverandør4,
                "Sherry", 29, 0);
        Fad fad11 = controller.opretFad(94, "Eg", fadLeverandør1,
                "Bourbon", 29, 0);

        Påfyldning påfyldning1 = controller.opretPåfyldning(fad1, LocalDate.of(2022,02,28));
        påfyldning1.tilføjMængde(new Mængde(50, destillering1));
        påfyldning1.tilføjMængde(new Mængde(44, destillering2));
        Påfyldning påfyldning2 = controller.opretPåfyldning(fad2, LocalDate.of(2021, 12,2));
        påfyldning2.tilføjMængde(new Mængde(32, destillering1));
        Påfyldning påfyldning3 = controller.opretPåfyldning(fad4, LocalDate.of(2020, 1,20));
        påfyldning3.tilføjMængde(new Mængde(94, destillering1));

        controller.påfyldFad(påfyldning1, fad1);
        controller.påfyldFad(påfyldning2, fad2);
        controller.påfyldFad(påfyldning3, fad4);

        System.out.println("Liter påfyldt i påfyldning3: " + påfyldning3.getLiterPåfyldt());
        System.out.println("Liter påfyldt i fad4: " + fad4.getMængdeFyldtPåFad());

        controller.flytFad(lager.getReoler().getFirst().getHylder().getFirst().getPladser().getFirst(), fad1);
        controller.flytFad(lager.getReoler().getFirst().getHylder().getFirst().getPladser().get(1), fad2);
        controller.flytFad(lager.getReoler().getFirst().getHylder().getFirst().getPladser().get(2), fad4);

        WhiskyProdukt whiskyProdukt1 = controller.opretWhiskyProdukt("Jule Whisky", LocalDate.now());
        Aftapning aftapning1 = new Aftapning(94, 68);
        aftapning1.setPåfyldning(påfyldning3);
        ArrayList<Aftapning> aftapningerTilWhiskyProdukt1 = new ArrayList<>(List.of(aftapning1));
        controller.lavAftapninger(aftapningerTilWhiskyProdukt1, whiskyProdukt1);
        whiskyProdukt1.tilføjVand(5);

        System.out.println(whiskyProdukt1.lavHistorie());
    }
}
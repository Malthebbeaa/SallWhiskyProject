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

public class gui extends Application {
    private Scene scene;
    private GridPane startPane;
    private StorageInterface storage;
    private Controller controller;

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
    }

    public void opretMaltbatchAction(){
       MaltbatchWindow dia = new MaltbatchWindow(startPane, scene);
       scene.setRoot(dia.getMaltPane());
    }
    public void opretDestilleringAction(){
        DestilleringWindow dia = new DestilleringWindow(startPane, scene, controller);
        scene.setRoot(dia.getDestilleringPane());
    }
}

package gui.fadSøgning;

import application.controller.Controller;
import application.model.Fad;
import application.model.Plads;
import application.model.Påfyldning;
import gui.GuiObserver;
import gui.GuiSubject;
import gui.PaneCreator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SøgningForm implements GuiObserver {
    private Controller controller;
    private List<Fad> fade;
    private GridPane søgningsPane, søgningsInfoPane;
    private TableView<Fad> tableViewFade, tableViewFadeMed3År;
    private TextField searchBar;
    private FilteredList<Fad> filteredData;

    private TableColumn<Fad, String> tcPlads;

    public SøgningForm(Controller controller, SøgningHandler handler) {
        this.controller = controller;
        søgningsPane = new GridPane();
        fade = new ArrayList<>();
        initForm(handler);
    }

    public void initForm(SøgningHandler handler) {
        søgningsInfoPane = new PaneCreator();
        Label lblOverskrift = new Label("Søg fade");
        søgningsPane.add(lblOverskrift, 0, 0);
        søgningsPane.add(søgningsInfoPane, 0, 1);
        søgningsPane.setHgap(10);
        søgningsPane.setVgap(10);

        ComboBox comboBoxSøg = new ComboBox<>();
        ArrayList søgningFiltre = new ArrayList<>(List.of("FadId", "Materiale", "Tidligere Indhold", "Lagerplads"));
        comboBoxSøg.getItems().addAll(søgningFiltre);
        comboBoxSøg.setValue(søgningFiltre.getFirst());
        searchBar = new TextField();
        searchBar.setPromptText("søg...");
        Button btnSøg = new Button("Søg");
        btnSøg.setOnAction(e -> {
            if (searchBar.getText() != null) {
                if (comboBoxSøg.getValue().equals(søgningFiltre.get(0))) {
                    Scanner scanner = new Scanner(searchBar.getText());
                    if (scanner.hasNextInt()) {
                        handler.søgningFadIdAction(this, Integer.valueOf(searchBar.getText()));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Fad Id skal være et gyldigt nummer");
                        alert.showAndWait();
                        searchBar.clear();
                    }
                } else if (comboBoxSøg.getValue().equals(søgningFiltre.get(1))) {
                    handler.søgningMaterialeAction(this, searchBar.getText());
                } else if (comboBoxSøg.getValue().equals(søgningFiltre.get(2))) {
                    handler.søgningTidligereIndholdAction(this, searchBar.getText());
                } else if (comboBoxSøg.getValue().equals(søgningFiltre.get(3))) {
                    handler.søgningLagerpladsAction(this, searchBar.getText());
                }
            }
        });
        Button btnKlarTilAftapning = new Button("Vis fade klar til aftapning");
        btnKlarTilAftapning.setOnAction(e -> {
            handler.findKlareFade(this);
        });
        Button btnAlleFade = new Button("Vis alle fade");
        btnAlleFade.setOnAction(e -> {
            handler.alleFade(this);
        });
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(comboBoxSøg, searchBar, btnSøg, btnAlleFade, btnKlarTilAftapning);
        søgningsInfoPane.add(hBox, 0, 0);


        tableViewFade = new TableView<>();
        TableColumn<Fad, Integer> tcFadId = new TableColumn<>("Fad Id");
        tcFadId.setCellValueFactory(new PropertyValueFactory<>("fadId"));
        TableColumn<Fad, String> tcLagringstid = new TableColumn<>("Lagringstid");
        tcLagringstid.setCellValueFactory(cellData -> {
            Fad fad = cellData.getValue();
            // Hent den nyeste påfyldning
            List<Påfyldning> påfyldninger = fad.getPåfyldninger();
            if (påfyldninger.isEmpty() || påfyldninger.getLast().getLiterPåfyldt() == 0) {
                return new SimpleStringProperty("Ingen påfyldninger");
            } else {
                Påfyldning senestePåfyldning = påfyldninger.getLast();
                return new SimpleStringProperty(senestePåfyldning.getAntalÅrMånederDage());
            }
        });
        TableColumn<Fad, String> tcTidligereIndhold = new TableColumn<>("Tidligere indhold");
        tcTidligereIndhold.setCellValueFactory(new PropertyValueFactory<>("tidligereIndhold"));
        TableColumn<Fad, String> tcMateriale = new TableColumn<>("Materiale");
        tcMateriale.setCellValueFactory(new PropertyValueFactory<>("materiale"));
        tcPlads = new TableColumn<>("Lagerplads");
        tcPlads.setCellValueFactory(cellData -> {
            Plads plads = cellData.getValue().getPlads();
            return new SimpleStringProperty(plads != null ? plads.getHylde().getReol().getLager() +
                    ", Reol: " + plads.getHylde().getReol().getReolNummer() +
                    " Hylde: " + plads.getHylde().getHyldeNummer() +
                    " plads: " + plads.getPladsNummer() : "Ikke tildelt");
        });

        TableColumn<Fad, Double> tcVæskeMængde = new TableColumn<>("Mængde på Fad (L)");
        tcVæskeMængde.setCellValueFactory(new PropertyValueFactory<>("mængdeFyldtPåFad"));
        TableColumn<Fad, Integer> tcStørrelse = new TableColumn<>("Størrelse (L)");
        tcStørrelse.setCellValueFactory(new PropertyValueFactory<>("størrelse"));
        tableViewFade.getColumns().addAll(tcFadId, tcLagringstid, tcTidligereIndhold, tcMateriale, tcPlads, tcVæskeMængde, tcStørrelse);
        tableViewFade.setMinWidth(900);
        tcPlads.setPrefWidth(200);
        filteredData = new FilteredList<>(controller.getStorage().getFade(), p -> true);
        SortedList<Fad> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewFade.comparatorProperty());
        tableViewFade.setItems(sortedData);
        søgningsInfoPane.add(tableViewFade, 0, 1);
        searchBar.setOnMouseClicked(e -> {
            tableViewFade.setItems(sortedData);
        });
    }

    public GridPane getSøgningsPane() {
        return søgningsPane;
    }

    public TableView<Fad> getTableViewFade() {
        return tableViewFade;
    }

    public TableColumn<Fad, String> getTcPlads() {
        return tcPlads;
    }

    @Override
    public void update(GuiSubject s) {
        tableViewFade.refresh();
    }
}

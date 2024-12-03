package gui.fadSøgning;

import application.controller.Controller;
import application.model.Fad;
import application.model.Plads;
import application.model.Påfyldning;
import gui.PaneCreator;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class SøgningForm {
    private Controller controller;
    private List<Fad> fade;
    private GridPane søgningsPane, søgningsInfoPane;
    private TableView<Fad> tableViewFade;

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

        tableViewFade = new TableView<>();
        TableColumn<Fad, Integer> tcFadId = new TableColumn<>("Fad Id");
        tcFadId.setCellValueFactory(new PropertyValueFactory<>("fadId"));
        TableColumn<Fad, String> tcLagringstid = new TableColumn<>("Lagrindstid");
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
        TableColumn<Fad, String> tcPlads = new TableColumn<>("Lagerplads");
        tcPlads.setCellValueFactory(cellData -> {
            Plads plads = cellData.getValue().getPlads();
            return new SimpleStringProperty(plads != null ? plads.toString() : "Ikke tildelt");
        });

        TableColumn<Fad, Double> tcVæskeMængde = new TableColumn<>("Mængde på Fad (L)");
        tcVæskeMængde.setCellValueFactory(new PropertyValueFactory<>("mængdeFyldtPåFad"));
        tableViewFade.setItems(controller.getStorage().getFade());
        tableViewFade.getColumns().addAll(tcFadId, tcLagringstid, tcTidligereIndhold, tcMateriale, tcPlads, tcVæskeMængde);
        tableViewFade.setMinWidth(600);
        søgningsInfoPane.add(tableViewFade, 0, 0);



    }

    public GridPane getSøgningsPane() {
        return søgningsPane;
    }
}

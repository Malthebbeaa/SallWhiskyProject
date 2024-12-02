package gui.flytfad;

import application.controller.Controller;
import application.model.*;
import gui.GuiObserver;
import gui.GuiSubject;
import gui.PaneCreator;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlytfadForm implements GuiObserver {
    private GridPane flytFadPane;
    private Controller controller;
    private FlytFadHandler handler;
    private ComboBox<Fad> cbFade;
    private ComboBox<Lager> cbLagre;
    private ListView<Reol> lvReol;
    private ListView<Hylde> lvHylde;
    private ListView<Plads> lvPlads;
    private Label lblValgtPlads;
    private Lager lager;
    private Reol reol;
    private Hylde hylde;
    private Plads plads;
    private Fad fad;

    public FlytfadForm(Controller controller, FlytFadHandler handler) {
        this.controller = controller;
        this.handler = handler;
        flytFadPane = new GridPane();
        initForm();
    }

    private void initForm() {
        flytFadPane.setHgap(10);
        flytFadPane.setVgap(10);

        Label lblVælgFlyt = new Label("Vælg Hvad der skal flyttes:");
        Label lblLedigePladser = new Label("Vælg placering:");
        flytFadPane.add(lblVælgFlyt, 0, 0);

        PaneCreator fadOgLagerPane = new PaneCreator();
        flytFadPane.add(fadOgLagerPane, 0, 1);

        Label lblVælgFad = new Label("Vælg fad:");
        cbFade = new ComboBox<>();
        for (Fad f : controller.getStorage().getFade()) {
            if (f.getMængdeFyldtPåFad() > 0)
                cbFade.getItems().add(f);
        }

        fadOgLagerPane.add(lblVælgFad, 0, 0);
        fadOgLagerPane.add(cbFade, 1, 0);

        Label lblVælgLager = new Label("Vælg Lager:");
        cbLagre = new ComboBox<>(controller.getStorage().getLager());
        fadOgLagerPane.add(lblVælgLager, 0, 1);
        fadOgLagerPane.add(cbLagre, 1, 1);

        PaneCreator pladsPane = new PaneCreator();
        flytFadPane.add(pladsPane, 1, 1);

        Label lblVælgReol = new Label("Vælg Reol:");
        lvReol = new ListView<>();
        pladsPane.add(lblVælgReol, 0, 0);
        pladsPane.add(lvReol, 0, 1);

        Label lblVælgHylde = new Label("Vælg Hylde: ");
        lvHylde = new ListView<>();
        pladsPane.add(lblVælgHylde, 1, 0);
        pladsPane.add(lvHylde, 1, 1);

        Label lblVælgPlads = new Label("Vælg Plads");
        lvPlads = new ListView<>();
        pladsPane.add(lblVælgPlads, 2, 0);
        pladsPane.add(lvPlads, 2, 1);

        lblValgtPlads = new Label();
        pladsPane.add(lblValgtPlads, 3, 1);

        ChangeListener<Lager> lagerListener = (lager, OldLager, NewLager) -> selectedLagerChanged();
        cbLagre.getSelectionModel().selectedItemProperty().addListener(lagerListener);

        ChangeListener<Reol> reolListener = (reol, OldReol, NewReol) -> selectedReolChanged();
        lvReol.getSelectionModel().selectedItemProperty().addListener(reolListener);

        ChangeListener<Hylde> hyldeListener = (hylde, OldHylde, NewHylde) -> selectedHyldeChanged();
        lvHylde.getSelectionModel().selectedItemProperty().addListener(hyldeListener);

        ChangeListener<Plads> pladsListener = (plads, OldPlads, NewPlads) -> selectedPladsChanged();
        lvPlads.getSelectionModel().selectedItemProperty().addListener(pladsListener);

        ChangeListener<Fad> fadListener = (plads, OldPlads, NewPlads) -> selectedFadChanged();
        cbFade.getSelectionModel().selectedItemProperty().addListener(fadListener);

        lvPlads.setCellFactory(lv -> new ListCell<Plads>() {
            @Override
            public void updateItem(Plads plads, boolean empty) {
                super.updateItem(plads, empty);
                if (empty || plads == null) {
                    setText(null);
                    setStyle(""); // Nulstil stil
                } else {
                  setText(plads.toString());

                    // Skift farve baseret på "ledig"-værdien
                    if (plads.isLedig()) {
                        setStyle("-fx-text-fill: green;"); // Grøn tekst for ledig
                    } else {
                        setStyle("-fx-text-fill: red;");   // Rød tekst for optaget
                    }
                }
            }
        });
    }

    public void clearAktion() {
    }

    public GridPane getFlytFadPane() {
        return flytFadPane;
    }

    public void selectedLagerChanged() {
        lager = cbLagre.getValue();
        lvReol.setItems(lager.getReoler());
        lvPlads.setItems(null);
        cbFade.getItems().clear();
        for (Fad f : controller.getStorage().getFade()) {
            if (f.getMængdeFyldtPåFad() > 0)
                cbFade.getItems().add(f);
        }
    }

    public void selectedReolChanged() {
        reol = lvReol.getSelectionModel().getSelectedItem();
        lvHylde.setItems(reol.getHylder());
    }

    public void selectedHyldeChanged() {
        hylde = lvHylde.getSelectionModel().getSelectedItem();
        lvPlads.setItems(hylde.getPladser());
    }

    public void selectedPladsChanged() {
        plads = lvPlads.getSelectionModel().getSelectedItem();
        lblValgtPlads.setText("");

        if (plads != null && plads.getFad() != null) {
            String påfyldning = "";
            List<Påfyldning> påfyldninger = plads.getFad().getPåfyldninger();
            List<Mængde> mængder = new ArrayList<>();
            for (int i = 0; i < påfyldninger.size(); i++) {
                påfyldning += "Destilleringsdato: " + påfyldninger.get(i).getPåfyldningsDato() +
                        "\nTid på fad: " +
                        "\nÅr: " + påfyldninger.get(i).antalÅrPåFad().getYears()+
                        "\nMåneder: " + påfyldninger.get(i).antalÅrPåFad().getMonths()+
                        "\nDage: " + påfyldninger.get(i).antalÅrPåFad().getDays()+
                        "\nklar til aftapning: " + (påfyldninger.get(i).klarTilAftapning()? "Ja\n" : "Nej\n");
                mængder = påfyldninger.get(i).getMængderPåfyldt();
                for (Mængde mængde : mængder) {
                    påfyldning += "Destillering: " + mængde.getDestillering() +"\n";
                }
            }
            lblValgtPlads.setText("FadID: " + plads.getFad().getFadId() + "\n" +
                    "Mængde på Fadet: " + plads.getFad().getMængdeFyldtPåFad() + "\n" + påfyldning + "\nMateriale: " + plads.getFad().getTidligereIndhold());
        }
    }

    public void selectedFadChanged() {
        fad = cbFade.getValue();
    }

    public ComboBox<Fad> getCbFade() {
        return cbFade;
    }

    public ComboBox<Lager> getCbLagre() {
        return cbLagre;
    }

    public ListView<Reol> getLvReol() {
        return lvReol;
    }

    public ListView<Hylde> getLvHylde() {
        return lvHylde;
    }

    public ListView<Plads> getLvPlads() {
        return lvPlads;
    }

    public Label getLblValgtPlads() {
        return lblValgtPlads;
    }

    public Lager getLager() {
        return lager;
    }

    public Reol getReol() {
        return reol;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public Plads getPlads() {
        return plads;
    }

    public Fad getFad() {
        return fad;
    }

    @Override
    public void update(GuiSubject s) {
        cbFade.getItems().clear();
        for (Fad f : controller.getStorage().getFade()) {
            if (f.getMængdeFyldtPåFad() > 0)
                cbFade.getItems().add(f);
        }
    }
}

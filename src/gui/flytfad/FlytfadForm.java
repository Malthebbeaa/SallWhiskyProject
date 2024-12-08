package gui.flytfad;

import application.controller.Controller;
import application.model.*;
import gui.GuiObserver;
import gui.GuiSubject;
import gui.PaneCreator;
import javafx.beans.value.ChangeListener;
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
    private Lager lager;
    private Reol reol;
    private Hylde hylde;
    private Plads plads;
    private Fad fad;
    private TextArea txFadInfo;

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
        flytFadPane.add(lblLedigePladser, 1, 0);

        PaneCreator fadOgLagerPane = new PaneCreator();
        flytFadPane.add(fadOgLagerPane, 0, 1);

        Label lblVælgFad = new Label("Vælg fad:");
        cbFade = new ComboBox<>();
        cbFade.setVisibleRowCount(3);
        getFyldteFade();

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
        lvReol.setStyle("-fx-control-inner-background: #F0F0F0");
        pladsPane.add(lblVælgReol, 0, 0);
        pladsPane.add(lvReol, 0, 1);

        Label lblVælgHylde = new Label("Vælg Hylde: ");
        lvHylde = new ListView<>();
        lvHylde.setStyle("-fx-control-inner-background: #F0F0F0");
        pladsPane.add(lblVælgHylde, 1, 0);
        pladsPane.add(lvHylde, 1, 1);

        Label lblVælgPlads = new Label("Vælg Plads:");
        lvPlads = new ListView<>();
        lvPlads.setStyle("-fx-control-inner-background: #F0F0F0");
        pladsPane.add(lblVælgPlads, 2, 0);
        pladsPane.add(lvPlads, 2, 1);

        txFadInfo = new TextArea();
        txFadInfo.setEditable(false);
        txFadInfo.setMaxWidth(300);
        txFadInfo.setStyle("-fx-control-inner-background: lightblue; -fx-font-weight: bold;");
        pladsPane.add(txFadInfo, 3, 1);

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
                    setStyle("");
                } else {
                    setText(plads.toString());
                    if (plads.isLedig()) {
                        setStyle("-fx-text-fill: green;");
                    } else {
                        setStyle("-fx-text-fill: red;");
                    }
                }
            }
        });
    }

    public void clearAktion() {
        cbFade.setValue(null);
        cbLagre.setValue(null);
        lvReol.setItems(null);
        lvHylde.setItems(null);
        lvPlads.setItems(null);
    }

    public GridPane getFlytFadPane() {
        return flytFadPane;
    }

    public void selectedLagerChanged() {
        lager = cbLagre.getValue();
        if (lager != null) {
            lvReol.setItems(lager.getReoler());
        }
        lvPlads.setItems(null);
    }

    public void selectedReolChanged() {
        reol = lvReol.getSelectionModel().getSelectedItem();
        if (reol != null) {
            lvHylde.setItems(reol.getHylder());
        }
    }

    public void selectedHyldeChanged() {
        hylde = lvHylde.getSelectionModel().getSelectedItem();
        if (hylde != null) {
            lvPlads.setItems(hylde.getPladser());
        }
    }

    public void selectedPladsChanged() {
        plads = lvPlads.getSelectionModel().getSelectedItem();
        txFadInfo.setText("");

        if (plads != null && plads.getFad() != null) {
            String påfyldning = "";
            List<PåfyldningsComponent> påfyldninger = plads.getFad().getPåfyldningsComponenter();
            List<PåfyldningsComponent> mængder = new ArrayList<>();
            List<LocalDate> omhældningsdatoer = new ArrayList<>();
            for (int i = 0; i < påfyldninger.size(); i++) {
                omhældningsdatoer = påfyldninger.get(i).getOmhældningsDatoer();
                påfyldning += "Påfyldningsdato: " + påfyldninger.get(i).getPåfyldningsDato() +
                        "\nTid på fad: " +
                        "\nÅr: " + påfyldninger.get(i).antalÅrPåFad(LocalDate.now()).getYears() +
                        "\nMåneder: " + påfyldninger.get(i).antalÅrPåFad(LocalDate.now()).getMonths() +
                        "\nDage: " + påfyldninger.get(i).antalÅrPåFad(LocalDate.now()).getDays() +
                        "\nklar til aftapning: " + (påfyldninger.get(i).klarTilAftapning(LocalDate.now()) ? "Ja\n" : "Nej\n");
                for (int j = 0; j < påfyldninger.get(i).getOmhældningsDatoer().size(); j++) {
                    påfyldning += "omhældningsdato: " + omhældningsdatoer +"\n";
                }
                mængder = påfyldninger.get(i).getPåfyldningsComponenter();
                for (PåfyldningsComponent væske : mængder) {
                    if (væske instanceof Væske) {
                        påfyldning += "batchnummer: " + væske.getDestillering().getBatchNummer() + "\nAlkohol: " + væske.getDestillering().getAlkoholProcent() + " %\nantal liter i fad: " + væske.getVæskeMængde() + " L\n";
                    }
                }
            }
            txFadInfo.setText("FadID: " + plads.getFad().getFadId() + "\n" +
                    "Mængde på Fadet: " + plads.getFad().getMængdeFyldtPåFad() + " L\n" + påfyldning + "Fadtype: " + plads.getFad().getTidligereIndhold());
        }
    }

    public void selectedFadChanged() {
        fad = cbFade.getValue();
    }

    public Lager getLager() {
        return lager;
    }

    public Plads getPlads() {
        return plads;
    }

    public Fad getFad() {
        return fad;
    }

    public ComboBox<Fad> getCbFade() {
        return cbFade;
    }

    public void getFyldteFade() {
        cbFade.getItems().clear();
        for (Fad f : controller.getStorage().getFade()) {
            if (f.getMængdeFyldtPåFad() > 0)
                cbFade.getItems().add(f);
        }
    }

    @Override
    public void update(GuiSubject s) {
        getFyldteFade();
    }
}

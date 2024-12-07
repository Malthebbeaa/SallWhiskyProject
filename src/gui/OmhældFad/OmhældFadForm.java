package gui.OmhældFad;

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

public class OmhældFadForm implements GuiObserver {
    private GridPane omhældFadPane;
    private Controller controller;
    private OmhældFadHandler handler;
    private ComboBox<Fad> cbFraFad;
    private ComboBox<Fad> cbTilFad;
    private ListView<PåfyldningsComponent> lvMuligeVæsker;
    private Fad fraFad;
    private Fad destinationsFad;
    private PåfyldningsComponent væske;
    private TextArea txFadInfo;
    private TextField txfMængde;

    public OmhældFadForm(Controller controller, OmhældFadHandler handler) {
        this.controller = controller;
        this.handler = handler;
        omhældFadPane = new GridPane();
        initForm();
    }

    private void initForm() {
        omhældFadPane.setHgap(10);
        omhældFadPane.setVgap(10);

        Label lblVælgFlyt = new Label("Vælg Hvad der skal flyttes:");
        Label lblLedigePladser = new Label("Vælg placering:");
        omhældFadPane.add(lblVælgFlyt, 0, 0);
        omhældFadPane.add(lblLedigePladser,1,0);

        PaneCreator fadOgLagerPane = new PaneCreator();
        omhældFadPane.add(fadOgLagerPane, 0, 1);

        Label lblVælgFad = new Label("Vælg fra fad:");
        cbFraFad = new ComboBox<>();
        cbFraFad.setVisibleRowCount(3);
        getFyldteFade();

        fadOgLagerPane.add(lblVælgFad, 0, 0);
        fadOgLagerPane.add(cbFraFad, 1, 0);

        Label lblVælgLager = new Label("Vælg til fad:");
        cbTilFad = new ComboBox<>(controller.getStorage().getFade());
        fadOgLagerPane.add(lblVælgLager, 0, 1);
        fadOgLagerPane.add(cbTilFad, 1, 1);

        Label lblMængde = new Label("Mængde: ");
        txfMængde = new TextField();
        fadOgLagerPane.add(lblMængde,0,2);
        fadOgLagerPane.add(txfMængde,1,2);

        PaneCreator pladsPane = new PaneCreator();
        omhældFadPane.add(pladsPane, 1, 1);

        Label lblVælgVæske = new Label("Vælg Væske:");
        lvMuligeVæsker = new ListView<>();
        lvMuligeVæsker.setStyle("-fx-control-inner-background: #F0F0F0");
        pladsPane.add(lblVælgVæske, 0, 0);
        pladsPane.add(lvMuligeVæsker, 0, 1);

        txFadInfo = new TextArea();
        txFadInfo.setEditable(false);
        txFadInfo.setMaxWidth(300);
        txFadInfo.setStyle("-fx-control-inner-background: lightblue; -fx-font-weight: bold;");
        pladsPane.add(txFadInfo,3,1);

        ChangeListener<Fad> fadListener = (fad, OldFad, NewFad) -> selectedFadChanged();
        cbFraFad.getSelectionModel().selectedItemProperty().addListener(fadListener);

        ChangeListener<Fad> destinationsFadListener = (fad, OldFad, NewFad) -> selectedDestinationsFadChanged();
        cbTilFad.getSelectionModel().selectedItemProperty().addListener(destinationsFadListener);
    }

    public void clearAktion() {
        cbFraFad.setValue(null);
        cbTilFad.setValue(null);
        lvMuligeVæsker.setItems(null);
    }

    public GridPane getOmhældFadPane() {
        return omhældFadPane;
    }

    public void selectedFadChanged() {
        fraFad = cbFraFad.getValue();
        lvMuligeVæsker.getItems().clear();
        if(fraFad != null) {
            lvMuligeVæsker.getItems().addAll(fraFad.getPåfyldningsComponenter());
        }
    }

    public void selectedDestinationsFadChanged(){
        destinationsFad = cbTilFad.getValue();
        væske = lvMuligeVæsker.getSelectionModel().getSelectedItem();
    }


    public Fad getfraFad() {
        return fraFad;
    }

    public Fad getDestinationsFad() {
        return destinationsFad;
    }

    public void getFyldteFade() {
        cbFraFad.getItems().clear();
        for (Fad f : controller.getStorage().getFade()) {
            if (f.getMængdeFyldtPåFad() > 0)
                cbFraFad.getItems().add(f);
        }
    }

    @Override
    public void update(GuiSubject s) {
        getFyldteFade();
    }

    public TextField getTxfMængde() {
        return txfMængde;
    }

    public PåfyldningsComponent getVæske() {
        return væske;
    }
}

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
import java.util.Date;
import java.util.List;

public class OmhældFadForm implements GuiObserver {
    private GridPane omhældFadPane;
    private Controller controller;
    private OmhældFadHandler handler;
    private ComboBox<Fad> cbFraFad;
    private ComboBox<Fad> cbTilFad;
    private Fad fraFad;
    private Fad destinationsFad;
    private PåfyldningsComponent væske;
    private TextField txfMængde;
    private DatePicker datePickerOmhældningsdato;

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
        omhældFadPane.add(lblVælgFlyt, 0, 0);

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
        cbTilFad.setVisibleRowCount(3);
        fadOgLagerPane.add(lblVælgLager, 0, 1);
        fadOgLagerPane.add(cbTilFad, 1, 1);

        Label lblMængde = new Label("Mængde: ");
        txfMængde = new TextField();
        fadOgLagerPane.add(lblMængde,0,2);
        fadOgLagerPane.add(txfMængde,1,2);

        Label lblOmhældningsdato = new Label("Omhældningsdato: ");
        datePickerOmhældningsdato = new DatePicker(LocalDate.now());
        fadOgLagerPane.add(lblOmhældningsdato, 0,3);
        fadOgLagerPane.add(datePickerOmhældningsdato, 1,3);

        ChangeListener<Fad> fadListener = (fad, OldFad, NewFad) -> selectedFadChanged();
        cbFraFad.getSelectionModel().selectedItemProperty().addListener(fadListener);

        ChangeListener<Fad> destinationsFadListener = (fad, OldFad, NewFad) -> selectedDestinationsFadChanged();
        cbTilFad.getSelectionModel().selectedItemProperty().addListener(destinationsFadListener);
    }

    public void clearAktion() {
        cbFraFad.setValue(null);
        cbTilFad.setValue(null);
    }

    public GridPane getOmhældFadPane() {
        return omhældFadPane;
    }

    public void selectedFadChanged() {
        fraFad = cbFraFad.getValue();
        if(this.fraFad != null) {
            væske = fraFad.getPåfyldningsComponenter().getFirst();
        }
    }

    public void selectedDestinationsFadChanged(){
        destinationsFad = cbTilFad.getValue();

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
    public LocalDate getOmhældningsDato(){
        return datePickerOmhældningsdato.getValue();
    }
}

package gui.aftapning;

import application.controller.Controller;
import application.model.Fad;
import gui.PaneCreator;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class AftapningForm {
    private Controller controller;
    private ComboBox<Fad> cboxFad;
    private DatePicker datePickerAftapningsDato;
    private TextField txfMængdeTilAftapning, txfAlkoholProcent;
    private GridPane aftapningsPane, aftapningsInfoPane;

    public AftapningForm(Controller controller) {
        this.controller = controller;
        aftapningsPane = new GridPane();
        initForm();
    }

    private void initForm() {
        aftapningsInfoPane = new PaneCreator();
        Label lblPåfyldning = new Label("Aftap fad:");
        aftapningsPane.add(lblPåfyldning, 0,0);
        aftapningsPane.add(aftapningsInfoPane, 0,1);
        aftapningsPane.setHgap(10);
        aftapningsPane.setVgap(10);


        Label lblFad = new Label("Vælg Fad:");
        aftapningsInfoPane.add(lblFad, 0,0);
        cboxFad = new ComboBox<>();
        cboxFad.setItems(controller.getStorage().getFade());
        aftapningsInfoPane.add(cboxFad,1,0);


        Label lblPåfyldningsDato = new Label("Aftapningsningsdato: ");
        aftapningsInfoPane.add(lblPåfyldningsDato, 0,1);
        datePickerAftapningsDato = new DatePicker(LocalDate.now());
        aftapningsInfoPane.add(datePickerAftapningsDato, 1,1);

        Label lblPåfyldningsMængde = new Label("Mængde til aftating (L): ");
        aftapningsInfoPane.add(lblPåfyldningsMængde, 0,2);
        txfMængdeTilAftapning = new TextField();
        txfMængdeTilAftapning.setMaxWidth(175);
        aftapningsInfoPane.add(txfMængdeTilAftapning, 1,2);

        Label lblAlkoholProcent = new Label("Alkoholprocent: ");
        aftapningsInfoPane.add(lblAlkoholProcent, 0,3);
        txfAlkoholProcent = new TextField();
        txfAlkoholProcent.setMaxWidth(175);
        aftapningsInfoPane.add(txfAlkoholProcent, 1,3);


    }

    public Fad getFad() {return cboxFad.getValue();}

    public LocalDate getAftapningsDato() {return datePickerAftapningsDato.getValue();}

    public double getTxfMængdeTilAftapning() {return Double.parseDouble(txfMængdeTilAftapning.getText());}
    public double getTxfAlkoholProcent() { return Double.parseDouble(txfAlkoholProcent.getText());
    }

    public GridPane getAftapningsPane() {return aftapningsPane;}

    public void clearAction(){
        cboxFad.setValue(null);
        datePickerAftapningsDato.setValue(LocalDate.now());
        txfMængdeTilAftapning.clear();
    }


}


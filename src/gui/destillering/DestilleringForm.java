package gui.destillering;

import application.controller.Controller;
import application.model.Maltbatch;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class DestilleringForm {
    private DatePicker datepickerstartDato, datepickerSlutDato;
    private TextField txfAlkoholProcent, txfVæskeMængde, txfAntalDestilleringer, txfKommentar;
    private ComboBox<Maltbatch> comboBoxMaltbatch;
    private GridPane destilleringPane;
    private Controller controller;

    public DestilleringForm(Controller controller) {
        this.destilleringPane = new GridPane();
        this.controller = controller;
        initForm();
    }

    private void initForm() {
        destilleringPane.setHgap(10);
        destilleringPane.setVgap(10);
        destilleringPane.setPadding(new Insets(20));

        Label lblMaltbatch = new Label("Maltbatch: ");
        comboBoxMaltbatch = new ComboBox();
        comboBoxMaltbatch.getItems().addAll(controller.getStorage().getMaltbatches());
        destilleringPane.add(lblMaltbatch, 0,0);
        destilleringPane.add(comboBoxMaltbatch, 1,0);

        Label lblAntalDestilleringer = new Label("Antal destilleringer: ");
        txfAntalDestilleringer = new TextField();
        txfAntalDestilleringer.setPrefWidth(30);
        destilleringPane.add(lblAntalDestilleringer, 2,0);
        destilleringPane.add(txfAntalDestilleringer, 3,0);

        Label lblStartDato = new Label("Startdato: ");
        datepickerstartDato = new DatePicker(LocalDate.now());
        destilleringPane.add(lblStartDato, 0,1);
        destilleringPane.add(datepickerstartDato, 1,1);
        Label lblSlutDato = new Label("Slutdato: ");
        datepickerSlutDato = new DatePicker(LocalDate.now());
        destilleringPane.add(lblSlutDato, 2,1);
        destilleringPane.add(datepickerSlutDato, 3,1);

        Label lblVæskeMængde = new Label("Væskemængde (L): ");
        destilleringPane.add(lblVæskeMængde, 0,2);
        txfVæskeMængde = new TextField();
        destilleringPane.add(txfVæskeMængde, 1,2);

        Label lblAlkoholProcent = new Label("Alkoholprocent: ");
        destilleringPane.add(lblAlkoholProcent, 2,2);
        txfAlkoholProcent = new TextField();
        destilleringPane.add(txfAlkoholProcent, 3,2);

        Label lblKommentar = new Label("Evt. kommentar: ");
        destilleringPane.add(lblKommentar, 0,3);
        txfKommentar = new TextField();
        destilleringPane.add(txfKommentar, 1,3, 3,1);
    }
    public LocalDate getStartDato(){return datepickerstartDato.getValue();}
    public LocalDate getSlutDato(){return datepickerSlutDato.getValue();}
    public double getAlkoholProcent(){return Double.parseDouble(txfAlkoholProcent.getText());}
    public int getAntalDestilleringer(){return Integer.parseInt(txfAntalDestilleringer.getText());}
    public double getVæskeMængde(){return Double.parseDouble(txfVæskeMængde.getText());}
    public Maltbatch getMaltbatch(){return comboBoxMaltbatch.getValue();}
    public String getKommentar(){return txfKommentar.getText();}
    public GridPane getDestilleringPane(){return destilleringPane;}

    public void clearAction(){
        datepickerstartDato.setValue(LocalDate.now());
        datepickerSlutDato.setValue(LocalDate.now());
        txfAlkoholProcent.clear();
        txfVæskeMængde.clear();
        txfAntalDestilleringer.clear();
        txfKommentar.clear();
        comboBoxMaltbatch.setValue(null);
    }
}

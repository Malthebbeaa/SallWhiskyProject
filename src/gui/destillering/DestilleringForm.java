package gui.destillering;

import application.controller.Controller;
import application.model.Maltbatch;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.time.LocalDate;

public class DestilleringForm {
    private DatePicker datepickerstartDato, datepickerSlutDato;
    private TextField txfAlkoholProcent, txfVæskeMængde, txfAntalDestilleringer, txfKommentar;
    private ComboBox<Maltbatch> comboBoxMaltbatch;
    private GridPane destilleringInfoPane, destilleringPane;
    private Controller controller;

    public DestilleringForm(Controller controller) {
        this.destilleringPane = new GridPane();
        this.controller = controller;
        initForm();
    }

    private void initForm() {
        destilleringInfoPane = new GridPane();
        destilleringInfoPane.setHgap(10);
        destilleringInfoPane.setVgap(10);
        destilleringInfoPane.setPadding(new Insets(20,50,50,10));
        destilleringInfoPane.setBorder(Border.stroke(Paint.valueOf("Black")));

        Label lblOpret = new Label("Opret Destillering:");
        destilleringPane.add(lblOpret, 0,0);
        destilleringPane.add(destilleringInfoPane, 0,1);
        destilleringPane.setHgap(10);
        destilleringPane.setVgap(10);

        Label lblMaltbatch = new Label("Maltbatch: ");
        comboBoxMaltbatch = new ComboBox();
        comboBoxMaltbatch.getItems().addAll(controller.getStorage().getMaltbatches());
        destilleringInfoPane.add(lblMaltbatch, 0,0);
        destilleringInfoPane.add(comboBoxMaltbatch, 1,0);

        Label lblAntalDestilleringer = new Label("Antal destilleringer: ");
        txfAntalDestilleringer = new TextField();
        txfAntalDestilleringer.setPrefWidth(30);
        destilleringInfoPane.add(lblAntalDestilleringer, 2,0);
        destilleringInfoPane.add(txfAntalDestilleringer, 3,0);

        Label lblStartDato = new Label("Startdato: ");
        datepickerstartDato = new DatePicker(LocalDate.now());
        destilleringInfoPane.add(lblStartDato, 0,1);
        destilleringInfoPane.add(datepickerstartDato, 1,1);
        Label lblSlutDato = new Label("Slutdato: ");
        datepickerSlutDato = new DatePicker(LocalDate.now());
        destilleringInfoPane.add(lblSlutDato, 2,1);
        destilleringInfoPane.add(datepickerSlutDato, 3,1);

        Label lblVæskeMængde = new Label("Væskemængde (L): ");
        destilleringInfoPane.add(lblVæskeMængde, 0,2);
        txfVæskeMængde = new TextField();
        destilleringInfoPane.add(txfVæskeMængde, 1,2);

        Label lblAlkoholProcent = new Label("Alkoholprocent: ");
        destilleringInfoPane.add(lblAlkoholProcent, 2,2);
        txfAlkoholProcent = new TextField();
        destilleringInfoPane.add(txfAlkoholProcent, 3,2);

        Label lblKommentar = new Label("Evt. kommentar: ");
        destilleringInfoPane.add(lblKommentar, 0,3);
        txfKommentar = new TextField();
        destilleringInfoPane.add(txfKommentar, 1,3, 3,1);
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

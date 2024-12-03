package gui.fadSøgning;

import application.controller.Controller;
import application.model.Fad;
import application.model.Påfyldning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javax.swing.text.TableView;
import java.time.LocalDate;
import java.util.List;

public class SøgningHandler {
    private Controller controller;
    public SøgningHandler(Controller controller) {
        this.controller = controller;
    }

    public void søgningFadIdAction(SøgningForm form, int søgId){
        int low = 0;
        int high = form.getTableViewFade().getItems().size() - 1;

        while (high >= low){
            int mid = low + (high - low) / 2;
            int fadId = form.getTableViewFade().getItems().get(mid).getFadId();

            if (fadId == søgId){
                form.getTableViewFade().getSelectionModel().select(fadId - 1);
                form.getTableViewFade().scrollTo(fadId - 1);
                break;
            }

            if (fadId < søgId){
                low = mid + 1;
            }

            if (fadId > søgId){
                high = mid - 1;
            }
        }
    }

    public void søgningMaterialeAction(SøgningForm form, String søgId){
        ObservableList<Fad> fadeMedMateriale = FXCollections.observableArrayList();
        for (int i = 0; i < form.getTableViewFade().getItems().size(); i++) {
            if(form.getTableViewFade().getItems().get(i).getMateriale().toLowerCase().contains(søgId.toLowerCase())){
                fadeMedMateriale.add(form.getTableViewFade().getItems().get(i));
                form.getTableViewFade().getSelectionModel().select(i);
                form.getTableViewFade().scrollTo(i);
            }
        }
        form.getTableViewFade().setItems(fadeMedMateriale);
    }

    public void søgningAction(SøgningForm form, int søgId){
        form.getTableViewFade().getItems().stream()
                .filter(item -> item.getFadId() == søgId)
                .findAny()
                .ifPresent(item -> {
                    form.getTableViewFade().getSelectionModel().select(item);
                    form.getTableViewFade().scrollTo(item);
                });
    }

    public void findKlareFade(SøgningForm form){
        ObservableList<Fad> alleFade = controller.getStorage().getFade();

        FilteredList<Fad> filtreredeFade = new FilteredList<>(alleFade, fad -> {
            List<Påfyldning> påfyldninger = fad.getPåfyldninger();
            return !påfyldninger.isEmpty() && påfyldninger.getLast().klarTilAftapning(LocalDate.now());
        });

        form.getTableViewFade().setItems(filtreredeFade);
    }

    public void alleFade(SøgningForm form){
        ObservableList<Fad> alleFade = controller.getStorage().getFade();

        // Sæt den oprindelige liste som TableView's items uden filter
        form.getTableViewFade().setItems(alleFade);
    }
}

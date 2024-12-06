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

    public void søgningFadIdAction(SøgningForm form, int søgId) {
        int low = 0;
        int high = form.getTableViewFade().getItems().size() - 1;

        while (high >= low) {
            int mid = low + (high - low) / 2;
            int fadId = form.getTableViewFade().getItems().get(mid).getFadId();

            if (fadId == søgId) {
                form.getTableViewFade().getSelectionModel().select(fadId - 1);
                form.getTableViewFade().scrollTo(fadId - 1);
                break;
            }

            if (fadId < søgId) {
                low = mid + 1;
            }

            if (fadId > søgId) {
                high = mid - 1;
            }
        }
    }

    public void søgningMaterialeAction(SøgningForm form, String søgId) {
        ObservableList<Fad> fadeMedMateriale = FXCollections.observableArrayList();
        for (int i = 0; i < form.getTableViewFade().getItems().size(); i++) {
            if (form.getTableViewFade().getItems().get(i).getMateriale().toLowerCase().contains(søgId.toLowerCase())) {
                fadeMedMateriale.add(form.getTableViewFade().getItems().get(i));
            }
        }
        form.getTableViewFade().setItems(fadeMedMateriale);
    }

    public void søgningTidligereIndholdAction(SøgningForm form, String søgId) {
        ObservableList<Fad> fadeMedTidligereIndhold = FXCollections.observableArrayList();
        for (int i = 0; i < form.getTableViewFade().getItems().size(); i++) {
            if (form.getTableViewFade().getItems().get(i).getTidligereIndhold().toLowerCase().contains(søgId.toLowerCase())) {
                fadeMedTidligereIndhold.add(form.getTableViewFade().getItems().get(i));
            }
        }
        form.getTableViewFade().setItems(fadeMedTidligereIndhold);
    }

    public void søgningLagerpladsAction(SøgningForm form, String søgId) {
        ObservableList<Fad> fadeLagerPlads= FXCollections.observableArrayList();
        for (int i = 0; i < form.getTableViewFade().getItems().size(); i++) {
            if(form.getTableViewFade().getItems().get(i).getPlads() != null) {
                if (form.getTableViewFade().getItems().get(i).getPlads().getHylde().getReol().getLager().getNavn().toLowerCase().contains(søgId.toLowerCase()) || søgId.toLowerCase().contains("reol") || søgId.toLowerCase().contains("hylde") || søgId.toLowerCase().contains("plads")) {
                    fadeLagerPlads.add(form.getTableViewFade().getItems().get(i));
                }
                else if(søgId.matches("\\d+")){
                    if(form.getTableViewFade().getItems().get(i).getPlads().getPladsNummer() == Integer.parseInt(søgId)) {
                        fadeLagerPlads.add(form.getTableViewFade().getItems().get(i));
                    }
                    else if(form.getTableViewFade().getItems().get(i).getPlads().getHylde().getHyldeNummer() == Integer.parseInt(søgId)) {
                        fadeLagerPlads.add(form.getTableViewFade().getItems().get(i));
                    }
                    else if(form.getTableViewFade().getItems().get(i).getPlads().getHylde().getReol().getReolNummer() == Integer.parseInt(søgId)) {
                        fadeLagerPlads.add(form.getTableViewFade().getItems().get(i));
                    }
                }
            }
            else{
            }
        }
        form.getTableViewFade().setItems(fadeLagerPlads);
    }

    public void søgningAction(SøgningForm form, int søgId) {
        form.getTableViewFade().getItems().stream()
                .filter(item -> item.getFadId() == søgId)
                .findAny()
                .ifPresent(item -> {
                    form.getTableViewFade().getSelectionModel().select(item);
                    form.getTableViewFade().scrollTo(item);
                });
    }

    public void findKlareFade(SøgningForm form) {
        ObservableList<Fad> alleFade = controller.getStorage().getFade();

        FilteredList<Fad> filtreredeFade = new FilteredList<>(alleFade, fad -> {
            List<Påfyldning> påfyldninger = fad.getPåfyldninger();

            // listen af påfyldninger må ikke være tom
            if (påfyldninger.isEmpty()) {
                return false;
            }

            // Find sidste påfyldning
            Påfyldning sidstePåfyldning = påfyldninger.get(påfyldninger.size() - 1);

            // Tjek om sidste påfyldning ikke er tom og klar til aftapning
            return sidstePåfyldning.getLiterPåfyldt() > 0 &&
                    sidstePåfyldning.klarTilAftapning(LocalDate.now());
        });

        form.getTableViewFade().setItems(filtreredeFade);
    }

    public void alleFade(SøgningForm form) {
        ObservableList<Fad> alleFade = controller.getStorage().getFade();

        // Sæt den oprindelige liste som TableView's items uden filter
        form.getTableViewFade().setItems(alleFade);
    }
}

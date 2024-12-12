package gui.fadSøgning;

import application.controller.Controller;
import application.model.Fad;
import application.model.PåfyldningsComponent;
import application.model.VæskeMix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.time.LocalDate;
import java.util.List;

public class SøgningHandler {
    private Controller controller;
    public SøgningHandler(Controller controller) {
        this.controller = controller;
    }

    public void søgningFadIdAction(SøgningForm form, int søgId){
        ObservableList<Fad> fadMedId = FXCollections.observableArrayList();
        int low = 0;
        int high = controller.getStorage().getFade().size() - 1;


        while (high >= low){
            int mid = low + (high - low) / 2;
            int fadId = controller.getStorage().getFade().get(mid).getFadId();

            if (fadId == søgId){
                fadMedId.add(controller.getStorage().getFade().get(fadId - 1));
                form.getTableViewFade().setItems(fadMedId);
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
        for (int i = 0; i < controller.getStorage().getFade().size(); i++) {
            if(controller.getStorage().getFade().get(i).getMateriale().toLowerCase().contains(søgId.toLowerCase())){
                fadeMedMateriale.add(controller.getStorage().getFade().get(i));
            }
        }
        form.getTableViewFade().setItems(fadeMedMateriale);
    }

    public void søgningTidligereIndholdAction(SøgningForm form, String søgId) {
        ObservableList<Fad> fadeMedTidligereIndhold = FXCollections.observableArrayList();
        for (int i = 0; i < controller.getStorage().getFade().size(); i++) {
            if (controller.getStorage().getFade().get(i).getTidligereIndhold().toLowerCase().contains(søgId.toLowerCase())) {
                fadeMedTidligereIndhold.add(controller.getStorage().getFade().get(i));
            }
        }
        form.getTableViewFade().setItems(fadeMedTidligereIndhold);
    }

    public void findKlareFade(SøgningForm form){
        ObservableList<Fad> alleFade = controller.getStorage().getFade();

        FilteredList<Fad> filtreredeFade = new FilteredList<>(alleFade, fad -> {
            PåfyldningsComponent påfyldninger = fad.getPåfyldningsComponent();

            // listen af påfyldninger må ikke være tom
            if (påfyldninger == null) {
                return false;
            }

            // Find sidste påfyldning
            PåfyldningsComponent sidsteVæskeMix = fad.getPåfyldningsComponent();

            // Tjek om sidste påfyldning ikke er tom og klar til aftapning
            if(sidsteVæskeMix instanceof VæskeMix) {
                return sidsteVæskeMix.getLiterPåfyldt() > 0 &&
                        sidsteVæskeMix.klarTilAftapning(LocalDate.now());
            }
            else{
                return sidsteVæskeMix.getVæskeMængde() > 0 && sidsteVæskeMix.klarTilAftapning(LocalDate.now());
            }
        });

        form.getTableViewFade().setItems(filtreredeFade);
    }

    public void alleFade(SøgningForm form){
        ObservableList<Fad> alleFade = controller.getStorage().getFade();

        // Sæt den oprindelige liste som TableView's items uden filter
        form.getTableViewFade().setItems(alleFade);
    }
}

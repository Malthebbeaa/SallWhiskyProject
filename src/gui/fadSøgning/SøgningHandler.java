package gui.fadSøgning;

import application.model.Fad;

public class SøgningHandler {
    public void søgningFadIdAction(SøgningForm form, int søgId){
        for (Fad fad : form.getTableViewFade().getItems()) {

        }
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
}

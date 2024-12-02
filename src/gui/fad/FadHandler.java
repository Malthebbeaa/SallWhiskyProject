package gui.fad;

import application.controller.Controller;
import application.model.Fad;
import application.model.FadLeverandør;

public class FadHandler {
    private Controller controller;

    public FadHandler(Controller controller){
        this.controller = controller;
    }

    public void opretFadAction(FadForm form){
        int størrelse = form.getTøndensStørrelse();
        String materiale = form.getMateriale();
        FadLeverandør fadleverandør = form.getFadLeverandør();
        String tidligereIndhold = form.getTidligerIndhold();
        int alder = form.getTøndensAlder();
        int antalGangeBrugt = form.getAntalGangeBrugt();


        Fad fad = controller.opretFad(størrelse, materiale, fadleverandør, tidligereIndhold, alder, antalGangeBrugt);

        clearAction(form);
    }

    public void clearAction(FadForm form){
        form.getTxfAlder().clear();
        form.getTxfAntalGangeBrugt().clear();

        form.getComboboxLeverandører().setValue(null);
        form.getComboboxMateriale().setValue(null);
        form.getComboboxStørrelse().setValue(null);
        form.getComboboxTidligereIndhold().setValue(null);
    }
}

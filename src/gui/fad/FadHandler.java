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

        form.clearAction();
    }
}

package gui.Whiskyprodukt;

import application.controller.Controller;
import application.model.Fad;
import application.model.FadLeverandør;

public class WhiskyProduktHandler {
    private Controller controller;

    public WhiskyProduktHandler(Controller controller){
        this.controller = controller;
    }

    public void opretWhiskyProduktAction(WhiskyProduktForm form){
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

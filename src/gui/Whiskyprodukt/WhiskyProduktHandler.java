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
        int størrelse = form.getStørrelse();
        String materiale = form.getMateriale();
        FadLeverandør fadleverandør = form.getFadleverandør();
        String tidligereIndhold = form.getTidligereIndhold();
        int alder = form.getAlder();
        int antalGangeBrugt = form.getAntalGangeBrugt();


        Fad fad = controller.opretFad(størrelse, materiale, fadleverandør, tidligereIndhold, alder, antalGangeBrugt);

        form.clearAction();
    }
}

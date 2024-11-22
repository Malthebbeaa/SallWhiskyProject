package application.controller;

import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
import application.model.Rygemateriale;
import storage.StorageInterface;

import java.time.LocalDate;

public class Controller {
    private StorageInterface storage;

    public Controller(StorageInterface storage){
        this.storage = storage;
    }

    public Maltbatch opretMaltbatch(String batchNummer, int mængde, Korn korn){
        Maltbatch maltbatch = new Maltbatch(batchNummer,mængde,korn);
        storage.addMaltbatch(maltbatch);
        return maltbatch;
    }

    public Mark opretMark(String markNavn, boolean økologisk){
        Mark mark = new Mark(markNavn, økologisk);
        return mark;
    }

    public Korn opretKorn(LocalDate høstdag, String sort, Mark mark){
        Korn korn = new Korn(høstdag, sort, mark);
        return korn;
    }

    public Rygemateriale opretRygemateriale(String type){
        Rygemateriale rygemateriale = new Rygemateriale(type);
        return rygemateriale;
    }
}

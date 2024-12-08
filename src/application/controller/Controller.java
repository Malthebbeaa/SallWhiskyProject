package application.controller;

import application.model.*;
import javafx.scene.control.Alert;
import storage.StorageInterface;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private StorageInterface storage;

    public Controller(StorageInterface storage) {
        this.storage = storage;
    }

    public Maltbatch opretMaltbatch(String batchNummer, double mængde, Korn korn) {
        Maltbatch maltbatch = new Maltbatch(batchNummer, mængde, korn);
        storage.addMaltbatch(maltbatch);
        return maltbatch;
    }

    public Mark opretMark(String markNavn, boolean økologisk) {
        Mark mark = new Mark(markNavn, økologisk);
        storage.addMark(mark);
        return mark;
    }

    public Korn opretKorn(LocalDate høstdag, String sort, Mark mark) {
        Korn korn = new Korn(høstdag, sort, mark);
        storage.addKorn(korn);
        return korn;
    }

    public Rygemateriale opretRygemateriale(String type) {
        Rygemateriale rygemateriale = new Rygemateriale(type);
        return rygemateriale;
    }

    public Destillering opretDestillering(int antalDistilleringer, LocalDate startDato, LocalDate slutDato,
                                          double væskeMængde, double alkoholProcent, Maltbatch maltbatch) {
        Destillering destillering = new Destillering(antalDistilleringer, startDato, slutDato,
                væskeMængde, alkoholProcent, maltbatch);
        storage.addDestillering(destillering);

        return destillering;
    }

    public void tilføjKommentarTilDestillering(String kommentarTekst, Destillering destillering) {
        Kommentar kommentar = new Kommentar(kommentarTekst);
        if (kommentar != null) {
            destillering.setKommentar(kommentar);
        }
    }

    public Fad opretFad(int størrelse, String materiale, FadLeverandør fadLeverandør, String tidligereIndhold,
                        int alder, int antalGangeBrugt) {
        Fad fad = new Fad(størrelse, materiale, fadLeverandør, tidligereIndhold, alder, antalGangeBrugt);
        storage.addFad(fad);
        return fad;
    }

    public Lager opretLager(String navn, String vejnavn, String postnummer, String by) {
        Lager lager = new Lager(navn, vejnavn, postnummer, by);
        storage.addLager(lager);
        return lager;
    }

    public WhiskyProdukt opretWhiskyProdukt(String navn, LocalDate opretDato) {
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(navn, opretDato);
        storage.addWhiskyProdukt(whiskyProdukt);
        return whiskyProdukt;
    }

    public Væske opretVæske(double mængde, Destillering destillering) {
        Væske væske = new Væske(mængde, destillering);
        return væske;
    }


    public VæskeMix opretVæskeMix(Fad fad, LocalDate påfyldningsDato) {
        VæskeMix væskeMix = new VæskeMix(påfyldningsDato, fad);
        storage.addPåfyldning(væskeMix);
        return væskeMix;
    }

    public void påfyldFad(VæskeMix væskeMix, Fad fad){
        try {
            fad.tilføjVæske(væskeMix);
        } catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }
    public void lavAftapninger(List<Aftapning> aftapninger, WhiskyProdukt whiskyProdukt){
        for (Aftapning aftapning : aftapninger){
            whiskyProdukt.tilføjAftapning(aftapning);
            aftapning.getPåfyldning().aftap(aftapning.getLiterAftappet());
            aftapning.getPåfyldning().getFad().aftapVæskePåFad(aftapning);
        }
    }

    public StorageInterface getStorage() {
        return storage;
    }


    public FadLeverandør opretFadlevandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        storage.addFadleverandør(fadLeverandør);
        return fadLeverandør;
    }

    public void flytFad(Plads plads, Fad fad){
        if(plads.isLedig() && fad != null){
            fad.setPlads(plads);
        }
        else{
        }
    }

    public void flytVæskeTilFad(Fad fraFad, Fad destinationsFad, PåfyldningsComponent væske, double mængde){
        fraFad.flytDelAfVæskeMixTilFadHjælper(destinationsFad,væske,mængde);
    }

    public void flytVæske(Væske væske){

    }
}

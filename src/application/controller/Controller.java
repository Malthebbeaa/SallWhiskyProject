package application.controller;

import application.model.*;
import storage.StorageInterface;

import java.time.LocalDate;

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

    public WhiskyProdukt opretWhiskyProdukt(int aarLagret, String navn, String whiskytype, boolean fortyndet) {
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(aarLagret, navn, whiskytype, fortyndet);
        storage.addWhiskyProdukt(whiskyProdukt);
        return whiskyProdukt;
    }

    public FadLeverandør opretFadlevandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        storage.addFadleverandør(fadLeverandør);
        return fadLeverandør;
    }

    public void påfyldFad(Påfyldning påfyldning, Fad fad) {
        fad.tilføjPåfyldning(påfyldning);
    }

    public Aftapning aftapFad(LocalDate aftapningsDato, Double alkoholProcent, Fad fad) {
        Aftapning aftapning = new Aftapning(aftapningsDato, alkoholProcent, fad);
        storage.addAftapning(aftapning);
        return aftapning;
    }

    public StorageInterface getStorage() {
        return storage;
    }


}

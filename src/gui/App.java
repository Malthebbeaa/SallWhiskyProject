package gui;

import application.controller.Controller;
import application.model.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Application.launch(gui.class);
        FadLeverandør fadLeverandør = new FadLeverandør("Gølle", "Finland");

        Mark mark = new Mark("Præstmark", true);

        Korn korn = new Korn(LocalDate.now(), "Guldkorn", mark);

        Maltbatch maltbatch = new Maltbatch("12345", 300.0, korn);

        Destillering destillering = new Destillering(2, LocalDate.MIN, LocalDate.now(), 500.0, 65.0, maltbatch);

        Fad fad = new Fad(90, "eg", fadLeverandør, "Sne", 30, 1);

        Påfyldning påfyldning = new Påfyldning(LocalDate.of(2012,12,12), fad);

        Mængde mængde = new Mængde(80, destillering);
        påfyldning.tilføjMængde(mængde);

        Aftapning aftapning = new Aftapning(500.0, 58.0);
        aftapning.setAftapningsDato(LocalDate.of(2022, 2, 2));
        aftapning.setPåfyldning(påfyldning);
        List<Aftapning> aftapninger = new ArrayList<>();
        aftapninger.add(aftapning);

        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(10.0, 0);
        whiskyProdukt.setAftapninger(aftapninger);
        whiskyProdukt.antalFlasker(0.7);
        whiskyProdukt.setAarLagret(aftapning, påfyldning);
        whiskyProdukt.setWhiskytype(aftapning, whiskyProdukt);
        whiskyProdukt.setNavn("Highland Spirit");

        String historie = whiskyProdukt.lavHistorie();
        System.out.println("\n" + historie);
    }

}

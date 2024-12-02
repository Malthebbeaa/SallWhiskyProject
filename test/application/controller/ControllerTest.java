package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private StorageInterface storage = new Storage();
    private Controller controller = new Controller(storage);
    private Mark mark;
    private Maltbatch maltbatch;
    private Destillering destillering;


    @BeforeEach
    void setUp() {
        mark = new Mark("Larsmark", true);
    }

    @Test
    void opretMaltbatch() {
        //Arrange
        String batchNummer = "FM2024";
        int mængde = 1000;
        Korn korn = new Korn(LocalDate.now(), "Evergreen", mark);

        //Act
        maltbatch = controller.opretMaltbatch(batchNummer,mængde, korn);
        boolean forventet = true;
        boolean aktuelt = controller.getStorage().getMaltbatches().contains(maltbatch);

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void opretMark() {
        //Arrange
        String markNavn = "Nybogaard";
        boolean økologisk = true;

        //Act
        Mark mark1 = controller.opretMark(markNavn, økologisk);
        boolean forvetnet = true;
        boolean aktuelt = controller.getStorage().getMarker().contains(mark1);

        //Assert
        assertEquals(forvetnet, aktuelt);
    }

    @Test
    void opretKorn() {
        //Arrange
        LocalDate høstdag = LocalDate.now();
        String sort = "Evergreen";
        Mark mark1 = mark;

        //Act
        Korn korn = controller.opretKorn(høstdag, sort, mark1);
        boolean forventet = true;
        boolean aktuelt = controller.getStorage().getKorn().contains(korn);

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void opretDestillering() {
        //Arrange
        int antalDestilleringer = 2;
        LocalDate startDato = LocalDate.of(2020, 10,20);
        LocalDate slutDato = LocalDate.now();
        double væskeMængde = 900;
        double alkoholProcent = 65;
        Maltbatch maltbatch1 = maltbatch;


        //Act
        destillering = controller.opretDestillering(antalDestilleringer, startDato,slutDato,væskeMængde, alkoholProcent, maltbatch1);
        boolean forventet = true;
        boolean aktuelt = controller.getStorage().getDestilleringer().contains(destillering);

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void tilføjKommentarTilDestillering() {
        //Arrange
        String kommentar = "Lavet af praktikant";
        int antalDestilleringer = 2;
        LocalDate startDato = LocalDate.of(2020, 10,20);
        LocalDate slutDato = LocalDate.now();
        double væskeMængde = 900;
        double alkoholProcent = 65;
        Maltbatch maltbatch1 = maltbatch;

        destillering = controller.opretDestillering(antalDestilleringer, startDato,slutDato,væskeMængde, alkoholProcent, maltbatch1);

        //Act
        controller.tilføjKommentarTilDestillering(kommentar, destillering);

        //Assert
        boolean forventet = true;
        boolean aktuelt = destillering.getKommentar().toString().equals(kommentar);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void opretFad() {
        //Arrange
        int størrelse = 100;
        String materiale = "Eg";
        FadLeverandør fadLeverandør = new FadLeverandør("Mikkel", "Spanien");
        String tidligereIndhold = "Sherry";
        int alder = 2;
        int antalGangeBrugt = 0;

        //Act
        Fad fad = controller.opretFad(størrelse,materiale,fadLeverandør,tidligereIndhold,alder,antalGangeBrugt);
        boolean forventet = true;
        boolean aktuelt = controller.getStorage().getFade().contains(fad);

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void opretLager() {
        //Arrange
        String navn = "Sønderhøj Lager";
        String vejnavn = "Sønderhøj 20";
        String postnummer = "8260";
        String by = "Viby";

        //Act
        Lager lager = controller.opretLager(navn,vejnavn,postnummer,by);
        boolean forventet = true;
        boolean aktuelt = controller.getStorage().getLager().contains(lager);

        //Assert
        assertEquals(forventet,aktuelt);
    }


}
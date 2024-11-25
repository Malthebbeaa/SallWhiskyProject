package application.controller;

import application.model.Destillering;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;
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
    }

    @Test
    void opretKorn() {
    }

    @Test
    void opretRygemateriale() {
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
        boolean aktuelt = destillering.getKommentar().equals("Lavet af praktikant");

        assertEquals(forventet, aktuelt);
    }
}
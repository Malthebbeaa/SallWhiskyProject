package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PåfyldningTest {
    private StorageInterface storage = new Storage();
    private Controller controller = new Controller(storage);
    private Fad fad;
    private Destillering destillering;
    private Maltbatch maltbatch;

    @BeforeEach
    void setUp() {
        int størrelse = 700;
        String materiale = "Eg";
        FadLeverandør fadLeverandør = new FadLeverandør("Mikkel", "Spanien");
        String tidligereIndhold = "Sherry";
        int alder = 2;
        int antalGangeBrugt = 0;
        fad = controller.opretFad(størrelse,materiale,fadLeverandør,tidligereIndhold,alder,antalGangeBrugt);

        String kommentar = "Lavet af praktikant";
        int antalDestilleringer = 2;
        LocalDate startDato = LocalDate.of(2020, 10,20);
        LocalDate slutDato = LocalDate.now();
        double væskeMængde = 900;
        double alkoholProcent = 65;

        Mark mark = new Mark("Larsmark", true);
        String batchNummer = "FM2024";
        int mængde = 1000;
        Korn korn = new Korn(LocalDate.now(), "Evergreen", mark);

        maltbatch = controller.opretMaltbatch(batchNummer,mængde, korn);
        destillering = controller.opretDestillering(antalDestilleringer, startDato,slutDato,væskeMængde, alkoholProcent, maltbatch);
    }

    @Test
    void test_korrektMængde() {
        //Arrange
        LocalDate påfyldningsDato = LocalDate.now();
        double mængde = 600;

        //Act
        Påfyldning påfyldning = controller.påfyldningFad(fad, destillering, påfyldningsDato,mængde);

        //Assert
        assertEquals(600, fad.getMængdeVæskeIFad());
    }

    @Test
    void test_fadFyldt() {
        LocalDate påfyldningsDato = LocalDate.now();
        double mængde = 600;

        //Act
        Påfyldning påfyldning = controller.påfyldningFad(fad, destillering, påfyldningsDato,mængde);

        //Assert
        assertEquals(true, fad.isFyldt());
    }
}

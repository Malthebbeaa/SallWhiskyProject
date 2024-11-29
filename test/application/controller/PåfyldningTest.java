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
    private Destillering destillering, destillering2;
    private Maltbatch maltbatch, maltbatch2;

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
        String batchNummer2 = "FM20242";
        int mængde = 1000;
        Korn korn = new Korn(LocalDate.now(), "Evergreen", mark);
        Korn korn1 = new Korn(LocalDate.of(2024, 11,27), "Irina", mark);

        maltbatch = controller.opretMaltbatch(batchNummer,mængde, korn);
        maltbatch2 = controller.opretMaltbatch(batchNummer2, 900, korn1);
        destillering = controller.opretDestillering(antalDestilleringer, startDato,slutDato,væskeMængde, alkoholProcent, maltbatch);
        destillering2 = controller.opretDestillering(antalDestilleringer, startDato.minusDays(1), slutDato.plusDays(1), væskeMængde, alkoholProcent, maltbatch2);
    }

    @Test
    void test_PåfyldningAfFad(){
        //Arrange
        Mængde mængde = destillering.afgivVæske(200);
        Mængde mængde1 = destillering2.afgivVæske(150);

        Påfyldning påfyldning = new Påfyldning(LocalDate.now(), fad);
        påfyldning.tilføjMængde(mængde);
        påfyldning.tilføjMængde(mængde1);

        fad.tilføjPåfyldning(påfyldning);

        assertEquals(350,påfyldning.getLiterPåfyldt());
        assertEquals(350, fad.getMængdeFyldtPåFad());
        assertEquals(700, destillering.getVæskeMængde());
        assertEquals(750, destillering2.getVæskeMængde());

        Mængde mængde2 = destillering.afgivVæske(100);


        assertEquals(true, påfyldning.getFad().equals(fad));
        assertEquals(true, fad.getPåfyldninger().contains(påfyldning));
        assertEquals(false, påfyldning.getMængderPåfyldt().contains(mængde2));

        Påfyldning påfyldning1 = new Påfyldning(LocalDate.now(), fad);
        påfyldning1.tilføjMængde(mængde2);
        fad.tilføjPåfyldning(påfyldning1);

        assertEquals(450, fad.getMængdeFyldtPåFad());
    }

}

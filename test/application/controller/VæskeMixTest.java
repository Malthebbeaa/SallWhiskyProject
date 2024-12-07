package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VæskeMixTest {
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
        Væske væske = controller.opretVæske(200, destillering);
        Væske væske1 = controller.opretVæske(150, destillering2);

        fad.tilføjVæske(væske);
        fad.tilføjVæske(væske1);
        PåfyldningsComponent væskeMix = fad.getVæskeMix();

        assertEquals(350, væskeMix.getLiterPåfyldt());
        assertEquals(350, fad.getMængdeFyldtPåFad());
        assertEquals(700, destillering.getVæskeMængde());
        assertEquals(750, destillering2.getVæskeMængde());
    }

    @Test
    void lavAftapninger() {

    }
}

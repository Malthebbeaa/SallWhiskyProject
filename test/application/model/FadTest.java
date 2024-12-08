package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.FormView;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad, fad1, fad2;
    private VæskeMix væskeMix1, væskeMix2;
    private Væske væske1, væske2, væske3;
    private Destillering destillering;
    @BeforeEach
    void setUp() {

        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        fad = new Fad(65, "Eg", fadLeverandør,"Sherry", 10, 1);
        fad1 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        fad2 = new Fad(150, "Eg", fadLeverandør, "Sherry", 3, 1);
        væskeMix1 = new VæskeMix(LocalDate.now(), fad);
        væskeMix2 = new VæskeMix(LocalDate.now(), fad);

        Maltbatch maltbatch = new Maltbatch("FM2323", 1000, new Korn(LocalDate.of(2024, 11,21), "Evergreen", new Mark("Lars Mark", true)));
        destillering = new Destillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 950,68,maltbatch);
        væske1 = new Væske(60, destillering);
        væske2 = new Væske(70, destillering);
        væske3 = new Væske(5, destillering);
    }

    @Test
    void påFyldningOvergårGrænseMindreMængde() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = fad.overskriderFadKapacitet(væske1.getVæskeMængde());

        assertEquals(forventet,aktuelt);
    }

    @Test
    void påFyldningOvergårGrænseForStorMængde() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = fad.overskriderFadKapacitet(væske2.getVæskeMængde());

        assertEquals(forventet,aktuelt);
    }
    @Test
    public void testFlytDelAfVæskeMixTilFad() {
        //ACT
        fad1.tilføjVæske(væske1);
        fad1.tilføjVæske(væske2);
        fad1.opretVæskemix(LocalDate.now(), væske3);

        PåfyldningsComponent valgtMix = fad1.getVæskeMix();
        fad1.flytDelAfVæskeMixTilFadHjælper(fad2, valgtMix, 50);
        double forventetFad2 = 50.0;
        double aktueltFad2 = fad2.getMængdeFyldtPåFad();
        double forventetFad1 = 85.0;
        double aktueltFad1 = fad1.getMængdeFyldtPåFad();
        //ASSERT
        assertEquals(forventetFad2, aktueltFad2);
        assertEquals(forventetFad1, aktueltFad1);
    }

    @Test
    void getMængdeFyldtPåFad() {
        væskeMix1.add(væske1);
        væskeMix2.add(væske3);
        fad.tilføjVæske(væskeMix1);
        fad.tilføjVæske(væskeMix2);

        double forventet = 65;
        double aktuelt = fad.getMængdeFyldtPåFad();

        assertEquals(forventet, aktuelt);
    }
}
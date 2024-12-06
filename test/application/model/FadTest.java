package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private VæskeMix væskeMix1, væskeMix2;
    private Væske væske1, væske2, væske3;
    private Destillering destillering;
    @BeforeEach
    void setUp() {
        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        fad = new Fad(65, "Eg", fadLeverandør,"Sherry", 10, 1);
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
        boolean aktuelt = fad.påFyldningOvergårGrænse(væske1.getMængde());

        assertEquals(forventet,aktuelt);
    }

    @Test
    void påFyldningOvergårGrænseForStorMængde() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = fad.påFyldningOvergårGrænse(væske2.getMængde());

        assertEquals(forventet,aktuelt);
    }
    @Test
    void tilføjPåfyldning() {
        //Arrange Act
        væskeMix1.tilføjMængde(væske1);
        væskeMix2.tilføjMængde(væske3);
        fad.tilføjPåfyldning(væskeMix1);
        fad.tilføjPåfyldning(væskeMix2);

        boolean forventet = true;
        boolean aktuelt = fad.getPåfyldninger().containsAll(List.of(væskeMix1, væskeMix2));

        assertEquals(forventet, aktuelt);
    }

    @Test
    void getMængdeFyldtPåFad() {
        væskeMix1.tilføjMængde(væske1);
        væskeMix2.tilføjMængde(væske3);
        fad.tilføjPåfyldning(væskeMix1);
        fad.tilføjPåfyldning(væskeMix2);

        double forventet = 65;
        double aktuelt = fad.getMængdeFyldtPåFad();

        assertEquals(forventet, aktuelt);
    }
}
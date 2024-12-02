package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestilleringTest {
    private Fad fad;
    private Destillering destillering;

    @BeforeEach
    void setUp() {
        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        Maltbatch maltbatch = new Maltbatch("FM2323", 1000, new Korn(LocalDate.of(2024, 11,21), "Evergreen", new Mark("Lars Mark", true)));
        fad = new Fad(700, "Eg", fadLeverandør,"Sherry", 10, 1);
        destillering = new Destillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 950,68,maltbatch);
    }


    @Test
    void test_afgivVæske() {
        //Arrange & Act
        double mængde = 600;

        destillering.afgivVæske(mængde);
        boolean forventet = true;
        boolean aktuelt = destillering.getVæskeMængde() == 350;

        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_forbindelse() {
        //Arrange & Act
        double mængdeAfgiv = 600;

        Mængde mængde = destillering.afgivVæske(mængdeAfgiv);
        boolean forventet = true;
        boolean aktuelt = destillering.getMængderGivet().contains(mængde);

        assertEquals(forventet, aktuelt);
    }
}
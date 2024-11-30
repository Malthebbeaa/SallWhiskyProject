package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private Påfyldning påfyldning1, påfyldning2;
    private Mængde mængde1, mængde2, mængde3;
    private Destillering destillering;
    @BeforeEach
    void setUp() {
        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        fad = new Fad(65, "Eg", fadLeverandør,"Sherry", 10, 1);
        påfyldning1 = new Påfyldning(LocalDate.now(), fad);
        påfyldning2 = new Påfyldning(LocalDate.now(), fad);

        Maltbatch maltbatch = new Maltbatch("FM2323", 1000, new Korn(LocalDate.of(2024, 11,21), "Evergreen", new Mark("Lars Mark", true)));
        destillering = new Destillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 950,68,maltbatch);
        mængde1 = new Mængde(60, destillering);
        mængde2 = new Mængde(70, destillering);
        mængde3 = new Mængde(5, destillering);
    }

    @Test
    void påFyldningOvergårGrænseMindreMængde() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = fad.påFyldningOvergårGrænse(mængde1.getMængde());

        assertEquals(forventet,aktuelt);
    }

    @Test
    void påFyldningOvergårGrænseForStorMængde() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = fad.påFyldningOvergårGrænse(mængde2.getMængde());

        assertEquals(forventet,aktuelt);
    }
    @Test
    void tilføjPåfyldning() {
        //Arrange Act
        påfyldning1.tilføjMængde(mængde1);
        påfyldning2.tilføjMængde(mængde3);
        fad.tilføjPåfyldning(påfyldning1);
        fad.tilføjPåfyldning(påfyldning2);

        boolean forventet = true;
        boolean aktuelt = fad.getPåfyldninger().containsAll(List.of(påfyldning1,påfyldning2));

        assertEquals(forventet, aktuelt);
    }

    @Test
    void getMængdeFyldtPåFad() {
        påfyldning1.tilføjMængde(mængde1);
        påfyldning2.tilføjMængde(mængde3);
        fad.tilføjPåfyldning(påfyldning1);
        fad.tilføjPåfyldning(påfyldning2);

        double forventet = 65;
        double aktuelt = fad.getMængdeFyldtPåFad();

        assertEquals(forventet, aktuelt);
    }
}
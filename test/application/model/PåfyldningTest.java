package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PåfyldningTest {
    private Fad fad;
    private Destillering destillering;
    private Påfyldning påfyldning;

    @BeforeEach
    void setUp() {
        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        Maltbatch maltbatch = new Maltbatch("FM2323", 1000, new Korn(LocalDate.of(2024, 11,21), "Evergreen", new Mark("Lars Mark", true)));
        fad = new Fad(700, "Eg", fadLeverandør,"Sherry", 10, 1);
        destillering = new Destillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 950,68,maltbatch);
    }

    @Test
    void fyldPåFad() {
        //Arrange
        påfyldning = new Påfyldning(LocalDate.now(), 600);

        //Act
        påfyldning.fyldPåFad(fad);
        boolean forventet = true;
        boolean aktuelt = fad.isFyldt();

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_forbindelse(){
        //Arrange
        påfyldning = new Påfyldning(LocalDate.now(), 600);

        //Act
        påfyldning.fyldPåFad(fad);
        boolean forventet = true;
        boolean aktuelt = påfyldning.getFade().contains(fad);

        //Assert
        assertEquals(forventet, aktuelt);
    }
}
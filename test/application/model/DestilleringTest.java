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
    void lavPåfyldning() {
        //Arrange
        LocalDate påfyldningsdato = LocalDate.now();

        //Act
        destillering.lavPåfyldning(fad, påfyldningsdato, 600);
        double forventet = 600;
        double aktuelt = fad.getMængdeVæskeIFad();

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_isFyldEfterPåfyldning(){
        //Arrange
        LocalDate påfyldningsdato = LocalDate.now();

        //Act
        destillering.lavPåfyldning(fad,påfyldningsdato,700);
        boolean forventet = true;
        boolean aktuelt = fad.isFyldt();

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void testExceptionsOverMuligMængde(){
        //Arrange
        LocalDate påfyldningsdato = LocalDate.now();

        //Act
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            destillering.lavPåfyldning(fad,påfyldningsdato,701);
        });

        String message = "Ikke tilstrækkeligt plads i fadet";
        //Assert
        assertEquals(message, runtimeException.getMessage());
    }

    @Test
    void testExceptionsOverNegativMængde(){
        //Arrange
        LocalDate påfyldningsdato = LocalDate.now();

        //Act
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            destillering.lavPåfyldning(fad,påfyldningsdato,-1);
        });

        String message = "Mængden skal være positiv";
        //Assert
        assertEquals(message, runtimeException.getMessage());
    }

    @Test
    void test_Forbindelse(){
        //Arrange
        LocalDate påfyldningsdato = LocalDate.now();

        //Act
        Påfyldning påfyldning = destillering.lavPåfyldning(fad, påfyldningsdato, 600);
        boolean forventet = true;
        boolean aktuelt = destillering.getPåfyldninger().contains(påfyldning);

        //Assert
        assertEquals(forventet, aktuelt);
    }
}
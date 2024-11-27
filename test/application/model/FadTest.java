package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    @BeforeEach
    void setUp() {
        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        fad = new Fad(700, "Eg", fadLeverandør,"Sherry", 10, 1);
    }

    @Test
    void påfyldVæskeFalse() {
        //Arrange
        double mængde = 800;

        //Act & Assert
        assertThrows(RuntimeException.class, () -> {
            fad.påfyldVæske(mængde);
        });
    }
    @Test
    void testExceptionsOverMuligMængde(){
        //Act
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            fad.påfyldVæske(701);
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
            fad.påfyldVæske(-1);
        });

        String message = "Mængden skal være positiv";
        //Assert
        assertEquals(message, runtimeException.getMessage());
    }


    @Test
    void påfyldVæskeTrue() {
        //Arrange
        double mængde = 600;

        //Act
        fad.påfyldVæske(mængde);
        double forventet = 600;
        double aktuelt = fad.getMængdeVæskeIFad();

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_setFyld_isFyldt(){
        //Act
        fad.setFyldt(true);
        boolean forventet = true;
        boolean aktuelt = fad.isFyldt();


        assertEquals(forventet,aktuelt);
    }
}
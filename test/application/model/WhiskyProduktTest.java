package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WhiskyProduktTest {
    private WhiskyProdukt whiskyProdukt;

    @BeforeEach
    void setUp() {
        Aftapning aftapning = new Aftapning(30, 70);
        Aftapning aftapning1 = new Aftapning(20, 68);

        whiskyProdukt = new WhiskyProdukt("Test", LocalDate.now());
        whiskyProdukt.tilføjAftapning(aftapning);
        whiskyProdukt.tilføjAftapning(aftapning1);
    }

    @Test
    void tilføjVand() {
        //Arrange
        double vand = 10;

        //Act
        whiskyProdukt.tilføjVand(vand);
        double forventet = 60;
        double aktuelt = whiskyProdukt.getTotalWhiskyMængde();

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void TC1_beregnAlkoholProcent0LiterTilføjet() {
        //Arrange
        double vand = 0;

        //Act
        whiskyProdukt.tilføjVand(0);
        double aktuelt = whiskyProdukt.beregnAlkoholProcent();
        double forventet = ((30.0 * 70.0) + (20.0 * 68.0)) / (30.0 + 20.0);

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void TC2_beregnAlkoholProcent1LiterTilføjet() {
        //Arrange
        double vand = 1;

        //Act
        whiskyProdukt.tilføjVand(vand);
        double aktuelt = whiskyProdukt.beregnAlkoholProcent();
        double alkoholMængde = 50.0 * (((30.0 * 70.0) + (20.0 * 68.0)) / (30.0 + 20.0)/ 100);
        double samletVolumen = 50.0 + 1.0;
        double forventet = (alkoholMængde / samletVolumen) * 100;

        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void TC3_beregnAlkoholProcent10LiterTilføjet() {
        //Arrange
        double vand = 10;

        //Act
        whiskyProdukt.tilføjVand(vand);

        double aktuelt = whiskyProdukt.beregnAlkoholProcent();
        double alkoholMængde = 50.0 * (((30.0 * 70.0) + (20.0 * 68.0)) / (30.0 + 20.0)/ 100);
        double samletVolumen = 50.0 + 10.0;
        double forventet = (alkoholMængde / samletVolumen) * 100;


        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void TC4_beregnAlkoholProcent50LiterTilføjet() {
        //Arrange
        double vand = 50;

        //Act
        whiskyProdukt.tilføjVand(vand);

        double aktuelt = whiskyProdukt.beregnAlkoholProcent();
        double alkoholMængde = 50.0 * (((30.0 * 70.0) + (20.0 * 68.0)) / (30.0 + 20.0)/ 100);
        double samletVolumen = 50.0 + 50.0;
        double forventet = (alkoholMængde / samletVolumen) * 100;


        //Assert
        assertEquals(forventet, aktuelt);

    }

    @Test
    void TC5_beregnAlkoholProcent55LiterTilføjet() {
        //Arrange
        double vand = 55;

        //Act
        whiskyProdukt.tilføjVand(vand);

        double aktuelt = whiskyProdukt.beregnAlkoholProcent();
        double alkoholMængde = 50.0 * (((30.0 * 70.0) + (20.0 * 68.0)) / (30.0 + 20.0)/ 100);
        double samletVolumen = 50.0 + 55.0;
        double forventet = (alkoholMængde / samletVolumen) * 100;

        //Assert
        assertEquals(forventet, aktuelt);

    }

    @Test
    void antalFlasker() {
        //Arrange
        double flaskeStørrelse = 0.7;

        //Act
        int forventetAntalFlasker = (int) (whiskyProdukt.getTotalWhiskyMængde() / flaskeStørrelse);
        int aktuelt = whiskyProdukt.antalFlasker(flaskeStørrelse);

        //Assert
        assertEquals(forventetAntalFlasker, aktuelt);
    }
}
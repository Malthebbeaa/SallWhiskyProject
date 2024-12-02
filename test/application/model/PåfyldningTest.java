package application.model;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PåfyldningTest {
    private Fad fad;
    private Destillering destillering;
    private Påfyldning påfyldning;
    private Mængde mængde1, mængde2;

    @BeforeEach
    void setUp() {
        FadLeverandør fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        Maltbatch maltbatch = new Maltbatch("FM2323", 1000, new Korn(LocalDate.of(2024, 11,21), "Evergreen", new Mark("Lars Mark", true)));
        fad = new Fad(60, "Eg", fadLeverandør,"Sherry", 10, 1);
        destillering = new Destillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 950,68,maltbatch);
        mængde1 = new Mængde(60, destillering);
        mængde2 = new Mængde(70, destillering);
        påfyldning = new Påfyldning(LocalDate.now(), fad);
    }


    @Test
    void mængdenOverskriderFadKapacitetMængdeInputTrue() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mængde2);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetMængdeInputFalse() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mængde1);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetDoubleInputTrue() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mængde2.getMængde());

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetDoubleInputFalse() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mængde1.getMængde());

        assertEquals(forventet, aktuelt);
    }
    @Test
    void tilføjMængde() {
        //Arrange Act
        påfyldning.tilføjMængde(mængde1);

        boolean forventet = true;
        boolean aktuelt = påfyldning.getMængderPåfyldt().contains(mængde1);

        assertEquals(forventet,aktuelt);
    }

    @Test
    void fjernMængde() {
        //Arrange Act
        påfyldning.tilføjMængde(mængde1);
        boolean forventet = true;
        boolean aktuelt = påfyldning.getMængderPåfyldt().contains(mængde1);

        assertEquals(forventet,aktuelt);

        påfyldning.fjernMængde(mængde1);
        boolean forventetEfter = false;
        boolean aktueltEfter = påfyldning.getMængderPåfyldt().contains(mængde1);

        assertEquals(forventetEfter,aktueltEfter);
    }

    @Test
    void setFad() {
        //Arrange Act
        påfyldning.setFad(fad);

        boolean forventet = true;
        boolean aktuelt = påfyldning.getFad().equals(fad);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void getLiterPåfyldt() {
        //Arrange act
        påfyldning.tilføjMængde(mængde1);
        double forventet = 60;
        double aktuelt = påfyldning.getLiterPåfyldt();

        assertEquals(forventet,aktuelt);
    }
}
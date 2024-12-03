package application.model;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class PåfyldningTest {
    private Fad mockFad;
    private Destillering mockDestillering;
    private Påfyldning påfyldning;
    private Mængde mockMængde1, mockMængde2;
    private Aftapning mockAftapning;

    @BeforeEach
    void setUp() {
        FadLeverandør mockFadLeverandør = mock(FadLeverandør.class);
        Maltbatch mockBatch = mock(Maltbatch.class);
        mockDestillering = mock(Destillering.class);
        when(mockDestillering.getVæskeMængde()).thenReturn(950.0);
        mockFad = mock(Fad.class);
        when(mockFad.getStørrelse()).thenReturn(60);
        mockMængde1 = mock(Mængde.class);
        when(mockMængde1.getMængde()).thenReturn(59.9);
        mockMængde2 = mock(Mængde.class);
        when(mockMængde2.getMængde()).thenReturn(60.1);
        påfyldning = new Påfyldning(LocalDate.of(2023,01,01), mockFad);
        mockAftapning = mock(Aftapning.class);
        when(mockAftapning.getLiterAftappet()).thenReturn(60.1);
    }

    @Test
    void aftapVæskeKasterException() {
        assertThrows(RuntimeException.class,() -> påfyldning.aftapVæske(mockAftapning));
    }

    @Test
    void aftapVæskeGåriMinusInputFalse() {
        //Arrange
        Aftapning mockAftapning = mock(Aftapning.class);
        Mængde mockMængde = mock(Mængde.class);
        when(mockMængde.getMængde()).thenReturn(60.0);
        påfyldning.tilføjMængde(mockMængde);
        when(mockAftapning.getLiterAftappet()).thenReturn(59.0);
        //Act
        påfyldning.aftapVæske(mockAftapning);
        //Assert
        double forventet = 1.0;
        double aktuelt = påfyldning.getLiterPåfyldt();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void aftapningGårIMinusInputTrue(){
        //Arrange & Act
        boolean forventet = true;
        boolean aktuelt = påfyldning.aftapningGårIMinus(951);
    }

    @Test
    void aftapningGårIMinusInputFalse(){
        //Arrange & Act
        boolean forventet = false;
        boolean aktuelt = påfyldning.aftapningGårIMinus(950);
    }


    @Test
    void mængdenOverskriderFadKapacitetMængdeInputTrue() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mockMængde2);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetMængdeInputFalse() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mockMængde1);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetDoubleInputTrue() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mockMængde2.getMængde());

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetDoubleInputFalse() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = påfyldning.mængdenOverskriderFadKapacitet(mockMængde1.getMængde());

        assertEquals(forventet, aktuelt);
    }
    @Test
    void tilføjMængde() {
        //Arrange Act
        påfyldning.tilføjMængde(mockMængde1);

        boolean forventet = true;
        boolean aktuelt = påfyldning.getMængderPåfyldt().contains(mockMængde1);

        assertEquals(forventet,aktuelt);
    }

    @Test
    void tilføjMængdeKasterException() {
        //Arrange Act
        assertThrows(RuntimeException.class,()->påfyldning.tilføjMængde(mockMængde2));
    }

    @Test
    void fjernMængde() {
        //Arrange Act
        påfyldning.tilføjMængde(mockMængde1);
        boolean forventet = true;
        boolean aktuelt = påfyldning.getMængderPåfyldt().contains(mockMængde1);

        assertEquals(forventet,aktuelt);

        påfyldning.fjernMængde(mockMængde1);
        boolean forventetEfter = false;
        boolean aktueltEfter = påfyldning.getMængderPåfyldt().contains(mockMængde1);

        assertEquals(forventetEfter,aktueltEfter);
    }

    @Test
    void setFad() {
        //Arrange Act
        påfyldning.setFad(mockFad);

        boolean forventet = true;
        boolean aktuelt = påfyldning.getFad().equals(mockFad);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void getLiterPåfyldt() {
        //Arrange act
        påfyldning.tilføjMængde(mockMængde1);
        double forventet = 59.9;
        double aktuelt = påfyldning.getLiterPåfyldt();

        assertEquals(forventet,aktuelt);
    }

    @Test
    void tilføjAftapning(){
        //Arrange & Act
        påfyldning.tilføjAftapning(mockAftapning);
        boolean forventet = true;
        boolean aktuelt = påfyldning.getAftapninger().contains(mockAftapning);

        assertEquals(forventet,aktuelt);
    }

    @Test
    void antalÅrPåFad() {
        //Arrange
        LocalDate datoNu = LocalDate.of(2024,01,01);
        //Act
        Period period = påfyldning.antalÅrPåFad(datoNu);
        int aktueltÅr = period.getYears();
        int aktueltMåned = period.getMonths();
        int aktueltDage = period.getDays();

        int forventetÅr = 1;
        int forventetMåneder = 0;
        int forventetDage = 0;
        //Assert

        assertEquals(forventetÅr,aktueltÅr);
        assertEquals(forventetMåneder,aktueltMåned);
        assertEquals(forventetDage,aktueltDage);
    }

    @Test
    void klarTilAftapning() {
    }
}
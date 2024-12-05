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
        when(mockDestillering.getVæskeMængde()).thenReturn(90.0);
        mockFad = mock(Fad.class);
        when(mockFad.getStørrelse()).thenReturn(60);
        mockMængde1 = mock(Mængde.class);
        when(mockMængde1.getMængde()).thenReturn(60.0);
        mockMængde2 = mock(Mængde.class);
        when(mockMængde2.getMængde()).thenReturn(60.1);
        påfyldning = new Påfyldning(LocalDate.of(2023,01,01), mockFad);
        mockAftapning = mock(Aftapning.class);
        when(mockAftapning.getLiterAftappet()).thenReturn(60.1);
    }

    @Test
    void aftapVæskeKasterException_grænseværdi() {
        //Assert
        assertThrows(RuntimeException.class,() -> påfyldning.aftapVæske(mockAftapning));
    }

    @Test
    void aftapVæskeGåriMinusInputFalse_grænseværdi() {
        //Arrange
        Aftapning mockAftapning = mock(Aftapning.class);
        when(mockAftapning.getLiterAftappet()).thenReturn(60.0);
        Mængde mockMængde = mock(Mængde.class);
        when(mockMængde.getMængde()).thenReturn(60.0);
        //Act
        påfyldning.tilføjMængde(mockMængde);
        påfyldning.aftapVæske(mockAftapning);
        //Assert
        double forventet = 0;
        double aktuelt = påfyldning.getLiterPåfyldt();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void aftapningGårIMinusInputTrue(){
        //Arrange & Act
        Mængde mockMængde3 = mock(Mængde.class);
        when(mockMængde3.getMængde()).thenReturn(60.0);
        påfyldning.tilføjMængde(mockMængde3);
        boolean forventet = true;
        boolean aktuelt = påfyldning.aftapningGårIMinus(60.1);
        assertEquals(forventet, aktuelt);
    }

    @Test
    void aftapningGårIMinusInputFalse(){
        //Arrange & Act
        Mængde mockMængde4 = mock(Mængde.class);
        when(mockMængde4.getMængde()).thenReturn(60.0);
        påfyldning.tilføjMængde(mockMængde4);
        boolean forventet = false;
        boolean aktuelt = påfyldning.aftapningGårIMinus(60.0);
        assertEquals(forventet, aktuelt);
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
        //Arrange
        Mængde mockMængde6 = mock(Mængde.class);
        when(mockMængde6.getMængde()).thenReturn(59.9);
        Mængde mockMængde5 = mock(Mængde.class);
        when(mockMængde5.getMængde()).thenReturn(0.1);
        //Act
        påfyldning.tilføjMængde(mockMængde1);
        påfyldning.tilføjMængde(mockMængde5);
        double forventetLiter = 60;
        double aktuelLiter = påfyldning.getLiterPåfyldt();
        //Assert
        assertEquals(forventetLiter,aktuelLiter);
    }

    @Test
    void tilføjMængdeKasterException() {
        //Arrange Act
        assertThrows(RuntimeException.class,()->påfyldning.tilføjMængde(mockMængde2));
    }


    @Test
    void setFad() {
        //Arrange
        påfyldning.setFad(mockFad);
        boolean forventet = true;
        boolean aktuelt = påfyldning.getFad().equals(mockFad);
        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void getLiterPåfyldt() {
        //Arrange act
        påfyldning.tilføjMængde(mockMængde1);
        double forventet = 59.9;
        double aktuelt = påfyldning.getLiterPåfyldt();
        //Assert
        assertEquals(forventet,aktuelt);
    }

    @Test
    void tilføjAftapning(){
        //Arrange & Act
        påfyldning.tilføjAftapning(mockAftapning);
        boolean forventet = true;
        boolean aktuelt = påfyldning.getAftapninger().contains(mockAftapning);
        //Assert
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
    void klarTilAftapningGrænseFalsk() {
        //Arrange
        Påfyldning påfyldningFør = new Påfyldning(LocalDate.of(2021,12,31), mockFad);
        //Act
        boolean forventet = false;
        boolean aktuelt = påfyldningFør.klarTilAftapning(LocalDate.of(2024,12,30));
        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void klarTilAftapningGrænseSand() {
        //Arrange
        Påfyldning påfyldningFør = new Påfyldning(LocalDate.of(2021,12,31), mockFad);
        //Act
        boolean forventet = true;
        boolean aktuelt = påfyldningFør.klarTilAftapning(LocalDate.of(2024,12,31));
        //Assert
        assertEquals(forventet, aktuelt);
    }
}
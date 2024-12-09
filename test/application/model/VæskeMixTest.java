package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class VæskeMixTest {
    private Fad mockFad;
    private Destillering mockDestillering;
    private VæskeMix væskeMix;
    private Væske mockVæske1, mockVæske2;
    private Aftapning mockAftapning;

    @BeforeEach
    void setUp() {
        FadLeverandør mockFadLeverandør = mock(FadLeverandør.class);
        Maltbatch mockBatch = mock(Maltbatch.class);
        mockDestillering = mock(Destillering.class);
        when(mockDestillering.getVæskeMængde()).thenReturn(90.0);
        mockFad = mock(Fad.class);
        when(mockFad.getStørrelse()).thenReturn(60);
        mockVæske1 = mock(Væske.class);
        when(mockVæske1.getVæskeMængde()).thenReturn(60.0);
        mockVæske2 = mock(Væske.class);
        when(mockVæske2.getVæskeMængde()).thenReturn(60.1);
        væskeMix = new VæskeMix(LocalDate.of(2023,01,01), mockFad);
        mockAftapning = mock(Aftapning.class);
        when(mockAftapning.getLiterAftappet()).thenReturn(60.1);
    }


    @Test
    void getVæskeMængde_1_VæskeMix() {
        //Arrange
        VæskeMix væskeMix1 = new VæskeMix(LocalDate.of(2023,01,01), mockFad);
        Væske væske1 = new Væske(mockDestillering, 60);
        //Act
        væskeMix1.add(væske1);
        double aktuelMængde = væskeMix1.getVæskeMængde();
        double forventetMængde = 60;
        //Assert
        assertEquals(forventetMængde,aktuelMængde);
    }

    @Test
    void getVæskeMængde_2_VæskeMix() {
        //Arrange
        VæskeMix væskeMix1 = new VæskeMix(LocalDate.of(2023,01,01), mockFad);
        VæskeMix væskeMix2 = new VæskeMix(LocalDate.of(2024,12,01),mockFad);
        Væske væske1 = new Væske(mockDestillering, 60);
        Væske væske2 = new Væske(mockDestillering, 30);
        Væske væske3 = new Væske(mockDestillering, 15);
        //Act
        væskeMix1.add(væske1);
        væskeMix1.add(væske2);
        væskeMix2.add(væskeMix1);
        væskeMix2.add(væske3);

        double aktuelMængde = væskeMix2.getVæskeMængde();
        double forventetMængde = 105;
        //Assert
        assertEquals(forventetMængde,aktuelMængde);
    }


    @Test
    void setFad() {
        //Arrange
        væskeMix.setFad(mockFad);
        boolean forventet = true;
        boolean aktuelt = væskeMix.getFad().equals(mockFad);
        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void getLiterPåfyldt() {
        //Arrange act
        væskeMix.add(mockVæske1);
        double forventet = 60.0;
        double aktuelt = væskeMix.getLiterPåfyldt();
        //Assert
        assertEquals(forventet,aktuelt);
    }

    @Test
    void tilføjAftapning(){
        //Arrange & Act
        væskeMix.tilføjAftapning(mockAftapning);
        boolean forventet = true;
        boolean aktuelt = væskeMix.getAftapninger().contains(mockAftapning);
        //Assert
        assertEquals(forventet,aktuelt);
    }

    @Test
    void antalÅrPåFad() {
        //Arrange
        LocalDate datoNu = LocalDate.of(2024,01,01);
        //Act
        Period period = væskeMix.antalÅrPåFad(datoNu);
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
        VæskeMix væskeMixFør = new VæskeMix(LocalDate.of(2021,12,31), mockFad);
        //Act
        boolean forventet = false;
        boolean aktuelt = væskeMixFør.klarTilAftapning(LocalDate.of(2024,12,30));
        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void klarTilAftapningGrænseSand() {
        //Arrange
        VæskeMix væskeMixFør = new VæskeMix(LocalDate.of(2021,12,31), mockFad);
        //Act
        boolean forventet = true;
        boolean aktuelt = væskeMixFør.klarTilAftapning(LocalDate.of(2024,12,31));
        //Assert
        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_aftap_(){
        //Arrange
        VæskeMix væskeMix1 = mock(VæskeMix.class);
    }
}
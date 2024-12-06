package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

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
        when(mockVæske1.getMængde()).thenReturn(60.0);
        mockVæske2 = mock(Væske.class);
        when(mockVæske2.getMængde()).thenReturn(60.1);
        væskeMix = new VæskeMix(LocalDate.of(2023,01,01), mockFad);
        mockAftapning = mock(Aftapning.class);
        when(mockAftapning.getLiterAftappet()).thenReturn(60.1);
    }

    @Test
    void aftapVæskeKasterException_grænseværdi() {
        //Assert
        assertThrows(RuntimeException.class,() -> væskeMix.aftapVæske(mockAftapning));
    }

    @Test
    void aftapVæskeGåriMinusInputFalse_grænseværdi() {
        //Arrange
        Aftapning mockAftapning = mock(Aftapning.class);
        when(mockAftapning.getLiterAftappet()).thenReturn(60.0);
        Væske mockVæske = mock(Væske.class);
        when(mockVæske.getMængde()).thenReturn(60.0);
        //Act
        væskeMix.tilføjVæske(mockVæske);
        væskeMix.aftapVæske(mockAftapning);
        //Assert
        double forventet = 0;
        double aktuelt = væskeMix.getLiterPåfyldt();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void aftapningGårIMinusInputTrue(){
        //Arrange & Act
        Væske mockVæske3 = mock(Væske.class);
        when(mockVæske3.getMængde()).thenReturn(60.0);
        væskeMix.tilføjVæske(mockVæske3);
        boolean forventet = true;
        boolean aktuelt = væskeMix.aftapningGårIMinus(60.1);
        assertEquals(forventet, aktuelt);
    }

    @Test
    void aftapningGårIMinusInputFalse(){
        //Arrange & Act
        Væske mockVæske4 = mock(Væske.class);
        when(mockVæske4.getMængde()).thenReturn(60.0);
        væskeMix.tilføjVæske(mockVæske4);
        boolean forventet = false;
        boolean aktuelt = væskeMix.aftapningGårIMinus(60.0);
        assertEquals(forventet, aktuelt);
    }


    @Test
    void mængdenOverskriderFadKapacitetMængdeInputTrue() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = væskeMix.mængdenOverskriderFadKapacitet(mockVæske2);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetMængdeInputFalse() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = væskeMix.mængdenOverskriderFadKapacitet(mockVæske1);

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetDoubleInputTrue() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = væskeMix.mængdenOverskriderFadKapacitet(mockVæske2.getMængde());

        assertEquals(forventet, aktuelt);
    }

    @Test
    void mængdenOverskriderFadKapacitetDoubleInputFalse() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = væskeMix.mængdenOverskriderFadKapacitet(mockVæske1.getMængde());

        assertEquals(forventet, aktuelt);
    }
    @Test
    void tilføjMængde() {
        //Arrange
        Væske mockVæske6 = mock(Væske.class);
        when(mockVæske6.getMængde()).thenReturn(59.9);
        Væske mockVæske5 = mock(Væske.class);
        when(mockVæske5.getMængde()).thenReturn(0.1);
        //Act
        væskeMix.tilføjVæske(mockVæske1);
        væskeMix.tilføjVæske(mockVæske5);
        double forventetLiter = 60;
        double aktuelLiter = væskeMix.getLiterPåfyldt();
        //Assert
        assertEquals(forventetLiter,aktuelLiter);
    }

    @Test
    void tilføjMængdeKasterException() {
        //Arrange Act
        assertThrows(RuntimeException.class,()-> væskeMix.tilføjVæske(mockVæske2));
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
        væskeMix.tilføjVæske(mockVæske1);
        double forventet = 59.9;
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
}
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
    private Fad mockFad, mockFad2;
    private Destillering mockDestillering, destillering;
    private VæskeMix væskeMix, væskeMix2;
    private Væske mockVæske1, mockVæske2, mockVæske3, mockVæske4;
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

        destillering = new Destillering(2, LocalDate.now(), LocalDate.now(), 100, 70, mockBatch);
        mockFad2 = mock(Fad.class);
        when(mockFad2.getStørrelse()).thenReturn(94);
        mockVæske3 = new Væske(48, destillering);
        mockVæske4 = new Væske(44, destillering);
        Væske væske = new Væske(2, destillering);
        væskeMix2 = new VæskeMix(LocalDate.now(), mockFad2);
        væskeMix2.add(mockVæske3);
        væskeMix2.add(mockVæske4);
        væskeMix2.add(væske);

    }


    @Test
    void tilføjMængde() {
        //Arrange
        Væske mockVæske6 = mock(Væske.class);
        when(mockVæske6.getVæskeMængde()).thenReturn(59.9);
        Væske mockVæske5 = mock(Væske.class);
        when(mockVæske5.getVæskeMængde()).thenReturn(0.1);
        //Act
        væskeMix.add(mockVæske6);
        væskeMix.add(mockVæske5);
        double forventetLiter = 60;
        double aktuelLiter = væskeMix.getLiterPåfyldt();
        //Assert
        assertEquals(forventetLiter,aktuelLiter);
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
    void test_aftap_NegativMængde(){
        //Arrange
        double mængde = -2;

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            væskeMix2.aftap(mængde);
        });

        String forventet = "Mængden skal være positiv";
        String aktuelt = exception.getMessage();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_aftap_NedreGrænseVærdiException(){
        //Arrange
        double mængde = 0;

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            væskeMix2.aftap(mængde);
        });

        String forventet = "Mængden skal være positiv";
        String aktuelt = exception.getMessage();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_aftap_AftapningOk(){
        //Arrange
        double mængde = 60;

        //Act
        væskeMix2.aftap(mængde);
        double forventet = 34;
        double aktuelt = væskeMix2.getVæskeMængde();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_aftap_AftapningØvreGrænse(){
        //Arrange
        double mængde = 94;

        //Act
        væskeMix2.aftap(mængde);
        System.out.println(væskeMix2.getVæskeMængde());
        double forventet = 0;
        double aktuelt = væskeMix2.getVæskeMængde();

        assertEquals(forventet, aktuelt);
    }

    @Test
    void test_aftap_OverskriderGrænse(){
        //Arrange
        double mængde = 96;

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            væskeMix2.aftap(mængde);
        });

        String forventet = "Der er ikke nok væske til aftapningen";
        String aktuelt = exception.getMessage();

        assertEquals(forventet, aktuelt);
    }
}
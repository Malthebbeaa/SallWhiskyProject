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
        when(mockVæske.getVæskeMængde()).thenReturn(60.0);
        //Act
        væskeMix.add(mockVæske);
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
        when(mockVæske3.getVæskeMængde()).thenReturn(60.0);
        væskeMix.add(mockVæske3);
        boolean forventet = true;
        boolean aktuelt = væskeMix.aftapningGårIMinus(60.1);
        assertEquals(forventet, aktuelt);
    }

    @Test
    void aftapningGårIMinusInputFalse(){
        //Arrange & Act
        Væske mockVæske4 = mock(Væske.class);
        when(mockVæske4.getVæskeMængde()).thenReturn(60.0);
        væskeMix.add(mockVæske4);
        boolean forventet = false;
        boolean aktuelt = væskeMix.aftapningGårIMinus(60.0);
        assertEquals(forventet, aktuelt);
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
        væskeMix.setNuværendeFad(mockFad);
        boolean forventet = true;
        boolean aktuelt = væskeMix.getNuværendeFad().equals(mockFad);
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
    void testComposite(){
        Mark mark = new Mark("Hansens Mark",true);
        Korn korn = new Korn(LocalDate.now(),"Evergreen",mark);
        Maltbatch mb = new Maltbatch("NM2P",1900.0,korn);
        Destillering ds = new Destillering(2,LocalDate.now(),LocalDate.now(),900,75,mb);
        Destillering ds2 = new Destillering(2,LocalDate.now(),LocalDate.now(),900,75,mb);
        Væske væske1 = new Væske(20,ds);
        Væske væske2 = new Væske(10,ds);
        Væske væske3 = new Væske(10,ds2);
        FadLeverandør fadLeverandør = new FadLeverandør("Barrels-R-US","Murica");
        Fad fad = new Fad(30,"Eg",fadLeverandør,"Sherry",30,2);
        Fad fad2 = new Fad(300,"Eg",fadLeverandør,"Sherry",30,2);
        fad.tilføjVæske(væske1);
        fad.tilføjVæske(væske2);
        //fad.tilføjVæske(væske3);
        fad2.tilføjVæske(væske1);
        fad2.tilføjVæske(væske2);
        fad2.tilføjVæske(væske3);
        PåfyldningsComponent væskem = fad2.getVæskeMix();
        Væske væske4;
        StorageInterface storage = new Storage();
        Controller controller = new Controller(storage);
        PåfyldningsComponent vm = fad.getVæskeMix();

        double aktuelt = vm.getVæskeMængde();
        double forventet = 30.0;
        assertEquals(forventet,aktuelt);
        assertThrows(RuntimeException.class,()-> fad.tilføjVæske(væske3));
        assertThrows(IllegalArgumentException.class,()-> controller.opretVæske(871,ds));

        for (PåfyldningsComponent påfyldningsComponent : fad2.getPåfyldningsComponenter()) {
            System.out.println(fad2.getPåfyldningsComponenter().size());
            System.out.println(væskem);
            List<PåfyldningsComponent> påfyldningsComponentArrayList = påfyldningsComponent.getPåfyldningsComponenter();
            for (PåfyldningsComponent component : påfyldningsComponentArrayList) {
                System.out.println(component);
            }
            System.out.println(påfyldningsComponent);
        }
    }
}
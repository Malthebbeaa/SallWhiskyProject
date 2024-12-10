package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FadTest {
    private Fad fad, fad1, fad2;
    private VæskeMix væskeMix1, væskeMix2;
    private Væske væske1, væske2, væske3;
    private Destillering destillering;
    private FadLeverandør fadLeverandør;
    @BeforeEach
    void setUp() {

        fadLeverandør = new FadLeverandør("Alberto", "Spanien");
        fad = new Fad(65, "Eg", fadLeverandør,"Sherry", 10, 1);
        fad1 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        fad2 = new Fad(150, "Eg", fadLeverandør, "Sherry", 3, 1);
        væskeMix1 = new VæskeMix(LocalDate.now(), fad);
        væskeMix2 = new VæskeMix(LocalDate.now(), fad);

        Maltbatch maltbatch = new Maltbatch("FM2323", 1000, new Korn(LocalDate.of(2024, 11,21), "Evergreen", new Mark("Lars Mark", true)));
        destillering = new Destillering(2, LocalDate.of(2024,11,25), LocalDate.of(2024, 11,27), 950,68,maltbatch);
        væske1 = new Væske(60, destillering);
        væske2 = new Væske(70, destillering);
        væske3 = new Væske(5, destillering);
    }

    @Test
    void påFyldningOvergårGrænseMindreMængde() {
        //Arrange Act
        boolean forventet = false;
        boolean aktuelt = fad.overskriderFadKapacitet(væske1.getVæskeMængde());

        assertEquals(forventet,aktuelt);
    }

    @Test
    void påFyldningOvergårGrænseForStorMængde() {
        //Arrange Act
        boolean forventet = true;
        boolean aktuelt = fad.overskriderFadKapacitet(væske2.getVæskeMængde());

        assertEquals(forventet,aktuelt);
    }
    @Test
    public void FlytDelAfVæskeMixTilFad_KanFindeYngstePåfyldningsDato() {
        //Arrange
        Destillering mockDestillering = mock(Destillering.class);
        Fad fad3 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        Fad fad4 = new Fad(150, "Mahogny", fadLeverandør, "Sherry", 3, 1);
        Fad fad5 = new Fad(120, "Linoleum", fadLeverandør, "Bourbon", 3, 1);
        Væske væske1Fad3 = new Væske(mockDestillering, 10);
        Væske væske2Fad3 = new Væske(mockDestillering, 20);
        VæskeMix væskeMixFad3 = new VæskeMix(LocalDate.of(2024, 01, 01), fad3);
        væskeMixFad3.add(væske1Fad3);
        væskeMixFad3.add(væske2Fad3);
        Væske Væske1Fad4 = new Væske(mockDestillering, 45);
        VæskeMix væskeMixFad4 = new VæskeMix(LocalDate.of(2023, 12, 31), fad4);
        væskeMixFad4.add(Væske1Fad4);
        fad3.tilføjVæske(væskeMixFad3.getPåfyldningsDato(), væskeMixFad3);
        fad4.tilføjVæske(væskeMixFad4.getPåfyldningsDato(), væskeMixFad4);

        //Act
        fad3.flytDelAfVæskeMixTilFad(fad4, væskeMixFad3, 20, LocalDate.now());
        fad4.flytDelAfVæskeMixTilFad(fad5,fad4.getPåfyldningsComponent(),60,LocalDate.now());

        //Assert
        List<LocalDate> datoInfo = new ArrayList<>();
        List<Fad> fadInfo = new ArrayList<>();
        List<Double> mængdeInfo = new ArrayList<>();
        fad5.traverseTree(fad5.getPåfyldningsComponent(), datoInfo, fadInfo, mængdeInfo);
        LocalDate aktuelDato = null;
        for (LocalDate localDate : datoInfo) {
            if(localDate == væskeMixFad3.getPåfyldningsDato()){
                aktuelDato = localDate;
            }
        }
        LocalDate forventetDato = LocalDate.of(2024, 01, 01);
        assertEquals(forventetDato, aktuelDato);
    }

    @Test
    public void FlytDelAfVæskeMixTilFad_GiverFadHistorik(){
        //Arrange
        Destillering mockDestillering = mock(Destillering.class);
        Fad fad3 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        Fad fad4 = new Fad(150, "Mahogny", fadLeverandør, "Sherry", 3, 1);
        Fad fad5 = new Fad(120, "Linoleum", fadLeverandør, "Bourbon", 3, 1);
        Væske væske1Fad3 = new Væske(mockDestillering, 10);
        Væske væske2Fad3 = new Væske(mockDestillering, 20);
        VæskeMix væskeMixFad3 = new VæskeMix(LocalDate.of(2024, 01, 01), fad3);
        væskeMixFad3.add(væske1Fad3);
        væskeMixFad3.add(væske2Fad3);
        Væske Væske1Fad4 = new Væske(mockDestillering, 45);
        VæskeMix væskeMixFad4 = new VæskeMix(LocalDate.of(2023, 12, 31), fad4);
        væskeMixFad4.add(Væske1Fad4);
        fad3.tilføjVæske(væskeMixFad3.getPåfyldningsDato(), væskeMixFad3);
        fad4.tilføjVæske(væskeMixFad4.getPåfyldningsDato(), væskeMixFad4);

        //Act
        fad3.flytDelAfVæskeMixTilFad(fad4, væskeMixFad3, 30, LocalDate.now());
        fad4.flytDelAfVæskeMixTilFad(fad5,fad4.getPåfyldningsComponent(),60,LocalDate.now());

        //Assert
        List<LocalDate> datoInfo = new ArrayList<>();
        List<Fad> fadInfo = new ArrayList<>();
        List<Double> mængdeInfo = new ArrayList<>();
        fad5.traverseTree(fad5.getPåfyldningsComponent(), datoInfo, fadInfo, mængdeInfo);
        Fad aktueltFad = null;
        for (Fad fad : fadInfo) {
            if(fad == fad4){
                aktueltFad = fad;
            }
        }
        Fad forventetFad = fad4;
        assertEquals(forventetFad, aktueltFad);
    }
    @Test
    public void FlytDelAfVæskeMixTilFad_NedskriverMængderKorrekt(){
        //Arrange
        Destillering mockDestillering = mock(Destillering.class);
        Fad fad3 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        Fad fad4 = new Fad(150, "Mahogny", fadLeverandør, "Sherry", 3, 1);
        Væske væske1Fad3 = new Væske(mockDestillering, 10);
        Væske væske2Fad3 = new Væske(mockDestillering, 20);
        VæskeMix væskeMixFad3 = new VæskeMix(LocalDate.of(2024, 01, 01), fad3);
        væskeMixFad3.add(væske1Fad3);
        væskeMixFad3.add(væske2Fad3);
        Væske Væske1Fad4 = new Væske(mockDestillering, 45);
        VæskeMix væskeMixFad4 = new VæskeMix(LocalDate.of(2023, 12, 31), fad4);
        væskeMixFad4.add(Væske1Fad4);
        fad3.tilføjVæske(væskeMixFad3.getPåfyldningsDato(), væskeMixFad3);
        fad4.tilføjVæske(væskeMixFad4.getPåfyldningsDato(), væskeMixFad4);
        //Act
        fad3.flytDelAfVæskeMixTilFad(fad4, væskeMixFad3, 15, LocalDate.now());

        List<LocalDate> datoInfo = new ArrayList<>();
        List<Fad> fadInfo = new ArrayList<>();
        List<Double> mængdeInfo = new ArrayList<>();
        fad3.traverseTree(fad3.getPåfyldningsComponent(), datoInfo, fadInfo, mængdeInfo);
        double aktueltMængde1 = 0.0;
        double aktueltMængde2 = 0.0;
        for (Double d : mængdeInfo) {
            if(d == 10){
                aktueltMængde1 = d;
            }
            if(d == 5){
                aktueltMængde2 = d;
            }
        }
        double forventetMængde1 = 10;
        double forventetMængde2 = 5;
        assertEquals(forventetMængde1, aktueltMængde1);
        assertEquals(forventetMængde2, aktueltMængde2);
    }

    @Test
        public void FlytDelAfVæskeMixTilFad_KanOmhældeHeleFadet_Grænseværdi(){
        //Arrange
        Destillering mockDestillering = mock(Destillering.class);
        Fad fad3 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        Fad fad4 = new Fad(150, "Mahogny", fadLeverandør, "Sherry", 3, 1);
        Væske væske1Fad3 = new Væske(mockDestillering, 10);
        Væske væske2Fad3 = new Væske(mockDestillering, 20);
        VæskeMix væskeMixFad3 = new VæskeMix(LocalDate.of(2024, 01, 01), fad3);
        væskeMixFad3.add(væske1Fad3);
        væskeMixFad3.add(væske2Fad3);
        Væske Væske1Fad4 = new Væske(mockDestillering, 45);
        VæskeMix væskeMixFad4 = new VæskeMix(LocalDate.of(2023, 12, 31), fad4);
        væskeMixFad4.add(Væske1Fad4);
        fad3.tilføjVæske(væskeMixFad3.getPåfyldningsDato(), væskeMixFad3);
        fad4.tilføjVæske(væskeMixFad4.getPåfyldningsDato(), væskeMixFad4);
        //Act
        fad3.flytDelAfVæskeMixTilFad(fad4, væskeMixFad3, 30, LocalDate.now());

        /**
         * Når fadet bliver tømt flyttes det til TidligerePåfyldningsComponenter og derfor er fad3.getPåfyldningsComponent = null
         * For at teste at mængden er 0.0 skal jeg derfor have fat i tidligerepåfyldningscomponenter.
         */
        double forventetMængdeFad3 = 0.0;
        double aktuelMængdeFad3 = fad3.getTidligerePåfyldningsComponenter().getFirst().getVæskeMængde();

        double forventetMængdeFad4 = 75.0;
        double aktuelMængdeFad4 = fad4.getPåfyldningsComponent().getVæskeMængde();
        assertEquals(forventetMængdeFad3, aktuelMængdeFad3);
        assertEquals(forventetMængdeFad4, aktuelMængdeFad4);
    }

    @Test
    public void FlytDelAfVæskeMixTilFad_KanOmhældeHeleFadet_Grænseværdi_KasterException(){
        //Arrange
        Destillering mockDestillering = mock(Destillering.class);
        Fad fad3 = new Fad(200, "Eg", fadLeverandør, "Rødvin", 5, 2);
        Fad fad4 = new Fad(150, "Mahogny", fadLeverandør, "Sherry", 3, 1);
        Væske væske1Fad3 = new Væske(mockDestillering, 10);
        Væske væske2Fad3 = new Væske(mockDestillering, 20);
        VæskeMix væskeMixFad3 = new VæskeMix(LocalDate.of(2024, 01, 01), fad3);
        væskeMixFad3.add(væske1Fad3);
        væskeMixFad3.add(væske2Fad3);
        Væske Væske1Fad4 = new Væske(mockDestillering, 45);
        VæskeMix væskeMixFad4 = new VæskeMix(LocalDate.of(2023, 12, 31), fad4);
        væskeMixFad4.add(Væske1Fad4);
        fad3.tilføjVæske(væskeMixFad3.getPåfyldningsDato(), væskeMixFad3);
        fad4.tilføjVæske(væskeMixFad4.getPåfyldningsDato(), væskeMixFad4);
        //Act
        assertThrows(RuntimeException.class,()-> fad3.flytDelAfVæskeMixTilFad(fad4, væskeMixFad3, 30.1, LocalDate.now()));
    }

    @Test
    public void testFlytDelAfVæskeMixTilFad() {
        //ACT
        fad1.tilføjVæske(LocalDate.now(), væske1);
        fad1.tilføjVæske(LocalDate.now(), væske2);
        fad1.tilføjVæske(LocalDate.now(), væske3);

        PåfyldningsComponent valgtMix = fad1.getVæskeMix();
        fad1.flytDelAfVæskeMixTilFad(fad2, valgtMix, 50, LocalDate.now());
        double forventetFad2 = 50.0;
        double aktueltFad2 = fad2.getMængdeFyldtPåFad();
        double forventetFad1 = 85.0;
        double aktueltFad1 = fad1.getMængdeFyldtPåFad();
        //ASSERT
        assertEquals(forventetFad2, aktueltFad2);
        assertEquals(forventetFad1, aktueltFad1);
    }

    @Test
    void getMængdeFyldtPåFad() {
        væskeMix1.add(væske1);
        væskeMix2.add(væske3);
        fad.tilføjVæske(LocalDate.now(), væskeMix1);
        fad.tilføjVæske(LocalDate.now(), væskeMix2);

        double forventet = 65;
        double aktuelt = fad.getMængdeFyldtPåFad();

        assertEquals(forventet, aktuelt);
    }
}
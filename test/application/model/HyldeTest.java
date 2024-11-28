package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HyldeTest {

    Hylde hylde;
    @BeforeEach
    void setUp() {
        hylde = new Hylde(1);
    }
    @Test
    void tilføjPlads() {
        //Act
        hylde.tilføjPlads(5);
        int actualAntal = hylde.getPladser().size();
        int expectedAntal = 5;
        //Assert
        assertEquals(expectedAntal, actualAntal);
    }
}
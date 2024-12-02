package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HyldeTest {

    Hylde hylde;
    @BeforeEach
    void setUp() {
        Reol mockReol = mock(Reol.class);
        hylde = new Hylde(1, mockReol);
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
package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReolTest {
    Reol reol;
    @BeforeEach
    void setUp() {
        reol = new Reol(1);
    }

    @Test
    void tilføjHylde() {
        //ACT
        reol.tilføjHylde(2,5);
        int actualSize = reol.getHylder().size();
        int expectedSize = 2;
        //Assert
        assertEquals(expectedSize, actualSize);
    }
}
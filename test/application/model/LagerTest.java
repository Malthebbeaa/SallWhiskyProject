package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {
    Lager lager;
    @BeforeEach
    void setUp() {
        lager = new Lager("Lars Gård","Sall hovedvej","8450","Hammel");
    }

    @Test
    void tilføjReol() {
        //ACT
        Reol reol = lager.tilføjReol();
        boolean expectedReol = true;
        boolean actualReol = lager.getReoler().contains(reol);
        //Assert
        assertEquals(expectedReol,actualReol);
    }

    @Test
    void ledigePladser() {
        //Arrange
        Reol reol = lager.tilføjReol();
        //ACT
        reol.tilføjHylde(2,5);
        reol.tilføjHylde(1,3);
        reol.tilføjHylde(1,1);
        int expectedAntal = 14;
        int actualAntal = lager.ledigePladser();
        //Assert
        assertEquals(expectedAntal,actualAntal);
    }
}
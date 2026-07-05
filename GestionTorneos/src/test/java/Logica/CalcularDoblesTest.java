package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcularDoblesTest {
    @Test
    void jscnkjdnc () {
        CalcularDobles bab = new CalcularDobles(new Torneo("A", "a", "a", "a"));
        for (int i=0; i<30;i++) {
            bab.siguiente();
            bab.Enfrentar(0);
            bab.siguiente();
            bab.Enfrentar(0);
        }

    }

}
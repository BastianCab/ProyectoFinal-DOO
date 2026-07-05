package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcularSimpleTest {
    @Test
    void jscnkjdnc () {
    CalcularSimple bab = new CalcularSimple(new Torneo("A", "a", "a", "a"));
    for (int i=0; i<10;i++) {
        bab.siguiente();
        bab.Enfrentar(1);
        bab.siguiente();
        bab.Enfrentar(1);
    }

    }

}
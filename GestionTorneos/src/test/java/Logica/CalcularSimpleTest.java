package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalcularSimpleTest {
    @Test
    void intentarCalulo () throws ErroresCalculo {
        CalcularSimple bab = new CalcularSimple();
        bab.empezar(10);
        for (int i = 0; i < 10; i++) {
            bab.siguiente();
            bab.enfrentar(1);
        }
    }
}
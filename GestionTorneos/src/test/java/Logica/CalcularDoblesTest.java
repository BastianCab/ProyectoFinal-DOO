package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalcularDoblesTest {
    @Test
    void IntentarCalculo () throws ErroresCalculo {
        CalcularDobles bab = new CalcularDobles();
        bab.empezar(10);
        for (int i=0; i<30;i++) {
            bab.siguiente();
            bab.enfrentar(0);
        }
        ArrayList<int[]> a = bab.getEnfrentamientoInfo();
    }

}
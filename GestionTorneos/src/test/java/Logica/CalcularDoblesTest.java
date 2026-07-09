package Logica;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class CalcularDoblesTest {
    @Test
    void IntentarCalculo () throws ErroresCalculo {
        CalcularDobles Calculo = new CalcularDobles();
        Calculo.empezar(10);
        for (int i=0; i<20;i++) {
            Calculo.siguiente();
            Calculo.enfrentar(0);
        }
    }

}
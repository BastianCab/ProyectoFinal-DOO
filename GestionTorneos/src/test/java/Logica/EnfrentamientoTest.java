package Logica;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class EnfrentamientoTest {
    Participante p1;
    Participante p2;
    Enfrentamiento enfrentamiento;
    @BeforeEach
    void setup() throws DatoInvalidoException {
        this.p1 = new Participante(TipoParticipante.PERSONA, "Pepe1");
        this.p2 = new Participante(TipoParticipante.PERSONA, "Pepe2");
        this.enfrentamiento = new Enfrentamiento(p1, p2);
    }

    @Test
    @DisplayName("Test todo correcto")
    void testEnfrentamientoCorrecto() {
        assertEquals(p1, enfrentamiento.getCompetidor1());
        assertEquals(p2, enfrentamiento.getCompetidor2());
        assertNull(enfrentamiento.getFechaHora());
        assertEquals(-1, enfrentamiento.getResultado());
    }

    @Test
    @DisplayName("Test toString con resultados distintos")
    void testToStringResultados() throws DatoInvalidoException {
        Enfrentamiento enfrentamiento = new Enfrentamiento(p1, p2);
        LocalDateTime fecha = LocalDateTime.of(2026, 7, 10, 15, 30);
        enfrentamiento.setFechaHora(fecha);

        assertEquals("Enfrentamiento: Pepe1 vs Pepe2\nEl 2026-07-10 a las 15:30", enfrentamiento.toString());

        enfrentamiento.setResultado(1);
        assertEquals("Pepe1 ganó el enfrentamiento contra Pepe2", enfrentamiento.toString());

        enfrentamiento.setResultado(2);
        assertEquals("Pepe2 ganó el enfrentamiento contra Pepe1", enfrentamiento.toString());
    }
}

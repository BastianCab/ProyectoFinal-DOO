package Logica;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

public class ParticipanteTest {
    @Test
    @DisplayName("Test todo correcto")
    void testParticipanteCorrecto() throws DatoInvalidoException {
        Participante participante = new Participante(TipoParticipante.PERSONA, "Jugador1");

        assertEquals(TipoParticipante.PERSONA, participante.getTipo());
        assertEquals("Jugador1", participante.getNombre());
    }

    @Test
    @DisplayName("Test nombre vacío o nulo")
    void testParticipanteNombreNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, null));

        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, ""));
    }
}

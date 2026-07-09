package Logica;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

public class ParticipanteTest {
    Participante participante;
    @BeforeEach
    void setup() throws DatoInvalidoException {
        participante = new Participante(TipoParticipante.PERSONA, "P1",
                "correo", "12345");
    }

    @Test
    @DisplayName("Test todo correcto")
    void testParticipanteCorrecto() throws DatoInvalidoException {
        assertEquals(TipoParticipante.PERSONA, participante.getTipo());
        assertEquals("P1", participante.getNombre());
        assertEquals("correo", participante.getCorreo());
        assertEquals("12345", participante.getTelefono());
    }

    @Test
    @DisplayName("Test nombre vacío o nulo")
    void testParticipanteNombreNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, null, "correo", "12345"));

        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, "", "correo", "12345"));
    }

    @Test
    @DisplayName("Test correo vacío o nulo")
    void testParticipanteCorreoNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, "P1", null, "12345"));

        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, "P1", "", "12345"));
    }

    @Test
    @DisplayName("Test teléfono vacío o nulo")
    void testParticipanteTelefonoNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, "!1", "correo", null));

        assertThrows(DatoInvalidoException.class, () ->
                new Participante(TipoParticipante.PERSONA, "P1", "correo", ""));
    }
}

package Logica;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TorneoTest {
    Torneo torneo;
    TipoParticipante participante;
    TipoTorneoEnum tipoTorneo;
    CalcularJuego juego;
    List<Enfrentamiento> enfrentamientos;
    ArrayList<Participante> participantes;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        this.participante = TipoParticipante.PERSONA;
        this.tipoTorneo = TipoTorneoEnum.ELIMINACION_DIRECTA;
        this.juego = new CalcularSimple(2);
        this.participantes = new ArrayList();
        for (int i = 0; i < 2; i++) {
            participantes.add(new Participante(participante, "a" + i));
        }

        this.torneo = new Torneo("org", "tor", "fut", participante,
                tipoTorneo, juego);
    }

    @Test
    @DisplayName("Test todo correcto")
    void testTorneoCorrecto() {
        assertEquals("org", torneo.getNombreOrganizador());
        assertEquals("tor", torneo.getNombre());
        assertEquals("fut", torneo.getDisciplina());
        assertEquals(TipoParticipante.PERSONA, torneo.getTipoParticipante());
        assertEquals(TipoTorneoEnum.ELIMINACION_DIRECTA, torneo.getTipoTorneo());
        assertEquals(2, torneo.getCantidadCompetidores());
        assertEquals(participantes, torneo.getCompetidores());
    }

    @Test
    @DisplayName("Test nombre del organizador vacío o nulo")
    void testTorneoOrganizadorNuloVacío() {
        assertThrows(DatoInvalidoException.class, () ->
                new Torneo(null, "tor", "fut", participante, tipoTorneo, juego));

        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("", "tor", "fut", participante, tipoTorneo, juego));
    }

    @Test
    @DisplayName("Test nombre del torneo vacío o nulo")
    void testTorneoNombreNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", null, "fut", participante, tipoTorneo, juego));

        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", "", "fut", participante, tipoTorneo, juego));
    }

    @Test
    @DisplayName("Test disciplina vacía o nula")
    void testTorneoDisciplinaNulaVacia() {
        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", "tor", null, participante, tipoTorneo, juego));

        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", "tor", "", participante, tipoTorneo, juego));
    }

    @Test
    @DisplayName("Test tipo de participante nulo")
    void testTorneoTipoParticipanteNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", "tor", "fut",
                        null, tipoTorneo, juego));
    }

    @Test
    @DisplayName("Test tipo de torneo nulo")
    void testTorneoTipoTorneoNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", "tor", "fut",
                        participante, null, juego));
    }

    @Test
    @DisplayName("Test estrategia nula")
    void testTorneoEstrategiaNula() {
        assertThrows(DatoInvalidoException.class, () ->
                new Torneo("org", "tor", "fut",
                        participante, tipoTorneo, null));
    }

    @Test
    @DisplayName("Test inscribir participante nulo")
    void testInscribirParticipanteNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                torneo.inscribirParticipante(null));
    }

    @Test
    @DisplayName("Test calcular enfrentamientos torneo")
    void testCalcularEnfrentamientosTorneo() {
        this.enfrentamientos = torneo.calcularEnfrentamientosTorneo();
        assertEquals(enfrentamientos, torneo.getEnfrentamientos());
    }

}

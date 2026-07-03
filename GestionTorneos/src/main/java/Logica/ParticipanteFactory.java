package Logica;

public class ParticipanteFactory {
    public static Participante crearParticipante(TipoParticipante tipo, String nombre, String contacto) {

        // Un switch con Enum es mucho más elegante y rápido que los if/else
        switch (tipo) {
            case INDIVIDUAL:
                return new ParticipantePersona(nombre, contacto); // (Usando tu clase Jugador de Composite)
            case EQUIPO:
                return new ParticipanteEquipo(nombre, contacto);
            default:
                throw new AssertionError("Tipo desconocido: " + tipo);
        }
    }
}

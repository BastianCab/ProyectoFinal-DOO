package Logica;

/**
 * Clase que extiende la clase tipoTorneo, representa el formato de torneo
 * "eliminatorio simple"
 */
public class EliminatoriaSimple implements TipoTorneo {
    /**
     * Selecciona el formato "eliminatorio simple"
     * @return un string con el formato elegido
     */
    @Override
    public String elegirTorneo() {
        return "Eliminatorio simple";
    }
}

package Logica;

/**
 * Clase que extiende la clase tipoTorneo, representa el formato de torneo
 * "eliminatorio simple"
 */
public class eliminatorioSimple extends tipoTorneo {
    /**
     * Selecciona el formato "eliminatorio simple"
     * @return un string con el formato elegido
     */
    @Override
    public String elegirTorneo() {
        return "Eliminatorio simple";
    }
}

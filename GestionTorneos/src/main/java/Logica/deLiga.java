package Logica;

/**
 * Clase que extiende la clase tipoTorneo, representa el formato de torneo "de liga"
 */
public class deLiga extends tipoTorneo {
    /**
     * Selecciona el formato "de liga"
     * @return un string con el formato elegido
     */
    @Override
    public String elegirTorneo() {
        return "De liga";
    }
}

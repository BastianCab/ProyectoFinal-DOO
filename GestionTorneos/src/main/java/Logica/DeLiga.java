package Logica;

/**
 * Clase que implementa la clase tipoTorneo, representa el formato de torneo "de liga"
 */
public class DeLiga implements TipoTorneo {
    /**
     * Selecciona el formato "de liga"
     * @return un string con el formato elegido
     */
    @Override
    public String elegirTorneo() {
        return "De liga";
    }
}

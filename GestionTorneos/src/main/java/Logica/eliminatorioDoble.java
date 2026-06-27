package Logica;

/**
 * Clase que extiende la clase tipoTorneo, representa el formato de torneo
 * "eliminatorio doble"
 */
public class eliminatorioDoble extends tipoTorneo {
    /**
     * Selecciona el formato "eliminatorio doble"
     * @return un string con el formato elegido
     */
    @Override
    public String elegirTorneo() {
        return "Eliminatorio doble";
    }
}

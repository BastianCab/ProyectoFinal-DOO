package Logica;

/**
 * Clase que extiende la clase tipoTorneo, representa el
 * formato de torneo "todos contra todos"
 */
public class todosContraTodos extends tipoTorneo {
    /**
     * Selecciona el formato "todos contra todos"
     * @return un string con el formato elegido
     */
    @Override
    public String elegirTorneo() {
        return "Todos contra todos";
    }
}

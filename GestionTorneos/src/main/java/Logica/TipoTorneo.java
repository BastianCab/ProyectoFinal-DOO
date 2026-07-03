package Logica;

/**
 * Clase que implementa el patrón "Factory method", representa los tipos
 * de formato que se pueden elegir para el torneo
 */
public interface TipoTorneo {
    /**
     * La forma genérica de elegir el torneo, cada subclase implementa
     * su propio formato del torneo
     * @return un string que indica el formato del torneo
     */
    public abstract String elegirTorneo();
}

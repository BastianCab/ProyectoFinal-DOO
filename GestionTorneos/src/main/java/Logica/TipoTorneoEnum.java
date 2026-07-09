package Logica;

/**
 * Catálogo enumerado que define los formatos estructurales disponibles para un torneo.
 * Centraliza la información descriptiva que se muestra en la interfaz gráfica (GUI).
 */
public enum TipoTorneoEnum {

    /**
     * Formato clásico de fase de grupos o liga general.
     */
    LIGA_SIMPLE(
            "Liga Simple",
            "Todos contra todos a una sola vuelta. Clasificación por puntos acumulados."

    ),

    /**
     * Formato de llaves o bracket tradicional sin repechaje.
     */
    ELIMINACION_DIRECTA(
            "Eliminación Directa",
            "Formato de muerte súbita. El que pierde queda eliminado y el ganador avanza."
    ),

    /**
     * Formato complejo con llaves de ganadores (Winner Bracket) y perdedores (Loser Bracket).
     */
    ELIMINACION_DOBLE(
            "Eliminación Doble",
            "Permite una segunda oportunidad. Los perdedores caen a una llave secundaria."
    );

    private final String nombre;
    private final String descripcion;

    /**
     * Constructor interno del enumerador.
     * Asigna los textos fijos que le darán contexto al usuario final al crear el torneo.
     * @param nombre      El título corto del formato (Ej. "Liga Simple").
     * @param descripcion La explicación de las reglas que se mostrará en pantalla.
     */
    TipoTorneoEnum(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el título amigable del formato.
     * @return El nombre del tipo de torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la explicación detallada de las reglas del formato.
     * @return La descripción del tipo de torneo.
     */
    public String getDescripcion() {
        return descripcion;
    }
}
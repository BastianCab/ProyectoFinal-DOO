package Logica;

public enum TipoTorneoEnum {
    LIGA_SIMPLE(
            "Liga Simple",
            "Todos contra todos a una sola vuelta. Clasificación por puntos acumulados."

    ),
    ELIMINACION_DIRECTA(
            "Eliminación Directa",
            "Formato de muerte súbita. El que pierde queda eliminado y el ganador avanza."
    ),
    ELIMINACION_DOBLE(
            "Eliminación Doble",
            "Permite una segunda oportunidad. Los perdedores caen a una llave secundaria."
    );

    private final String nombre;
    private final String descripcion;

    TipoTorneoEnum(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
}

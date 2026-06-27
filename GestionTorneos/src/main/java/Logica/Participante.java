package Logica;

/**
* Clase que implementa el patrón "Strategy" y la interfaz "Inscribible",
 * representa a un participante genérico el cual puede ser inscrito a un torneo,
 * contiene el nombre del participante
 */
public class Participante implements Inscribible {
    private String nombre;

    /**
     * Crea un participante genérico con un nombre
     * @param nombre el nombre del participante
     */
    public Participante(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del nombre del participante
     * @return el nombre del participante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre del participante
     * @param nombre el nombre al cual se quiere modificar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Representa que el participante puede ser inscrito
     */
    @Override
    public void inscribir() {
    }
}

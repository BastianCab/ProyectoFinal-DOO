package Logica;

/**
* Clase que implementa el patrón "Strategy" y la interfaz "Inscribible",
 * representa a un participante genérico el cual puede ser inscrito a un torneo,
 * contiene el nombre del participante
 */
public interface Participante {

    /**
     * Getter del nombre del participante
     * @return el nombre del participante
     */
    String getNombre();
    String getContacto();


    void agregar(Participante participante);
    void eliminar(Participante participante);
    // void mostrarDetalles(int nivel);
}

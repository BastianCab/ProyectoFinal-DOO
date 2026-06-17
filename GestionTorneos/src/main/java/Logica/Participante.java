package Logica;

public class Participante implements Inscribible {
    private String nombre;
    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void inscribir() {
    }
}

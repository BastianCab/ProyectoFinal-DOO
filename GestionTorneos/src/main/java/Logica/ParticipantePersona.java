package Logica;

public class ParticipantePersona extends Participante implements Inscribible {
    public ParticipantePersona(String nombre) {
        super(nombre);
    }

    @Override
    public void inscribir() {
    }

    @Override
    public String toString() {
        return "Nombre del competidor: " + super.getNombre();
    }
}

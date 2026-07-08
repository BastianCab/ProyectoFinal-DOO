package Logica;

import java.util.List;

public interface CalcularJuegoPlaceholder {
    public void siguiente();
    // void mostrarDetalles(int nivel);

    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> cometidores);
    public int getTipo();
}

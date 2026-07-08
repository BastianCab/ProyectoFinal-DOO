package Logica;

import java.util.ArrayList;

public interface CalcularJuegoPlaceholder {
    public void siguiente();
    // void mostrarDetalles(int nivel);

    public ArrayList<Enfrentamiento> calcularEnfrentamientos(ArrayList<Participante> cometidores);
    public int getTipo();
}

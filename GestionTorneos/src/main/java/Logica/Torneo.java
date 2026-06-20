package Logica;
import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private String nombre;
    private String diciplina;
    private List<ObservadorTorneo> observadores = new ArrayList<>();

    public Torneo(String nombre, String diciplina){
        this.nombre = nombre;
        this.diciplina = diciplina;
    }

    public void agregarObservador(ObservadorTorneo observador) {
        observadores.add(observador);
    }

    public void registrarResultado(String partido, String resultado) {
        System.out.println("\n[SISTEMA] Organizador registró: " + partido + " -> " + resultado);

        String datosDelPartido = partido + " quedó " + resultado;

        for (ObservadorTorneo obs : observadores) {
            obs.actualizar(datosDelPartido);
        }
    }
}

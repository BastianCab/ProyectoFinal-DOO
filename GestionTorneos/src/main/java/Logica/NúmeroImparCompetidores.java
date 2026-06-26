package Logica;

public class NúmeroImparCompetidores extends Exception {
    public NúmeroImparCompetidores() {
        super("El formato del torneo requiere un número par de competidores");
    }
}

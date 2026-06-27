package Logica;

/**
 * Exception por si se escoge un formato eliminatorio y la cantidad de
 * participantes es impar
 */
public class NúmeroImparCompetidores extends Exception {
    public NúmeroImparCompetidores() {
        super("El formato del torneo requiere un número par de competidores");
    }
}

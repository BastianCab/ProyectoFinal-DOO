package Logica;

/**
 * Exception por si se escoge un formato eliminatorio y la cantidad de
 * participantes es impar
 */
public class ErroresCalculo extends Exception {
    public ErroresCalculo(String a) {
        super("Error en calculo: " + a);
    }
}

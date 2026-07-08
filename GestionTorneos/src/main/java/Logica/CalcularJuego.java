package Logica;

public interface CalcularJuego {
    public void siguiente();
    public void enfrentar(int a) throws ErroresCalculo;

    public int getTipo();
    public int getCompetidores();



}

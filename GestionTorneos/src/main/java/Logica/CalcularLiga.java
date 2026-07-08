package Logica;

public class CalcularLiga implements CalcularJuego{
    public Torneo torneo;

    CalcularLiga(Torneo torneo) {
        this.torneo = torneo;
    }

    @Override
    public int getTipo(){
        return 3;
    }
    @Override
    public int getCompetidores() {
        return torneo.getCantidadCompetidores();
    }
    @Override
    public void siguiente() {

    }
    @Override
    public void enfrentar(int a) {

    }
}

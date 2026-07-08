package Logica;

import java.util.ArrayList;

public class CalcularLigaPlaceholder implements CalcularJuegoPlaceholder{

    public CalcularLigaPlaceholder() {}

    @Override
    public void siguiente() {

    }

    @Override
    public ArrayList<Enfrentamiento> calcularEnfrentamientos(ArrayList<Participante> competidores) {
        return null;
    }

    @Override
    public int getTipo(){
        return 3;
    }
}

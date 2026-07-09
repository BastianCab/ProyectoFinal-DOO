package Logica;

import java.util.ArrayList;
import java.util.List;

public interface CalcularJuego {
    ArrayList<String> EnfrentamientosPH = new ArrayList<>();

    public void siguiente();

    public void enfrentar(int a) throws ErroresCalculo;

    public default void reorganizar(int[][] partidoC, int num, int ronda) {
        int c2 = 0;
        int i = 0;
        int c1= 0;
        while (partidoC[i][ronda]!=0){
            c2 = partidoC[i][ronda];
            partidoC[i][ronda] = c1;
            c1 = c2;
            i++;
        }
        partidoC[i][ronda] = c1;
        partidoC[0][ronda]=num;
        partidoC[66][ronda]=66;
    }

    public default int getCompetidores() {
        return 666;
    }

    public int getTipo();

    public default void guardarEnfrentamientoPH(int a, int b, int c,String d,int f){
        if (c==0){
            EnfrentamientosPH.add("Jugador "+String.valueOf(a)+" Gano a Jugador "+String.valueOf(b)+" , En la ronda: "+String.valueOf(f+1)+ " "+ d);
        } else {
            EnfrentamientosPH.add("Jugador "+String.valueOf(b)+" Gano a Jugador "+String.valueOf(a)+" , En la ronda: "+String.valueOf(f+1)+ " "+ d);
        }

    }

    public default ArrayList<String> getEnfrentamientosPH(){
        return EnfrentamientosPH;
    }

    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> cometidores);

}

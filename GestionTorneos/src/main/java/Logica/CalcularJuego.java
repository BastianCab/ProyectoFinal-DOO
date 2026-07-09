package Logica;

import java.util.ArrayList;
import java.util.List;

public interface CalcularJuego {

    public ArrayList<int[]> EnfrentamientosInfo = new ArrayList<int[]>();

    public void empezar(int i);

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

    public default void guardarEnfrentamientoInfo(int a, int b, int c){
        int[] Info = {a,b,c};
        EnfrentamientosInfo.add(Info);
    }

    public default ArrayList<int[]> getEnfrentamientoInfo(){
        return EnfrentamientosInfo;
    }

    public int[] getParticipantes();

    public default int getCompetidores() {
        return 666;
    }

    public int getTipo();
    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> cometidores) throws DatoInvalidoException;

}

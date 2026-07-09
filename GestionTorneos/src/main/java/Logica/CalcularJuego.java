package Logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Para que el torneo sea representado se representa
 * el progreso en una doble array cuál es modificado con
 * el avanze del torneo
 */
public interface CalcularJuego {

    public ArrayList<int[]> EnfrentamientosInfo = new ArrayList<int[]>();

    /**
     * Se inicia la forma del torneo despues de ser creado
     * @param Jugadores cantidad de jugadores en el torneo
     */
    public void empezar(int Jugadores);

    /**
     * Se ocupa para avanzar en el torneo
     */
    public void siguiente();

    /**
     * Metodo que indica el llamar enfrentamiento
     * Tiene en considerado en que braket esta
     * @param Resultado entre que gana participante 1 o 2
     * @throws ErroresCalculo si hay errores al ejecutar
     */
    public void enfrentar(int Resultado) throws ErroresCalculo;

    /**
     * Cuando el en una ronda queda un resultado impar
     * el se vuelve un carry entre rondas, para evitarlo
     * se invierte el orden de los jugadores
     * @param partidoC
     * @param num
     * @param ronda
     */
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

    /**
     * Guarda los resultados de cada enfrantamiero
     * @param Jugador1
     * @param Jugador2
     * @param Resultado
     */
    public default void guardarEnfrentamientoInfo(int Jugador1, int Jugador2, int Resultado){
        int[] Info = {Jugador1,Jugador2,Resultado};
        EnfrentamientosInfo.add(Info);
    }

    /**
     * getter que da la lista del esqueleto creado
     * @return Lista de enfrentamientos en int
     * @throws DatoInvalidoException
     */
    public default ArrayList<int[]> getEnfrentamientoInfo(){
        return EnfrentamientosInfo;
    }

    /**
     * getter que da los participantes actuales
     * @return
     */
    public int[] getParticipantes();

    /**
     * Crea la lista de enfrentamientos asociada al esqueleto creado
     * @return Lista de enfrentamientos
     * @throws DatoInvalidoException
     */
    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> cometidores) throws DatoInvalidoException;

    /**
     * Determina si se termino el Torneo
     * @return
     */
    public boolean getEnd();

}

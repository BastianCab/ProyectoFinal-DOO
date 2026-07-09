package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculadora de Eliminatorias dobles
 * es un esqueleto que asocia partes enteros
 * a los jugadores
 */
public class CalcularDobles implements CalcularJuego {
    /**
     * Se crean dos arrays dobles para representar el progreso de
     * en el torneo, tanto en los Winners como Loosers
     */
    int[][] partido = new int[100][20];
    int[][] partidoPerdedores = new int[100][20];
    int participante1;
    int participante2;

    private boolean Perdedores = false;
    private int buffer = 0;

    /**
     * Hay tres flags que indican el progreso del torneo
     * cuando termina ganadores
     * cuando termina perdedores
     * cuando termina el torneo
     */
    private boolean endW = false;
    private boolean endL = false;
    private boolean end = false;

    private int partidoAct = 0;
    private int rondaAct;

    /**
     * Metodo que indica el llamar enfrentamiento
     * @param Resultado entre que gana participante 1 o 2
     * @throws ErroresCalculo si hay errores al ejecutar
     */
    @Override
    public void enfrentar(int Resultado) throws ErroresCalculo {
        if (Resultado != 1 && Resultado !=0){
            throw new ErroresCalculo("Valor de ganador no valido [entre 1 y 0]");
        } else if (endW && endL){
            partido[partidoAct+1][rondaAct] = partidoPerdedores[0][rondaAct];
            if (Resultado == 0) {
            partido[partidoAct][rondaAct + 1] = partido[partidoAct][rondaAct];
            } else if (Resultado == 1) {
            partido[partidoAct+1][rondaAct + 1] = partido[partidoAct][rondaAct];
            }
            Perdedores = false;
            end = true;
            if(participante1!=0 && participante2!=0) {
                guardarEnfrentamientoInfo(participante1, participante2, Resultado); //debug
            }
        } else if (Perdedores == false) {
            if (endW && end == false) {
                partido[partidoAct][rondaAct + 1] = participante1;
            } else if (Resultado == 0) {
                partido[partidoAct][rondaAct + 1] = participante1;
                partidoPerdedores[partidoAct + buffer][rondaAct] = participante2;
            } else if (Resultado == 1) {
                partido[partidoAct][rondaAct + 1] = participante2;
                partidoPerdedores[partidoAct + buffer][rondaAct] = participante1;
            }
            if(participante1!=0 && participante2!=0) {
                guardarEnfrentamientoInfo(participante1, participante2, Resultado); //debug
            }
            partidoAct++;
        } else {
            if (endW && end == false) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante1;
            } else if (Resultado == 0) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante1;
            } else if (Resultado == 1) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante2;
            }
            guardarEnfrentamientoInfo(participante1,participante2,Resultado); //debug
            partidoAct++;
            buffer++;
        }
    }

    /**
     * Se inicia la forma del torneo despues de ser creado
     * @param jugadores cantidad de jugadores en el torneo
     */
    @Override
    public void empezar(int jugadores) {
        for (int i=0;i<jugadores;i++){ //placeholder
            partido[i][0] = i+1;
        }
    }

    /**
     * Se ocupa para avanzar en el torneo
     */
    @Override
    public void siguiente() {
        if (end) {
            System.out.println("Ganador torneo: " + String.valueOf(partido[0][rondaAct]));
        }
        else if (Perdedores == false){
            if (partido[partidoAct * 2 + 1][rondaAct] == 0) {
                if (partido[partidoAct * 2][rondaAct] != 0) {
                    reorganizar(partido,partido[partidoAct * 2][rondaAct],rondaAct+1);
                    //partido[partidoAct][rondaAct + 1] = partido[partidoAct * 2][rondaAct];
                }
                partidoAct = 0;
                participante1 = partidoPerdedores[partidoAct * 2][rondaAct];
                participante2 = partidoPerdedores[partidoAct * 2 + 1][rondaAct];
                buffer = 0;
                if (partido[1][rondaAct+1]==0){
                    endW = true;
                    System.out.println("Ganador torneo Winners: " + String.valueOf(partido[0][rondaAct+1]));
                }
                Perdedores = true;
            } else {
                participante1 = partido[partidoAct * 2][rondaAct];
                participante2 = partido[partidoAct * 2 + 1][rondaAct];
            }
        } else {
            if (partidoPerdedores[partidoAct * 2 + 1][rondaAct] == 0) {
                if (partidoPerdedores[partidoAct * 2][rondaAct] != 0) {
                    reorganizar(partidoPerdedores,partidoPerdedores[partidoAct * 2][rondaAct],rondaAct+1);
                    //partidoPerdedores[partidoAct][rondaAct + 1] = partidoPerdedores[partidoAct * 2][rondaAct];
                    buffer++;
                }
                partidoAct = 0;
                participante1 = partido[partidoAct * 2][rondaAct + 1];
                participante2 = partido[partidoAct * 2 + 1][rondaAct + 1];
                Perdedores = false;
                rondaAct++;
                if (partidoPerdedores[1][rondaAct]==0){
                    endL = true;
                    System.out.println("Ganador torneo Losers: " + String.valueOf(partidoPerdedores[0][rondaAct]));
                }
            } else {
                participante1 = partidoPerdedores[partidoAct * 2][rondaAct];
                participante2 = partidoPerdedores[partidoAct * 2 + 1][rondaAct];
            }
        }
    }

    /**
     * Crea la lista de enfrentamientos asociada al esqueleto creado
     * @param competidores
     * @return Lista de enfrentamientos
     * @throws DatoInvalidoException
     */
    @Override
    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> competidores) throws DatoInvalidoException {
        List<Enfrentamiento> partidos = new ArrayList<>();
        Collections.shuffle(competidores);

        int c = 0;
        for (int i = 0; i < EnfrentamientosInfo.size(); i++) {
            Enfrentamiento A = new Enfrentamiento(competidores.get((EnfrentamientosInfo.get(i))[0]-1),competidores.get((EnfrentamientosInfo.get(i))[1]-1));
            A.setResultado(EnfrentamientosInfo.get(i)[2]);
            partidos.add(A);
            c++;
        }
        for (int i = 0+c; i < competidores.size(); i += 2) {
            Participante p1 = competidores.get(i);
            Participante p2 = (i + 1 < competidores.size()) ? competidores.get(i + 1) : new Participante(null, "Por definir", "-", "-");
            partidos.add(new Enfrentamiento(p1, p2));
        }

        int partidosRondaPerdedores = competidores.size() - 1;

        for (int i = 0+c; i < partidosRondaPerdedores; i++) {
            Participante tbd = new Participante(null, "Por definir", "-", "-");
            partidos.add(new Enfrentamiento(tbd, tbd));
        }

        return partidos;
    }

    @Override
    public boolean getEnd() {
        return end;
    }

    /**
     * getter que da los participantes actuales
     * @return
     */
    @Override
    public int[] getParticipantes() {
        return new int[]{participante1,participante2};
    }
}

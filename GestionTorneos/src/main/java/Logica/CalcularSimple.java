package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalcularSimple implements CalcularJuego{
    int[][] partido = new int[100][20];
    int participante1;
    int participante2;

    private int partidoAct = 0;
    private int rondaAct;

    private boolean end = false;

    public CalcularSimple() {
    }

    @Override
    public void empezar(int jugadores) {
        for (int i=0;i<jugadores;i++){ //placeholder
            partido[i][0] = i+1;
        }
    }

    @Override
    public void enfrentar(int Resultado) throws ErroresCalculo {
        if (Resultado == 0){
            partido[partidoAct][rondaAct+1]=participante1;
        } else if (Resultado == 1){
            partido[partidoAct][rondaAct+1]=participante2;
        } else {
            throw new ErroresCalculo("Valor de ganador no valido [entre 1 y 0]");
        }
        if(participante1!=0 && participante2!=0) {
            guardarEnfrentamientoInfo(participante1, participante2, Resultado);
        }
        partidoAct++;
    }

    @Override
    public void siguiente() {
        if (end == true) {
            System.out.println("fin del torneo, ganador: " + String.valueOf(partido[0][rondaAct]));
        } else {
            if (partido[partidoAct * 2 + 1][rondaAct] == 0) {
                if (partido[partidoAct * 2][rondaAct] != 0) {
                    reorganizar(partido,partido[partidoAct * 2][rondaAct],rondaAct+1);
                    //partido[partidoAct][rondaAct + 1] = partido[partidoAct * 2][rondaAct];
                }
                partidoAct = 0;
                participante1 = partido[partidoAct * 2][rondaAct + 1];
                participante2 = partido[partidoAct * 2 + 1][rondaAct + 1];
                if (participante2 == 0) {
                    end = true;
                } else {
                    rondaAct++;
                }
            } else {
                participante1 = partido[partidoAct * 2][rondaAct];
                participante2 = partido[partidoAct * 2 + 1][rondaAct];
            }
        }
    }
 /*
    @Override
    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> competidores) {
        List<Enfrentamiento> partidos = new ArrayList<>();
        Collections.shuffle(competidores);

        // 1. CREAMOS LA PRIMERA RONDA (Partidos reales)
        for (int i = 0; i < competidores.size(); i += 2) {
            Participante p1 = competidores.get(i);
            Participante p2 = (i + 1 < competidores.size()) ? competidores.get(i + 1) : new Participante(null, "Por definir", null, null);
            partidos.add(new Enfrentamiento(p1, p2));
        }

        int partidosFaltantes = (competidores.size() / 2) - 1;
        for (int i = 0; i < partidosFaltantes; i++) {
            Participante fantasma1 = competidores.get((EnfrentamientosInfo.get(i))[0]);
            Participante fantasma2 = competidores.get((EnfrentamientosInfo.get(i))[1]);
            Enfrentamiento A = new Enfrentamiento(fantasma1, fantasma2);
            A.setResultado(EnfrentamientosInfo.get(i)[2]);
            partidos.add(A);
        }

        return partidos;

    }
    */
    @Override
    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> competidores) {
        List<Enfrentamiento> partidos = new ArrayList<>();
        Collections.shuffle(competidores);

        // 1. CREAMOS LA PRIMERA RONDA (Partidos reales)
        for (int i = 0; i < competidores.size(); i += 2) {
            Participante p1 = competidores.get(i);
            Participante p2 = (i + 1 < competidores.size()) ? competidores.get(i + 1) : new Participante(null, "Por definir", null, null);
            partidos.add(new Enfrentamiento(p1, p2));
        }

        int partidosFaltantes = (competidores.size() / 2) - 1;
        for (int i = 0; i < partidosFaltantes; i++) {
            Participante fantasma1 = new Participante(null, "Por definir", "-", "-");
            Participante fantasma2 = new Participante(null, "Por definir", "-", "-");

            partidos.add(new Enfrentamiento(fantasma1, fantasma2));
        }
        return partidos;
    }

    @Override
    public int getTipo() {
        return 1;
    }
}

package Logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CalcularSimplePlaceholder implements CalcularJuegoPlaceholder
{
    private int[][] partido = new int[30][30];

    private int participante1;
    private int participante2;

    private int partidoAct = 0;
    private int rondaAct;

    private boolean end = false;

    public CalcularSimplePlaceholder() {
        for (int i=0;i<9;i++){ //placeholder
            partido[i][0] = i+1;
        }

    }
    public void Enfrentar(int Resultado){
        //Enfrentamiento buffer = new Enfrentamiento(torneo.getCompetidores().get(participante1),torneo.getCompetidores().get(participante1));
        //buffer.setResultado(Resultado);
        //torneo.agregarEnfrentamiento(buffer);
        if (Resultado == 0){
            partido[partidoAct][rondaAct+1]=participante1;
        } else if (Resultado == 1){
            partido[partidoAct][rondaAct+1]=participante2;
        }
        partidoAct++;
    }

    @Override
    public void siguiente() {
        if (partido[partidoAct*2 +1][rondaAct]==0){
            if (partido[partidoAct*2][rondaAct]!=0){
                partido[partidoAct][rondaAct+1]=partido[partidoAct*2][rondaAct];
            }
            partidoAct = 0;
            participante1=partido[partidoAct*2][rondaAct+1];
            participante2=partido[partidoAct*2 +1][rondaAct+1];
            rondaAct++;
        } else {
            participante1=partido[partidoAct*2][rondaAct];
            participante2=partido[partidoAct*2 +1][rondaAct];
        }
        if(participante1==0 && participante2==0){
            end = true;
        }
    }

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

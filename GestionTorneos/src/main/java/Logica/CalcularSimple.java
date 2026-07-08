package Logica;

import java.util.ArrayList;

public class CalcularSimple implements CalcularJuego{
    private Torneo torneo;
    private int[][] partido = new int[30][30];

    private int participante1;
    private int participante2;

    private int partidoAct = 0;
    private int rondaAct;

    private boolean end = false;

    CalcularSimple(Torneo torneo) {
        this.torneo = torneo;
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
        if (end == true) {
            System.out.println("fin del torneo, ganador: " + String.valueOf(partido[0][rondaAct]));
        } else {
            if (partido[partidoAct * 2 + 1][rondaAct] == 0) {
                if (partido[partidoAct * 2][rondaAct] != 0) {
                    partido[partidoAct][rondaAct + 1] = partido[partidoAct * 2][rondaAct];
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
    @Override
    public int getTipo() {
        return 1;
    }

    @Override
    public int getCompetidores() {
        return torneo.getCantidadCompetidores();
    }
}

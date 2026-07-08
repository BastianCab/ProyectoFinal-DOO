package Logica;

import java.util.ArrayList;

public class CalcularDoblesPlaceholder implements CalcularJuegoPlaceholder {
    private int[][] partido = new int[30][30];
    private int[][] partidoPerdedores = new int[30][30];

    private boolean Perdedores = false;
    private int BufferP = 0;
    private int participante1;
    private int participante2;

    private int partidoAct = 0;
    private int rondaAct;

    private boolean end = false;

    public CalcularDoblesPlaceholder() {
        for (int i=0;i<9;i++){ //placeholder
            partido[i][0] = i+1;
        }

    }
    public void Enfrentar(int Resultado){
        //Enfrentamiento buffer = new Enfrentamiento(torneo.getCompetidores().get(participante1),torneo.getCompetidores().get(participante1));
        //buffer.setResultado(Resultado);
        //torneo.agregarEnfrentamiento(buffer);
        if (Perdedores == false) {
            if (Resultado == 0) {
                partido[partidoAct][rondaAct + 1] = participante1;
                partidoPerdedores[partidoAct][rondaAct] = participante2;
            } else if (Resultado == 1) {
                partido[partidoAct][rondaAct + 1] = participante2;
                partidoPerdedores[partidoAct][rondaAct] = participante1;
            }
            partidoAct++;
        } else {
            if (Resultado == 0) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante1;
            } else if (Resultado == 1) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante2;
            }
            partidoAct++;
        }
    }


    @Override
    public void siguiente() {
        if (Perdedores == false){
            if (partido[partidoAct*2+1][rondaAct]==0){
                if (partido[partidoAct*2][rondaAct]!=0){
                    partido[partidoAct][rondaAct+1]=partido[partidoAct*2][rondaAct];
                }
                partidoAct = 0;
                participante1=partidoPerdedores[partidoAct*2][rondaAct];
                participante2=partidoPerdedores[partidoAct*2 +1][rondaAct];
                Perdedores=true;
            } else {
                participante1=partido[partidoAct*2][rondaAct];
                participante2=partido[partidoAct*2 +1][rondaAct];
            }
            if(participante1==0 && participante2==0){
                end = true;
                rondaAct++;
        }
        } else {
            if (partidoPerdedores[partidoAct*2+1][rondaAct]==0){
                if (partidoPerdedores[partidoAct*2][rondaAct]!=0){
                    partidoPerdedores[partidoAct][rondaAct+1]=partidoPerdedores[partidoAct*2][rondaAct];
                }
                partidoAct = 0;
                participante1=partido[partidoAct*2][rondaAct+1];
                participante2=partido[partidoAct*2 +1][rondaAct+1];
                Perdedores = false;
                rondaAct++;
            } else {
                participante1=partidoPerdedores[partidoAct*2][rondaAct];
                participante2=partidoPerdedores[partidoAct*2 +1][rondaAct];
            }
            if(participante1==0 && participante2==0){
                end = true;
            }
        }
    }

    @Override
    public ArrayList<Enfrentamiento> calcularEnfrentamientos(ArrayList<Participante> competidores) {
        return null;
    }

    @Override
    public int getTipo() {
        return 1;
    }

}

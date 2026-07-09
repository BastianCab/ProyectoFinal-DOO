package Logica;

public class CalcularSimple implements CalcularJuego{
    int[][] partido = new int[100][20];
    int participante1;
    int participante2;

    private int partidoAct = 0;
    private int rondaAct;

    private boolean end = false;

    CalcularSimple(int jugadores) {
        for (int i=0;i<jugadores;i++){ //placeholder
            partido[i][0] = i+1;
        }
    }

    public void enfrentar(int Resultado) throws ErroresCalculo {
        if (Resultado == 0){
            partido[partidoAct][rondaAct+1]=participante1;
        } else if (Resultado == 1){
            partido[partidoAct][rondaAct+1]=participante2;
        } else {
            throw new ErroresCalculo("Valor de ganador no valido [entre 1 y 0]");
        }
        if(participante1!=0 && participante2!=0) {
            guardarEnfrentamientoPH(participante1, participante2, Resultado, "", rondaAct); //PH guardar info
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

    @Override
    public int getTipo() {
        return 1;
    }
}

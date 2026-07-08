package Logica;

public class CalcularDobles implements CalcularJuego {
    private Torneo torneo;
    private int[][] partido = new int[30][30];
    private int[][] partidoPerdedores = new int[30][30];

    private boolean Perdedores = false;
    private boolean endW = false;
    private boolean endL = false;
    private int buffer = 0;
    private int participante1;
    private int participante2;

    private int partidoAct = 0;
    private int rondaAct;

    private boolean end = false;

    CalcularDobles(Torneo torneo) {
        this.torneo = torneo;
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
                partidoPerdedores[partidoAct+buffer][rondaAct] = participante2;
            } else if (Resultado == 1) {
                partido[partidoAct][rondaAct + 1] = participante2;
                partidoPerdedores[partidoAct+buffer][rondaAct] = participante1;
            }
            partidoAct++;
        } else {
            if (Resultado == 0) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante1;
            } else if (Resultado == 1) {
                partidoPerdedores[partidoAct][rondaAct + 1] = participante2;
            }
            partidoAct++;
            buffer++;
        }
    }


    @Override
    public void siguiente() {
        if (end) {
            System.out.println("Ganador torneo: " + String.valueOf(partido[0][rondaAct]));
        } else if (endW && endL){
            partido[partidoAct][rondaAct + 1] = partido[0][rondaAct];
            partido[partidoAct][rondaAct + 1] = partidoPerdedores[0][rondaAct];
            Perdedores = false;
            endW = false;
            end = true;
            rondaAct++;
        }
        else if (Perdedores == false){
            if (endW){
                System.out.println("[out of bounds] Ganador torneo Winners: " + String.valueOf(partido[0][rondaAct]));
                Perdedores = true;
            } else {
                if (partido[partidoAct * 2 + 1][rondaAct] == 0) {
                    if (partido[partidoAct * 2][rondaAct] != 0) {
                        partido[partidoAct][rondaAct + 1] = partido[partidoAct * 2][rondaAct];
                    }
                    partidoAct = 0;
                    participante1 = partidoPerdedores[partidoAct * 2][rondaAct];
                    participante2 = partidoPerdedores[partidoAct * 2 + 1][rondaAct];
                    buffer = 0;
                    if (partido[1][rondaAct+1]==0){
                        endW = true;
                        System.out.println("Ganador torneo Winners: " + String.valueOf(partido[0][rondaAct]));
                    }
                    Perdedores = true;
                } else {
                    participante1 = partido[partidoAct * 2][rondaAct];
                    participante2 = partido[partidoAct * 2 + 1][rondaAct];
                }
            }
        } else {
            if (endL) {
                System.out.println("[out of bounds] Ganador torneo Losers: " + String.valueOf(partidoPerdedores[0][rondaAct]));
                Perdedores = false;
            } else {
                if (partidoPerdedores[partidoAct * 2 + 1][rondaAct] == 0) {
                    if (partidoPerdedores[partidoAct * 2][rondaAct] != 0) {
                        partidoPerdedores[partidoAct][rondaAct + 1] = partidoPerdedores[partidoAct * 2][rondaAct];
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

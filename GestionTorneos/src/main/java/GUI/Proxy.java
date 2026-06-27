package GUI;

import Logica.*;

/**
 * Implementación del patrón "Proxy", para conectar la GUI con la lógica del gestor
 */
public class Proxy {
    Organizador organizador;
    Torneo torneo;
    Participante participante;

    /**
     * Inicializa el proxy y sus atributos
     */
    public Proxy() {
        organizador = new Organizador("Pepe");
        torneo = new Torneo();
        participante = new Participante("Carlitos");
    }

    /**
     * El método para que el proxy sepa que hacer una vez se presione algún boton
     * de un panel cualquiera
     * @param boton el botón específico el cual se presionó
     */
    //Botones del panel principal: Organizar torneo, modificar torneo y espectar
    public void actionClick(int boton) {
        switch (boton) {
            case (1):
                break;
            case (2):
                break;
            case (3):
                break;
        }
    }

    /**
     * Getter del organizador
     * @return el organizador del torneo
     */
    public Organizador getOrganizador() {
        return organizador;
    }

    /**
     * Getter del torneo
     * @return el torneo creado
     */
    public Torneo getTorneo() {
        return torneo;
    }


    /**
     * Getter del participante
     * @return el participante
     */
    public Participante getParticipante() {
        return participante;
    }
}

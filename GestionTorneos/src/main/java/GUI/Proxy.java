package GUI;

import Logica.*;

public class Proxy {
    Organizador organizador;
    Torneo torneo;
    Participante participante;
    public Proxy() {
        organizador = new Organizador("Pepe", 1);
        torneo = new Torneo();
        participante = new Participante("Carlitos");
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public Participante getParticipante() {
        return participante;
    }
}

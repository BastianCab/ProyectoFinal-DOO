package GUI;

import Logica.Enfrentamiento;
import Logica.TipoParticipante;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel contenedor de 3 pasos para la creación de un torneo.
 * Maneja su propio CardLayout interno.
 */
public class PanelCrearTorneoMaster extends JPanel {
    private CardLayout layoutFormulario;
    private Proxy proxy;

    private JPanel panelContenedor;
    private CardLayout cardLayout;

    private PanelCrearTorneoInscripcion panelCrearTorneoInscripcion;
    private PanelCrearTorneoFechas panelCrearTorneoFechas;

    public PanelCrearTorneoMaster(Proxy proxyGlobal, JPanel panelContenedor, CardLayout cardLayout) {
        this.proxy = proxyGlobal;
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;


        this.layoutFormulario = new CardLayout();
        this.setLayout(layoutFormulario);

        JPanel panelCrearTorneoConfiguracionBasica = new PanelCrearTorneoConfiguracionBasica(this, proxy);
        this.panelCrearTorneoInscripcion = new PanelCrearTorneoInscripcion(this, proxy);
        this.panelCrearTorneoFechas = new PanelCrearTorneoFechas(this, proxy);


        this.add(panelCrearTorneoConfiguracionBasica, "PASO_1");
        this.add(panelCrearTorneoInscripcion, "PASO_2");
        this.add(panelCrearTorneoFechas, "PASO_3");
    }

    /**
     * Vuelve al inicio. Útil si el usuario quiere corregir el nombre del torneo.
     */
    public void volverAPaso1() {
        layoutFormulario.show(this, "PASO_1");
    }

    /**
     * Avanza a las inscripciones.
     */
    public void irAPaso2(TipoParticipante tipoParticipante) {
        panelCrearTorneoInscripcion.personalizarTextos(tipoParticipante);
        layoutFormulario.show(this, "PASO_2");
    }

    /**
     * Vuelve a las inscripciones desde el calendario.
     */
    public void volverAPaso2() {
        layoutFormulario.show(this, "PASO_2");
    }

    /**
     * Avanza al calendario final.
     */
    public void irAPaso3() {
        List<Enfrentamiento> partidosGenerados = proxy.generarEnfrentamientos();
        panelCrearTorneoFechas.cargarEnfrentamientos(partidosGenerados);
        layoutFormulario.show(this, "PASO_3");
    }

    public void irApaso4(){
        this.cardLayout.show(panelContenedor, "PANTALLA_MENU");
    }
}

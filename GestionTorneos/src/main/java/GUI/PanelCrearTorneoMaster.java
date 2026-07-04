package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Panel contenedor de 3 pasos para la creación de un torneo.
 * Maneja su propio CardLayout interno.
 */
public class PanelCrearTorneoMaster extends JPanel {
    private CardLayout layoutFormulario;
    private Proxy proxy;

    public PanelCrearTorneoMaster(Proxy proxyGlobal) {
        this.proxy = proxyGlobal;


        this.layoutFormulario = new CardLayout();
        this.setLayout(layoutFormulario);

        JPanel panelCrearTorneoConfiguracionBasica = new PanelCrearTorneoConfiguracionBasica(this, proxy);
        JPanel panelCrearTorneoInscripcion = new JPanel(); // Temporal
        JPanel panelCrearTorneoFechas = new JPanel(); // Temporal

        panelCrearTorneoInscripcion.add(new JLabel("PASO 2: INSCRIBIR PARTICIPANTES"));
        panelCrearTorneoFechas.add(new JLabel("PASO 3: CALENDARIO DE ENFRENTAMIENTOS"));


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
    public void irAPaso2() {
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
        layoutFormulario.show(this, "PASO_3");
    }
}

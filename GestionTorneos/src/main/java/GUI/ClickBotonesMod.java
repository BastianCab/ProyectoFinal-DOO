package GUI;

import Logica.ErroresCalculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Clase que implementa el action listener a los botones de modificar
 * Torneo
 */
public class ClickBotonesMod implements ActionListener {
    Proxy proxy;
    int boton;
    JPanel panel;

    /**
     * El método que conecta el botón con el proxy
     * @param boton el botón específico que se presionó
     * @param proxy el proxy al cual se conecta el botón
     */
    public ClickBotonesMod (int boton, Proxy proxy,JPanel Panel) {
        this.boton = boton;
        this.proxy = proxy;
        this.panel = Panel;
    }

    /**
     * Llama a quien gana en el torneo y avanza el orden
     * @param e el evento a ser procesado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (boton) {
            case (1):
                try {
                    proxy.torneo.getEstrategia().enfrentar(0);
                } catch (ErroresCalculo ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case (2):
                try {
                    proxy.torneo.getEstrategia().enfrentar(1);
                } catch (ErroresCalculo ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
        proxy.torneo.getEstrategia().siguiente();
        panel.repaint();
    }
}


package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para implementar el Action Listener, conecta el presionar algún boton con
 * una función en el Proxy
 */
public class BotonesPanelCreacion implements ActionListener {
    PH_Proxy Proxy;
    int Button;

    public BotonesPanelCreacion(int Button, PH_Proxy Proxy){
        this.Button = Button;
        this.Proxy = Proxy;
    }
    /**
     * Una vez se presiona un JButton, se le da un número al proxy que identifica que debe hacer.
     * Cada que se presiona algún JButton, se vuelve a pintar toda la ventana para actualizar
     * lo que haya cambiado.
     * @param e el evento a ser procesado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Proxy.actionBotton(Button);

        Component botonClick = (Component)e.getSource();
        if (SwingUtilities.getWindowAncestor(botonClick) != null) {
            SwingUtilities.getWindowAncestor(botonClick).repaint();
        }
    }
}

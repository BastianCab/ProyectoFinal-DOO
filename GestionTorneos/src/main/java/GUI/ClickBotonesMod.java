package GUI;

import Logica.ErroresCalculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class ClickBotonesMod implements ActionListener {
    Proxy proxy;
    int boton;
    JPanel panel;

    public ClickBotonesMod (int boton, Proxy proxy,JPanel Panel) {
        this.boton = boton;
        this.proxy = proxy;
        this.panel = Panel;
    }

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


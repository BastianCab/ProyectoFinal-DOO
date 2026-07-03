package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 * Clase para implementar el Action Listener, conecta el presionar algún boton con
 * una función en el Proxy
 */
public class ClickBotonesPanelPrincipal implements ActionListener {
    Proxy proxy;
    int boton;

    private JPanel panelContenedor;
    private CardLayout cardLayout;
    /**
     * El método que conecta el botón con el proxy
     * @param boton el botón específico que se presionó
     * @param proxy el proxy al cual se conecta el botón
     */
    public ClickBotonesPanelPrincipal(int boton, Proxy proxy, JPanel contenedor, CardLayout cardLayout) {
        this.boton = boton;
        this.proxy = proxy;
        this.panelContenedor = contenedor;
        this.cardLayout = cardLayout;
    }

    /**
     * Cuando se presione un botón, se le asigna un valor el cual el proxy identifica
     * y ejecuta el código correspondiente al botón
     * @param e el evento a ser procesado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (boton) {
            case (1):
                cardLayout.show(panelContenedor, "PANTALLA_CREAR");
                break;
            case (2):
                cardLayout.show(panelContenedor, "PANTALLA_MODIFICAR");
                break;
            case (3):
                cardLayout.show(panelContenedor, "PANTALLA_ESPECTAR");
                break;
        }
    }
}

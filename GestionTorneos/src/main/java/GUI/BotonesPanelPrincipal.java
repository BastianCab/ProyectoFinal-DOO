package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel con los botones del panel principal, incluye los botones:
 * Organizar un nuevo torneo, Modificar el torneo actual y Espectar el torneo actual.
 */
public class BotonesPanelPrincipal extends JPanel {
    JButton organizarNuev = new JButton("Organizar nuevo torneo");
    JButton modificarOrg = new JButton("Modificar torneo actual");
    JButton espectar = new JButton("Espectar torneo");

    /**
     * Método que inicializa los 3 botones
     * @param proxy el proxy para conectar su función a la lógica
     */
    public BotonesPanelPrincipal(Proxy proxy, JPanel contenedor, CardLayout cardLayout){
        this.setLayout(new GridLayout(3, 1,0,20));
        this.setBackground(Color.WHITE);

        organizarNuev.addActionListener(new ClickBotonesPanelPrincipal(1, proxy, contenedor, cardLayout));
        modificarOrg.addActionListener((new ClickBotonesPanelPrincipal(2, proxy, contenedor, cardLayout)));
        espectar.addActionListener(new ClickBotonesPanelPrincipal(3, proxy, contenedor, cardLayout));

        this.add(organizarNuev);
        this.add(modificarOrg);
        this.add(espectar);
    }
}

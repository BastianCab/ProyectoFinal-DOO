package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel con los botones del panel principal, incluye los botones:
 * Organizar un nuevo torneo, Modificar el torneo actual y Espectar el torneo actual.
 */
public class BotonesPanelPrincipal extends JPanel {
    JButton OrganizarNuev = new JButton("Organizar nuevo torneo");
    JButton ModificarOrg = new JButton("Modificar torneo actual");
    JButton Espectar = new JButton("Espectar torneo");

    /**
     * Método que inicializa los 3 botones
     * @param proxy el proxy para conectar su función a la lógica
     */
    public BotonesPanelPrincipal(Proxy proxy){
        this.setLayout(new GridLayout(3, 1,0,20));
        this.setBackground(Color.WHITE);

        OrganizarNuev.addActionListener(new ClickBotones(1, proxy));
        ModificarOrg.addActionListener((new ClickBotones(2, proxy)));
        Espectar.addActionListener(new ClickBotones(3, proxy));

        this.add(OrganizarNuev);
        this.add(ModificarOrg);
        this.add(Espectar);
    }
}

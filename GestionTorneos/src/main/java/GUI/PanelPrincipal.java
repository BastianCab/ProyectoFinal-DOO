package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel principal donde se puede ver el título y presionar los botones para organizar
 * un nuevo torneo, modificar el actual o espectar el torneo actual
 */
public class PanelPrincipal extends JPanel {
    Proxy proxy;
    BotonesPanelPrincipal botones;

    /**
     * Inicializa el panel principal, pinta el fondo y agrega el título y los botones
     */
    public PanelPrincipal(Proxy proxy,JPanel contenedor, CardLayout cardLayout) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        this.proxy = proxy;
        this.botones = new BotonesPanelPrincipal(proxy, contenedor, cardLayout);

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setPreferredSize(new Dimension(550, 480));
        panelMenu.setBackground(Color.WHITE);

        JLabel Titulo = new JLabel("Gestor de Torneos");
        Titulo.setFont(new Font("Arial", Font.BOLD, 40));
        Titulo.setBounds(120, 0, 400, 200);
        botones.setBounds(150,175,300,260);

        panelMenu.add(Titulo);
        panelMenu.add(botones);
        this.add(panelMenu);
    }
}

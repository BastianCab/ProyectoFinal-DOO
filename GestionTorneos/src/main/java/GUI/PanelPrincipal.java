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
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        this.proxy = proxy;
        this.botones = new BotonesPanelPrincipal(proxy, contenedor, cardLayout);

        JLabel Titulo = new JLabel("Gestor de Torneos");
        Titulo.setFont(new Font("Arial", Font.BOLD, 40));

        Titulo.setBounds(421, 86, 358, 200);
        botones.setBounds(450,300,300,260);

        this.add(Titulo);
        this.add(botones);
    }
}

package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Crea una ventana para la interfaz gráfica
 */
public class Ventana extends JFrame {
    /**
     * Inicializa la ventana
     */
    public Ventana() {
        Proxy proxyGlobal = new Proxy();

        CardLayout cardLayout = new CardLayout();
        JPanel panelContenedor = new JPanel(cardLayout);

        JPanel panelMenu = new PanelPrincipal(proxyGlobal, panelContenedor, cardLayout);

        JPanel panelCrear = new PanelCrearTorneoMaster(proxyGlobal, panelContenedor, cardLayout);

        JPanel panelModificar = new JPanel();
        panelModificar.setBackground(Color.LIGHT_GRAY);
        panelModificar.setLayout(new GridBagLayout()); // Solo para centrar el texto temporal
        panelModificar.add(new JLabel("PANTALLA: FORMULARIO PARA REGISTRAR RESULTADOS DE ENFRENTAMIENTOS"));

        JPanel panelEspectar = new JPanel();
        panelEspectar.setBackground(Color.LIGHT_GRAY);
        panelEspectar.setLayout(new GridBagLayout()); // Solo para centrar el texto temporal
        panelEspectar.add(new JLabel("PANTALLA: ESPECTAR TORNEO (BRACKET)"));

        panelContenedor.add(panelMenu, "PANTALLA_MENU");
        panelContenedor.add(panelCrear, "PANTALLA_CREAR");
        panelContenedor.add(panelModificar, "PANTALLA_MODIFICAR");
        panelContenedor.add(panelEspectar, "PANTALLA_ESPECTAR");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Gestión de torneos");
        this.setSize(1200, 820);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.add(panelContenedor);
        this.setVisible(true);
    }
}

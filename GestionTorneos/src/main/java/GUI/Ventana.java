package GUI;

import javax.swing.*;

/**
 * Crea una ventana para la interfaz gráfica
 */
public class Ventana extends JFrame {
    /**
     * Inicializa la ventana
     */
    public Ventana() {
        JPanel panel = new PanelPrincipal();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Gestión de torneos");
        this.setSize(1200, 820);
        this.setVisible(true);
        this.setResizable(false);
        this.add(panel);
    }
}

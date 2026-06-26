package GUI;

import javax.swing.*;

public class Ventana extends JFrame {
    public Ventana() {
        JFrame ventana = new JFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Gestión de torneos");
        this.setSize(1200, 820);
        this.setVisible(true);
        this.setResizable(false);
        this.add(ventana);
    }
}

package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación de Gestión de Torneos.
 * Actúa como el contenedor raíz (JFrame) de toda la interfaz gráfica.
 * Utiliza un administrador de diseño CardLayout para alternar fluidamente
 * entre los módulos principales del programa (Menú, Asistente de Creación,
 * Registro de Resultados y Visualización del Bracket) sin necesidad de abrir múltiples ventanas.
 */
public class Ventana extends JFrame {

    /**
     * Constructor de la ventana principal.
     * Instancia el Proxy global que compartirá toda la aplicación, prepara el mazo
     * principal de tarjetas (CardLayout), genera las pantallas de cada módulo
     * y configura las dimensiones y el comportamiento de cierre del programa.
     */
    public Ventana() {

        // Se crea la única instancia del Proxy que vivirá durante toda la ejecución.
        // Se inyecta en los demás paneles para que todos compartan la misma memoria y lógica.
        Proxy proxyGlobal = new Proxy();

        // Configuración del contenedor global dinámico
        CardLayout cardLayout = new CardLayout();
        JPanel panelContenedor = new JPanel(cardLayout);

        // --- INSTANCIACIÓN DE LOS MÓDULOS PRINCIPALES ---

        // 1. Pantalla del Menú Principal
        JPanel panelMenu = new PanelPrincipal(proxyGlobal, panelContenedor, cardLayout);

        // 2. Módulo de Creación de Torneos (El asistente por pasos que armamos)
        JPanel panelCrear = new PanelCrearTorneoMaster(proxyGlobal, panelContenedor, cardLayout);

        // 3. Módulo de Registro de Resultados (En construcción)
        JPanel panelModificar = new JPanel();
        panelModificar.setBackground(Color.LIGHT_GRAY);
        panelModificar.setLayout(new GridBagLayout());
        panelModificar.add(new JLabel("PANTALLA: FORMULARIO PARA REGISTRAR RESULTADOS DE ENFRENTAMIENTOS"));

        // 4. Módulo de Visualización (En construcción)
        JPanel panelEspectar = new JPanel();
        panelEspectar.setBackground(Color.LIGHT_GRAY);
        panelEspectar.setLayout(new GridBagLayout());
        panelEspectar.add(new JLabel("PANTALLA: ESPECTAR TORNEO (BRACKET)"));

        // --- REGISTRO DE LAS PANTALLAS EN EL MAZO PRINCIPAL ---
        panelContenedor.add(panelMenu, "PANTALLA_MENU");
        panelContenedor.add(panelCrear, "PANTALLA_CREAR");
        panelContenedor.add(panelModificar, "PANTALLA_MODIFICAR");
        panelContenedor.add(panelEspectar, "PANTALLA_ESPECTAR");

        // --- CONFIGURACIÓN DE LA VENTANA (JFRAME) ---
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Gestión de torneos");
        this.setSize(1024, 768);
        this.setResizable(true);
        this.setLocationRelativeTo(null);

        // Se adhiere el contenedor dinámico a la ventana y se enciende la interfaz
        this.add(panelContenedor);
        this.setVisible(true);
    }
}
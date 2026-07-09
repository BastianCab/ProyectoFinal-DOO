package GUI;

import Logica.Enfrentamiento;
import Logica.Torneo;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Representa la interfaz gráfica encargada de mostrar el estado actual del torneo.
 * En lugar de dibujar un bracket complejo, genera dinámicamente una lista de tarjetas (Card List)
 * con todos los partidos programados, detallando los competidores y la fecha asignada.
 */
public class PanelEspectarTorneo extends JPanel {

    private Proxy proxy;
    private JPanel panelContenedor;
    private CardLayout cardLayout;

    private JPanel panelPartidos;
    private JLabel lblTituloTorneo;

    /**
     * Constructor del panel de visualización del torneo.
     * Inicializa la estructura visual dividida en cabecera (título y controles),
     * centro (lista desplazable de partidos) y pie de página (navegación).
     * @param proxy           El intermediario lógico para consultar el estado del torneo en memoria.
     * @param panelContenedor El contenedor principal de la aplicación (JFrame).
     * @param cardLayout      El administrador de diseño para permitir el retorno al menú principal.
     */
    public PanelEspectarTorneo(Proxy proxy, JPanel panelContenedor, CardLayout cardLayout) {
        this.proxy = proxy;
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;

        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);

        // --- CABECERA (TÍTULO Y BOTÓN DE ACTUALIZAR) ---
        JPanel panelHeader = new JPanel(new GridLayout(2, 1));
        panelHeader.setBackground(Color.WHITE);

        lblTituloTorneo = new JLabel("Espectar Torneo", SwingConstants.CENTER);
        lblTituloTorneo.setFont(new Font("Arial", Font.BOLD, 24));
        panelHeader.add(lblTituloTorneo);

        JPanel panelBtnCenter = new JPanel();
        panelBtnCenter.setBackground(Color.WHITE);
        JButton btnActualizar = new JButton("Actualizar / Cargar Datos");
        btnActualizar.setPreferredSize(new Dimension(200, 35));

        // Al hacer clic, le pedimos al sistema que lea los partidos
        btnActualizar.addActionListener(e -> cargarDatos());

        panelBtnCenter.add(btnActualizar);
        panelHeader.add(panelBtnCenter);

        this.add(panelHeader, BorderLayout.NORTH);

        // --- CENTRO (LISTA DE PARTIDOS) ---
        panelPartidos = new JPanel();
        panelPartidos.setLayout(new BoxLayout(panelPartidos, BoxLayout.Y_AXIS));
        panelPartidos.setBackground(Color.WHITE);

        // Un JScrollPane para poder bajar si hay muchos partidos
        JScrollPane scroll = new JScrollPane(panelPartidos);
        scroll.getVerticalScrollBar().setUnitIncrement(16); // Hace que el scroll sea más rápido
        this.add(scroll, BorderLayout.CENTER);

        // --- PIE DE PÁGINA (BOTÓN VOLVER) ---
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFooter.setBackground(Color.WHITE);
        JButton btnVolver = new JButton("<- Volver al Menú");
        btnVolver.setPreferredSize(new Dimension(150, 40));

        // Regresa a la pantalla principal
        btnVolver.addActionListener(e -> cardLayout.show(panelContenedor, "PANTALLA_MENU"));

        panelFooter.add(btnVolver);
        this.add(panelFooter, BorderLayout.SOUTH);
    }

    /**
     * Consulta el torneo almacenado en el Proxy y dibuja dinámicamente en pantalla
     * una tarjeta por cada enfrentamiento generado.
     * Formatea la fecha (LocalDateTime) para mostrarla de manera amigable al usuario.
     * Si no hay torneos o partidos, actualiza los textos para informar al espectador.
     */
    public void cargarDatos() {
        Torneo torneo = proxy.getTorneo();
        panelPartidos.removeAll(); // Limpiamos la pantalla antes de dibujar

        if (torneo == null) {
            lblTituloTorneo.setText("No hay ningún torneo activo en este momento.");
        } else {
            lblTituloTorneo.setText("Torneo: " + torneo.getNombre() + " (" + torneo.getDisciplina() + ")");
            List<Enfrentamiento> partidos = torneo.getEnfrentamientos();

            if (partidos == null || partidos.isEmpty()) {
                panelPartidos.add(new JLabel("El calendario de partidos aún no ha sido generado."));
            } else {
                // Formateador para que la fecha se vea bonita
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

                for (int i = 0; i < partidos.size(); i++) {
                    Enfrentamiento p = partidos.get(i);

                    // Creamos un mini-panel (tarjeta) para cada partido
                    JPanel carta = new JPanel(new GridLayout(2, 1));
                    carta.setBorder(BorderFactory.createTitledBorder("Partido " + (i + 1)));
                    carta.setBackground(new Color(245, 245, 245));
                    carta.setMaximumSize(new Dimension(800, 70));

                    String txtPartido = p.getCompetidor1().getNombre() + " VS " + p.getCompetidor2().getNombre();
                    JLabel lblCruce = new JLabel(txtPartido, SwingConstants.CENTER);
                    lblCruce.setFont(new Font("Arial", Font.BOLD, 14));

                    String txtFecha = (p.getFechaHora() != null) ? p.getFechaHora().format(formatter) : "Fecha por definir";
                    JLabel lblDetalle = new JLabel("Fecha: " + txtFecha, SwingConstants.CENTER);

                    carta.add(lblCruce);
                    carta.add(lblDetalle);

                    panelPartidos.add(carta);
                    panelPartidos.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
        }

        panelPartidos.revalidate();
        panelPartidos.repaint();
    }
}
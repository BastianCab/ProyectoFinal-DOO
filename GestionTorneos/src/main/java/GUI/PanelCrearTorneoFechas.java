package GUI;

import Logica.Enfrentamiento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Representa el tercer paso visual de la creación de torneos.
 * En este panel, el organizador visualiza los enfrentamientos generados automáticamente
 * y asigna una fecha y hora específica para cada partido utilizando selectores nativos.
 */
public class PanelCrearTorneoFechas extends JPanel{

    private PanelCrearTorneoMaster panelMaster;
    private Proxy proxy;

    private JPanel panelListaPartidos;

    private List<JSpinner> selectoresDeFecha;

    private JButton btnVolver;
    private JButton btnFinalizar;

    /**
     * Constructor de la interfaz de asignación de fechas.
     * Inicializa la ventana con un contenedor dinámico (Scroll) para soportar
     * una cantidad variable de enfrentamientos sin deformar la pantalla.
     * @param master El controlador principal que permite navegar entre los pasos de la creacio de torneo.
     * @param proxy  El intermediario lógico para guardar las fechas en la memoria del sistema.
     */
    public PanelCrearTorneoFechas(PanelCrearTorneoMaster master, Proxy proxy) {
        this.panelMaster = master;
        this.proxy = proxy;
        this.selectoresDeFecha = new ArrayList<>();

        // Se le pide al proxy que prepare los enfrentamientos en memoria
        this.proxy.generarEnfrentamientos();

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setPreferredSize(new Dimension(550, 480));
        panelFormulario.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Paso 3: Calendario de Enfrentamientos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(0, 10, 550, 40);
        panelFormulario.add(lblTitulo);

        // Panel interno que apila los partidos verticalmente
        panelListaPartidos = new JPanel();
        panelListaPartidos.setLayout(new BoxLayout(panelListaPartidos, BoxLayout.Y_AXIS));
        panelListaPartidos.setBackground(Color.WHITE);

        // Scroll para poder navegar si hay demasiados partidos
        JScrollPane scrollPartidos = new JScrollPane(panelListaPartidos);
        scrollPartidos.setBounds(20, 70, 510, 320);
        scrollPartidos.getVerticalScrollBar().setUnitIncrement(16);
        panelFormulario.add(scrollPartidos);

        btnVolver = new JButton("<- Volver");
        btnVolver.setBounds(20, 410, 130, 40);
        panelFormulario.add(btnVolver);

        btnFinalizar = new JButton("Crear Torneo");
        btnFinalizar.setBounds(390, 410, 140, 40);
        panelFormulario.add(btnFinalizar);

        ClickBotonesPanelCrearTorneoFechas controlador = new ClickBotonesPanelCrearTorneoFechas();
        btnVolver.addActionListener(controlador);
        btnFinalizar.addActionListener(controlador);

        this.add(panelFormulario);
    }

    /**
     * Dibuja dinámicamente en pantalla una fila por cada partido generado por el sistema.
     * Cada fila incluye los nombres de los competidores y un selector de fecha/hora.
     * @param partidosGenerados La lista de enfrentamientos ya calculados por el patrón Strategy.
     */
    public void cargarEnfrentamientos(List<Enfrentamiento> partidosGenerados) {
        // Se limpia el panel para evitar partidos duplicados si el usuario retrocedió de pantalla
        panelListaPartidos.removeAll();
        selectoresDeFecha.clear();

        for (Enfrentamiento partido : partidosGenerados) {

            // Contenedor horizontal para un solo partido
            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            fila.setBackground(Color.WHITE);
            fila.setMaximumSize(new Dimension(480, 45)); // Evita que Swing deforme las filas

            // Etiqueta visual: Competidor 1 VS Competidor 2
            JLabel lblPartido = new JLabel(partido.getCompetidor1().getNombre() + " VS " +
                    partido.getCompetidor2().getNombre());
            lblPartido.setFont(new Font("Arial", Font.PLAIN, 14));
            lblPartido.setPreferredSize(new Dimension(260, 25));

            // Selector nativo de Java para fecha y hora
            SpinnerDateModel modeloFecha = new SpinnerDateModel();
            JSpinner selectorFecha = new JSpinner(modeloFecha);

            JSpinner.DateEditor editor = new JSpinner.DateEditor(selectorFecha, "dd/MM/yyyy HH:mm");
            selectorFecha.setEditor(editor);
            selectorFecha.setPreferredSize(new Dimension(140, 25));

            // Se almacena el selector para extraer su valor al finalizar
            selectoresDeFecha.add(selectorFecha);

            fila.add(lblPartido);
            fila.add(selectorFecha);
            panelListaPartidos.add(fila);
        }

        // Fuerza la actualización visual de la interfaz
        panelListaPartidos.revalidate();
        panelListaPartidos.repaint();
    }

    /**
     * Clase interna encargada de gestionar los eventos de los botones en este panel.
     * Maneja la navegación, la conversión de tipos de fecha y las validaciones de seguridad.
     */
    private class ClickBotonesPanelCrearTorneoFechas implements ActionListener {

        /**
         * Detecta si el usuario desea volver al paso anterior o finalizar el torneo.
         * En caso de finalizar, valida que ninguna fecha esté en el pasado y convierte
         * los datos al formato moderno (LocalDateTime) antes de enviarlos al Proxy.
         * @param e El evento de clic detectado por los botones.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnVolver) {
                panelMaster.volverAPaso2();
            }

            else if (e.getSource() == btnFinalizar) {
                List<LocalDateTime> fechasModernas = new ArrayList<>();

                // Marca de tiempo actual para la validación de seguridad
                LocalDateTime ahora = LocalDateTime.now();

                // Extracción y conversión de las fechas de todos los selectores
                for (int i = 0; i < selectoresDeFecha.size(); i++) {
                    JSpinner selector = selectoresDeFecha.get(i);
                    Date fechaAntigua = (Date) selector.getValue();

                    // Conversión de java.util.Date a java.time.LocalDateTime
                    LocalDateTime fechaConvertida = fechaAntigua.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();

                    // Validación: Freno preventivo si el organizador programa un partido en el pasado
                    if (fechaConvertida.isBefore(ahora)) {
                        JOptionPane.showMessageDialog(null,
                                "La fecha del Partido " + (i + 1) + " está en el pasado.\n" +
                                        "Por favor, programa todos los encuentros a futuro.",
                                "Fecha Inválida", JOptionPane.ERROR_MESSAGE);

                        return; // Corta la ejecución; no se guarda nada
                    }

                    fechasModernas.add(fechaConvertida);
                }

                // Todas las fechas son válidas, se envían al Proxy
                proxy.guardarFechasEnfrentamientos(fechasModernas);

                JOptionPane.showMessageDialog(null,
                        "¡El torneo y su calendario de fechas han sido creados con éxito!",
                        "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);

                // Vuelve al menú
                panelMaster.irApaso4();
            }
        }
    }
}
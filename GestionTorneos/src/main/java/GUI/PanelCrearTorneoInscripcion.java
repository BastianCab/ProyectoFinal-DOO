package GUI;

import Logica.DatoInvalidoException;
import Logica.TipoParticipante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Representa el segundo paso visual de la creación de torneos.
 * En este panel, el usuario inscribe a los competidores (ya sean equipos o jugadores individuales),
 * capturando sus datos de contacto y mostrándolos en una lista visual.
 */
public class PanelCrearTorneoInscripcion extends JPanel {

    private PanelCrearTorneoMaster panelMaster;
    private Proxy proxy;

    private JLabel lblNombre;
    private JTextField txtNombreParticipante;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JLabel lblNumeroTelefonico;
    private JTextField txtNumeroTelefonico;
    private JButton btnAgregar;
    private JButton btnVolver;
    private JButton btnSiguiente;

    private JLabel lblLista;
    private JList<String> listaVisualCompetidores;
    private DefaultListModel<String> modeloLista;

    // Almacena la modalidad seleccionada en el Paso 1 para uso del Proxy
    private TipoParticipante tipoActual;

    /**
     * Constructor de la interfaz de inscripciones.
     * Prepara el formulario de ingreso y la lista de visualización.
     * @param master El controlador principal para la navegación entre ventanas.
     * @param proxy  El intermediario lógico encargado de registrar a los participantes.
     */
    public PanelCrearTorneoInscripcion(PanelCrearTorneoMaster master, Proxy proxy) {
        this.panelMaster = master;
        this.proxy = proxy;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setPreferredSize(new Dimension(550, 480));
        panelFormulario.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Paso 2: Inscripción de Participantes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(20, 10, 550, 40);
        panelFormulario.add(lblTitulo);

        // Los textos de los JLabel se inician vacíos porque se llenan dinámicamente
        lblNombre = new JLabel("");
        lblNombre.setBounds(20, 70, 180, 30);
        panelFormulario.add(lblNombre);

        txtNombreParticipante = new JTextField();
        txtNombreParticipante.setBounds(200, 70, 200, 30);
        panelFormulario.add(txtNombreParticipante);

        lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(20, 110, 180, 30);
        panelFormulario.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(200, 110, 200, 30);
        panelFormulario.add(txtCorreo);

        lblNumeroTelefonico = new JLabel("Número de teléfono:");
        lblNumeroTelefonico.setBounds(20, 150, 180, 30);
        panelFormulario.add(lblNumeroTelefonico);

        txtNumeroTelefonico = new JTextField();
        txtNumeroTelefonico.setBounds(200, 150, 200, 30);
        panelFormulario.add(txtNumeroTelefonico);

        btnAgregar = new JButton("");
        btnAgregar.setBounds(200, 190, 200, 35); // Centrado entre los dos textfields
        panelFormulario.add(btnAgregar);

        lblLista = new JLabel("");
        lblLista.setBounds(20, 240, 200, 20);
        panelFormulario.add(lblLista);

        // Lista visual con barra de desplazamiento para los inscritos
        modeloLista = new DefaultListModel<>();
        listaVisualCompetidores = new JList<>(modeloLista);
        JScrollPane scrollLista = new JScrollPane(listaVisualCompetidores);
        scrollLista.setBounds(20, 270, 520, 130);
        panelFormulario.add(scrollLista);

        btnVolver = new JButton("<- Volver");
        btnVolver.setBounds(20, 420, 130, 40);
        panelFormulario.add(btnVolver);

        btnSiguiente = new JButton("Siguiente ->");
        btnSiguiente.setBounds(410, 420, 130, 40);
        panelFormulario.add(btnSiguiente);

        ClickBotonesPanelCrearTorneoInscripcion controlador = new ClickBotonesPanelCrearTorneoInscripcion();
        btnAgregar.addActionListener(controlador);
        btnVolver.addActionListener(controlador);
        btnSiguiente.addActionListener(controlador);

        this.add(panelFormulario);
    }

    /**
     * Adapta los textos de las etiquetas y botones de la interfaz dependiendo de
     * la modalidad elegida por el usuario en el paso anterior.
     * @param modalidad El tipo de participante (ej. EQUIPO o INDIVIDUAL).
     */
    public void personalizarTextos(TipoParticipante modalidad) {

        // Guardamos la modalidad para cuando el Proxy la necesite al hacer clic en Inscribir
        this.tipoActual = modalidad;

        if (modalidad == TipoParticipante.EQUIPO) {
            lblNombre.setText("Nombre del Equipo:");
            btnAgregar.setText("Inscribir Equipo");
            lblLista.setText("Equipos Inscritos:");
        } else {
            lblNombre.setText("Nombre del Competidor:");
            btnAgregar.setText("Inscribir Competidor");
            lblLista.setText("Competidores Inscritos:");
        }

        // Fuerza a Java Swing a redibujar el panel con los nuevos textos
        this.revalidate();
        this.repaint();
    }

    /**
     * Limpia los campos de texto y vacía la lista de competidores visuales.
     */
    public void limpiarCampos() {
        txtNombreParticipante.setText("");
        txtCorreo.setText("");
        txtNumeroTelefonico.setText("");
        modeloLista.clear();
    }

    /**
     * Clase interna encargada de gestionar los eventos de los botones en este panel.
     * Centraliza la validación de ingreso y la navegación.
     */
    private class ClickBotonesPanelCrearTorneoInscripcion implements ActionListener {

        /**
         * Maneja las acciones dependiendo del botón que haya sido presionado:
         * - Agregar: Valida los campos vacíos, inscribe al participante vía Proxy y actualiza la lista.
         * - Volver: Retorna al paso 1 de configuración básica.
         * - Siguiente: Verifica que exista un mínimo de competidores antes de avanzar al paso 3.
         *  @param e El evento de clic detectado.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // Lógica para registrar a un nuevo participante
            if (e.getSource() == btnAgregar) {
                String nombre = txtNombreParticipante.getText().trim();
                String correo = txtCorreo.getText().trim();
                String telefono = txtNumeroTelefonico.getText().trim();

                // Validación de seguridad para evitar datos en blanco
                if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor completa el nombre, correo y teléfono.",
                            "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Actualización de la interfaz y envío de datos al backend
                modeloLista.addElement(nombre + " | " + correo + " | " + telefono);
                try {
                    proxy.inscribirParticipante(nombre, correo, telefono, tipoActual);
                } catch (DatoInvalidoException m) {
                    System.out.println("[PROXY] El participante no puede ser nulo");
                }

                // Limpieza de campos para el siguiente ingreso
                txtNombreParticipante.setText("");
                txtCorreo.setText("");
                txtNumeroTelefonico.setText("");
                txtNombreParticipante.requestFocus();
            }

            // Lógica de navegación: Regresar
            else if (e.getSource() == btnVolver) {
                panelMaster.volverAPaso1();
            }

            // Lógica de navegación: Avanzar
            else if (e.getSource() == btnSiguiente) {

                // Validación estructural: Un torneo requiere al menos 2 competidores
                if (modeloLista.getSize() < 2) {
                    JOptionPane.showMessageDialog(null,
                            "Debes inscribir al menos a 2 competidores.",
                            "Faltan inscritos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                panelMaster.irAPaso3();
            }
        }
    }
}
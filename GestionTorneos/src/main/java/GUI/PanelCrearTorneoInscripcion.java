package GUI;

import Logica.TipoParticipante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private TipoParticipante tipoActual;

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

    public void personalizarTextos(TipoParticipante modalidad) {

        // Guardamos la modalidad para cuando el Proxy la necesite al hacer clic en Inscribir
        this.tipoActual = modalidad;

        // Asumiendo que tu Enum tiene un valor llamado EQUIPO (ajústalo a tu código)
        if (modalidad == TipoParticipante.EQUIPO) {
            lblNombre.setText("Nombre del Equipo:");
            btnAgregar.setText("Inscribir Equipo");
            lblLista.setText("Equipos Inscritos:");
        } else {
            // Si es jugador individual / competidor
            lblNombre.setText("Nombre del Competidor:");
            btnAgregar.setText("Inscribir Competidor");
            lblLista.setText("Competidores Inscritos:");
        }

        // Forzamos a Java a redibujar el panel con los nuevos textos por si acaso
        this.revalidate();
        this.repaint();
    }

    private class ClickBotonesPanelCrearTorneoInscripcion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnAgregar) {
                String nombre = txtNombreParticipante.getText().trim();
                String correo = txtCorreo.getText().trim();
                String telefono = txtNumeroTelefonico.getText().trim();

                // Validamos que los tres campos tengan información
                if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor completa el nombre, correo y teléfono.",
                            "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                modeloLista.addElement(nombre + " | " + correo + " | " + telefono);

                proxy.inscribirParticipante(nombre, correo, telefono, tipoActual);

                txtNombreParticipante.setText("");
                txtCorreo.setText("");
                txtNumeroTelefonico.setText("");
                txtNombreParticipante.requestFocus();
            }

            else if (e.getSource() == btnVolver) {
                panelMaster.volverAPaso1();
            }

            else if (e.getSource() == btnSiguiente) {
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

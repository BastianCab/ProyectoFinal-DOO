package GUI;


import Logica.TipoTorneoEnum;
import Logica.TipoParticipante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearTorneoConfiguracionBasica extends JPanel {
    private PanelCrearTorneoMaster panelMaster;
    private Proxy proxy;

    private JTextField txtOrganizador;
    private JTextField txtNombreTorneo;
    private JTextField txtDisciplina;

    private JComboBox<TipoTorneoEnum> comboFormatos;
    private JTextArea txtDescripcionFormato;
    private JComboBox<TipoParticipante> comboTipoParticipante;

    public PanelCrearTorneoConfiguracionBasica(PanelCrearTorneoMaster master, Proxy proxy) {
        this.panelMaster = master;
        this.proxy = proxy;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setPreferredSize(new Dimension(550, 480));
        panelFormulario.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Paso 1: Configuración Básica");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(20, 10, 550, 40);
        panelFormulario.add(lblTitulo);

        JLabel lblOrganizador = new JLabel("Nombre del Organizador:");
        lblOrganizador.setBounds(20, 70, 200, 30);
        txtOrganizador = new JTextField();
        txtOrganizador.setBounds(220, 70, 300, 30);
        panelFormulario.add(lblOrganizador);
        panelFormulario.add(txtOrganizador);

        JLabel lblTorneo = new JLabel("Nombre del Torneo:");
        lblTorneo.setBounds(20, 120, 200, 30);
        txtNombreTorneo = new JTextField();
        txtNombreTorneo.setBounds(220, 120, 300, 30);
        panelFormulario.add(lblTorneo);
        panelFormulario.add(txtNombreTorneo);

        JLabel lblDisciplina = new JLabel("Disciplina:");
        lblDisciplina.setBounds(20, 170, 200, 30);
        txtDisciplina = new JTextField();
        txtDisciplina.setBounds(220, 170, 300, 30);
        panelFormulario.add(lblDisciplina);
        panelFormulario.add(txtDisciplina);

        JLabel lblTipoPart = new JLabel("Modalidad:");
        lblTipoPart.setBounds(20, 220, 200, 30);
        comboTipoParticipante = new JComboBox<>(TipoParticipante.values());
        comboTipoParticipante.setBounds(220, 220, 300, 30);
        panelFormulario.add(lblTipoPart);
        panelFormulario.add(comboTipoParticipante);

        JLabel lblFormato = new JLabel("Formato del Torneo:");
        lblFormato.setBounds(20, 270, 200, 30);

        comboFormatos = new JComboBox<>(TipoTorneoEnum.values());
        comboFormatos.setBounds(220, 270, 300, 30);
        panelFormulario.add(lblFormato);
        panelFormulario.add(comboFormatos);

        txtDescripcionFormato = new JTextArea();
        txtDescripcionFormato.setBounds(220, 310, 300, 60);
        txtDescripcionFormato.setWrapStyleWord(true);
        txtDescripcionFormato.setLineWrap(true);
        txtDescripcionFormato.setEditable(false); // Evita que el usuario escriba encima
        txtDescripcionFormato.setFocusable(false);
        txtDescripcionFormato.setBackground(new Color(245, 245, 245)); // Un gris muy clarito
        txtDescripcionFormato.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelFormulario.add(txtDescripcionFormato);

        TipoTorneoEnum formatoInicial = (TipoTorneoEnum) comboFormatos.getSelectedItem();
        txtDescripcionFormato.setText(formatoInicial.getDescripcion());

        comboFormatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoTorneoEnum formatoSeleccionado = (TipoTorneoEnum) comboFormatos.getSelectedItem();
                txtDescripcionFormato.setText(formatoSeleccionado.getDescripcion());
            }
        });

        JButton btnSiguiente = new JButton("Siguiente ->");
        btnSiguiente.setBounds(370, 390, 150, 40);
        panelFormulario.add(btnSiguiente);

        btnSiguiente.addActionListener(new ClickBotonesPanelCrearTorneoConfiguracionBasica());

        this.add(panelFormulario);
    }

    /**
     * Maneja los clics de la pantalla de Configuración Básica.
     */
    private class ClickBotonesPanelCrearTorneoConfiguracionBasica implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String organizador = txtOrganizador.getText().trim();
            String torneo = txtNombreTorneo.getText().trim();
            String disciplina = txtDisciplina.getText().trim();
            TipoParticipante tipoParticipante = (TipoParticipante) comboTipoParticipante.getSelectedItem();
            TipoTorneoEnum formato = (TipoTorneoEnum) comboFormatos.getSelectedItem();

            if (organizador.isEmpty() || torneo.isEmpty() || disciplina.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, completa todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            proxy.crearTorneo(organizador, torneo, disciplina, tipoParticipante, formato);
            panelMaster.irAPaso2(tipoParticipante);
        }
    }
}
package GUI;


import Logica.TipoTorneoEnum;
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

    public PanelCrearTorneoConfiguracionBasica(PanelCrearTorneoMaster master, Proxy proxy) {
        this.panelMaster = master;
        this.proxy = proxy;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setPreferredSize(new Dimension(550, 450));
        panelFormulario.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Paso 1: Configuración Básica");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        //lblTitulo.setBounds(50, 30, 400, 40);
        lblTitulo.setBounds(20, 10, 550, 40);
        panelFormulario.add(lblTitulo);

        JLabel lblOrganizador = new JLabel("Nombre del Organizador:");
        //lblOrganizador.setBounds(50, 100, 200, 30);
        lblOrganizador.setBounds(20, 80, 200, 30);
        txtOrganizador = new JTextField();
        //txtOrganizador.setBounds(250, 100, 250, 30);
        txtOrganizador.setBounds(220, 80, 300, 30);
        panelFormulario.add(lblOrganizador);
        panelFormulario.add(txtOrganizador);

        JLabel lblTorneo = new JLabel("Nombre del Torneo:");
        //lblTorneo.setBounds(50, 150, 200, 30);
        lblTorneo.setBounds(20, 130, 200, 30);
        txtNombreTorneo = new JTextField();
        //txtNombreTorneo.setBounds(250, 150, 250, 30);
        txtNombreTorneo.setBounds(220, 130, 300, 30);
        panelFormulario.add(lblTorneo);
        panelFormulario.add(txtNombreTorneo);

        JLabel lblDisciplina = new JLabel("Disciplina:");
        //lblDisciplina.setBounds(50, 200, 200, 30);
        lblDisciplina.setBounds(20, 180, 200, 30);
        txtDisciplina = new JTextField();
        //txtDisciplina.setBounds(250, 200, 250, 30);
        txtDisciplina.setBounds(220, 180, 300, 30);
        panelFormulario.add(lblDisciplina);
        panelFormulario.add(txtDisciplina);

        JLabel lblFormato = new JLabel("Formato del Torneo:");
        //lblFormato.setBounds(50, 250, 200, 30);
        lblFormato.setBounds(20, 230, 200, 30);

        comboFormatos = new JComboBox<>(TipoTorneoEnum.values());
        //comboFormatos.setBounds(250, 250, 250, 30);
        comboFormatos.setBounds(220, 230, 300, 30);
        panelFormulario.add(lblFormato);
        panelFormulario.add(comboFormatos);

        txtDescripcionFormato = new JTextArea();
        txtDescripcionFormato.setBounds(220, 270, 300, 60);
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
        //btnSiguiente.setBounds(400, 150, 150, 40);
        btnSiguiente.setBounds(370, 360, 150, 40);
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
            TipoTorneoEnum formato = (TipoTorneoEnum) comboFormatos.getSelectedItem();

            if (organizador.isEmpty() || torneo.isEmpty() || disciplina.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, completa todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            proxy.crearTorneo(organizador, torneo, disciplina, "liga"); //(organizador, torneo, disciplina, formato);
            panelMaster.irAPaso2();
        }
    }
}

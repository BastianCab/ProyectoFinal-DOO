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

    public PanelCrearTorneoConfiguracionBasica(PanelCrearTorneoMaster master, Proxy proxy) {
        this.panelMaster = master;
        this.proxy = proxy;

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Paso 1: Configuración Básica");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(50, 30, 400, 40);
        this.add(lblTitulo);

        JLabel lblOrganizador = new JLabel("Nombre del Organizador:");
        lblOrganizador.setBounds(50, 100, 200, 30);
        txtOrganizador = new JTextField();
        txtOrganizador.setBounds(250, 100, 250, 30);
        this.add(lblOrganizador);
        this.add(txtOrganizador);

        JLabel lblTorneo = new JLabel("Nombre del Torneo:");
        lblTorneo.setBounds(50, 150, 200, 30);
        txtNombreTorneo = new JTextField();
        txtNombreTorneo.setBounds(250, 150, 250, 30);
        this.add(lblTorneo);
        this.add(txtNombreTorneo);

        JLabel lblDisciplina = new JLabel("Disciplina:");
        lblDisciplina.setBounds(50, 200, 200, 30);
        txtDisciplina = new JTextField();
        txtDisciplina.setBounds(250, 200, 250, 30);
        this.add(lblDisciplina);
        this.add(txtDisciplina);

        JLabel lblFormato = new JLabel("Formato del Torneo:");
        lblFormato.setBounds(50, 250, 200, 30);

        comboFormatos = new JComboBox<>(TipoTorneoEnum.values());
        comboFormatos.setBounds(250, 250, 250, 30);
        this.add(lblFormato);
        this.add(comboFormatos);

        // --- 3. BOTÓN DE ACCIÓN ---
        JButton btnSiguiente = new JButton("Siguiente ->");
        btnSiguiente.setBounds(350, 330, 150, 40);
        this.add(btnSiguiente);

        // Conectamos el botón con su controlador
        btnSiguiente.addActionListener(new ClickBotonesPanelCrearTorneoConfiguracionBasica());
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

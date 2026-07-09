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

public class PanelCrearTorneoFechas extends JPanel{
    private PanelCrearTorneoMaster panelMaster;
    private Proxy proxy;

    private JPanel panelListaPartidos;

    private List<JSpinner> selectoresDeFecha;

    private JButton btnVolver;
    private JButton btnFinalizar;

    public PanelCrearTorneoFechas(PanelCrearTorneoMaster master, Proxy proxy) {
        this.panelMaster = master;
        this.proxy = proxy;
        this.selectoresDeFecha = new ArrayList<>();

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

        panelListaPartidos = new JPanel();
        panelListaPartidos.setLayout(new BoxLayout(panelListaPartidos, BoxLayout.Y_AXIS));
        panelListaPartidos.setBackground(Color.WHITE);

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

    public void cargarEnfrentamientos(List<Enfrentamiento> partidosGenerados) {
        panelListaPartidos.removeAll();
        selectoresDeFecha.clear();

        for (Enfrentamiento partido : partidosGenerados) {

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            fila.setBackground(Color.WHITE);
            fila.setMaximumSize(new Dimension(480, 45)); // Evita que Swing deforme las filas


            JLabel lblPartido = new JLabel(partido.getCompetidor1().getNombre() + " VS " +
                    partido.getCompetidor2().getNombre());
            lblPartido.setFont(new Font("Arial", Font.PLAIN, 14));
            lblPartido.setPreferredSize(new Dimension(260, 25));


            SpinnerDateModel modeloFecha = new SpinnerDateModel();
            JSpinner selectorFecha = new JSpinner(modeloFecha);


            JSpinner.DateEditor editor = new JSpinner.DateEditor(selectorFecha, "dd/MM/yyyy HH:mm");
            selectorFecha.setEditor(editor);
            selectorFecha.setPreferredSize(new Dimension(140, 25));


            selectoresDeFecha.add(selectorFecha);

            fila.add(lblPartido);
            fila.add(selectorFecha);
            panelListaPartidos.add(fila);
        }

        panelListaPartidos.revalidate();
        panelListaPartidos.repaint();
    }

    private class ClickBotonesPanelCrearTorneoFechas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnVolver) {
                panelMaster.volverAPaso2();
            }

            else if (e.getSource() == btnFinalizar) {
                List<LocalDateTime> fechasModernas = new ArrayList<>();

                LocalDateTime ahora = LocalDateTime.now();

                for (int i = 0; i < selectoresDeFecha.size(); i++) {
                    JSpinner selector = selectoresDeFecha.get(i);
                    Date fechaAntigua = (Date) selector.getValue();

                    LocalDateTime fechaConvertida = fechaAntigua.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();

                    if (fechaConvertida.isBefore(ahora)) {
                        JOptionPane.showMessageDialog(null,
                                "La fecha del Partido " + (i + 1) + " está en el pasado.\n" +
                                        "Por favor, programa todos los encuentros a futuro.",
                                "Fecha Inválida", JOptionPane.ERROR_MESSAGE);

                        return;
                    }

                    fechasModernas.add(fechaConvertida);
                }

                proxy.guardarFechasEnfrentamientos(fechasModernas);

                JOptionPane.showMessageDialog(null,
                        "¡El torneo y su calendario de fechas han sido creados con éxito!",
                        "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);

                panelMaster.irApaso4();
            }
        }
    }
}

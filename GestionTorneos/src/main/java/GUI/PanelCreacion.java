package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelCreacion extends JPanel {
    String nombreOrganizador = null;
    String nombre = null;
    String disciplina = null;
    int tipoTorneo = 0;

    JButton ButOrganizador = new JButton();
    JButton ButNombre = new JButton();
    JButton ButDisciplina = new JButton();
    JButton ButTiTorneo = new JButton();
    JButton ButIntegrante = new JButton();
    JButton ButFinalizar = new JButton();

    JLabel a = new JLabel();
    JLabel b = new JLabel();
    JLabel c = new JLabel();
    JLabel d = new JLabel();
    JLabel e = new JLabel();
    JLabel f = new JLabel();


    PH_Proxy Proxy = new PH_Proxy(this);
    public PanelCreacion() {
        this.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

        this.setBackground(Color.BLUE);

        ButOrganizador.addActionListener(new BotonesPanelCreacion(1,Proxy));
        ButNombre.addActionListener(new BotonesPanelCreacion(2,Proxy));
        ButDisciplina.addActionListener(new BotonesPanelCreacion(3,Proxy));
        ButTiTorneo.addActionListener(new BotonesPanelCreacion(4,Proxy));
        ButIntegrante.addActionListener(new BotonesPanelCreacion(5,Proxy));
        ButFinalizar.addActionListener(new BotonesPanelCreacion(6,Proxy));

        ButOrganizador.add(new JLabel("Nombre Organizador"));
        ButNombre.add(new JLabel("Nombre"));
        ButDisciplina.add(new JLabel("Disciplina"));
        ButTiTorneo.add(new JLabel("Tipo"));
        ButIntegrante.add(new JLabel("Agregar Integrante"));
        ButFinalizar.add(new JLabel("Finalizar"));

        this.add(ButOrganizador);
        this.add(ButNombre);
        this.add(ButDisciplina);
        this.add(ButTiTorneo);
        this.add(ButIntegrante);
        this.add(ButFinalizar);

        this.add(a);
        this.add(b);
        this.add(c);
        this.add(d);
        this.add(e);
        this.add(f);

        this.setLayout(new GridLayout(2,6));
    }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            a.setText(Proxy.getNombreOrganizador());
            b.setText(Proxy.getNombre());
            c.setText(Proxy.getDisciplina());
            d.setText(String.valueOf(Proxy.getTipoTorneo()));
            e.setText(Proxy.getDisciplina());
            f.setText(Proxy.getDisciplina());

            a.setBackground(Color.GRAY);
            b.setBackground(Color.BLUE);
            c.setBackground(Color.GRAY);
            d.setBackground(Color.GRAY);
            e.setBackground(Color.GRAY);
            f.setBackground(Color.GRAY);
        }
}

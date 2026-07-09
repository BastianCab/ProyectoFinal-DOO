package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Panel donde se decide el ganador de cada pelea
 * Contiene un panel cual indica el estado del torneo
 * 2 bottones los cuales permiten eleguir quien pasa a la siguiente ronda
 * y un botton para volver
 */
public class PanelMod extends JPanel {
    Proxy proxy;
    JButton jugador1 = new JButton();
    JButton jugador2 =  new JButton();
    JButton Salir = new JButton();
    JLabel Instruccion = new JLabel();

    private JPanel panelContenedor;
    private CardLayout cardLayout;
    public PanelMod(Proxy proxy,JPanel panelContenedor,CardLayout cardLayout){
        this.proxy = proxy;
        jugador1.addActionListener(new ClickBotonesMod(1,proxy,this));
        jugador2.addActionListener(new ClickBotonesMod(2,proxy,this));
        Salir.addActionListener(e -> cardLayout.show(panelContenedor, "PANTALLA_MENU"));
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        Instruccion.setFont(new Font("Arial",Font.BOLD,20));
        jugador1.setFont(new Font("Arial",Font.BOLD,20));
        jugador2.setFont(new Font("Arial",Font.BOLD,20));
        Salir.setFont(new Font("Arial",Font.BOLD,20));
        Instruccion.setBackground(Color.WHITE);
        Salir.setText("Salir a menu");

        Instruccion.setPreferredSize(new Dimension(600,150));
        jugador1.setPreferredSize(new Dimension(200,400));
        jugador2.setPreferredSize(new Dimension(200,400));
        Salir.setPreferredSize(new Dimension(600,150));

        this.setLayout(new BorderLayout(50,50));
        this.add(Instruccion,BorderLayout.NORTH);
        this.add(jugador1,BorderLayout.EAST);
        this.add(jugador2,BorderLayout.WEST);
        this.add(Salir,BorderLayout.SOUTH);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (proxy.getTorneo().getEstrategia().getEnd()){
            Instruccion.setText("Fin el torneo");
            jugador1.addActionListener(null);
            jugador2.addActionListener(null);
            jugador1.setText(" ");
            jugador2.setText(" ");
        } else {
            Instruccion.setText("Quien gana la ronda");
            jugador1.setText(proxy.torneo.getCompetidores().get(proxy.torneo.getEstrategia().getParticipantes()[0]-1).getNombre());
            jugador2.setText(proxy.torneo.getCompetidores().get(proxy.torneo.getEstrategia().getParticipantes()[1]-1).getNombre());
        }
    }
}

package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelMod extends JPanel {
    Proxy proxy;
    JButton jugador1 = new JButton();
    JButton jugador2 =  new JButton();
    public PanelMod(Proxy proxy){
        this.proxy = proxy;
        jugador1.addActionListener(new ClickBotonesMod(1,proxy,this));
        jugador2.addActionListener(new ClickBotonesMod(2,proxy,this));

        jugador1.setFont(new Font("Comic Sans MS",Font.BOLD,24));
        jugador2.setFont(new Font("Comic Sans MS",Font.BOLD,24));
        this.add(jugador1);
        this.add(jugador2);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        jugador1.setText(proxy.torneo.getCompetidores().get(proxy.torneo.getEstrategia().getParticipantes()[0]-1).getNombre());
        jugador2.setText(proxy.torneo.getCompetidores().get(proxy.torneo.getEstrategia().getParticipantes()[1]-1).getNombre());
    }
}

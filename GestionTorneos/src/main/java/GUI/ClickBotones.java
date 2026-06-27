package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para implementar el Action Listener, conecta el presionar algún boton con
 * una función en el Proxy
 */
public class ClickBotones implements ActionListener {
    Proxy proxy;
    int boton;

    /**
     * El método que conecta el botón con el proxy
     * @param boton el botón específico que se presionó
     * @param proxy el proxy al cual se conecta el botón
     */
    public ClickBotones(int boton, Proxy proxy) {
        this.boton = boton;
        this.proxy = proxy;
    }

    /**
     * Cuando se presione un botón, se le asigna un valor el cual el proxy identifica
     * y ejecuta el código correspondiente al botón
     * @param e el evento a ser procesado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        proxy.actionClick(boton);
    }
}

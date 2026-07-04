package GUI;
import Logica.Torneo;

import javax.swing.*;

/**
 * Implementación del patrón "Proxy", para conectar la GUI con la lógica del gestor
 */
public class PH_Proxy {
    String nombreOrganizador = "";
    String nombre = "";
    String disciplina = "";
    int tipoTorneo = 0;
    Torneo TorActual;
    boolean[] ValidarTorneo = new boolean[5];
    PanelCreacion Panel;

    public PH_Proxy(PanelCreacion Panel) {
        this.Panel = Panel;
    }


    /* public void crearTorneo(String nombreOrganizador, String nombre, String disciplina, String tipoTorneo) {
        if (nombreOrganizador == null || nombreOrganizador.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El nombre del organizador no puede estar vacío.");
            return;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El nombre del torneo no puede estar vacío.");
            return;
        }

        if (disciplina == null || disciplina.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] La disciplina no puede estar vacía.");
            return;
        }

        if (tipoTorneo == null || tipoTorneo.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] Debe seleccionar un tipo de torneo.");
            return;
        }
    }
    */
    public void actionBotton(int Botton) {
        switch (Botton){
            case (1):
                nombreOrganizador = popupString("Nombre");
                ValidarTorneo[0] = true;
                break;

            case (2):
                nombre = popupString("Nombre");
                ValidarTorneo[1] = true;
                break;

            case (3):
                disciplina = popupString("Disciplina");
                ValidarTorneo[2] = true;
                break;

            case (4):
                tipoTorneo = popupInt("Tipo de torneo");
                ValidarTorneo[3] = true;
                break;

            case (5):
                boolean c = true;
                for(int i=0;i<4;i++){
                    if (ValidarTorneo[i]==false){
                        c = false;
                    }
                }
                if (c == true) {
                    CrearTorneo();
                } else {
                    JOptionPane.showMessageDialog(null, "Falta información", "Valor no permitido", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
        Panel.repaint();
    }

    public int popupInt(String Peticion) throws NumberFormatException{
        int error = 0;
        while(error==0) {
            String Ref = "0";
            try {
                error = 1;
                Ref = JOptionPane.showInputDialog(Peticion);
                Integer.valueOf(Ref);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Valor no permitido", JOptionPane.ERROR_MESSAGE);
                error = 0;
            }
            if (error == 1) {
                return Integer.valueOf(Ref);
            }
        }
        return 666666;
    }

    public String popupString(String Peticion) {
        boolean error = false;
        String Ref="error";
        while(error == false) {
            Ref = JOptionPane.showInputDialog(Peticion);
            try {
                if (Ref.isEmpty()|| Ref.isBlank()){
                    JOptionPane.showMessageDialog(null, "No puede ser nulo", "Valor no permitido", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    error = true;
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Valor no permitido", JOptionPane.ERROR_MESSAGE);
            }
        }
        return Ref;
    }

    public void CrearTorneo() {
        TorActual = new Torneo(nombreOrganizador,nombre,disciplina,String.valueOf(tipoTorneo));
        ValidarTorneo[4] = true;
    }

    public String getNombre(){
        return nombre;
    }
    public String getNombreOrganizador(){
        return nombreOrganizador;
    }
    public String getDisciplina() {
        return disciplina;
    }
    public int getTipoTorneo() {
        return tipoTorneo;
    }
    public String getTorListo() {
        if (ValidarTorneo[4]){
            return "Torneo Hecho";
        }
        else{
            return "";
        }
    }

}

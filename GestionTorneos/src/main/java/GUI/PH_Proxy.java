package GUI;
import Logica.*;
import javax.swing.*;

/**
 * Implementación del patrón "Proxy", para conectar la GUI con la lógica del gestor
 */
public class PH_Proxy {
    String nombreOrganizador = "";
    String nombre = "";
    String disciplina = "";
    int tipoTorneo = 0;
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
                break;

            case (2):
                nombre = popupString("Nombre");
                break;

            case (3):
                disciplina = popupString("Disciplina");
                break;

            case (4):
                tipoTorneo = popupInt("Tipo de torneo");
                break;

            case (5):
                CrearInterante();
                break;

            case (6):
                CrearTorneo();
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
        int error = 0;
        String Ref;
        while(error ==0) {
            Ref = JOptionPane.showInputDialog(Peticion);
            if (Ref != ""){
                return Ref;
            }
        }
        return "error";
    }

    public void CrearTorneo() {
    }
    public void CrearInterante() {
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
}

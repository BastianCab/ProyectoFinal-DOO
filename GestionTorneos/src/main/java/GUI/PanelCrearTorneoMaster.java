package GUI;

import Logica.Enfrentamiento;
import Logica.TipoParticipante;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel maestro y coordinador de los pasos para la creación de torneos.
 * Implementa el patrón de diseño Mediator al centralizar la navegación y comunicación
 * entre las distintas pantallas del formulario (Configuración, Inscripción y Calendario)
 * utilizando un mazo de cartas guiado por {@link CardLayout}.
 */
public class PanelCrearTorneoMaster extends JPanel {
    private CardLayout layoutFormulario;
    private Proxy proxy;

    // Referencias a la ventana principal para permitir el retorno al menú
    private JPanel panelContenedor;
    private CardLayout cardLayout;

    private PanelCrearTorneoConfiguracionBasica panelCrearTorneoConfiguracionBasica;
    private PanelCrearTorneoInscripcion panelCrearTorneoInscripcion;
    private PanelCrearTorneoFechas panelCrearTorneoFechas;

    /**
     * Constructor del panel maestro de creación de torneos.
     * Instancia las pantallas secundarias y las registra dentro del mazo de tarjetas.
     * @param proxyGlobal     El intermediario único del sistema para el manejo de la lógica.
     * @param panelContenedor El contenedor global de la aplicación (de la ventana principal).
     * @param cardLayout      El administrador de diseño global para alternar entre menús y asistentes.
     */
    public PanelCrearTorneoMaster(Proxy proxyGlobal, JPanel panelContenedor, CardLayout cardLayout) {
        this.proxy = proxyGlobal;
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;

        this.layoutFormulario = new CardLayout();
        this.setLayout(layoutFormulario);

        // Instanciación de los tres pasos del asistente
        this.panelCrearTorneoConfiguracionBasica = new PanelCrearTorneoConfiguracionBasica(this, proxy);
        this.panelCrearTorneoInscripcion = new PanelCrearTorneoInscripcion(this, proxy);
        this.panelCrearTorneoFechas = new PanelCrearTorneoFechas(this, proxy);

        // Registro de tarjetas con identificadores de texto únicos
        this.add(panelCrearTorneoConfiguracionBasica, "PASO_1");
        this.add(panelCrearTorneoInscripcion, "PASO_2");
        this.add(panelCrearTorneoFechas, "PASO_3");
    }

    /**
     * Hace retroceder al Paso 1 (Configuración Básica).
     * Como medida de seguridad y consistencia, limpia los campos del formulario de inscripciones
     * para evitar que queden datos residuales si el usuario cambia los parámetros esenciales.
     */
    public void volverAPaso1() {
        this.panelCrearTorneoInscripcion.limpiarCampos();
        layoutFormulario.show(this, "PASO_1");
    }

    /**
     * Hace avanzar al Paso 2 (Inscripción de Participantes).
     * Antes de mostrar la tarjeta, invoca la personalización dinámica de las etiquetas
     * y botones de acuerdo con la modalidad seleccionada en la configuración inicial.
     *  @param tipoParticipante La modalidad del torneo (ej. EQUIPO o INDIVIDUAL) que define los textos.
     */
    public void irAPaso2(TipoParticipante tipoParticipante) {
        panelCrearTorneoInscripcion.personalizarTextos(tipoParticipante);
        layoutFormulario.show(this, "PASO_2");
    }

    /**
     * Hace retroceder al Paso 2 (Inscripción de Participantes) desde la pantalla de fechas.
     * Permite al organizador modificar la lista de competidores si detecta un error de último minuto.
     */
    public void volverAPaso2() {
        layoutFormulario.show(this, "PASO_2");
    }

    /**
     * Hace avanzar el asistente al Paso 3 (Calendario de Enfrentamientos).
     * Solicita activamente al Proxy la generación matemática de los Enfrentamientos,
     * inyecta el resultado en la pantalla de calendario para su renderizado y despliega la tarjeta.
     */
    public void irAPaso3() {
        List<Enfrentamiento> partidosGenerados = proxy.generarEnfrentamientos();
        panelCrearTorneoFechas.cargarEnfrentamientos(partidosGenerados);
        layoutFormulario.show(this, "PASO_3");
    }

    /**
     * Finaliza por completo el flujo del asistente tras la creación exitosa del torneo.
     * Ejecuta una rutina de limpieza profunda en los formularios de los pasos 1 y 2,
     * reajusta el índice del al inicio y le ordena a la ventana global
     * que redireccione al usuario de vuelta a la pantalla del menú principal.
     */
    public void irApaso4(){
        this.panelCrearTorneoConfiguracionBasica.limpiarCampos();
        this.panelCrearTorneoInscripcion.limpiarCampos();

        // Resetea el estado del asistente para futuras creaciones
        layoutFormulario.show(this, "PASO_1");

        // Regresa visualmente a la pantalla del menú principal de la aplicación
        this.cardLayout.show(panelContenedor, "PANTALLA_MENU");
    }
}
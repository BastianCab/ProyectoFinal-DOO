# ProyectoFinal-DOO
# Informe de Proyecto: Sistema de Gestión de Torneos

## 1. Grupo y Autores
* **Número de Grupo:** 1
* **Integrantes:** 
    * Laura Malina Mardones Cuevas
    * Bastián Gonzalo Cabezas Oliva
    * Ricardo Ignacio Lagos Lizama

---
## 2. Enunciado
Este sistema está diseñado para facilitar la organizacíon de torneos deportivos o de juegos. Permitirá a un organizador definir las características del torneo, como el nombre, la disciplina (ej. fútbol, ajedrez, videojuegos), las fechas y un formato principal (como eliminatoria directa, eliminatorio doble, liga simple...). Se podrán inscribir participantes, ya sean jugadores individuales o equipos, almacenando información básica como nombres y datos de contacto. El sistema deberá ser capaz de generar un calendario de enfrentamientos o un bracket inicial basado en los inscritos y el formato. Durante el torneo, se registrarán los resultados de cada enfrentamiento, lo que actualizará automáticamente las posiciones, el avance en el bracket o las tablas de clasificación. Los usuarios podrán visualizar el estado actual del torneo, los próximos encuentros y las estadísticas generales.

---

## 3. Diagrama de Casos de Uso
El siguiente diagrama ilustra las funcionalidades del sistema y las interacciones con sus dos actores principales: el **Organizador** (administrador del evento) y el **Usuario** (espectador o competidor).

<img width="769" height="406" alt="gestionDeTorneos" src="https://github.com/user-attachments/assets/86f89171-cc36-4fed-9f48-9ad84fa50505" />
---

## 4. Capturas de Pantalla de la Interfaz
![Menu](https://github.com/BastianCab/ProyectoFinal-DOO/blob/main/1.png)
![Crear Torneo paso 1](https://github.com/BastianCab/ProyectoFinal-DOO/blob/main/2.png)
![Crear Torneo paso 2](https://github.com/BastianCab/ProyectoFinal-DOO/blob/main/3.png)
![Crear Torneo paso 3]()
![Espectar Torneo](https://github.com/BastianCab/ProyectoFinal-DOO/blob/main/5.png)
--
## 6. Patrones de Diseño Implementados

### A. Patrón Strategy (Comportamiento)
* **Justificación:** Se utilizó para encapsular los algoritmos que generan los cruces de partidos. En lugar de usar sentencias `switch` gigantes dentro de `Torneo`, el patrón inyecta la estrategia de cálculo en tiempo de ejecución. Esto permite agregar nuevos formatos en el futuro sin modificar el código base.
* **Clases:**
    * `Logica.CalcularJuego` (Interfaz / Strategy)
    * `Logica.CalcularLiga` (Estrategia Concreta)
    * `Logica.CalcularSimple` (Estrategia Concreta)
    * `Logica.CalcularDobles` (Estrategia Concreta)
    * `Logica.Torneo` (Contexto)

### B. Patrón Proxy (Estructural)
* **Justificación:** Se implementó para evitar el acoplamiento directo entre las ventanas (Swing) y la lógica. El Proxy actúa como intermediario: valida los inputs del usuario, traduce datos visuales y protege la instanciación de los objetos lógicos.
* **Clases:**
    * `GUI.Proxy` (Intermediario)
    * `GUI.PanelPrincipal` (Cliente)
    * `GUI.ClickBotonesPanelPrincipal` (Cliente)
    * `GUI.PanelCrearTorneoMaster` (Cliente)
    * `GUI.PanelCrearTorneoConfiguracionBasica` (Cliente)
    * `GUI.PanelCrearTorneoInscripcion` (Cliente)
    * `GUI.PanelCrearTorneoFechas` (Cliente)
    * `Logica.Torneo` (Sujeto real)

---
## 7. Decisiones Importantes y Mejoras
1. **Validación Moderna de Fechas:** Se reemplazó el uso de `Date` por `java.time.LocalDateTime` para programar el calendario, permitiendo un control estricto que impide asignar partidos en fechas pasadas.
2. **Participantes "Fantasma" (Placeholders):** Para renderizar las llaves de los torneos antes de conocer a los ganadores, inyectamos objetos genéricos con el nombre "Por definir", permitiendo armar todo el calendario desde el inicio.
3. **Flujo "Wizard":** En vez de múltiples ventanas, diseñamos un flujo de pasos secuenciales usando `CardLayout` para guiar al usuario sin perder el progreso.

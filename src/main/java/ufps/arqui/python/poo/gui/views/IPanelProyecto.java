package ufps.arqui.python.poo.gui.views;

import javax.swing.*;
import java.util.Observer;

/**
 * Interfaz del proyecto para visualizar las clases del proyecto, así como sus relaciones.
 * 
 * En la interfaz tambien tendrán los botones para crear una nueva clase
 * y crear una relación de herencia.
 * @author Omar Ramón Montes
 */
public interface IPanelProyecto  extends Observer {

    JPanel getPanel();
}

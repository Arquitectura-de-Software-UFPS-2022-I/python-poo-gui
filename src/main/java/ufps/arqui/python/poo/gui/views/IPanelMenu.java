package ufps.arqui.python.poo.gui.views;

import java.util.Observer;
import javax.swing.JPanel;

/**
 * Interfaz Panel para visaulizar las opciones del proyecto.
 * 
 * En el menú se podrán realizar las diferentes opciones del sistema,
 * como por ejemplo guardar el proyecto, crear uno nuevo, ver el manual, etc.
 * @author Omar Ramón Montes
 */
public interface IPanelMenu extends Observer {
    
    JPanel getPanel();
    
}

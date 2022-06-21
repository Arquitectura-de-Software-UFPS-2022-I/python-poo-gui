package ufps.arqui.python.poo.gui.views;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;

/**
 * Interfaz todos los paneles del proyecto.
 *
 * @author Omar Ramón Montes
 */
public interface IPanelView {
    JPanel getPanel();

    /**
     * Debe inicializar los componentes que tendrá en panel.
     */
    void inicializarContenido();
    default void mostrarError(Exceptions e){
           JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
    };
}

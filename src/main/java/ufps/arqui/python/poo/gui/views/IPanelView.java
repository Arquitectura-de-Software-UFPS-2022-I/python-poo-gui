package ufps.arqui.python.poo.gui.views;

import javax.swing.JPanel;

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

}

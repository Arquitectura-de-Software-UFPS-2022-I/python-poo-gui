package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagLayout;
import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.views.IPanelMundo;

import javax.swing.JPanel;
import java.util.Observable;

/**
 * Panel para visualizar el mundo el proyecto, es decir todas las intancias.
 *
 * En el panel mundo se visualizarán las instancias de las clases creadas,
 * con sus respectivos nombres y datos adicionales
 * @author Omar Ramón Montes
 */
public class PanelMundo implements IPanelMundo {

    private final IMundoController controller;
    private final JPanel panel;
    
    // elementos de GUI

    public PanelMundo(IMundoController controller) {
        this.controller = controller;
        this.panel = new JPanel(new GridBagLayout());
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

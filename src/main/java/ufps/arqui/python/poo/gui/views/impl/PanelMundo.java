package ufps.arqui.python.poo.gui.views.impl;

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

    public PanelMundo(IMundoController controller) {
        this.controller = controller;
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

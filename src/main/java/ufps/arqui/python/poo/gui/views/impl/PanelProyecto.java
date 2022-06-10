package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;

import javax.swing.JPanel;
import java.util.Observable;

/**
 * PAnel del proyecto para visualizar las clases del proyecto, así como sus relaciones.
 *
 * En la interfaz tambien tendrán los botones para crear una nueva clase
 * y crear una relación de herencia.
 * @author Omar Ramón Montes
 */
public class PanelProyecto implements IPanelProyecto {

    private final IProyectoController controller;

    public PanelProyecto(IProyectoController controller) {
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

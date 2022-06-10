package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagLayout;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

import javax.swing.*;
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
    private final JPanel panel;
    
    // elementos de GUI
    

    public PanelProyecto(IProyectoController controller) {
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

package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagLayout;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import ufps.arqui.python.poo.gui.utility.ViewTool;

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

    public PanelProyecto(IProyectoController controller) {
        this.controller = controller;
        this.panel = new JPanel(new GridBagLayout());

        this.inicializarContenido();
    }
    
    @Override
    public void inicializarContenido() {
        PanelClass pc1 = new PanelClass("SoloTest 1", this.panel);
        PanelClass pc2 = new PanelClass("SoloTest 2", this.panel);
        
        ViewTool.insert(this.panel, pc1.getPanel(), 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, null, 100, 40);
        ViewTool.insert(this.panel, pc2.getPanel(), 1, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, null, 100, 40);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }
}

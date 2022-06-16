package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelFichero;
import ufps.arqui.python.poo.gui.views.IPanelFicheroProyecto;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;

/**
 * Implementación de interfaz que contiene los elementos del proyecto.
 *
 * Los elementos del proyecto está comformado por la barra lateral y el contenedor de clases.
 * @author Sachikia
 */
public class PanelFicheroProyecto implements IPanelFicheroProyecto{
    
    private final JPanel panel;

    private final IPanelFichero panelFichero;
    private final IPanelProyecto panelProyecto;

    public PanelFicheroProyecto(IPanelFichero panelFichero, IPanelProyecto panelProyecto) throws Exception {
        this.panelFichero = panelFichero;
        this.panelProyecto = panelProyecto;
        this.panel = new JPanel(new GridBagLayout());
        
        this.inicializarContenido();
    }
    
    @Override
    public void inicializarContenido() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        splitPane.add(this.panelFichero.getPanel());
        splitPane.add(this.panelProyecto.getPanel());
        splitPane.setResizeWeight(0.15);

        ConfGrid config = new ConfGrid(panel, splitPane);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.BOTH);

        ViewTool.insert(config);
//        ViewTool.insert(this.panel, splitPane, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, null, 0, 0);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}

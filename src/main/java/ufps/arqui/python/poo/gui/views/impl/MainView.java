package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.views.IMainView;
import ufps.arqui.python.poo.gui.views.IPanelFicheroProyecto;
import ufps.arqui.python.poo.gui.views.IPanelMenu;
import ufps.arqui.python.poo.gui.views.IPanelMundoTerminal;

/**
 * Vista principal, donde se concentra la inicialización de cada parte 
 * del proyecto.
 *  
 * @author Omar Ramón Montes
 */
public class MainView implements IMainView{
    
    private final JFrame frame;
    private final IPanelMenu panelMenu;
    private final IPanelFicheroProyecto panelFicheroProyecto;
    private final IPanelMundoTerminal panelMundoTerminal;

    public MainView(String title, IPanelMenu panelMenu, IPanelFicheroProyecto panelFicheroProyecto, 
            IPanelMundoTerminal panelMundoTerminal) throws Exception {
        this.frame = new JFrame(title);

        this.panelMenu = panelMenu;
        this.panelFicheroProyecto = panelFicheroProyecto;
        this.panelMundoTerminal = panelMundoTerminal;
        
        this.init();
    }

    /**
     * Inicializar la ventana principal del sistema.
     * @throws java.lang.Exception
     */
    public void init() throws Exception {
        this.frame.setPreferredSize(new Dimension(1100, 700));
        this.frame.pack();
        
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(this.panelFicheroProyecto.getPanel());
        splitPane.add(this.panelMundoTerminal.getPanel());
        splitPane.setResizeWeight(0.1);
        
        ViewTool.insert(container, this.panelMenu.getPanel(),   0, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(container, splitPane,                   0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, null, 0, 0);
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    @Override
    public IPanelMenu getPanelMenu() {
        return panelMenu;
    }

    @Override
    public IPanelFicheroProyecto getPanelFicheroProyecto() {
        return panelFicheroProyecto;
    }

    @Override
    public IPanelMundoTerminal getPanelMundoTerminal() {
        return panelMundoTerminal;
    }
}

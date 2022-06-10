package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.views.IMainView;
import ufps.arqui.python.poo.gui.views.IPanelMenu;
import ufps.arqui.python.poo.gui.views.IPanelMundo;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

/**
 * Vista principal, donde se concentra la inicialización de cada parte 
 * del proyecto.
 *  
 * @author Omar Ramón Montes
 */
public class MainView implements IMainView{
    
    private final JFrame frame;
    private final IPanelMenu panelMenu;
    private final IPanelMundo panelMundo;
    private final IPanelProyecto panelProyecto;
    private final IPanelTerminal panelTerminal;

    public MainView(String title, IPanelMenu panelMenu, IPanelMundo panelMundo,
                    IPanelProyecto panelProyecto, IPanelTerminal panelTerminal) throws HeadlessException {
        this.frame = new JFrame(title);

        this.panelMenu = panelMenu;
        this.panelMundo = panelMundo;
        this.panelProyecto = panelProyecto;
        this.panelTerminal = panelTerminal;

    }
    
    /**
     * Inicializar la ventana principal del sistema.
     */
    @Override
    public void init() {
        this.frame.setPreferredSize(new Dimension(800, 600));
        this.frame.pack();

        if (this.panelMenu != null) {
            this.frame.add(this.panelMenu.getPanel());
        }
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    @Override
    public IPanelMenu getPanelMenu() {
        return panelMenu;
    }

    @Override
    public IPanelProyecto getPanelProyecto() {
        return panelProyecto;
    }

    @Override
    public IPanelTerminal getPanelTerminal() {
        return panelTerminal;
    }

    @Override
    public IPanelMundo getPanelMundo() {
        return panelMundo;
    }
    
}

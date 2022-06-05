package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.controllers.impl.MenuController;
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
 * 
 * @author Omar Ramón Montes
 */
public class MainView extends JFrame implements IMainView{
    
    IPanelMenu panelMenu;
    IPanelMundo panelMundo;
    IPanelProyecto panelProyecto;
    IPanelTerminal panelTerminal;
    
    private final Proyecto modelo;

    public MainView(String title) throws HeadlessException {
        super(title);
        
        this.modelo = new Proyecto();
        
        this.initMenu();
        this.init();
    }
    
    /**
     * Inicialización de patron MVC del panel de menú
     */
    private void initMenu() {    
        IMenuController menuController = new MenuController(this.modelo);
        this.panelMenu = new PanelMenu(menuController);
        
        this.modelo.addObserver(this.panelMenu);
        
        this.add(this.panelMenu.getPanel());
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
    
    /**
     * Inicializar la ventana principal del sistema
     */
    private void init() {        
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}

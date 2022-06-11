package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagLayout;
import java.util.Observable;
import javax.swing.JPanel;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.views.IPanelMundo;
import ufps.arqui.python.poo.gui.views.IPanelMundoTerminal;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

/**
 *
 * @author Sachikia
 */
public class PanelMundoTerminal implements IPanelMundoTerminal{
    private final IPanelMundo panelMundo;
    private final IPanelTerminal panelTerminal;
    private final JPanel panel;
    
    // elementos de GUI

    public PanelMundoTerminal(IPanelMundo panelMundo, IPanelTerminal panelTerminal) {
        this.panel = new JPanel(new GridBagLayout());
        
        this.panelMundo = panelMundo;
        this.panelTerminal = panelTerminal;
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}

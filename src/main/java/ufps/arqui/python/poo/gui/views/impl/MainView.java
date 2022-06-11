package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import javax.swing.*;

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
public class MainView extends JFrame implements IMainView {

    private final IPanelMenu panelMenu;
    private final IPanelMundo panelMundo;
    private final IPanelProyecto panelProyecto;
    private final IPanelTerminal panelTerminal;
    private JSplitPane splitPanePro;
    private JSplitPane splitPaneTer;

    public MainView(String title, IPanelMenu panelMenu, IPanelMundo panelMundo,
                    IPanelProyecto panelProyecto, IPanelTerminal panelTerminal) throws HeadlessException {
        super(title);

        this.panelMenu = panelMenu;
        this.panelMundo = panelMundo;
        this.panelProyecto = panelProyecto;
        this.panelTerminal = panelTerminal;

    }

    @Override
    public void inicializarContenido() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));

        this.splitPaneTer = new JSplitPane(SwingConstants.VERTICAL, this.panelMundo.getPanel(), this.panelTerminal.getPanel());
        this.splitPaneTer.setDividerLocation(400);
        this.panelMundo.getPanel().setMinimumSize(new Dimension(150, 100));
        this.panelTerminal.getPanel().setMinimumSize(new Dimension(150, 100));

        this.splitPanePro = new JSplitPane(SwingConstants.HORIZONTAL, this.panelProyecto.getPanel(), this.splitPaneTer);
        this.splitPanePro.setDividerLocation(300);
        this.panelProyecto.getPanel().setMinimumSize(new Dimension(800, 100));
        this.splitPaneTer.setMinimumSize(new Dimension(800, 100));

        this.add(this.panelMenu.getPanel(), BorderLayout.NORTH);
        this.add(this.splitPanePro, BorderLayout.CENTER);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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

package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.PythonPooGui;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IMainView;
import ufps.arqui.python.poo.gui.views.IPanelFicheroProyecto;
import ufps.arqui.python.poo.gui.views.IPanelMenu;
import ufps.arqui.python.poo.gui.views.IPanelMundoTerminal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

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
            IPanelMundoTerminal panelMundoTerminal) {
        this.frame = new JFrame(title);
        try {
            InputStream logo = PythonPooGui.class.getClassLoader().getResourceAsStream("icon.png");
            this.frame.setIconImage((new ImageIcon(ImageIO.read(logo)).getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.panelMenu = panelMenu;
        this.panelFicheroProyecto = panelFicheroProyecto;
        this.panelMundoTerminal = panelMundoTerminal;
        
        this.init();
    }

    /**
     * Inicializar la ventana principal del sistema.
     */
    public void init() {
        this.frame.setPreferredSize(new Dimension(1100, 700));
        this.frame.pack();
        
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(this.panelFicheroProyecto.getPanel());
        splitPane.add(this.panelMundoTerminal.getPanel());
        splitPane.setResizeWeight(0.08);

        ConfGrid config = new ConfGrid(container, this.panelMenu.getPanel());
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.LINE_START);
        ViewTool.insert(config);

        config = new ConfGrid(container, splitPane);
        config.setGridy(1);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.BOTH);
        ViewTool.insert(config);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
